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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.biagomes.darkplace.model.DTO.CasesWithoutSolutionDTO;
import com.biagomes.darkplace.services.CasesWithoutSolutionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/cases")
public class CasesWithoutSolutionController {

    @Autowired
    private CasesWithoutSolutionService casesService;

    @GetMapping
    public ResponseEntity<Page<CasesWithoutSolutionDTO>> getAll(
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "999") int size,
        @RequestParam(required = false, defaultValue = "id") String sort){
            return new ResponseEntity<>(casesService.getAll(page, size, sort), HttpStatus.OK);
        }

    @GetMapping("/{id}")
    public ResponseEntity<CasesWithoutSolutionDTO> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(casesService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/writer/{id}")
    public ResponseEntity<Page<CasesWithoutSolutionDTO>> getAllByWriter(
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "999") int size,
        @RequestParam(required = false, defaultValue = "id") String sort,
        @PathVariable(value = "name") Long id){
            return new ResponseEntity<>(casesService.getAllByWriter(page, size, sort, id), HttpStatus.OK);
        }

    @PostMapping
    public ResponseEntity<CasesWithoutSolutionDTO> create(@Valid @RequestBody CasesWithoutSolutionDTO casesWithoutSolutionDTO){
        return new ResponseEntity<>(casesService.create(casesWithoutSolutionDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CasesWithoutSolutionDTO> update(
        @Valid 
        @RequestBody CasesWithoutSolutionDTO casesWithoutSolutionDTO, 
        @PathVariable("id") Long id){
            return new ResponseEntity<>(casesService.update(id, casesWithoutSolutionDTO), HttpStatus.OK);
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<CasesWithoutSolutionDTO> delete(@PathVariable("id") Long id){
        casesService.delete(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
