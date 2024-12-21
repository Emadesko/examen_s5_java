package com.emadesko.repositories.jpa;

import java.util.List;

import com.emadesko.core.repository.impl.RepositoryJpa;
import com.emadesko.entities.Article;
import com.emadesko.entities.DetteMere;
import com.emadesko.entities.Entite;
import com.emadesko.repositories.DetailMereRepository;

public class DetailMereRepositoryJpa <T extends Entite> extends RepositoryJpa<T> implements DetailMereRepository<T>{
    
    private String whithId;
    
    public DetailMereRepositoryJpa(Class<T> clazz, String whithId){
        super(clazz);
        this.whithId = whithId;
    }
    
    @Override
    public List<T> getDetailMeresByDetteMere(DetteMere detteMere) {
        return super.selectManyBy(whithId + " = :id", "id", detteMere.getId());
    }

    @Override
    public List<T> getDetailMeresByArticle(Article article) {
        return super.selectManyBy("article_id = :id", "id", article.getId());
    }

}
