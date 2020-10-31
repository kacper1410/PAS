#ifndef CINEMA_MOVIEMANAGER_H
#define CINEMA_MOVIEMANAGER_H


#include "MovieRepository.h"
#include "iostream"

class MovieManager {
    MovieRepository movies;
public:
    explicit MovieManager( MovieRepository &movies);

    virtual ~MovieManager();

    bool addMovieToRepository(MoviePtr_t movie);

    bool removeMovieFromRepository(MoviePtr_t movie);

    MovieRepository getMovies();

    std::string report();
};


#endif //CINEMA_MOVIEMANAGER_H
