package com.nallani.service.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nallani.model.Address;
import com.nallani.model.FullName;
import com.nallani.model.Registation;
import com.nallani.service.CreateWriteFile;
import com.nallani.service.ReadFile;
import com.nallani.service.TestDataGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Rest {

    @Autowired
    private TestDataGeneration dataGeneration;

    @Autowired
    private FullName fullName;

    @Autowired
    private Address address;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private Registation registation;

    @Autowired
    private CreateWriteFile createWriteFile;

    @Autowired
    private ReadFile readFile;

    @GetMapping("/user")
    public ResponseEntity<String> getUserTestData(@RequestParam(value = "count", defaultValue = "1") int count) throws Exception {

        Logger logger = LoggerFactory.getLogger(Rest.class);
        long start = System.currentTimeMillis();

        for (int i = 0; i <= count; i++) {
            fullName.setFirstName(dataGeneration.generateName(9));
            fullName.setLastName(dataGeneration.generateName(7));

            address.setLine1(dataGeneration.generateAddressLine1());
            address.setCity("Richmond");
            address.setState("Va");
            address.setPostalCode(dataGeneration.generateZip());

            registation.setUsername(dataGeneration.generateUsername());
            registation.setPassword(dataGeneration.generatePassword());
            registation.setFullName(fullName);
            registation.setAddress(address);
            registation.setEmail(dataGeneration.generateEmail());
            registation.setPhone(dataGeneration.generatePhoneNumber());
            registation.setDateOfBirth(dataGeneration.generateDOB());

            String testData = mapper.writeValueAsString(registation);
            createWriteFile.createFileAndWrite(testData);

            long end = System.currentTimeMillis();
            logger.info("Processing " +count+ " takes " + (end - start)/1000 + " seconds");

        }
        return ResponseEntity.created(null).body(readFile.readFileAndReturn());
    }
}