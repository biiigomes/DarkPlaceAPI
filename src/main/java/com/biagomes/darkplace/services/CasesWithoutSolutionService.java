package com.biagomes.darkplace.services;

import org.springframework.data.domain.Page;

import com.biagomes.darkplace.model.request.CasesWithouSolutionRequestDTO;
import com.biagomes.darkplace.model.response.CasesWithoutSolutionResponseDTO;

public interface CasesWithoutSolutionService {
    Page<CasesWithoutSolutionResponseDTO> getAll(int page, int size, String sort);
    
    CasesWithoutSolutionResponseDTO getById(Long id);

    Page<CasesWithoutSolutionResponseDTO> getAllByWriter(int page, int size, String sort, Long id);

    CasesWithoutSolutionResponseDTO create(CasesWithouSolutionRequestDTO cases);

    CasesWithoutSolutionResponseDTO update(Long id, CasesWithouSolutionRequestDTO cases);

    void delete(Long id);
}
