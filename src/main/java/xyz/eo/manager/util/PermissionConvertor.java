package xyz.eo.manager.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PermissionConvertor implements AttributeConverter<Permissions, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Permissions attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        }
        catch(JsonProcessingException e){
            throw new RuntimeException("Failed to convert to json string"); // Handle the exception as needed, e.g., log it or return a default value
        }
    }

    @Override
    public Permissions convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Permissions.class);
        }
        catch(JsonProcessingException e){
            throw new RuntimeException("Failed to convert to json string"); // Handle the exception as needed, e.g., log it or return a default value
        }
    }
    
    
}
