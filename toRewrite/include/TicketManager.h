#ifndef CINEMA_TICKETMANAGER_H
#define CINEMA_TICKETMANAGER_H


#include "TicketRepository.h"
#include "iostream"

class TicketManager {
private:
    TicketRepository tickets;

public:
    explicit TicketManager(const TicketRepository &tickets);

    virtual ~TicketManager();

    bool addTicketToRepository(const TicketPtr_t& ticket);

    bool removeTicketFromRepository(const TicketPtr_t& ticket);

    TicketRepository getTickets();

    std::string report();
};


#endif
