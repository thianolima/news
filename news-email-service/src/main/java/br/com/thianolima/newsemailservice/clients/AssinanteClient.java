package br.com.thianolima.newsemailservice.clients;

import br.com.thianolima.newsemailservice.dtos.AssinanteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url="${feign.client.assinantes.url}", name="assinantes")
public interface AssinanteClient {

    @GetMapping
    public ResponseEntity<List<AssinanteDTO>> listar();
}
