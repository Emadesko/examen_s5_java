package com.emadesko.services;

import java.util.List;

import com.emadesko.entities.Client;
import com.emadesko.entities.Entite;
import com.emadesko.repositories.DetteMereRepository;

public class DetteMereService <T extends Entite> extends ServiceImpl<T>{
    
    private DetteMereRepository <T> detteMereRepository;

    public DetteMereService(DetteMereRepository <T> detteMereRepository) {
        super(detteMereRepository);
        this.detteMereRepository = detteMereRepository;
    }
    
    public List <T> getDetteMeresByClient(Client client){
        return this.detteMereRepository.getDetteMereByClient(client);
    }


}
