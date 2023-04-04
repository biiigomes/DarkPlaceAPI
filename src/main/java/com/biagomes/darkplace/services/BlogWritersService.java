package com.biagomes.darkplace.services;

import org.springframework.data.domain.Page;

import com.biagomes.darkplace.model.DTO.response.BlogWritersDTO;

public interface BlogWritersService {
    Page<BlogWritersDTO> getAll(int page, int size, String sort);

    BlogWritersDTO getById(Long id);

    BlogWritersDTO create(BlogWritersDTO blogWritersDTO);

    BlogWritersDTO update(Long id, BlogWritersDTO blogWritersDTO);

    void delete(Long id);

}
