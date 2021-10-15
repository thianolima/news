package br.com.thianolima.news.noticia.cotrollers;

import br.com.thianolima.news.noticia.dtos.NoticiaDTO;
import br.com.thianolima.news.noticia.entities.NoticiaEntity;
import br.com.thianolima.news.noticia.services.NoticiaService;
import br.com.thianolima.news.noticia.vos.NoticiaVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    NoticiaService service;
    ModelMapper modelMapper;

    @PostMapping
    @ApiOperation("Inserir")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Noticia cadastrada.")
    })
    public ResponseEntity<NoticiaDTO> inserir(@Valid @RequestBody NoticiaVO vo, UriComponentsBuilder uriBuilder){
        NoticiaEntity noticia = service.salvar(modelMapper.map(vo, NoticiaEntity.class));
        NoticiaDTO dto = modelMapper.map(noticia, NoticiaDTO.class);
        URI uri = uriBuilder.path("/noticias/{id}").buildAndExpand(dto.getId()).toUri();
        service.publicar(dto);
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("{id}")
    @ApiOperation("Pesquisar")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Noticia encontrado.")
    })
    public ResponseEntity<NoticiaDTO> pesquisarPorId(@PathVariable Long id){
        NoticiaEntity assinante = service.pesquisarPorId(id);
        NoticiaDTO dto = modelMapper.map(assinante, NoticiaDTO.class);
        return ResponseEntity.ok(dto);
    }
}
