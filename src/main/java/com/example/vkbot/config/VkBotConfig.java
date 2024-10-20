package com.example.vkbot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
@EnableConfigurationProperties(VkBotConfig.class)
@ConfigurationProperties("vk.bot")
public class VkBotConfig {
    private String token = "";
    private Long groupId = 0L;
}