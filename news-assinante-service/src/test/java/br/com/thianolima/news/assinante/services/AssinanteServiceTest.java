package br.com.thianolima.news.assinante.services;

import br.com.thianolima.news.assinante.entities.AssinanteEntity;
import br.com.thianolima.news.assinante.repositories.AssinanteRepository;
import br.com.thianolima.news.assinante.templates.AssinanteEntityTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AssinanteServiceTest {

    AssinanteService service;

    @MockBean
    AssinanteRepository repository;

    @BeforeEach
    public void setup(){
        this.service = new AssinanteService(repository);
    }

    @Test
    public void deveSalvar(){
        AssinanteEntity assinanteMock = AssinanteEntityTemplate.getInstance().getObjectValid();

        when(repository.save(assinanteMock)).thenReturn(assinanteMock);

        AssinanteEntity assinanteSalvo = service.salvar(assinanteMock);

        assertThat(assinanteSalvo.getId()).isEqualTo(assinanteMock.getId());
        assertThat(assinanteSalvo.getNome()).isEqualTo(assinanteMock.getNome());
        assertThat(assinanteSalvo.getEmail()).isEqualTo(assinanteMock.getEmail());

        verify(repository,atLeastOnce()).save(assinanteMock);
    }

    @Test
    public void devePesquisarPorId(){
        AssinanteEntity assinanteMock = AssinanteEntityTemplate.getInstance().getObjectValid();

        when(repository.pesquisarPorId(anyLong())).thenReturn(Optional.of(assinanteMock));

        AssinanteEntity assinanteRecuperado = service.pesquisarPorId(1L);

        assertThat(assinanteRecuperado.getId()).isEqualTo(assinanteMock.getId());
        assertThat(assinanteRecuperado.getNome()).isEqualTo(assinanteMock.getNome());
        assertThat(assinanteRecuperado.getEmail()).isEqualTo(assinanteMock.getEmail());

        verify(repository,atLeastOnce()).pesquisarPorId(1L);
    }
}
