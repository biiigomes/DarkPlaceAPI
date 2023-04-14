package com.biagomes.darkplace.services;

import org.springframework.data.domain.Page;

import com.biagomes.darkplace.model.request.BlogWritersRequestDTO;
import com.biagomes.darkplace.model.response.BlogWritersResponseDTO;

public interface BlogWritersService {
    Page<BlogWritersResponseDTO> getAll(int page, int size, String sort);

    BlogWritersResponseDTO getById(Long id);

    BlogWritersResponseDTO create(BlogWritersRequestDTO writers);

    BlogWritersResponseDTO update(Long id, BlogWritersRequestDTO writers);

    void delete(Long id);

}
