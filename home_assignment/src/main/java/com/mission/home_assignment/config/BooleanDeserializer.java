package com.mission.home_assignment.config;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.NumberUtils;

import java.io.IOException;

public class BooleanDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String text = jsonParser.getText();
        if (text.equalsIgnoreCase("true") || text.equalsIgnoreCase("false")) {
            return Boolean.valueOf(text);
        }
        return null;

    }
}
