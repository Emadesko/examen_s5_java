package com.emadesko.core.factory.repository;

import com.emadesko.core.services.YamlService;
import com.emadesko.enums.RepositoryType;
import com.emadesko.repositories.DetailDemandeRepository;
import com.emadesko.repositories.jpa.DetailDemandeRepositoryJpa;

public abstract class DetailDemandeRepositoryFactory {

    private static DetailDemandeRepository repository;

    public static DetailDemandeRepository getInstance(YamlService yamlService) {
        if (repository == null) {
            RepositoryType repositoryType = yamlService.getRepositoryType("detailDemande");
            switch (repositoryType) {
                case JPA:
                    repository = new DetailDemandeRepositoryJpa();
                    break;

                default:
                    break;
            }
        }
        return repository;
    }

}
