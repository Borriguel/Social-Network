package com.api.socialnetwork.services;

import com.api.socialnetwork.models.Scrap;
import com.api.socialnetwork.repositories.ScrapRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScrapService {

    final
    ScrapRepository scrapRepository;

    public ScrapService(ScrapRepository scrapRepository) {
        this.scrapRepository = scrapRepository;
    }

    @Transactional
    public Scrap salvar(Scrap scrap){
        return scrapRepository.save(scrap);
    }

    public Page<Scrap> listarScraps(Pageable page){
        return scrapRepository.findAll(page);
    }

    @Transactional
    public void deletar(Scrap scrap){
        scrapRepository.delete(scrap);
    }

    public Optional<Scrap> acharScrapPorId(UUID id){
        return scrapRepository.findById(id);
    }

    public List<Scrap> acharScrapPorUsuario(UUID id){
        return scrapRepository.findByUsuarioId(id);
    }
}
