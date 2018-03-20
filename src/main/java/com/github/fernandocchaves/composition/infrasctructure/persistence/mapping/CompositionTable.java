package com.github.fernandocchaves.composition.infrasctructure.persistence.mapping;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@Builder
@Entity
@Table(name="composition")
public class CompositionTable {

    @Tolerate
    public CompositionTable() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private CompositionTable parentId;

    @OneToOne(mappedBy = "parentId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CompositionTable children;

}
