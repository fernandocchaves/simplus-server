package com.github.fernandocchaves.composition.application.impl;

import com.github.fernandocchaves.composition.application.CompositionProcessManager;
import com.github.fernandocchaves.composition.domain.command.CreateComposition;
import com.github.fernandocchaves.composition.domain.command.UpdateComposition;
import com.github.fernandocchaves.composition.domain.shared.CommandFailure;
import com.github.fernandocchaves.composition.domain.shared.CompositionErrorCode;
import com.github.fernandocchaves.composition.infrasctructure.persistence.mapping.CompositionTable;
import com.github.fernandocchaves.composition.infrasctructure.persistence.repository.CompositionRepositoryImpl;
import com.github.fernandocchaves.composition.infrasctructure.persistence.repository.CompositionStore;
import com.github.fernandocchaves.composition.interfaces.rest.model.result.CompositionResult;
import io.vavr.control.Either;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class CompositionProcessManagerImplTest {

    CompositionProcessManager compositionProcessManager;

    @Mock
    CompositionRepositoryImpl compositionRepository;

    @Mock
    CompositionStore compositionStore;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        compositionRepository = new CompositionRepositoryImpl(compositionStore);
        compositionProcessManager = new CompositionProcessManagerImpl(compositionRepository);
    }

    @Test
    public void shouldCreateCompositionNoChildren() {
        CreateComposition command = CompositionProcessManagerImplMock.createDataCommand(null);
        CompositionTable compositionTable = CompositionProcessManagerImplMock.dataTable(new Long(1), null);

        when(compositionStore.save(any(CompositionTable.class))).thenReturn(compositionTable);
        Either<CommandFailure, CompositionResult> response = compositionProcessManager.create(command);

        assertTrue(response.isRight());
        assertTrue(response.right().get() instanceof CompositionResult);
    }

    @Test
    public void shouldCreateCompositionWithChildren() {
        CreateComposition command = CompositionProcessManagerImplMock.createDataCommand(new Long(1));
        CompositionTable parentTable = CompositionProcessManagerImplMock.dataTable(new Long(1), null);
        CompositionTable compositionTable = CompositionProcessManagerImplMock.dataTable(new Long(2), parentTable);

        when(compositionStore.save(any(CompositionTable.class))).thenReturn(compositionTable);
        when(compositionStore.findOne(any(Long.class))).thenReturn(parentTable);

        Either<CommandFailure, CompositionResult> response = compositionProcessManager.create(command);

        assertTrue(response.isRight());
        assertTrue(response.right().get() instanceof CompositionResult);
    }

    @Test
    public void shouldUpdateCompositionNoChildren() {
        UpdateComposition command = CompositionProcessManagerImplMock.updateDataCommand(new Long(1),null);
        CompositionTable compositionTable = CompositionProcessManagerImplMock.dataTable(new Long(1), null);

        when(compositionStore.save(any(CompositionTable.class))).thenReturn(compositionTable);
        when(compositionStore.findOne(any(Long.class))).thenReturn(compositionTable);
        Either<CommandFailure, CompositionResult> response = compositionProcessManager.update(command);

        assertTrue(response.isRight());
        assertTrue(response.right().get() instanceof CompositionResult);
    }

    @Test
    public void shouldUpdateCompositionWithChildren() {
        UpdateComposition command = CompositionProcessManagerImplMock.updateDataCommand(new Long(1),null);

        CompositionTable parentTable = CompositionProcessManagerImplMock.dataTable(new Long(1), null);
        CompositionTable compositionTable = CompositionProcessManagerImplMock.dataTable(new Long(2), parentTable);

        when(compositionStore.save(any(CompositionTable.class))).thenReturn(compositionTable);
        when(compositionStore.findOne(any(Long.class))).thenReturn(compositionTable);
        Either<CommandFailure, CompositionResult> response = compositionProcessManager.update(command);

        assertTrue(response.isRight());
        assertTrue(response.right().get() instanceof CompositionResult);
    }

    @Test
    public void shouldUpdateCompositionNotFound() {
        UpdateComposition command = CompositionProcessManagerImplMock.updateDataCommand(new Long(1),new Long(1));
        CompositionTable compositionTable = CompositionProcessManagerImplMock.dataTable(new Long(2), null);

        when(compositionStore.save(any(CompositionTable.class))).thenReturn(compositionTable);
        when(compositionStore.findOne(any(Long.class))).thenReturn(null);
        Either<CommandFailure, CompositionResult> response = compositionProcessManager.update(command);

        assertTrue(response.isLeft());
        assertTrue(response.left().get() instanceof CommandFailure);
        assertTrue(response.left().get().equals(CommandFailure.of(CompositionErrorCode.COMPOSITION_NOT_FOUND)));
    }

}
