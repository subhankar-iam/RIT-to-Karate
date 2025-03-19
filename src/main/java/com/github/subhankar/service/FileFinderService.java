package com.github.subhankar.service;

import com.github.subhankar.util.ApplicationContextHelper;
import com.github.subhankar.util.FileFinderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class FileFinderService {

    @Autowired
    private KarateGenerationService karateGenerationService;


    public void findFilesInDirectory() throws IOException {
        karateGenerationService = ApplicationContextHelper.getKarateGenerationService();
        FileFinderUtil fileFinderUtil = ApplicationContextHelper.getFileFinderUtil();
        var requests =  fileFinderUtil.extractRequests(".tsq");
        karateGenerationService.generateKarateTests(requests);
    }

}
