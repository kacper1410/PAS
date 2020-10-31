#include "NormalClient.h"
#include "boost/uuid/uuid_io.hpp"

NormalClient::NormalClient(const string &name, int age) : Client(name, age, 5) {}

NormalClient::~NormalClient() {}

std::string NormalClient::report() {
    std::stringstream out;
    out << "Client name: " << getName() << std::endl << "Client type: Normal" << std::endl << "Client ID: " << getId() << this->clientTicketsReport() << std::endl;
    return out.str();
}
