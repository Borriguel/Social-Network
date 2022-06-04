package com.api.socialnetwork.repositories;

import com.api.socialnetwork.models.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScrapRepository extends JpaRepository<Scrap, UUID> {
    List<Scrap> findByUsuarioId(UUID id);
}
