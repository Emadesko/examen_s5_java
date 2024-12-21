package com.emadesko.core.factory.repository;

import com.emadesko.core.services.YamlService;
import com.emadesko.enums.RepositoryType;
import com.emadesko.repositories.ArticleRepository;
import com.emadesko.repositories.jpa.ArticleRepositoryJpa;

public abstract class ArticleRepositoryFactory {

    private static ArticleRepository repository;

    public static ArticleRepository getInstance(YamlService yamlService) {
        if (repository == null) {
            RepositoryType repositoryType = yamlService.getRepositoryType("article");
            switch (repositoryType) {
                case JPA:
                    repository = new ArticleRepositoryJpa();
                    break;
                default:
                    break;
            }
        }
        return repository;
    }

}
