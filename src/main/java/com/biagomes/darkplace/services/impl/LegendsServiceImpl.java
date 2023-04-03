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
import com.biagomes.darkplace.model.DTO.LegendsDTO;
import com.biagomes.darkplace.repository.BlogWritersRepository;
import com.biagomes.darkplace.repository.CasesWithoutSolutionRepository;
import com.biagomes.darkplace.repository.LegendsRepository;
import com.biagomes.darkplace.services.LegendsService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LegendsServiceImpl implements LegendsService {

    private Logger logger = Logger.getLogger(LegendsService.class.getName());
    
    @Autowired
    private LegendsRepository repository;

    @Autowired
    private BlogWritersRepository writersRepository;
    
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public Page<LegendsDTO> getAll(int page, int size, String sort) {
        logger.info("Encontrando todas as lendas");

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort);
        Page<Legends> result = repository.findAll(pageable);
        return result.map(obj -> mapper.map(obj, LegendsDTO.class));
    }

    @Override
    public LegendsDTO getById(Long id) {
        logger.info("Encontrando uma lenda");

        Legends legends = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Autor com esse id não existe."));

        return mapper.map(legends, LegendsDTO.class);
    }

    @Override
    public LegendsDTO create(LegendsDTO legendsDTO) {
        Optional<Legends> legendsOptional = repository.findLegendsByTitle(legendsDTO.getTitle());
        Optional<BlogWriters> writersOptional = writersRepository.findById(legendsDTO.getBlogWriters().getId()); 

        if(writersOptional.isEmpty()) throw new Error("Escritor não encontrado");

        if (legendsOptional.isPresent()) throw new Error("Lenda já existe no banco de dados");

        Legends legends = mapper.map(legendsDTO, Legends.class);
        legends.setBlogWriters(writersOptional.get());

        Legends legendSaved = repository.save(legends);
        return mapper.map(legendSaved, LegendsDTO.class);
    }

    @Override
    public LegendsDTO update(LegendsDTO legendsDTO, Long id) {
        Optional<Legends> legendsOptional = repository.findById(id);
        Optional<BlogWriters> writersOptional = writersRepository.findById(legendsDTO.getBlogWriters().getId());

        if(writersOptional.isEmpty()) throw new Error("Escritor não encontrado");

        if(legendsOptional.isPresent()) throw new Error("Lenda já existe no banco de dados");

        Legends legends = mapper.map(legendsDTO, Legends.class);
        legends.setId(id);
        legends.setBlogWriters(writersOptional.get());

        Legends legendSaved = repository.save(legends);
        return mapper.map(legendSaved, LegendsDTO.class);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deletando um escritor");

        var legends = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nenhum escritor com esse id encontrado"));
        
        repository.delete(legends);
    }

    @Override
    public Page<LegendsDTO> getAllByWriter(int page, int size, String sort, Long id) {
        Pageable pageabe = PageRequest.of(page, size, Sort.Direction.ASC, sort);
        Page<Legends> legendPage = repository.findLegendsByWriter(pageabe, id); 

        return legendPage.map(obj -> mapper.map(obj, LegendsDTO.class));
    }
    
}
