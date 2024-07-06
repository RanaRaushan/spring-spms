package com.dark.spring.spms.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RestController
public class TestController {

    @GetMapping("/park")
    public String parking() {
        // TODO: Temp just a HACK added to load html content to test websocket.
        System.out.println("In MyController!!! to return html content");
        URL fileResource = this.getClass().getResource("/templates/parking.html");
        return Objects.nonNull(fileResource) ? readFile(fileResource) : "Something went wrong!!";
    }


    public String readFile(URL fileResource) {
        try (InputStreamReader streamReader = new InputStreamReader(fileResource.openStream(), StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(streamReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file";
        }
    }
}