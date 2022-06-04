package com.api.socialnetwork.controllers;

import com.api.socialnetwork.dtos.ScrapDto;
import com.api.socialnetwork.exception.BadRequestException;
import com.api.socialnetwork.models.Scrap;
import com.api.socialnetwork.models.Usuario;
import com.api.socialnetwork.services.ScrapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/scrap")
public class ScrapController {

    final
    ScrapService scrapService;

    public ScrapController(ScrapService scrapService) {
        this.scrapService = scrapService;
    }

    @PostMapping("/usuario/{id}")
    @Operation(summary = "Posta um scrap passando um id(UUID) de um usuário como parâmetro.")
    @Tag(name = "Scrap")
    public ResponseEntity<Scrap> postarScrap(@PathVariable Usuario id, @RequestBody @Valid ScrapDto scrapDto){
        var scrap = new Scrap();
        BeanUtils.copyProperties(scrapDto, scrap);
        scrap.setUsuario(id);
        scrap.setData(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(scrapService.salvar(scrap));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista um scrap passando seu id(UUID) como parâmetro.")
    @Tag(name = "Scrap")
    public ResponseEntity<Scrap> listarScrapPorId(@PathVariable(value = "id") UUID id){
        Optional<Scrap> scrapOptional = scrapService.acharScrapPorId(id);
        if(scrapOptional.isEmpty()){
            throw new BadRequestException("Erro: Id inválido.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(scrapOptional.get());
    }

    @GetMapping
    @Operation(summary = "Lista todos os scraps de forma paginada contendo 15 scraps por página ordenados pela data.")
    @Tag(name = "Scrap")
    public ResponseEntity<Page<Scrap>> listarScraps(@PageableDefault(size = 15, sort = "data")Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(scrapService.listarScraps(page));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um scrap passando seu id(UUID) como parâmetro.")
    @Tag(name = "Scrap")
    public ResponseEntity<Void> deletarScrapPorId(@PathVariable(value = "id") UUID id){
        Optional<Scrap> scrapOptional = scrapService.acharScrapPorId(id);
        if(scrapOptional.isEmpty()){
            throw new BadRequestException("Erro: Id inválido.");
        }
        scrapService.deletar(scrapOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/usuario/{id}")
    @Operation(summary = "Lista todos os scraps de um usuário de forma paginada passando seu id(UUID) como parâmetro.")
    @Tag(name = "Scrap")
    public ResponseEntity<Page<Scrap>> listarScrapsPorUsuarioId(@PageableDefault(size = 15, sort = "data") @PathVariable UUID id, Pageable page){
        List<Scrap> scraps = scrapService.acharScrapPorUsuario(id);
        Page<Scrap> pages = new PageImpl<>(scraps, page, scraps.size());
        return ResponseEntity.status(HttpStatus.OK).body(pages);
    }
}
