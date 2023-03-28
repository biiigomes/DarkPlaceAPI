package com.biagomes.darkplace.repository;

import java.util.Optional;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biagomes.darkplace.model.CasesWithoutSolution;

@Repository
public interface CasesWithoutSolutionRepository extends JpaRepository<CasesWithoutSolution, Long> {
    @Query(
            value = 
                "SELECT c FROM CasesWithoutSolution c WHERE " +
                "c.writer.id = :id ",
            countQuery = 
                "SELECT COUNT(c) FROM CasesWithoutSolution c WHERE " +
                "c.writer.id = :id")
    public Page<CasesWithoutSolution> findCasesWithoutSolutionByWriter(Pageable pageable, Long id);

    @Query("SELECT c FROM CasesWithoutSolution c WHERE " +
            "c.title = :title")
    public Optional<CasesWithoutSolution> findCasesWithoutSolutionByTitle(@Param("title") String title);
}
