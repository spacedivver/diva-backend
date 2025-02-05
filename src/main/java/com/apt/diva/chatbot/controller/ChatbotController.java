package com.apt.diva.chatbot.controller;

import com.apt.diva.chatbot.domain.request.ChatRequest;
import com.apt.diva.chatbot.domain.response.ChatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/chat")
public class ChatbotController {

    private static final Logger logger = LoggerFactory.getLogger(ChatbotController.class);

    @Value("${ai.server.url}")
    private String aiServerUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    private final RestTemplate restTemplate;

    public ChatbotController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<?> sendMessageToGPT(@RequestBody Map<String, String> requestBody) {

        logger.info("Received message: {}", requestBody.get("message"));

        String userMessage = requestBody.get("message");
        if (userMessage == null || userMessage.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("메시지가 비어있습니다.");
        }

        // AI 서버 호출
        String fullUrl = aiServerUrl + "/chatbot";
        ChatRequest chatRequest = new ChatRequest(userMessage);
        ChatResponse chatResponse = restTemplate.postForObject(fullUrl, chatRequest, ChatResponse.class);

        if (chatResponse == null || chatResponse.getBotMessage() == null) {
            throw new RuntimeException("챗봇 추론 서버 응답이 null입니다");
        }

        // AI 서버로부터 받은 res 추출
        String res = chatResponse.getBotMessage();
        logger.info("AI Server Response(res): {}", res);

        // OpenAI ChatCompletion 요청에 사용할 JSON Body 생성
        Map<String, Object> requestMap = new HashMap<>();
        // 모델명은 상황에 맞춰 실제 사용 가능 모델명으로 변경하세요.
        requestMap.put("model", "gpt-3.5-turbo");

        // messages 배열에 role, content 설정
        // 1) AI 서버의 res를 system 역할로 제공하여 ChatGPT가 이를 기반으로 답변 생성
        // 2) 사용자의 실제 질문은 user 역할로 제공
        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", res));
        messages.add(Map.of("role", "user", "content", userMessage));
        requestMap.put("messages", messages);

        // 옵션 설정 (예: max_tokens, temperature 등)
        requestMap.put("max_tokens", 500);
        requestMap.put("temperature", 0.7);

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        // RestTemplate로 OpenAI API 호출
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestMap, headers);

        try {
            ResponseEntity<Map> response = new RestTemplate().exchange(
                    OPENAI_API_URL,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();

                // OpenAI 응답에서 content 추출
                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map<String, Object> choice = choices.get(0);
                    Map<String, Object> messageObj = (Map<String, Object>) choice.get("message");
                    String content = (String) messageObj.get("content");

                    // 결과를 JSON 형태로 반환
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
            logger.error("OpenAI API 호출 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류 발생: " + e.getMessage());
        }
    }
}
