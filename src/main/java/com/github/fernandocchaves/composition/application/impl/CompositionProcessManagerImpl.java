package com.github.fernandocchaves.composition.application.impl;

import com.github.fernandocchaves.composition.application.CompositionProcessManager;
import com.github.fernandocchaves.composition.domain.command.CreateComposition;
import com.github.fernandocchaves.composition.domain.command.UpdateComposition;
import com.github.fernandocchaves.composition.domain.event.CompositionCreated;
import com.github.fernandocchaves.composition.domain.event.CompositionUpdated;
import com.github.fernandocchaves.composition.domain.shared.CommandFailure;
import com.github.fernandocchaves.composition.infrasctructure.persistence.repository.CompositionRepositoryImpl;
import com.github.fernandocchaves.composition.interfaces.rest.model.result.CompositionResult;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CompositionProcessManagerImpl implements CompositionProcessManager {

    private final CompositionRepositoryImpl compositionRepository;

    public CompositionProcessManagerImpl(CompositionRepositoryImpl compositionRepository) {
        this.compositionRepository = compositionRepository;
    }

    @Override
    public Either<CommandFailure, CompositionResult> create(CreateComposition command) {

        CompositionCreated event = CompositionCreated.eventOf(
                command.getCode(),
                command.getPacking(),
                command.getQuantity(),
                command.getHeight(),
                command.getHeightMeasure(),
                command.getWidth(),
                command.getWidthMeasure(),
                command.getDepth(),
                command.getDepthMeasure(),
                command.getGrossWeight(),
                command.getGrossWeightMeasure(),
                command.getNetWeight(),
                command.getNetWeightMeasure(),
                command.getChildren()
        );

        log.debug("Service event {}", event);

        return compositionRepository.store(event);
    }

    @Override
    public Either<CommandFailure, CompositionResult> update(UpdateComposition command) {
        CompositionUpdated event = CompositionUpdated.eventOf(
                command.getId(),
                command.getCode(),
                command.getPacking(),
                command.getQuantity(),
                command.getHeight(),
                command.getHeightMeasure(),
                command.getWidth(),
                command.getWidthMeasure(),
                command.getDepth(),
                command.getDepthMeasure(),
                command.getGrossWeight(),
                command.getGrossWeightMeasure(),
                command.getNetWeight(),
                command.getNetWeightMeasure()
        );

        log.debug("Service event {}", event);

        return compositionRepository.store(event);
    }

    @Override
    public void delete(Long id) {
        compositionRepository.delete(id);
    }

    @Override
    public List<CompositionResult> listAll() {
        List<CompositionResult> compositions = compositionRepository.listAll();
        return compositions;
    }
}
