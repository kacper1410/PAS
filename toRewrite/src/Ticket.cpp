//
// Created by kamil on 1/3/2020.
//

#include "../include/Ticket.h"

#include <utility>

Ticket::Ticket(MoviePtr_t movie, float price, int seat) :movie(std::move(movie)), price(price), seat(seat) {
    takeSeat(seat);
}

Ticket::~Ticket() = default;

std::string Ticket::toString() {
    std::stringstream out;
    out << "Movie name: " << movie->getName() << std::endl << "Ticket price: " << this->getPrice() << std::endl
    << "Movie length: " << movie->getLength() << std::endl << "Seat: " << seat << std::endl
    << movie->getDate()->tm_mday << "." <<
    movie->getDate()->tm_mon + 1 << "." << movie->getDate()->tm_year + 1900
    << " " << movie->getDate()->tm_hour << ":" << movie->getDate()->tm_min << std::endl;
    return out.str();
}

const MoviePtr_t &Ticket::getMovie() const {
    return movie;
}

int Ticket::getSeat() const {
    return seat;
}

void Ticket::setPrice(float price) {
    this->price = price;
}

float Ticket::getPrice() const {
    return price;
}

void Ticket::takeSeat(int seat) {
    try {
        if( ! movie->takeSeat(seat) ) throw "trying to buy ticket for taken seat";
    }catch (const char* msg){
        std::cout << "Error: " << msg << std::endl;
    }
}



