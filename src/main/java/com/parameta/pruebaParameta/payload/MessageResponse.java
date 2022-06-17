package com.parameta.pruebaParameta.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageResponse {
    private String message;

    public MessageResponse(String message){
        this.message = message;
    }
}
