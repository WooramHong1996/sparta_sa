package com.example.sparta_project.dto;

import lombok.Getter;

@Getter
public class SendMessageDto{
    private String message;
    public void sendMessage(String message)
    {
        this.message = message;
    }
}
