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

import com.biagomes.darkplace.model.DTO.LegendsDTO;
import com.biagomes.darkplace.services.LegendsService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/legends")
public class LegendsController {

    @Autowired
    private LegendsService legendsService;

    @GetMapping
    public ResponseEntity<Page<LegendsDTO>> getAll(
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "999") int size,
        @RequestParam(required = false, defaultValue = "id") String sort){
            return new ResponseEntity<>(legendsService.getAll(page, size, sort), HttpStatus.OK);
        }
    
    @GetMapping("/{id}")
    public ResponseEntity<LegendsDTO> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(legendsService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/writer/{id}")
    public ResponseEntity<Page<LegendsDTO>> getAllByWriter(
        @RequestParam(required =  false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "999") int size,
        @RequestParam(required = false, defaultValue = "id") String sort,
        @PathVariable(value = "id") Long id) {
            return new ResponseEntity<>(legendsService.getAllByWriter(page, size, sort, id), HttpStatus.OK);
        }
    
    @PostMapping
    public ResponseEntity<LegendsDTO> create(@Valid @RequestBody LegendsDTO legendsDTO){
        return new ResponseEntity<>(legendsService.create(legendsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LegendsDTO> update(
        @Valid
        @RequestBody LegendsDTO legendsDTO,
        @PathVariable("id") Long id){
            return new ResponseEntity<>(legendsService.update(id, legendsDTO), HttpStatus.OK);
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<LegendsDTO> delete(
        @PathVariable("id") Long id){
            legendsService.delete(id);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
}
