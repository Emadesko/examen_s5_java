package com.emadesko.repositories;

import java.util.List;

import com.emadesko.core.repository.Repository;
import com.emadesko.entities.Client;


public interface DetteMereRepository <T> extends Repository<T>{
    List<T> getDetteMereByClient(Client client);
}