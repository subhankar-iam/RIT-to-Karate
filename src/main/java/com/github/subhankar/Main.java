package com.github.subhankar;

import com.github.subhankar.service.FileFinderService;
import com.github.subhankar.util.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        FileFinderService fileFinderService = new FileFinderService();

        fileFinderService.findFilesInDirectory();
    }
}