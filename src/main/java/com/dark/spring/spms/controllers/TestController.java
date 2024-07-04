package com.dark.spring.spms.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@RestController
public class TestController {

    @GetMapping("/park")
    public String parking() {
        // TODO: Temp just a HACK added to load html content to test websocket.
        System.out.println("In MyController!!! to return html content");
        String filePath = "src/main/resources/templates/parking.html";
        return readFile(filePath);
    }


    private String readFile(String path) {
        File file = new File(path);
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}