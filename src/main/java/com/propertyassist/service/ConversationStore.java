package com.propertyassist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.google.genai.types.Content;

@Component
public class ConversationStore {

    private final ConcurrentHashMap<String, List<Content>> conversations =
            new ConcurrentHashMap<>();

    public List<Content> getHistory(String conversationId) {

        return conversations.computeIfAbsent(
                conversationId,
                key -> new ArrayList<>()
        );
    }

    public void clearHistory(String conversationId) {
        conversations.remove(conversationId);
    }
}