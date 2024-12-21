package com.emadesko.repositories;

import java.util.List;

import com.emadesko.core.repository.Repository;
import com.emadesko.entities.Client;


public interface ClientRepository extends Repository<Client>{
    Client getClientByTelephone(String telephone);
    Client getClientBySurnom(String surnom);
    List <Client> getClientsByAccountStatus(boolean with);
}
