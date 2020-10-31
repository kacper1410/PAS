//
// Created by kamil on 1/4/2020.
//

#ifndef CINEMA_TICKETREPOSITORY_H
#define CINEMA_TICKETREPOSITORY_H


#include "Repository.h"

class Ticket;

typedef std::shared_ptr<Ticket> TicketPtr_t;

class TicketRepository: public Repository<Ticket> {

public:
    TicketRepository();

    virtual ~TicketRepository();

    std::string report() override;

};


#endif //CINEMA_TICKETREPOSITORY_H
