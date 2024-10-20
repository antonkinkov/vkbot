package com.example.vkbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response {
    private Long peer_id = 0L;
    private String text = "";
    private int random_id = 0;

    public Response(Long peerId, String s, int randomId) {

    }
}
