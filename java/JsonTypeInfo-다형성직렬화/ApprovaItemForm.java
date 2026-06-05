package com.inje.coneportal.service.approve.form;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inje.cone.base.command.BaseCommand;

public class ApprovaItemForm {
    
    private static ObjectMapper mapper = new ObjectMapper();
    public static String get(BaseCommand command){

        String rs = null;
        Map<String, Object> map = Map.of(
            "user", command.getActionUserCommand(),
            "request", command
        );

        try {
            rs = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            rs = "";
        }

        return rs;
    }
}
