package com.biagomes.darkplace.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biagomes.darkplace.model.Legends;

@Repository
public interface LegendsRepository extends JpaRepository<Legends, Long> {
    @Query(
            value = 
                "SELECT l FROM Legends l WHERE " +
                "l.writer.id = :id ",
            countQuery = 
                "SELECT COUNT(l) FROM Legends l WHERE " +
                "l.writer.id = :id")
    public Page<Legends> findLegendsByWriter(Pageable pageable, Long id);

    @Query("SELECT l FROM Legends l WHERE " +
            "l.title.id = :id")
    public Optional<Legends> findLegendsByTitle(@Param("title") String title);
}
