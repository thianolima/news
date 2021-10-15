package br.com.thianolima.newsemailservice.consumers;

import br.com.thianolima.newsemailservice.clients.AssinanteClient;
import br.com.thianolima.newsemailservice.dtos.AssinanteDTO;
import br.com.thianolima.newsemailservice.dtos.NoticiaDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@RefreshScope
@AllArgsConstructor
@Component
public class EmailConsumer {

    private JavaMailSender emailSender;
    private AssinanteClient assinanteClient;
    private CircuitBreakerFactory circuitBreakerFactory;

    @RabbitListener(queues = "${spring.rabbitmq.queue.email}")
    public void listener(@Payload NoticiaDTO noticia){
        buscarAssinantes().forEach(assinante -> enviarEmail(assinante, noticia));
    }

    private List<AssinanteDTO> buscarAssinantes(){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitBreaker.run(() -> getAssinantes(), throwable -> fallback());
    }

    private void enviarEmail(AssinanteDTO assinante, NoticiaDTO noticia){
        /*
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(assinante.getEmail());
        mensagem.setSubject(noticia.getTitulo());
        mensagem.setText(noticia.getTexto());
        emailSender.send(mensagem);
        */
    }

    private List<AssinanteDTO> getAssinantes(){
        List<AssinanteDTO> assinantes = assinanteClient.listar().getBody();
        System.out.println("Executando: envio de email");
        return  assinantes;
    }

    private List<AssinanteDTO> fallback(){
        AssinanteDTO dto = new AssinanteDTO();
        dto.setEmail("thianolima@hotmail.com");
        dto.setNome("thiano");

        System.out.println("Executando: fallback");

        return Arrays.asList(dto);
    }
}
