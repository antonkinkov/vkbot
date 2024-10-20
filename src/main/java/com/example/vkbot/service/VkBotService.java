package com.example.vkbot.service;
import com.example.vkbot.config.VkBotConfig;
import com.example.vkbot.model.Response;
import com.example.vkbot.model.Update;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class VkBotService {

    @Autowired
    private VkBotConfig vkBotConfig;

    public Response handleUpdate(Update update) {
        String message = update.getObject().getText();
        Long peerId = update.getObject().getPeer_id();

        if (message != null && peerId != null) {
            return new Response(
                    peerId,
                    "Цитата: " + message,
                    update.getObject().getRandom_id()
            );
        }
        return new Response(
                peerId,
                "Ошибка обработки сообщения",
                0
        );
    }

    public void sendResponse(Response response) {
        try {
            URL url = new URL("https://api.vk.com/method/messages.send");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String params = "access_token=" + URLEncoder.encode(vkBotConfig.getToken(), StandardCharsets.UTF_8) +
                    "&peer_id=" + response.getPeer_id() +
                    "&message=" + URLEncoder.encode(response.getText(), StandardCharsets.UTF_8) +
                    "&random_id=" + response.getRandom_id();

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(params.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            System.out.println("Response: " + content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}