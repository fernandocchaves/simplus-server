package com.github.fernandocchaves.composition.domain.shared;

public class CompositionErrorCode extends ErrorCode {
    public final static CompositionErrorCode COMPOSITION_NOT_FOUND = new CompositionErrorCode("ERROR-001", "Composition not found");
    public final static CompositionErrorCode CHILDREN_NOT_FOUND = new CompositionErrorCode("ERROR-002", "Children not found");
    public final static CompositionErrorCode INVALID_CHILDREN = new CompositionErrorCode("ERROR-003", "Invalid children");

    protected CompositionErrorCode(String code, String message) {
        super(code, message);
    }
}
