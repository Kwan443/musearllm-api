package com.musearllm.api.logging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class VersionLogger implements CommandLineRunner {

    @Value("${version.build}")
    private String version;

    @Override
    public void run(String... args) {
        String content = "Muse AR LLM API Version: " + version;
        int width = content.length();
        String border = "═".repeat(width + 2); // +2 for padding

        System.out.println("╔" + border + "╗");
        System.out.println("║ " + content + " ║");
        System.out.println("╚" + border + "╝");
    }
}