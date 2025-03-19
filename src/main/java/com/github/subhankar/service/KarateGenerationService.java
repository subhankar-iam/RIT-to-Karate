package com.github.subhankar.service;


import com.github.subhankar.util.ApplicationContextHelper;
import com.github.subhankar.util.KarateGenUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;

@Service
public class KarateGenerationService {


    private KarateGenUtil karateGenUtil;

    public void generateKarateTests(List<AbstractMap.SimpleEntry<String,String>> req) throws IOException {
        karateGenUtil = ApplicationContextHelper.getKarateGenUtil();
        karateGenUtil.getKarateTests(req);
    }
}
