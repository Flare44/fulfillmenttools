package de;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Utils {
    public static void printAPIToken() throws IOException {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + System.getenv("AUTH_KEY");
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String body = """
                {
                    "email": "%s",
                    "password": "%s",
                    "returnSecureToken": true
                }
                """.formatted(System.getenv("LOGIN"), System.getenv("PASSWORD"));

        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(body.getBytes(StandardCharsets.UTF_8));
        }

        int httpResponseCode = connection.getResponseCode();
        System.out.println(httpResponseCode);

        try (InputStream inputStream = (httpResponseCode == 200 || httpResponseCode == 201) ? connection.getInputStream() : connection.getErrorStream()) {
            String response = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(response);
        }
    }

    public static void sendHttpRequest(String url, String body, String httpVerb) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(httpVerb);
        connection.setRequestProperty("Authorization", "Bearer " + System.getenv("API_KEY"));
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        if(!httpVerb.equals("GET")) {
            connection.setDoOutput(true);

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(body.getBytes(StandardCharsets.UTF_8));
            }
        }

        int httpResponseCode = connection.getResponseCode();
        System.out.println(httpResponseCode);

        try (InputStream inputStream = (httpResponseCode == 200 || httpResponseCode == 201) ? connection.getInputStream() : connection.getErrorStream()) {
            String response = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(response);
        }
    }
}
