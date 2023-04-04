package com.biagomes.darkplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biagomes.darkplace.model.BlogWriters;

public interface BlogWritersRepository extends JpaRepository<BlogWriters, Long>{
    @Query("SELECT w FROM BlogWriters w WHERE " + 
            "w.name = :name AND " +
            "w.fullname = :fullname AND " +
            "w.username = :username")
    public Optional<BlogWriters> findWriterByNameFullnameAndUsername(
        @Param("name") String name,
        @Param("fullname") String fullname,
        @Param("username") String username);
}
