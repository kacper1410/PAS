#ifndef CINEMA_PREMIUMCLIENT_H
#define CINEMA_PREMIUMCLIENT_H


#include "Client.h"

class PremiumClient: public Client {
public:
    PremiumClient(const string &name, int age);

    virtual ~PremiumClient();

    std::string report() override;
};


#endif //CINEMA_PREMIUMCLIENT_H
