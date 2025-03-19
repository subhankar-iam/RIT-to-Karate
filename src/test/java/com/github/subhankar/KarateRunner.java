package com.github.subhankar;


import com.intuit.karate.junit5.Karate;

public class KarateRunner {

    @Karate.Test
    Karate test(){
        return  Karate.run("classpath:features")
                .relativeTo(getClass());
    }
}
