package com.github.fernandocchaves.composition.domain.entity;

import com.github.fernandocchaves.composition.domain.event.CompositionCreated;
import com.github.fernandocchaves.composition.domain.event.CompositionUpdated;
import com.github.fernandocchaves.composition.domain.shared.CommandFailure;
import com.github.fernandocchaves.composition.interfaces.rest.model.result.CompositionResult;
import io.vavr.control.Either;

import java.util.List;

public interface CompositionRepository {
    Either<CommandFailure, CompositionResult> store(CompositionCreated nodeEvent);
    Either<CommandFailure, CompositionResult> store(CompositionUpdated nodeEvent);
    void delete(Long id);
    List<CompositionResult> listAll();
}
