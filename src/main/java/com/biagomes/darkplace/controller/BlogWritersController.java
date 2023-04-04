package com.biagomes.darkplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biagomes.darkplace.model.DTO.BlogWritersDTO;
import com.biagomes.darkplace.services.BlogWritersService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/writers")
public class BlogWritersController {

    @Autowired
    private BlogWritersService blogWritersService;

    @GetMapping
    public ResponseEntity<Page<BlogWritersDTO>> getAll(
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "999") int size,
        @RequestParam(required = false, defaultValue = "id") String sort){
            return new ResponseEntity<>(blogWritersService.getAll(page, size, sort), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogWritersDTO> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(blogWritersService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BlogWritersDTO> create(@Valid @RequestBody BlogWritersDTO writers){
        return new ResponseEntity<>(blogWritersService.create(writers), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogWritersDTO> update(
        @Valid 
        @RequestBody BlogWritersDTO writers, 
        @PathVariable("id") Long id){
            return new ResponseEntity<>(blogWritersService.update(id, writers), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BlogWritersDTO> delete(@PathVariable("id") Long id){
        blogWritersService.delete(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
