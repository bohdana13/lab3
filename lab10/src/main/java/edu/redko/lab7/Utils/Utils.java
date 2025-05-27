package edu.redko.lab7.Utils;

/*
  @author User
  @project lab7
  @class Utils
  @version 1.0.0
  @since 27.05.2025 - 19.10
*/


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    public static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}