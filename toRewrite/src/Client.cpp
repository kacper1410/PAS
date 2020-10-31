//
// Created by kamil on 12/31/2019.
//

#include "../include/Client.h"

#include <utility>
#include "../include/TicketRepository.h"
#include <boost/uuid/uuid.hpp>
#include <boost/uuid/uuid_io.hpp>
#include <boost/uuid/random_generator.hpp>

Client::Client(std::string name, int age, int maxTickets) : name(name), age(age), maxTickets(maxTickets) {
    boost::uuids::random_generator generator;
    id = generator();
}

Client::~Client() = default;

std::string Client::report() {
    std::stringstream out;
    out << "Client name: " << getName() << std::endl << "Client ID: " << getId() << this->clientTicketsReport() << std::endl;
    return out.str();
}

const std::string &Client::getName() const {
    return name;
}

boost::uuids::uuid Client::getId() const {
    return id;
}

void Client::addTicket(const TicketPtr_t& ticket) {
    try {
        if( this->getClientTickets().size() >= this->getMaxTickets() ) throw "client has already maximum amount of tickets.";
        if( ticket == nullptr ) throw "no ticket";
        clientTickets.push_back(ticket);
    } catch( const char* msg){
        std::cout << "Error: " << msg << std::endl;
    }
}

std::string Client::clientTicketsReport() {
    std::stringstream out;
    out << "\nTickets: " << std::endl;
    if (clientTickets.empty()) out << "This client has no tickets" << endl;
    for (auto & i : clientTickets ) {
        out << i->toString();
    }
    out << std::endl;
    return out.str();
}

int Client::getMaxTickets() const {
    return maxTickets;
}

int Client::getAge() const {
    return age;
}

vector<TicketPtr_t> &Client::getClientTickets() {
    return clientTickets;
}

const TicketPtr_t Client::getClientTicket(TicketPtr_t ticket) const {
    for( auto &x: clientTickets ){
        if( x == ticket ) return x;
    }
    return nullptr;
}

bool Client::removeTicket(const TicketPtr_t &ticket) {
    for( int i = 0; i < clientTickets.size(); i++ ){
        if(clientTickets[i] == ticket ){
            clientTickets.erase(clientTickets.begin() + i );
            return true;
        }
    }
    return false;
}
