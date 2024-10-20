package com.example.vkbot.controller;
import com.example.vkbot.model.Event;
import com.example.vkbot.model.Response;
import com.example.vkbot.service.VkBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VkBotController {

    @Autowired
    private VkBotService vkBotService;

    @PostMapping("/")
    public Response handleUpdate(@RequestBody Event event) {
        Response response = vkBotService.handleUpdate(event.getObject());
        vkBotService.sendResponse(response);
        return response;
    }
}