package com.shri.ad.de.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shri.ad.de.exception.BadRequestException;

public abstract class BaseServiceConfiguration implements ServiceConfiguration {

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceConfiguration that = (ServiceConfiguration) o;
        try {
            return this.asJsonNode().equals(that.asJsonNode());
        } catch (Exception e) {
            return false;
        }
    }

    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new BadRequestException(e);
        }
    }

}