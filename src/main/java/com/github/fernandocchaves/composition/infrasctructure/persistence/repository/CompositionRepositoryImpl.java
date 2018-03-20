package com.github.fernandocchaves.composition.infrasctructure.persistence.repository;

import com.github.fernandocchaves.composition.domain.entity.CompositionRepository;
import com.github.fernandocchaves.composition.domain.event.CompositionCreated;
import com.github.fernandocchaves.composition.domain.event.CompositionUpdated;
import com.github.fernandocchaves.composition.domain.shared.CommandFailure;
import com.github.fernandocchaves.composition.domain.shared.CompositionErrorCode;
import com.github.fernandocchaves.composition.infrasctructure.persistence.mapping.CompositionTable;
import com.github.fernandocchaves.composition.infrasctructure.persistence.mapping.MappingFactory;
import com.github.fernandocchaves.composition.interfaces.rest.model.result.CompositionResult;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class CompositionRepositoryImpl implements CompositionRepository {

    private final CompositionStore compositionStore;

    public CompositionRepositoryImpl(CompositionStore compositionStore) {
        this.compositionStore = compositionStore;
    }

    @Override
    public Either<CommandFailure, CompositionResult> store(CompositionCreated compositionEvent) {

        CompositionTable composition = new CompositionTable();
        CompositionTable children = null;

        if (Objects.nonNull(compositionEvent.getChildren())) {
            children = checkComposition(compositionEvent.getChildren());

            if(Objects.isNull(children)) {
                return Either.left(CommandFailure.of(CompositionErrorCode.CHILDREN_NOT_FOUND));
            }

            if(Objects.nonNull(children.getParentId())) {
                composition.setParentId(children.getParentId());
            }

        }

        composition.setCode(compositionEvent.getCode());
        composition.setPacking(compositionEvent.getPacking());
        composition.setQuantity(compositionEvent.getQuantity());
        composition.setHeight(compositionEvent.getHeight());
        composition.setHeightMeasure(compositionEvent.getHeightMeasure());
        composition.setWidth(compositionEvent.getWidth());
        composition.setWidthMeasure(compositionEvent.getWidthMeasure());
        composition.setDepth(compositionEvent.getDepth());
        composition.setDepthMeasure(compositionEvent.getDepthMeasure());
        composition.setNetWeight(compositionEvent.getNetWeight());
        composition.setNetWeightMeasure(compositionEvent.getNetWeightMeasure());
        composition.setGrossWeight(compositionEvent.getGrossWeight());
        composition.setGrossWeightMeasure(compositionEvent.getGrossWeightMeasure());

        log.debug("Repository event {}", composition);

        CompositionTable result = compositionStore.save(composition);

        if(Objects.nonNull(children)) {
            children.setParentId(result);
            compositionStore.save(children);
        }

        return Either.right(MappingFactory.mapSingle(result));
    }

    @Override
    public Either<CommandFailure, CompositionResult> store(CompositionUpdated compositionEvent) {
        CompositionTable composition = checkComposition(compositionEvent.getId());

        if(Objects.isNull(composition)) {
            return Either.left(CommandFailure.of(CompositionErrorCode.COMPOSITION_NOT_FOUND));
        }

        composition.setCode(compositionEvent.getCode());
        composition.setPacking(compositionEvent.getPacking());
        composition.setQuantity(compositionEvent.getQuantity());
        composition.setHeight(compositionEvent.getHeight());
        composition.setHeightMeasure(compositionEvent.getHeightMeasure());
        composition.setWidth(compositionEvent.getWidth());
        composition.setWidthMeasure(compositionEvent.getWidthMeasure());
        composition.setDepth(compositionEvent.getDepth());
        composition.setDepthMeasure(compositionEvent.getDepthMeasure());
        composition.setNetWeight(compositionEvent.getNetWeight());
        composition.setNetWeightMeasure(compositionEvent.getNetWeightMeasure());
        composition.setGrossWeight(compositionEvent.getGrossWeight());
        composition.setGrossWeightMeasure(compositionEvent.getGrossWeightMeasure());

        log.debug("Repository event {}", composition);

        compositionStore.save(composition);
        return Either.right(MappingFactory.mapSingle(composition));
    }

    @Override
    public void delete(Long id) {
        List<CompositionTable> childrens = compositionStore.findAllByParentId(
            CompositionTable
                .builder()
                .id(id)
                .build()
        );

        for(CompositionTable child : childrens) {
            child.setParentId(null);
            compositionStore.saveAndFlush(child);
        }

        CompositionTable composition = compositionStore.findOne(id);
        composition.setChildren(null);
        compositionStore.delete(composition);
    }

    @Override
    public List<CompositionResult> listAll() {
        List<CompositionTable> compositions = compositionStore.findAllByParentId(null);
        return MappingFactory.map(compositions);
    }

    private CompositionTable checkComposition(Long id) {
        CompositionTable composition = compositionStore.findOne(id);
        return composition;
    }
}
