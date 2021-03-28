package com.nallani.service.impl;

import com.nallani.service.ReadFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReadFileServiceImpl implements ReadFile {
    @Override
    public String readFileAndReturn() throws IOException {

        try (Stream<String> stream = Files.lines(Paths.get("registation-data.txt"))) {
            return stream.collect(Collectors.joining());
        }
    }
}