package com.github.fernandocchaves.composition.application.impl;

import com.github.fernandocchaves.composition.domain.command.CreateComposition;
import com.github.fernandocchaves.composition.domain.command.UpdateComposition;
import com.github.fernandocchaves.composition.infrasctructure.persistence.mapping.CompositionTable;

public class CompositionProcessManagerImplMock {

    public static CompositionTable dataTable(Long id, CompositionTable parent) {
        CompositionTable node = CompositionTable.builder()
            .id(id)
            .code("123")
            .packing("CAIXA")
            .quantity(1)
            .height(1)
            .heightMeasure("cm")
            .width(1)
            .widthMeasure("cm")
            .depth(1)
            .depthMeasure("cm")
            .grossWeight(1)
            .grossWeightMeasure("kg")
            .netWeight(1)
            .netWeightMeasure("kg")
            .build();

        return node;
    }

    public static CreateComposition createDataCommand(Long parent) {
        CreateComposition command = CreateComposition.commandOf(
            "123",
            "CAIXA",
            1,
            1,
            "cm",
            1,
            "cm",
            1,
            "cm",
            1,
            "kg",
            1,
            "kg",
            null
        );

        return command;
    }

    public static UpdateComposition updateDataCommand(Long id, Long parent) {
        UpdateComposition command = UpdateComposition.commandOf(
            id,
            "123",
            "CAIXA",
            1,
            1,
            "cm",
            1,
            "cm",
            1,
            "cm",
            1,
            "kg",
            1,
            "kg"
        );
        return command;
    }
}
