package com.nallani.service.impl;

import com.nallani.service.CreateWriteFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

@Service
public class CreateFileAndWriteImpl implements CreateWriteFile {

    @Override
    @Async
    public void createFileAndWrite(String value) throws Exception {

        Logger logger = LoggerFactory.getLogger(CreateFileAndWriteImpl.class);


        if (value == null && value.isEmpty()){
            throw new Exception("value is empty");
        }

        File fileName = new File("registation-data.txt");

        if (fileName.createNewFile()) {

            logger.info("File has been created.");
        } else {

            logger.info("File already exists.");
        }

        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(value);
        bw.newLine();
        bw.close();
    }
}
