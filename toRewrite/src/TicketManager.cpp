#include "TicketManager.h"

bool TicketManager::addTicketToRepository(const TicketPtr_t& ticket) {
    try {
        if( ticket == nullptr ) throw "no ticket";
        tickets.add(ticket);
        return true;
    }catch( const char* msg) {
        std::cout << "Error: " << msg << std::endl;
        return false;
    }
}

bool TicketManager::removeTicketFromRepository(const TicketPtr_t& ticket) {
    try {
        if( ticket == nullptr ) throw "no ticket";
        tickets.remove(ticket);
        return true;
    }catch (const char* msg){
        std::cout << "Error: " << msg << std::endl;
        return false;
    }
}

TicketManager::~TicketManager() {}

TicketManager::TicketManager(const TicketRepository &tickets) : tickets(tickets) {}

std::string TicketManager::report() {
    return tickets.report();
}

TicketRepository TicketManager::getTickets(){
    return tickets;
}


