package com.github.subhankar.util;

import com.github.subhankar.service.VertexAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Component
public class KarateGenUtil extends FileUtility {

    @Autowired
    private VertexAIService vertexAIService;

    @Value("${testgenie.directory.features.path}")
    private  String featureFilePath;


    public void getKarateTests(List<AbstractMap.SimpleEntry<String,String>> req) throws IOException {

        String baseURL = "http://localhost:8080";
        req.forEach(entry -> {
            String filePath = entry.getKey();
            String requestBodyPath = entry.getValue();
            Map<String, String> request = new HashMap<>();
            request.put("base_url",baseURL);
            request.put("endpoint",filePath.contains("get")?"/api/student/get":"/api/student/add");
            request.put("method",filePath.contains("get")?"GET":"POST");
            request.put("request_body_path",requestBodyPath);
            request.put("code",filePath.contains("get")?"200":"201");
            String karateFile = "";
            try {
                karateFile =vertexAIService.generateKarateTests(request,"gherkin");
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                saveFile(featureFilePath+filePath+".feature",karateFile);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });

    }

}
