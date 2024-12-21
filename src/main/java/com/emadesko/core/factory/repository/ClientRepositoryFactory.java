package com.emadesko.core.factory.repository;

import com.emadesko.core.services.YamlService;
import com.emadesko.enums.RepositoryType;
import com.emadesko.repositories.ClientRepository;
import com.emadesko.repositories.jpa.ClientRepositoryJpa;

public abstract class ClientRepositoryFactory {

    private static ClientRepository repository;

    public static ClientRepository getInstance(YamlService yamlService) {
        if (repository == null) {
            RepositoryType repositoryType = yamlService.getRepositoryType("client");
            switch (repositoryType) {
                case JPA:
                    repository = new ClientRepositoryJpa();
                    break;
                default:
                    break;
            }
        }
        return repository;
    }

}
