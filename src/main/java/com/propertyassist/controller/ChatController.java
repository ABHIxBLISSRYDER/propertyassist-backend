package com.propertyassist.controller;

import com.propertyassist.dto.ChatRequest;
import com.propertyassist.dto.ChatResponse;
import com.propertyassist.service.ChatService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://propertyassist-ai-chatbot.vercel.app"
})
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<ChatResponse> chat(
            @Valid @RequestBody ChatRequest request) {

        String reply = chatService.chat(
                request.getConversationId(),
                request.getMessage()
        );

        return ResponseEntity.ok(
                new ChatResponse(reply)
        );
    }
}