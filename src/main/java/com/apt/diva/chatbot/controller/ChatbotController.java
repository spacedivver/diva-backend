package com.apt.diva.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/chat")
public class ChatbotController {

    private static final Logger logger = LoggerFactory.getLogger(ChatbotController.class);


    @Value("${openai.api.key}")
    private String apiKey;

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    @PostMapping
    public ResponseEntity<?> sendMessageToGPT(@RequestBody Map<String, String> requestBody) {

        logger.info("Received message: {}", requestBody.get("message"));

        String userMessage = requestBody.get("message");
        if (userMessage == null || userMessage.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("메시지가 비어있습니다.");
        }

        // OpenAI ChatCompletion 요청에 사용할 JSON Body 생성
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("model", "gpt-3.5-turbo");

        //messages 배열에 role("user"), content("사용자 메시지")
        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "user", "content", userMessage));
        requestMap.put("messages", messages);

        // 선택 파라미터
        requestMap.put("max_tokens", 150);
        requestMap.put("temperature", 0.7);

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        // RestTemplate 호출
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestMap, headers);

        try {
            // ChatGPT API 호출
            ResponseEntity<Map> response = restTemplate.exchange(
                    OPENAI_API_URL,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();

                // 응답 구조에서 answer 추출
                // {"id":"...","object":"...","choices":[{"message":{"role":"assistant","content":"..."},"finish_reason":"stop","index":0}],"usage":{...}}
                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map<String, Object> choice = choices.get(0);
                    Map<String, Object> messageObj = (Map<String, Object>) choice.get("message");
                    String content = (String) messageObj.get("content");

                    // 결과를 JSON으로 반환
                    Map<String, String> result = new HashMap<>();
                    result.put("botMessage", content.trim());
                    return ResponseEntity.ok(result);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("GPT 응답에 choices가 없습니다.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("OpenAI API 호출 실패: " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류 발생: " + e.getMessage());
        }
    }
}
