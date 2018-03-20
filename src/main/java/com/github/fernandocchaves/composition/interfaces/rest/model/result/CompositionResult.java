package com.github.fernandocchaves.composition.interfaces.rest.model.result;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class CompositionResult {

    @Tolerate
    public CompositionResult() {
    }

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
    private CompositionResult children;
}
