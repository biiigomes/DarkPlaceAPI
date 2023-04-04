package com.biagomes.darkplace.services;

import org.springframework.data.domain.Page;

import com.biagomes.darkplace.model.DTO.request.CasesRequestDTO;
import com.biagomes.darkplace.model.DTO.response.CasesWithoutSolutionDTO;

public interface CasesWithoutSolutionService {
    Page<CasesWithoutSolutionDTO> getAll(int page, int size, String sort);
    
    CasesWithoutSolutionDTO getById(Long id);

    CasesWithoutSolutionDTO create(CasesRequestDTO casesRequestDTO);

    CasesWithoutSolutionDTO update(Long id, CasesRequestDTO casesRequestDTO);

    void delete(Long id);

    Page<CasesWithoutSolutionDTO> getAllByWriter(int page, int size, String sort, Long id);
}
