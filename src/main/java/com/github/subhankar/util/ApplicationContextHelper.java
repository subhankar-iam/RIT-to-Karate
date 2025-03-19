package com.github.subhankar.util;

import com.github.subhankar.service.KarateGenerationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHelper implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    public static FileFinderUtil getFileFinderUtil() {

        return applicationContext.getBean(FileFinderUtil.class);
    }
    public static VertexAIUtil getVertexAIUtil() {
        return applicationContext.getBean(VertexAIUtil.class);
    }
    public static KarateGenUtil getKarateGenUtil() {
        return applicationContext.getBean(KarateGenUtil.class);
    }
    public static KarateGenerationService getKarateGenerationService() {
        return applicationContext.getBean(KarateGenerationService.class);
    }
}
