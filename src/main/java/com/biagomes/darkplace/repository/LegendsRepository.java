package com.biagomes.darkplace.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biagomes.darkplace.model.Legends;

public interface LegendsRepository extends JpaRepository<Legends, Long> {
    @Query(
            value = 
                "SELECT l FROM Legends l WHERE " +
                "l.blog_writers.id = :id ",
            countQuery = 
                "SELECT COUNT(l) FROM Legends l WHERE " +
                "l.blog_writers.id = :id")
    public Page<Legends> findLegendsByWriter(Pageable pageable, Long id);

    @Query("SELECT l FROM Legends l WHERE " +
            "l.title = :title")
    public Optional<Legends> findLegendsByTitle(@Param("title") String title);
}
