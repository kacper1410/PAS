//
// Created by kamil on 1/2/2020.
//

#ifndef CINEMA_MOVIE_H
#define CINEMA_MOVIE_H

#include "string"
#include "iostream"
#include "ctime"
#include "memory"
#include "MovieRepository.h"

typedef std::shared_ptr<std::tm> DatePtr_t;

class Movie {
private:
    std::string name;
    int length;
    DatePtr_t date;
    int seatsNumber = 120;
    vector<bool> freeSeats;

public:
    Movie(std::string name, int length, int hour, int min, int day, int month);

    virtual ~Movie();

    std::string report();

    const std::string &getName() const;

    int getLength() const;

    const DatePtr_t &getDate() const;

    bool takeSeat(int seat);
};

typedef std::shared_ptr<Movie> MoviePtr_t;

#endif //CINEMA_MOVIE_H
