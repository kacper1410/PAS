//
// Created by kamil on 1/3/2020.
//

#ifndef CINEMA_TICKET_H
#define CINEMA_TICKET_H

#include "memory"
#include "string"
#include "sstream"
#include "ctime"
#include "Movie.h"

class Movie;

typedef std::shared_ptr<Movie> MoviePtr_t;

class Ticket {
private:
    MoviePtr_t movie;
    int seat;
    float price;

public:
    Ticket(MoviePtr_t movie, float price, int seat);

    void takeSeat(int seat);

    virtual ~Ticket();

    std::string toString();

    const MoviePtr_t &getMovie() const;

    float getPrice() const;

    void setPrice(float price);

    int getSeat() const;
};


#endif //CINEMA_TICKET_H
