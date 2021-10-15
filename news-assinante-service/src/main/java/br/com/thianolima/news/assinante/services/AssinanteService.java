package br.com.thianolima.news.assinante.services;

import br.com.thianolima.news.assinante.entities.AssinanteEntity;
import br.com.thianolima.news.assinante.exceptions.QuantidadeMaximaAssinantesException;
import br.com.thianolima.news.assinante.repositories.AssinanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RefreshScope
@Service
public class AssinanteService {

    AssinanteRepository repository;

    @Value("${assinantes.maximo:1}")
    Integer quantidadeMaxima;

    @Autowired
    AssinanteService(AssinanteRepository repository){
        this.repository = repository;
    }

    @Transactional
    public AssinanteEntity salvar(AssinanteEntity assinante) throws QuantidadeMaximaAssinantesException {
        if(repository.count() < quantidadeMaxima){
            return repository.save(assinante);
        }
        throw new QuantidadeMaximaAssinantesException(quantidadeMaxima.toString());
    }

    public AssinanteEntity pesquisarPorId(Long id){
        return repository.pesquisarPorId(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(0));
    }

    public Integer getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public List<AssinanteEntity> listar() {
        return repository.listarAtivos();
    }
}
