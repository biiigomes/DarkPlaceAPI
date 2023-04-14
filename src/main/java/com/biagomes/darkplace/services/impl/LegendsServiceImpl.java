package com.biagomes.darkplace.services.impl;

import java.util.Optional;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.biagomes.darkplace.model.BlogWriters;
import com.biagomes.darkplace.model.Legends;
import com.biagomes.darkplace.model.request.LegendsRequestDTO;
import com.biagomes.darkplace.model.response.LegendsResponseDTO;
import com.biagomes.darkplace.repository.BlogWritersRepository;
import com.biagomes.darkplace.repository.LegendsRepository;
import com.biagomes.darkplace.services.LegendsService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LegendsServiceImpl implements LegendsService {

    private final Logger logger = Logger.getLogger(LegendsService.class.getName());
    
    @Autowired
    private LegendsRepository repository;

    @Autowired
    private BlogWritersRepository writersRepository;
    
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public Page<LegendsResponseDTO> getAll(int page, int size, String sort) {
        logger.info("Encontrando todas as lendas");

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort);
        Page<Legends> result = repository.findAll(pageable);
        return result.map(obj -> mapper.map(obj, LegendsResponseDTO.class));
    }

    @Override
    public LegendsResponseDTO getById(Long id) {
        logger.info("Encontrando uma lenda");

        Legends legends = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Lenda com esse id não existe."));

        return mapper.map(legends, LegendsResponseDTO.class);
    }

    @Override
    public Page<LegendsResponseDTO> getAllByWriter(int page, int size, String sort, Long id) {
        logger.info("Procurando lendas por autor");
        
        Pageable pageabe = PageRequest.of(page, size, Sort.Direction.ASC, sort);
        Page<Legends> legendPage = repository.findLegendsByWriter(pageabe, id); 

        return legendPage.map(obj -> mapper.map(obj, LegendsResponseDTO.class));
    }

    @Override
    public LegendsResponseDTO create(LegendsRequestDTO legends) {
        logger.info("Criando uma lenda");

        Optional<Legends> legendsOptional = repository.findLegendsByTitle(legends.getTitle());
        if (legendsOptional.isPresent()) throw new Error("Lenda já existe");

        Optional<BlogWriters> writersOptional = writersRepository.findById(legends.getWriter());
        if(writersOptional.isEmpty()) throw new Error("Escritor não encontrado");

        Legends mappedLegends = mapper.map(legends, Legends.class);
        // mappedLegends.setWriter(writersOptional.get());

        Legends legendSaved = repository.save(mappedLegends);
        return mapper.map(legendSaved, LegendsResponseDTO.class);
    }

    @Override
    public LegendsResponseDTO update(Long id, LegendsRequestDTO legends) {
        logger.info("Atualizando uma lenda");

        Optional<Legends> legendsOptional = repository.findById(id);
        Optional<BlogWriters> writersOptional = writersRepository.findById(legends.getWriter());

        if(writersOptional.isEmpty()) throw new Error("Escritor não encontrado");

        if(legendsOptional.isEmpty()) throw new Error("Lenda com esse id não encontrado");

        Legends mappedLegends = mapper.map(legends, Legends.class);
        mappedLegends.setId(id);
        // mappedLegends.setWriter(writersOptional.get());

        Legends legendSaved = repository.save(mappedLegends);
        return mapper.map(legendSaved, LegendsResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deletando um escritor");

        var legends = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nenhum escritor com esse id encontrado"));
        
        repository.delete(legends);
    }
}
