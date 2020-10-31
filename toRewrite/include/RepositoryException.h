//
// Created by student on 03.02.2020.
//

#ifndef CINEMA_REPOSITORYEXCEPTION_H
#define CINEMA_REPOSITORYEXCEPTION_H
#include <stdexcept>

using namespace std;

class RepositoryException : public logic_error
{
public:
    explicit RepositoryException(const string&);
    static const string exceptionNullPtr;
    static const string exceptionExist;
};

#endif //CINEMA_REPOSITORYEXCEPTION_H
