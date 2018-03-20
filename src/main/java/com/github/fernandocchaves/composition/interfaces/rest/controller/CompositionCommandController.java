package com.github.fernandocchaves.composition.interfaces.rest.controller;

import com.github.fernandocchaves.composition.application.CompositionProcessManager;
import com.github.fernandocchaves.composition.domain.command.CreateComposition;
import com.github.fernandocchaves.composition.domain.command.UpdateComposition;
import com.github.fernandocchaves.composition.domain.shared.CommandFailure;
import com.github.fernandocchaves.composition.interfaces.rest.model.request.CreateCompositionRequest;
import com.github.fernandocchaves.composition.interfaces.rest.model.request.UpdateCompositionRequest;
import com.github.fernandocchaves.composition.interfaces.rest.model.result.CompositionResult;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class CompositionCommandController {

    private final CompositionProcessManager compositionProcessManager;

    public CompositionCommandController(CompositionProcessManager compositionProcessManager) {
        this.compositionProcessManager = compositionProcessManager;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody CreateCompositionRequest request) {

        CreateComposition command = CreateComposition.commandOf(
                request.getCode(),
                request.getPacking(),
                request.getQuantity(),
                request.getHeight(),
                request.getHeightMeasure(),
                request.getWidth(),
                request.getWidthMeasure(),
                request.getDepth(),
                request.getDepthMeasure(),
                request.getGrossWeight(),
                request.getGrossWeightMeasure(),
                request.getNetWeight(),
                request.getNetWeightMeasure(),
                request.getChildren()
        );

        log.debug("Controller command {}", command);

        Either<CommandFailure, CompositionResult> response = compositionProcessManager.create(command);

        if(response.isLeft()) {
            return new ResponseEntity<>(response.left().get(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(response.right().get(), HttpStatus.OK);

    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@Valid @RequestBody UpdateCompositionRequest request) {

        UpdateComposition command = UpdateComposition.commandOf(
                request.getId(),
                request.getCode(),
                request.getPacking(),
                request.getQuantity(),
                request.getHeight(),
                request.getHeightMeasure(),
                request.getWidth(),
                request.getWidthMeasure(),
                request.getDepth(),
                request.getDepthMeasure(),
                request.getGrossWeight(),
                request.getGrossWeightMeasure(),
                request.getNetWeight(),
                request.getNetWeightMeasure()
        );

        log.debug("Controller command {}", command);

        Either<CommandFailure, CompositionResult> response = compositionProcessManager.update(command);

        if(response.isLeft()) {
            return new ResponseEntity<>(response.left().get(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(response.right().get(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listParent(@PathVariable Long id) {
        compositionProcessManager.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
