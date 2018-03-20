package com.github.fernandocchaves.composition.interfaces.rest.model.response;

import com.github.fernandocchaves.composition.interfaces.rest.model.result.CompositionResult;
import lombok.Data;

import java.util.List;

@Data
public class CompositionListResponse {
    List<CompositionResult> nodes;
}
