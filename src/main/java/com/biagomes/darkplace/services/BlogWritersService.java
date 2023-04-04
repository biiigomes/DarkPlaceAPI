package com.biagomes.darkplace.services;

import org.springframework.data.domain.Page;

import com.biagomes.darkplace.model.DTO.BlogWritersDTO;

public interface BlogWritersService {
    Page<BlogWritersDTO> getAll(int page, int size, String sort);

    BlogWritersDTO getById(Long id);

    BlogWritersDTO create(BlogWritersDTO blogWritersDTO);

    BlogWritersDTO update(BlogWritersDTO blogWritersDTO, Long id);

    void delete(Long id);

}
