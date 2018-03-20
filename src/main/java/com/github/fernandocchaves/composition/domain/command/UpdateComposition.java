package com.github.fernandocchaves.composition.domain.command;

import lombok.Value;

@Value(staticConstructor = "commandOf")
public class UpdateComposition implements CompositionCommand {
    private Long id;
    private String code;
    private String packing;
    private Integer quantity;
    private Integer height;
    private String heightMeasure;
    private Integer width;
    private String widthMeasure;
    private Integer depth;
    private String depthMeasure;
    private Integer grossWeight;
    private String grossWeightMeasure;
    private Integer netWeight;
    private String netWeightMeasure;
}
