package com.biagomes.darkplace.services;

import org.springframework.data.domain.Page;

import com.biagomes.darkplace.model.request.LegendsRequestDTO;
import com.biagomes.darkplace.model.response.LegendsResponseDTO;

public interface LegendsService {
    Page<LegendsResponseDTO> getAll(int page, int size, String sort);
    
    LegendsResponseDTO getById(Long id);

    Page<LegendsResponseDTO> getAllByWriter(int page, int size, String sort, Long id);

    LegendsResponseDTO create(LegendsRequestDTO legends);

    LegendsResponseDTO update(Long id, LegendsRequestDTO legends);

    void delete(Long id);
}
