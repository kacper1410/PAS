//
// Created by kamil on 1/4/2020.
//

#include "../include/TicketRepository.h"
#include "../include/Ticket.h"

TicketRepository::TicketRepository() = default;

TicketRepository::~TicketRepository() = default;

std::string TicketRepository::report() {
    std::stringstream out;
    if (!elements.empty()) {
        out << "Tickets: ";
        for (auto & i : elements) {
            out << "\n" << i->toString();
        }
    }
    else out << "No tickets." << std::endl;
    return out.str();
}
