#include "MovieManager.h"

MovieManager::MovieManager(MovieRepository &movies) : movies(movies) {}

MovieManager::~MovieManager() {}

bool MovieManager::addMovieToRepository(MoviePtr_t movie) {
    try {
        if( movie == nullptr ) throw "no movie";
        movies.add(movie);
        return true;
    }catch( const char* msg) {
        std::cout << "Error: " << msg << std::endl;
        return false;
    }
}

bool MovieManager::removeMovieFromRepository(MoviePtr_t movie) {
    try {
        if( movie == nullptr ) throw "no movie";
        movies.remove(movie);
        return true;
    }catch (const char* msg){
        std::cout << "Error: " << msg << std::endl;
        return false;
    }
}

std::string MovieManager::report() {
    return movies.report();
}

MovieRepository MovieManager::getMovies() {
    return movies;
}
