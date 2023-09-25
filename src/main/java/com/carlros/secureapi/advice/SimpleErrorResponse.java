package com.carlros.secureapi.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleErrorResponse {
    private String[] messages;
    public SimpleErrorResponse(String s){
        this.messages = new String[]{s};
    }
}
