package com.github.fernandocchaves.composition.infrasctructure.persistence.repository;

import com.github.fernandocchaves.composition.infrasctructure.persistence.mapping.CompositionTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompositionStore extends JpaRepository<CompositionTable, Long> {
    List<CompositionTable> findAllByParentId(CompositionTable parent);
}
