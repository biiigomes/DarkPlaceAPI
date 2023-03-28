package com.biagomes.darkplace.services;

import org.springframework.data.domain.Page;

import com.biagomes.darkplace.model.DTO.CasesWithoutSolutionDTO;

public interface CasesWithoutSolutionService {
    Page<CasesWithoutSolutionDTO> getAll(int page, int size, String sort);
    
    CasesWithoutSolutionDTO getById(Long id);

    CasesWithoutSolutionDTO create(CasesWithoutSolutionDTO casesWithoutSolutionDTO);

    CasesWithoutSolutionDTO update(CasesWithoutSolutionDTO casesWithoutSolutionDTO);

    void delete(Long id);
}
