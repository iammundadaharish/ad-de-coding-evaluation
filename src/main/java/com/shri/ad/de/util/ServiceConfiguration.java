package com.shri.ad.de.util;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shri.ad.de.vo.CollectorServiceConfiguration;


@JsonDeserialize(as = CollectorServiceConfiguration.class)
public interface ServiceConfiguration extends Serializable {

    

    default JsonNode asJsonNode() throws IOException {
        ObjectMapper om = new ObjectMapper();
        String jsonString = om.writeValueAsString(this);
        return om.readTree(jsonString);
    }

    static <T extends ServiceConfiguration> T fromString(String value, Class<T> cls) {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.readValue(value, cls);
        } catch (IOException e) {
            throw new IllegalArgumentException("Given configuration is not a valid " + cls + "configuration.");
        }
    }
}