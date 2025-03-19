package com.github.subhankar.service;

import com.github.subhankar.util.ApplicationContextHelper;
import com.github.subhankar.util.VertexAIUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Service
public class VertexAIService {

    private VertexAIUtil vertexAIUtil;

    public String extractRequest(String content,String fileType) throws IOException, URISyntaxException, InterruptedException {
        vertexAIUtil = ApplicationContextHelper.getVertexAIUtil();
        return vertexAIUtil.extractRequest(content, fileType);
    }

    public String generateKarateTests(Map<String,String> requests, String fileType) throws URISyntaxException, IOException, InterruptedException {
        vertexAIUtil = ApplicationContextHelper.getVertexAIUtil();
        return vertexAIUtil.generateKarateTests(requests,fileType);
    }
}
