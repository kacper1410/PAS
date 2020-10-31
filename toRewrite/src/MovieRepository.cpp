//
// Created by kamil on 1/3/2020.
//

#include "../include/MovieRepository.h"
#include "../include/Movie.h"

MovieRepository::MovieRepository() = default;

MovieRepository::~MovieRepository() = default;

std::string MovieRepository::report() {
    std::stringstream out;
    out << "Films: " << std::endl;
    for (auto & i : elements ) {
        out << i->report();
    }
    out << std::endl;
    return out.str();
}
