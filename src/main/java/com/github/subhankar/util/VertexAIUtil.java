package com.github.subhankar.util;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@Component
public class VertexAIUtil {

    @Value("${spring.gemini.api-key}")
    private String apiKey;

    @Value("${spring.gemini.url}")
    private String URL;


    private String getResponseFromVertex(String prompt,String fileType) throws URISyntaxException, IOException, InterruptedException {
        String responseText = "";

        String uri = URL+apiKey;

        HttpClient httpClient = HttpClient.newHttpClient();

        String requestBody = """
                {
                       "contents": {
                         "role": "user",
                         "parts": [
                           {
                             "text": "${prompt}"
                           }
                         ]
                        }
                  }
                """.replace("${prompt}", prompt);

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());

        responseText = response.body();
        Gson gson = new Gson();
        JsonObject rootObject = gson.fromJson(responseText, JsonObject.class);
        String text = rootObject.getAsJsonArray("candidates")
                .get(0).getAsJsonObject().getAsJsonObject("content")
                .getAsJsonArray("parts")
                .get(0).getAsJsonObject()
                .get("text").getAsString();
        text =  text.replace("```","");
        text = text.substring(text.indexOf(fileType)+fileType.length());

        return text;
    }

    public String extractRequest(String fileContent, String fileType) throws IOException, URISyntaxException, InterruptedException {

        String template = "Please extract the ${fileType} request body  from the following Json file ${fileContent}." +
                " !Alert: Strictly chunkout only request body in ${fileType} format so that it can be used as a request for api call }";
        String prompt = template.replace("${fileType}", fileType).replace("${fileContent}", fileContent.replace("\"","'"));

        String response =  getResponseFromVertex(prompt,fileType);
        return  response;
    }

    public String generateKarateTests(Map<String,String> request,String fileType) throws URISyntaxException, IOException, InterruptedException {


        String template = "You are an genius in karate BDD framework ," +
                " given the baseURL ${base_url}, ${endpoint}, please read ${fileType} request body from this path ${request_body_path}" +
                " when the ${method} request is hit, the response code should be ${code} " +
                "based on this above criteria write one working karate file in ${fileType} syntax" +
                "!!Alert: Strictly write the runnable karate file only in ${fileType} format." +
                "No need to add any extra fields";



            String requestBodyPath = request.get("request_body_path");
            String method = request.get("method");
            String code = request.get("code");
            String baseUrl = request.get("base_url");
            String endpoint = request.get("endpoint");

            String prompt = template.replace("${base_url}",baseUrl).replace("${endpoint}",endpoint).replace("${method}",method).
                    replace("${request_body_path}",requestBodyPath).replace("${code}",code).replace("${fileType}",fileType);

            return getResponseFromVertex(prompt,fileType);
    }
}
