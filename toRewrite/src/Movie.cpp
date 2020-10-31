//
// Created by kamil on 1/2/2020.
//

#include "../include/Movie.h"
#include "iostream"

#include <utility>

Movie::Movie(std::string name, int length, int hour, int min, int day, int month)
        : name(std::move(name)), length(length), seatsNumber(120) {

        for (int i = 0; i < seatsNumber; i++) {
            freeSeats.push_back(true);
        }

    try {
        if( hour < 0 || hour > 23 ) throw "hours";
        if( min < 0  || min > 59 ) throw "minutes";
        if( day < 1 || day > 32 ) throw "days";
        if( month < 0 || month > 11 ) throw "months";

        std::time_t t = std::time(0);
        date = std::make_shared<std::tm>();

        date->tm_hour = hour;
        date->tm_min = min;
        date->tm_mday = day;
        date->tm_mon = month - 1;
        date->tm_year = 120;

    } catch(const char* x) {
        std::cout << "Error: " << x << " value out of range." << std::endl;
    }
}

Movie::~Movie() = default;

std::string Movie::report() {
    std::stringstream out;
    out << "Movie name: " << name << std::endl << "Movie length: " << length << std::endl << date->tm_mday << "." << date->tm_mon + 1 << "." << date->tm_year + 1900
                                                                                             << " " << date->tm_hour << ":" << date->tm_min << std::endl;

    return out.str();
}

const DatePtr_t &Movie::getDate() const {
    return date;
}

const std::string &Movie::getName() const {
    return name;
}

int Movie::getLength() const {
    return length;
}

bool Movie::takeSeat(int seat) {
    if (freeSeats[seat] == true) {
        freeSeats[seat] = false;
        return true;
    }
    return false;
}