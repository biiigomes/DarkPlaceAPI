package com.biagomes.darkplace.services.impl;

import java.util.Optional;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.biagomes.darkplace.model.BlogWriters;
import com.biagomes.darkplace.model.DTO.BlogWritersDTO;
import com.biagomes.darkplace.repository.BlogWritersRepository;
import com.biagomes.darkplace.services.BlogWritersService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogWritersServiceImpl implements BlogWritersService {

    private final Logger logger = Logger.getLogger(BlogWritersService.class.getName());
    
    @Autowired
    private BlogWritersRepository repository;
    
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public Page<BlogWritersDTO> getAll(int page, int size, String sort) {
        logger.info("Encontrando todos os escritores");
        
        var pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort);
        Page<BlogWriters> result = repository.findAll(pageable);
        return result.map(obj -> mapper.map(obj, BlogWritersDTO.class));
    }

    @Override
    public BlogWritersDTO getById(Long id) {
        logger.info("Encontrando um escritor");

        BlogWriters writer = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Autor com esse id não encontrado"));
        return mapper.map(writer, BlogWritersDTO.class);
    }

    @Override
    public BlogWritersDTO create(BlogWritersDTO blogWritersDTO) {
        logger.info("Criando um escritor");

        Optional<BlogWriters> writerExists = repository
            .findWriterByNameFullnameAndUsername(
                blogWritersDTO.getName(), 
                blogWritersDTO.getFullname(), 
                blogWritersDTO.getUsername()
            );

        if(writerExists.isPresent()) throw new Error("Autor já registrado.");

        BlogWriters blogWriters = repository.save(mapper.map(blogWritersDTO, BlogWriters.class));
        return mapper.map(blogWriters, BlogWritersDTO.class);
    }

    @Override
    public BlogWritersDTO update(Long id, BlogWritersDTO blogWritersDTO) {
        logger.info("Atualizando um escritor");

        Optional<BlogWriters> writerExists = repository.findById(id);

        if(writerExists.isEmpty()) throw new Error("Autor inexistente.");

        blogWritersDTO.setId(id);
        BlogWriters blogWriters = repository.save(mapper.map(blogWritersDTO, BlogWriters.class));
        return mapper.map(blogWriters, BlogWritersDTO.class);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deletando um escritor");

        var writer = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nenhum escritor com esse id encontrado"));

        repository.delete(writer);
    }
    
}
