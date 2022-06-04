package com.api.socialnetwork.repositories;

import com.api.socialnetwork.models.Scrap;
import com.api.socialnetwork.models.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@DisplayName("Testes do repositório de scrap")
class ScrapRepositoryTest {
    @Autowired
    private ScrapRepository scrapRepository;

    @Test
    @DisplayName("Salvar persiste scrap se OK")
    void salvar_PersistirScrap(){
        Scrap scrap = criarScrap();
        scrap.setData(LocalDateTime.now());
        scrap.setMensagem("Meu nome é Cristiano Ronaldo e eu uso Clear Men!");
        scrap.setUsuario(criarUsuario());
        Scrap scrapSalvo = this.scrapRepository.save(scrap);
        Assertions.assertThat(scrapSalvo).isEqualTo(scrap);
    }

    @Test
    @DisplayName("Deleta scrap se OK")
    void delete_ApagaScrap(){
        Scrap scrap = new Scrap();
        scrap.setData(LocalDateTime.now());
        scrap.setUsuario(criarUsuario());
        scrap.setMensagem("Ser ou não ser, eis a questão.");
        Scrap scrapSalvo = this.scrapRepository.save(scrap);
        this.scrapRepository.delete(scrapSalvo);
        Optional<Scrap> scrapOptional = this.scrapRepository.findById(scrapSalvo.getId());
        Assertions.assertThat(scrapOptional).isEmpty();
    }

    @Test
    @DisplayName("Acha scrap por Id se OK")
    void find_ListaScrapPorId(){
        Scrap scrap = new Scrap();
        scrap.setData(LocalDateTime.now());
        scrap.setUsuario(criarUsuario());
        scrap.setMensagem("Seu sucesso só depende de você!");
        Scrap scrapSalvo = this.scrapRepository.save(scrap);
        Optional<Scrap> scrapOptional = this.scrapRepository.findById(scrapSalvo.getId());
        Assertions.assertThat(scrapOptional).isPresent();
        Assertions.assertThat(scrapOptional.get().getId()).isEqualTo(scrapSalvo.getId());
    }

    private Scrap criarScrap(){
        return new Scrap();
    }

    private Usuario criarUsuario(){
        return new Usuario("Cristiano Ronaldo","cristiano.ronaldo@gmail.com");
    }
}