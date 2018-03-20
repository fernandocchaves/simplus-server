package com.github.fernandocchaves.composition.domain.shared;

import lombok.Value;

@Value(staticConstructor = "of")
public class CommandFailure {
    private CompositionErrorCode error;
}
