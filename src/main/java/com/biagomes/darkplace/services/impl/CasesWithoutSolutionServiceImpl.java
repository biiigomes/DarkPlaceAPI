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
import com.biagomes.darkplace.model.request.CasesWithouSolutionRequestDTO;
import com.biagomes.darkplace.model.response.CasesWithoutSolutionResponseDTO;
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
    public Page<CasesWithoutSolutionResponseDTO> getAll(int page, int size, String sort) {
        logger.info("Encontrando todos os casos");

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort);
        Page<CasesWithoutSolution> result = repository.findAll(pageable);
        return result.map(obj -> mapper.map(obj, CasesWithoutSolutionResponseDTO.class));
    }

    @Override
    public CasesWithoutSolutionResponseDTO getById(Long id) {
        logger.info("Encontrando o caso");

        CasesWithoutSolution casesWithoutSolution = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Caso com esse id não existe."));

        return mapper.map(casesWithoutSolution, CasesWithoutSolutionResponseDTO.class);
    }

    @Override
    public Page<CasesWithoutSolutionResponseDTO> getAllByWriter(int page, int size, String sort, Long id) {
       logger.info("Procurando por casos por autor");

       Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort);
       Page<CasesWithoutSolution> casesPage = repository.findCasesWithoutSolutionByWriter(pageable, id);

       return casesPage.map(obj -> mapper.map(obj, CasesWithoutSolutionResponseDTO.class));

    }

    @Override
    public CasesWithoutSolutionResponseDTO create(CasesWithouSolutionRequestDTO cases) {
        logger.info("Criando um caso");

        Optional<CasesWithoutSolution> casesOptional = repository.findCasesWithoutSolutionByTitle(cases.getTitle());
        if(casesOptional.isPresent()) throw new Error("Caso já existe");

        Optional<BlogWriters> writerOptional = writersRepository.findById(cases.getWriter());
        if(writerOptional.isEmpty()) throw new Error("Escritor não encontrado");

        CasesWithoutSolution casesWSolution = mapper.map(cases, CasesWithoutSolution.class);
        casesWSolution.setWriter(writerOptional.get());

        CasesWithoutSolution casesWSolutionSaved = repository.save(casesWSolution);
        return mapper.map(casesWSolutionSaved, CasesWithoutSolutionResponseDTO.class);
    }

    @Override
    public CasesWithoutSolutionResponseDTO update(Long id, CasesWithouSolutionRequestDTO cases) {
        logger.info("Atualizando um caso");

        Optional<CasesWithoutSolution> casesOptional = repository.findById(id);
        if(casesOptional.isEmpty()) throw new Error("Caso com esse id não encontrado");

        CasesWithoutSolution casesWSolution = mapper.map(cases, CasesWithoutSolution.class);
        casesWSolution.setId(id);
        // casesWSolution.setBlog_writers(writersOpt.get());

        CasesWithoutSolution casesSaved = repository.save(casesWSolution);
        return mapper.map(casesSaved, CasesWithoutSolutionResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deletando um caso");

        var cases = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nenhum caso com esse id foi encontrado"));

        repository.delete(cases);
    }
}
