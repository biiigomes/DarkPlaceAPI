package com.biagomes.darkplace.services;

import org.springframework.data.domain.Page;

import com.biagomes.darkplace.model.DTO.LegendsDTO;

public interface LegendsService {
    Page<LegendsDTO> getAll(int page, int size, String sort);
    
    LegendsDTO getById(Long id);

    Page<LegendsDTO> getAllByWriter(int page, int size, String sort, Long id);

    LegendsDTO create(LegendsDTO legends);

    LegendsDTO update(Long id, LegendsDTO legends);

    void delete(Long id);
}
