//
// Created by kamil on 12/31/2019.
//

#include "../include/ClientRepository.h"
#include "../include/Client.h"

ClientRepository::ClientRepository() = default;

ClientRepository::~ClientRepository() = default;

std::string ClientRepository::report() {
    std::stringstream out;
    out << "Clients: " << std::endl;
    for (auto & i : elements ) {
        out << i->report();
    }
    out << std::endl;
    return out.str();
}