package com.biagomes.darkplace.services.impl;

import java.util.Optional;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.biagomes.darkplace.model.BlogWriters;
import com.biagomes.darkplace.model.CasesWithoutSolution;
import com.biagomes.darkplace.model.DTO.CasesWithoutSolutionDTO;
import com.biagomes.darkplace.repository.BlogWritersRepository;
import com.biagomes.darkplace.repository.CasesWithoutSolutionRepository;
import com.biagomes.darkplace.services.CasesWithoutSolutionService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CasesWithoutSolutionServiceImpl implements CasesWithoutSolutionService {
    
    private final Logger logger = Logger.getLogger(CasesWithoutSolutionService.class.getName());

    @Autowired
    private CasesWithoutSolutionRepository repository;

    @Autowired
    private BlogWritersRepository writersRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<CasesWithoutSolutionDTO> getAll(int page, int size, String sort) {
        logger.info("Encontrando todos os casos");

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort);
        Page<CasesWithoutSolution> result = repository.findAll(pageable);
        return result.map(obj -> mapper.map(obj, CasesWithoutSolutionDTO.class));
    }

    @Override
    public CasesWithoutSolutionDTO getById(Long id) {
        logger.info("Encontrando o caso");

        CasesWithoutSolution casesWithoutSolution = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Caso com esse id não existe."));

        return mapper.map(casesWithoutSolution, CasesWithoutSolutionDTO.class);
    }

    @Override
    public CasesWithoutSolutionDTO create(CasesWithoutSolutionDTO casesWithoutSolutionDTO) {
        logger.info("Criando uma lenda");

        Optional<CasesWithoutSolution> casesOptional = repository.findCasesWithoutSolutionByTitle(casesWithoutSolutionDTO.getTitle());
        Optional<BlogWriters> writersOpt = writersRepository.findWriterByNameFullnameAndUsername(
            casesWithoutSolutionDTO.getBlogWritersDTO().getName(), 
            casesWithoutSolutionDTO.getBlogWritersDTO().getFullname(), 
            casesWithoutSolutionDTO.getBlogWritersDTO().getUsername());
        
            if(writersOpt.isEmpty()) throw new Error("Escritor não encontrado");

            if(casesOptional.isPresent()) throw new Error("Caso já existe");

            CasesWithoutSolution casesWithoutSolution = mapper.map(casesWithoutSolutionDTO, CasesWithoutSolution.class);
            casesWithoutSolution.setBlog_writers(writersOpt.get());

            CasesWithoutSolution casesWithoutSolutionSaved = repository.save(casesWithoutSolution);
            return mapper.map(casesWithoutSolutionSaved, CasesWithoutSolutionDTO.class);
    }

    @Override
    public CasesWithoutSolutionDTO update(CasesWithoutSolutionDTO casesWithoutSolutionDTO, Long id) {
        logger.info("Atualizando um caso");

        Optional<CasesWithoutSolution> casesOptional = repository.findById(id);
        Optional<BlogWriters> writersOptional = writersRepository.findById(casesWithoutSolutionDTO.getBlogWritersDTO().getId());

        if(writersOptional.isEmpty()) throw new Error("Escritor com esse id não encontrado");

        if(casesOptional.isEmpty()) throw new Error("Caso com esse id não encontrado");

        CasesWithoutSolution cases = mapper.map(casesWithoutSolutionDTO, CasesWithoutSolution.class);
        cases.setId(id);
        cases.setBlog_writers(writersOptional.get());

        CasesWithoutSolution casesSaved = repository.save(cases);
        return mapper.map(casesSaved, CasesWithoutSolutionDTO.class);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deletando um caso");

        var cases = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nenhum caso com esse id foi encontrado"));

        repository.delete(cases);
    }

    @Override
    public Page<CasesWithoutSolutionDTO> getAllByWriter(int page, int size, String sort, Long id) {
       logger.info("Procurando por casos por autor");

       Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort);
       Page<CasesWithoutSolution> casesPage = repository.findCasesWithoutSolutionByWriter(pageable, id);

       return casesPage.map(obj -> mapper.map(obj, CasesWithoutSolutionDTO.class));

    }

    
    
}
