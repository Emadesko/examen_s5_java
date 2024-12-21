package com.emadesko.repositories.jpa;

import java.util.List;

import com.emadesko.core.repository.impl.RepositoryJpa;
import com.emadesko.entities.Client;
import com.emadesko.entities.Entite;
import com.emadesko.repositories.DetteMereRepository;

public class DetteMereRepositoryJpa <T extends Entite> extends RepositoryJpa<T> implements DetteMereRepository<T>{
    
    public DetteMereRepositoryJpa(Class<T> clazz){
        super(clazz);
    }

    @Override
    public List<T> getDetteMereByClient(Client client) {
        return super.selectManyBy("client_id = :id", "id", client.getId());
    }

}
