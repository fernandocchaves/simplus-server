package com.github.fernandocchaves.composition.infrasctructure.persistence.mapping;

import com.github.fernandocchaves.composition.interfaces.rest.model.result.CompositionResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public final class MappingFactory {

    public static List<CompositionResult> map(List<CompositionTable> records) {
        List<CompositionResult> nodes = records.stream()
                .map(singleTable -> mapSingle(singleTable))
                .collect(Collectors.toList());

        return nodes;
    }

    public static CompositionResult mapSingle(CompositionTable singleTable) {

        CompositionResult children = null;

        if(Objects.nonNull(singleTable.getChildren())) {
            children = mapSingle(singleTable.getChildren());
        }

        CompositionResult nodeResult = CompositionResult.builder()
                .id(singleTable.getId())
                .code(singleTable.getCode())
                .packing(singleTable.getPacking())
                .quantity(singleTable.getQuantity())
                .height(singleTable.getHeight())
                .heightMeasure(singleTable.getHeightMeasure())
                .width(singleTable.getWidth())
                .widthMeasure(singleTable.getWidthMeasure())
                .depth(singleTable.getDepth())
                .depthMeasure(singleTable.getDepthMeasure())
                .grossWeight(singleTable.getGrossWeight())
                .grossWeightMeasure(singleTable.getGrossWeightMeasure())
                .netWeight(singleTable.getNetWeight())
                .netWeightMeasure(singleTable.getNetWeightMeasure())
                .children(children)
                .build();

        return nodeResult;
    }

}
