package com.propertyassist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.genai.types.Content;
import com.google.genai.types.Part;

@Service
public class ChatService {

    private final GeminiService geminiService;
    private final ConversationStore conversationStore;

    public ChatService(
            GeminiService geminiService,
            ConversationStore conversationStore) {

        this.geminiService = geminiService;
        this.conversationStore = conversationStore;
    }

    public String chat(String conversationId, String message) {

        List<Content> history =
                conversationStore.getHistory(conversationId);

        Content userMessage = Content.fromParts(
                Part.fromText(message)
        );

        history.add(userMessage);

        String reply = geminiService.generateResponse(history);

        Content modelMessage = Content.fromParts(
                Part.fromText(reply)
        );

        history.add(modelMessage);

        return reply;
    }
}