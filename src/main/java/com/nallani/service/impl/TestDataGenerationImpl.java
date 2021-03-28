package com.nallani.service.impl;

import com.nallani.service.TestDataGeneration;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TestDataGenerationImpl implements TestDataGeneration {

    Logger logger = LoggerFactory.getLogger(TestDataGenerationImpl.class);

    @Override
    public String generateName(int length) {

        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        if(length == 3){
            return generatedString.toUpperCase();
        }
        logger.info(WordUtils.capitalize(generatedString.toLowerCase()));
        return WordUtils.capitalize(generatedString.toLowerCase());
    }

    @Override
    public String generateUsername() {

        String a = generateName(3);
        String b = RandomStringUtils.randomNumeric(3);
        logger.info(a + b);
        return a + b;
    }

    @Override
    public String generatePassword() {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45)
                .build();
        String generatedString = pwdGenerator.generate(9);

        logger.info(generatedString);
        return generatedString;
    }

    @Override
    public String generateDOB() {
        Instant inc = Instant.ofEpochSecond(ThreadLocalRandom.current().nextInt());
        String abc = inc.toString();
        logger.info(abc.substring(0,10));
        return abc.substring(0,10);
    }

    @Override
    public String generatePhoneNumber() {
        String generatedString = RandomStringUtils.randomNumeric(10);
        logger.info(generatedString);
        return generatedString;
    }

    @Override
    public String generateZip() {

        Random rand = new Random();
        Integer a = rand.nextInt(100000);
        logger.info(a.toString());
        return a.toString();
    }

    @Override
    public String generateEmail() {
        String prefix = RandomStringUtils.random(8, true,false);
        String suefix = RandomStringUtils.random(8, true,false);
        logger.info(prefix.toLowerCase() + "." + suefix.toLowerCase() + "@gmail.com");
        return prefix.toLowerCase() + "." + suefix.toLowerCase() + "@gmail.com";
    }

    @Override
    public String generateAddressLine1() {
        String a = generateName(4);
        String b = generateName(7);
        logger.info(a + " " + b + "Ave");
        return a + " " + b + "Ave";
    }
}
