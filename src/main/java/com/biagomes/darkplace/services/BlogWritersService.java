package com.biagomes.darkplace.services;

import org.springframework.data.domain.Page;

public interface BlogWritersService {
    Page<BlogWritersService> getAll();
}
