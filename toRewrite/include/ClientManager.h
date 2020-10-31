//
// Created by kamil on 12/31/2019.
//

#ifndef CINEMA_CLIENTMANAGER_H
#define CINEMA_CLIENTMANAGER_H

#include "Ticket.h"
#include "Client.h"
#include "ClientRepository.h"

class ClientManager {
private:
    ClientRepository clients;

public:
    explicit ClientManager(const ClientRepository &clients);

    virtual ~ClientManager();

    bool addClientToRepository(ClientPtr_t client);

    bool removeClientFromRepository(ClientPtr_t client);

    void addTicketToClient(ClientPtr_t client, TicketPtr_t ticket);

    void removeTicketFromClient(ClientPtr_t client, TicketPtr_t ticket);

    ClientRepository getClients();

    std::string report();
};


#endif //CINEMA_CLIENTMANAGER_H
