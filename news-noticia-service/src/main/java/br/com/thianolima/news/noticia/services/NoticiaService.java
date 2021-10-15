package br.com.thianolima.news.noticia.services;

import br.com.thianolima.news.noticia.dtos.NoticiaDTO;
import br.com.thianolima.news.noticia.entities.NoticiaEntity;
import br.com.thianolima.news.noticia.repositories.NoticiaRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NoticiaService {

    private RabbitTemplate rabbitTemplate;
    private Exchange exchangeNoticia;
    private NoticiaRepository repository;

    public NoticiaEntity salvar(NoticiaEntity noticia) {
        return repository.save(noticia);
    }

    public NoticiaEntity pesquisarPorId(Long id) {
        return repository.pesquisarPorId(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(0));
    }

    public void publicar(NoticiaDTO noticia){
        rabbitTemplate.convertAndSend(exchangeNoticia.getName(), "", noticia);
    }
}
