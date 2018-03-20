package com.github.fernandocchaves.composition.application;

import com.github.fernandocchaves.composition.domain.command.CreateComposition;
import com.github.fernandocchaves.composition.domain.command.UpdateComposition;
import com.github.fernandocchaves.composition.domain.shared.CommandFailure;
import com.github.fernandocchaves.composition.interfaces.rest.model.result.CompositionResult;
import io.vavr.control.Either;

import java.util.List;

public interface CompositionProcessManager {
    Either<CommandFailure, CompositionResult> create(CreateComposition command);
    Either<CommandFailure, CompositionResult> update(UpdateComposition command);
    void delete(Long id);
    List<CompositionResult> listAll();
}
