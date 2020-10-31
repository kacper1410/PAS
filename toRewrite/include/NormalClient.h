#ifndef CINEMA_NORMALCLIENT_H
#define CINEMA_NORMALCLIENT_H


#include "Client.h"

class NormalClient: public Client {
public:
    NormalClient(const string &name, int age);

    virtual ~NormalClient();

    std::string report() override;
};


#endif //CINEMA_NORMALCLIENT_H
