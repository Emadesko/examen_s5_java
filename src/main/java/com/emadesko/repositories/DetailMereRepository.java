package com.emadesko.repositories;

import java.util.List;

import com.emadesko.core.repository.Repository;
import com.emadesko.entities.Article;
import com.emadesko.entities.DetteMere;


public interface DetailMereRepository<T> extends Repository<T>{
    List <T> getDetailMeresByDetteMere(DetteMere detteMere);
    List <T> getDetailMeresByArticle(Article article);
}