package br.com.thianolima.news.noticia.cotrollers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Api(value = "Pagina Inicial")
@RequestMapping("/")
public class HomeController {

    @ApiOperation(value = "Pagina inicial da API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Redireciona para o Swagger UI."),
            @ApiResponse(code = 500, message = "Um erro não esperado ocorreu.")
    })
    @GetMapping
    public ResponseEntity<Void> getHome() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/swagger-ui.html"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}