package com.biagomes.darkplace.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_cases_without_solution")
public class CasesWithoutSolution implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cases_without_solution_id")
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "history")
    private String history;

    @ManyToOne()
    @JoinColumn(name = "writer_id", referencedColumnName = "writer_id")
    private BlogWriters writer;
}
