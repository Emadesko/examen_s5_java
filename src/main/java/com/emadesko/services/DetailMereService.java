package com.emadesko.services;

import java.util.List;

import com.emadesko.entities.DetteMere;
import com.emadesko.entities.Entite;
import com.emadesko.repositories.DetailMereRepository;

public class DetailMereService <T extends Entite> extends ServiceImpl<T>{
    
    private DetailMereRepository <T> detailMereRepository;

    public DetailMereService(DetailMereRepository <T> detailMereRepository) {
        super(detailMereRepository);
        this.detailMereRepository = detailMereRepository;
    }
    
    public List <T> getDetailMeresByClient(DetteMere detteMere){
        return this.detailMereRepository.getDetailMeresByDetteMere(detteMere);
    }


}
