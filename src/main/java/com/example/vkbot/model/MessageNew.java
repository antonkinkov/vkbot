package com.example.vkbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageNew {
    private String text = "";
    private Long from_id = 0L;
    private Long peer_id = 0L;
    private int random_id = 0;
}
