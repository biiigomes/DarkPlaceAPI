package com.biagomes.darkplace.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "blog_writers")
public class BlogWriters implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "writer_id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "fullname")
    private String fullname;

    @NotNull
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "blog_writers")
    private List<CasesWithoutSolution> casesWithoutSolution;

    @OneToMany(mappedBy = "blog_writers")
    private List<Legends> legends;
}
