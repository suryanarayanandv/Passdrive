package com.github.passdrive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.google.gson.*;

public class App {
    // Chrome extention native app host
    // entry point
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // Read message from Chrome
            String input = reader.readLine();

            // Parse JSON message
            JsonElement jsonElement = JsonParser.parseString(input);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // data: {domain: '', subdomain: ''}
            String messageText = jsonObject.get("data").getAsString();
            String responseText = "Received message: " + messageText;

            // Prepare response
            JsonObject response = new JsonObject();
            response.addProperty("data", responseText);

            // Send response to Chrome
            System.out.println(response.toString());
        }
    }
}

// TODO: test + exception handle