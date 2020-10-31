//
// Created by student on 03.02.2020.
//

#include "RepositoryException.h"

RepositoryException::RepositoryException(const string &arg) : logic_error(arg)
{}

const string RepositoryException::exceptionNullPtr = "INVALID (NULL)";
const string RepositoryException::exceptionExist = "THIS ALREADY EXISTS IN REPOSITORY";
