#include <boost/test/unit_test.hpp>
#include <MovieManager.h>
#include "Movie.h"

BOOST_AUTO_TEST_SUITE(MovieTest)

    MovieRepository movies;
    MovieManager movieManager(movies);
    MoviePtr_t movie = std::make_shared<Movie>("tytul1", 160, 1,2,3,4);

BOOST_AUTO_TEST_CASE(MovieConstructor){
    MoviePtr_t movie1 = std::make_shared<Movie>("tytul1", 160, 1,2,3,4);
    MoviePtr_t movie2 = std::make_shared<Movie>("tytul2", 180, 20,2,3,4);
    BOOST_CHECK_EQUAL(movie1->getName(), "tytul1");
    BOOST_CHECK_EQUAL(movie2->getName(), "tytul2");
    BOOST_CHECK_EQUAL(movie1->getLength(), 160);
    BOOST_CHECK_EQUAL(movie2->getLength(), 180);
}

BOOST_AUTO_TEST_CASE(AddMovieToRepository){
    movieManager.addMovieToRepository(movie);
    BOOST_CHECK_EQUAL(movieManager.getMovies().getSize(), 1);
    BOOST_CHECK_EQUAL(movieManager.getMovies().getElement(movie), movie);
    BOOST_CHECK_EQUAL(movieManager.getMovies().getElement(0), movie);
}

BOOST_AUTO_TEST_CASE(RemoveMovieFromRepository){
    movieManager.removeMovieFromRepository(movie);
    BOOST_CHECK_EQUAL(movieManager.getMovies().getSize(), 0);
    BOOST_CHECK_EQUAL(movieManager.getMovies().getElement(movie), nullptr);
}

BOOST_AUTO_TEST_SUITE_END()