//
// Created by kamil on 12/31/2019.
//

#ifndef CINEMA_REPOSITORY_H
#define CINEMA_REPOSITORY_H

#include "memory"
#include "vector"
#include "string"
#include "sstream"
#include "RepositoryException.h"

template <class T>
class Repository {
protected:
    std::vector< std::shared_ptr<T> > elements;

public:
    virtual void add(std::shared_ptr<T>);

    virtual void remove(std::shared_ptr<T>);

    virtual std::shared_ptr<T> getElement(const int position);

    virtual std::shared_ptr<T> getElement(std::shared_ptr<T>);

    virtual const int getSize() const;

    virtual std::string report() = 0;
};

template<class T>
void Repository<T>::add(std::shared_ptr<T> element) {
    if( element == nullptr ) throw RepositoryException(RepositoryException::exceptionNullPtr);
    else{
        for (auto x : elements ) {
            if( x == element ) throw RepositoryException(RepositoryException::exceptionExist);;
        }
        elements.push_back(element);
    }
}

template<class T>
void Repository<T>::remove(std::shared_ptr<T> element) {
    if( element == nullptr ) throw RepositoryException(RepositoryException::exceptionNullPtr);
    else {
        for(unsigned int i = 0; i < elements.size(); i++ ) {
            if( element == elements[i] ){
                elements.erase(elements.begin() + i);
            }
        }
    };
}

template<class T>
const int Repository<T>::getSize() const {
    return elements.size();
}

template<class T>
std::shared_ptr<T> Repository<T>::getElement(const int position) {
    return elements[position];
}

template<class T>
std::shared_ptr<T> Repository<T>::getElement(std::shared_ptr<T> element) {
    for( auto& x: elements ){
        if( element == x ) return x;
    }
    return nullptr;
}

#endif //CINEMA_REPOSITORY_H