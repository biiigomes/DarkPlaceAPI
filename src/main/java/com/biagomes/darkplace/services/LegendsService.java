package com.biagomes.darkplace.services;

import org.springframework.data.domain.Page;

import com.biagomes.darkplace.model.DTO.response.LegendsDTO;

public interface LegendsService {
    Page<LegendsDTO> getAll(int page, int size, String sort);
    
    LegendsDTO getById(Long id);

    LegendsDTO create(LegendsDTO legendsDTO);

    LegendsDTO update(LegendsDTO legendsDTO, Long id);

    void delete(Long id);

    Page<LegendsDTO> getAllByWriter(int page, int size, String sort, Long id);
}
