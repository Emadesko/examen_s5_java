package com.emadesko.core.services;

import java.util.Map;

import com.emadesko.enums.RepositoryType;

public interface YamlService {
    Map<String, Object> load();
    Map<String, Object> load(String path);
    RepositoryType getRepositoryType(String entite);
}
