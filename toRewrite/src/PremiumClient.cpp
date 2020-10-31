#include "PremiumClient.h"
#include "boost/uuid/uuid_io.hpp"

PremiumClient::PremiumClient(const string &name, int age) : Client(name, age, 20) {}

PremiumClient::~PremiumClient() {}

std::string PremiumClient::report() {
    std::stringstream out;
    out << "Client name: " << getName() << std::endl << "Client type: premium" << std::endl << "Client ID: " << getId() << this->clientTicketsReport() << std::endl;
    return out.str();
}