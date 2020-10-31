//
// Created by kamil on 12/31/2019.
//

#ifndef CINEMA_CLIENT_H
#define CINEMA_CLIENT_H

#include "string"
#include "sstream"
#include "memory"
#include "TicketRepository.h"
#include "ClientManager.h"
#include <boost/uuid/uuid.hpp>

class TicketRepository;

class Client {
private:
    std::string name;
    boost::uuids::uuid id;
    std::vector<TicketPtr_t> clientTickets;
    int age;
    int maxTickets;

public:
    Client(std::string name, int age, int maxTickets);

    virtual ~Client();

    virtual std::string report();

    const std::string &getName() const;

    boost::uuids::uuid getId() const;

    void addTicket(const TicketPtr_t& ticket);

    bool removeTicket(const TicketPtr_t& ticket);

    std::string clientTicketsReport();

    int getMaxTickets() const;

    const TicketPtr_t getClientTicket(TicketPtr_t ticket) const;

    vector<TicketPtr_t> &getClientTickets();

    int getAge() const;
};


#endif //CINEMA_CLIENT_H
