//
// Created by kamil on 1/3/2020.
//

#ifndef CINEMA_MOVIEREPOSITORY_H
#define CINEMA_MOVIEREPOSITORY_H

#include "Repository.h"

class Movie;

typedef std::shared_ptr<Movie> MoviePtr_t;

class MovieRepository : public Repository<Movie> {
public:
    MovieRepository();

    virtual ~MovieRepository();

    std::string report() override;

};


#endif //CINEMA_MOVIEREPOSITORY_H
