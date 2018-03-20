package com.github.fernandocchaves.composition.interfaces.rest.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class CreateCompositionRequest {

    @NotNull
    private String code;
    @NotNull
    private String packing;
    @NotNull
    private Integer quantity;
    @NotNull
    private Integer height;
    @NotNull
    private String heightMeasure;
    @NotNull
    private Integer width;
    @NotNull
    private String widthMeasure;
    @NotNull
    private Integer depth;
    @NotNull
    private String depthMeasure;
    @NotNull
    private Integer grossWeight;
    @NotNull
    private String grossWeightMeasure;
    @NotNull
    private Integer netWeight;
    @NotNull
    private String netWeightMeasure;

    private Long children;


}
