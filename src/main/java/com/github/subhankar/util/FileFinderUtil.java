package com.github.subhankar.util;

import com.github.subhankar.service.VertexAIService;
import com.google.protobuf.MapEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class FileFinderUtil extends FileUtility {

    @Autowired
    private VertexAIService vertexAIService;

    @Value("${testgenie.directory.request.path}")
    private  String filePath;

    @Value("${testgenie.directory.response.filetype}")
    private  String fileType;

    @Value("${testgenie.directory.response.path}")
    private  String responsePath;


    private List<Map.Entry<String,String>> findFilesInDirectory(String extension) throws IOException {

        List<Map.Entry<String,String>> fileContents = new ArrayList<>();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:" + filePath + "/**/*" + extension);

        for (Resource resource : resources) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
                String content = reader.lines().collect(Collectors.joining("\n"));

                fileContents.add(Map.entry(filePath+"/"+Objects.requireNonNull(resource.getFilename()), content));
            }
        }
        return fileContents;
    }

    public List<AbstractMap.SimpleEntry<String,String>> extractRequests(String extension) throws IOException {

        List<AbstractMap.SimpleEntry<String,String>> requestFilePaths = new ArrayList<>();
        List<Map.Entry<String,String>> fileContents = findFilesInDirectory(extension);
        fileContents.forEach(fileContent -> {
            String fileName = fileContent.getKey();
            String content = null;
            try {
                content = vertexAIService.extractRequest(fileContent.getValue(),fileType);
            } catch (IOException | URISyntaxException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            String destinationPath = responsePath+fileName;

            destinationPath = destinationPath.substring(0, destinationPath.lastIndexOf("."))+"."+fileType;
            requestFilePaths.add(new AbstractMap.SimpleEntry<>(fileName.substring(0,fileName.lastIndexOf(".")), destinationPath));

            try {
                saveFile(destinationPath, content);
            } catch (IOException | URISyntaxException | InterruptedException e) {
                throw new RuntimeException(e);
            }


        });

        return requestFilePaths;
    }

}
