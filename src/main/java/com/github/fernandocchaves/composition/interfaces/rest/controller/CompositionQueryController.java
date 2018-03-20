package com.github.fernandocchaves.composition.interfaces.rest.controller;

import com.github.fernandocchaves.composition.application.CompositionProcessManager;
import com.github.fernandocchaves.composition.infrasctructure.persistence.repository.CompositionStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompositionQueryController {

    private final CompositionProcessManager compositionProcessManager;

    public CompositionQueryController(CompositionProcessManager compositionProcessManager) {
        this.compositionProcessManager = compositionProcessManager;
    }

    @Autowired
    CompositionStore nodeRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(compositionProcessManager.listAll(), HttpStatus.OK);
    }

}
