package com.emadesko.core.factory.repository;

import com.emadesko.core.services.YamlService;
import com.emadesko.enums.RepositoryType;
import com.emadesko.repositories.DemandeRepository;
import com.emadesko.repositories.jpa.DemandeRepositoryJpa;

public abstract class DemandeRepositoryFactory {

    private static DemandeRepository repository;

    public static DemandeRepository getInstance(YamlService yamlService) {
        if (repository == null) {
            RepositoryType repositoryType = yamlService.getRepositoryType("demande");
            switch (repositoryType) {
                case JPA:
                    repository = new DemandeRepositoryJpa();
                    break;
                default:
                    break;
            }
        }
        return repository;
    }

}
