//
// Created by kamil on 12/31/2019.
//

#ifndef CINEMA_CLIENTREPOSITORY_H
#define CINEMA_CLIENTREPOSITORY_H

#include "Repository.h"

class Client;

typedef std::shared_ptr<Client> ClientPtr_t;

class ClientRepository : public Repository<Client> {
public:
    ClientRepository();

    virtual ~ClientRepository();

    std::string report() override;
};


#endif //CINEMA_CLIENTREPOSITORY_H
