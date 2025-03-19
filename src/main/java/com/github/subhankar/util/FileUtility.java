package com.github.subhankar.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class FileUtility {

    public void saveFile(String fileName, String content) throws IOException, URISyntaxException, InterruptedException {


        String filePath = System.getProperty("user.dir")+"/"+fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
