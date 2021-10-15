package br.com.thianolima.news.assinante.controllers;

import br.com.thianolima.news.assinante.vos.AssinanteVO;
import br.com.thianolima.news.assinante.entities.AssinanteEntity;
import br.com.thianolima.news.assinante.services.AssinanteService;
import br.com.thianolima.news.assinante.templates.AssinanteEntityTemplate;
import br.com.thianolima.news.assinante.templates.AssinanteVOTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AssinanteController.class)
public class AssinanteControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    AssinanteService service;

    @Test
    public void deveInserirUmNovoAssinante() throws Exception {
        AssinanteVO vo = AssinanteVOTemplate.getInstance().getObjectValid();
        AssinanteEntity entity = AssinanteEntityTemplate.getInstance().getObjectValid();

        String json = new ObjectMapper().writeValueAsString(vo);

        BDDMockito.given(service.salvar(any(AssinanteEntity.class)))
                .willReturn(entity);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/assinantes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("nome").isNotEmpty())
                .andExpect(jsonPath("email").isNotEmpty());
    }

    @Test
    public void deveLancarExceptionAoInserirUmAssinanteSemEmail() throws Exception {
        AssinanteVO vo = AssinanteVOTemplate.getInstance().getObjectValid();
        vo.setEmail("");

        String json = new ObjectMapper().writeValueAsString(vo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/assinantes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].mensagem").isNotEmpty());
    }

    @Test
    public void deveLancarExceptionAoInserirUmAssinanteSemNome() throws Exception {
        AssinanteVO vo = AssinanteVOTemplate.getInstance().getObjectValid();
        vo.setNome("");

        String json = new ObjectMapper().writeValueAsString(vo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/assinantes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].mensagem").isNotEmpty());
    }

    @Test
    public void devePesquisarAssinantePorId() throws Exception {
        AssinanteEntity entity = AssinanteEntityTemplate.getInstance().getObjectValid();

        BDDMockito.given(service.pesquisarPorId(Mockito.anyLong()))
                .willReturn(entity);

        mvc.perform(get("/assinantes/{id}", 1l))
                .andDo(print())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("nome").isNotEmpty())
                .andExpect(jsonPath("email").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    public void deveLancarExceptionAoTentarPesquisarIdInvalido() throws Exception {
        AssinanteEntity entity = AssinanteEntityTemplate.getInstance().getObjectValid();

        BDDMockito.given(service.pesquisarPorId(anyLong())).
                willThrow(new EmptyResultDataAccessException(1));

        mvc.perform(get("/assinantes/{id}",1L))
                .andDo(print())
                .andExpect(jsonPath("mensagem").isNotEmpty())
                .andExpect(status().isNotFound());
    }
}
