package br.com.thianolima.news.assinante.controllers;

import br.com.thianolima.news.assinante.entities.AssinanteEntity;
import br.com.thianolima.news.assinante.services.AssinanteService;
import br.com.thianolima.news.assinante.vos.AssinanteVO;
import br.com.thianolima.news.assinante.dtos.AssinanteDTO;
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
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/assinantes")
public class AssinanteController {

    private AssinanteService service;
    private ModelMapper modelMapper;

    @PostMapping
    @ApiOperation("Inserir")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Assinante cadastrado.")
    })
    public ResponseEntity<AssinanteDTO> inserir(@Valid @RequestBody AssinanteVO vo, UriComponentsBuilder uriBuilder){
        AssinanteEntity assinante = service.salvar(modelMapper.map(vo, AssinanteEntity.class));
        AssinanteDTO dto = modelMapper.map(assinante, AssinanteDTO.class);
        URI uri = uriBuilder.path("/assinantes/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/maximo")
    @ApiOperation("Retorna a quantidade maxima de assaintes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Quantidade maxima configurada.")
    })
    public ResponseEntity<?> maximoAssinantes(){
        return ResponseEntity.ok(service.getQuantidadeMaxima());
    }

    @GetMapping("{id}")
    @ApiOperation("Pesquisar")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Assinante encontrado.")
    })
    public ResponseEntity<AssinanteDTO> listar(@PathVariable Long id){
        AssinanteEntity assinante = service.pesquisarPorId(id);
        AssinanteDTO dto = modelMapper.map(assinante, AssinanteDTO.class);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @ApiOperation("Listar")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Assinante encontrado.")
    })
    public ResponseEntity<List<AssinanteDTO>> listar(){
        List<AssinanteEntity> assinantes = service.listar();
        if(assinantes == null || assinantes.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<AssinanteDTO> dtos = assinantes.stream()
                .map(assinante -> modelMapper.map(assinante, AssinanteDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}
