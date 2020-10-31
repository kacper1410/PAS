//
// Created by kamil on 12/31/2019.
//

#include <iostream>
#include "../include/ClientManager.h"


bool ClientManager::addClientToRepository(ClientPtr_t client) {
    try {
        if( client == nullptr ) throw "no client";
        clients.add(client);
        return true;
    }catch( const char* msg) {
        std::cout << "Error: " << msg << std::endl;
        return false;
    }
}

bool ClientManager::removeClientFromRepository(ClientPtr_t client) {
    try {
        if( client == nullptr ) throw "no client";
        clients.remove(client);
        return true;
    }catch (const char* msg){
        std::cout << "Error: " << msg << std::endl;
        return false;
    }

}

void ClientManager::addTicketToClient(ClientPtr_t client, TicketPtr_t ticket) {
    try {
        if( client == nullptr ) throw "no client";
        if( ticket == nullptr ) throw "no ticket";
        if( clients.getElement(client)->getClientTickets().size() >= clients.getElement(client)->getMaxTickets() ) throw "client has too many tickets cant add another";
        clients.getElement(client)->addTicket(ticket);
        if( client->getAge() >= 60 || client->getAge() <= 26 ) {
            clients.getElement(client)->getClientTicket(ticket)->setPrice(clients.getElement(client)->getClientTicket(ticket)->getPrice()-5);
        }

    }catch (const char* msg){
        std::cout << "Error: " << msg << std::endl;
    }

}

ClientManager::ClientManager(const ClientRepository &clients) : clients(clients) {}

ClientManager::~ClientManager() {

}

std::string ClientManager::report() {
    return clients.report();
}

ClientRepository ClientManager::getClients() {
    return clients;
}

void ClientManager::removeTicketFromClient(ClientPtr_t client, TicketPtr_t ticket) {
    try {
        if (client == nullptr) throw "no client";
        if (ticket == nullptr) throw "no ticket";
        if( clients.getElement(client)->removeTicket(ticket) ) return;
        throw "this client doesnt have this ticket";
    }catch (const char* msg){
        std::cout << "Error: " << msg << std::endl;
    }
}
