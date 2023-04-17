package fr.dopolytech.polyshop.catalog.services;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class QueueService {
    private final ObjectMapper mapper = new ObjectMapper();

    public String stringify(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    public <T> T parse(String json, Class<T> type) throws JsonProcessingException {
        return mapper.readValue(json, type);
    }
}
