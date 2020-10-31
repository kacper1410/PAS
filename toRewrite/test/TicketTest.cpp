#include <boost/test/unit_test.hpp>
#include <TicketManager.h>
#include "Ticket.h"
#include "ClientManager.h"

BOOST_AUTO_TEST_SUITE(TicketTest)

    TicketRepository tickets;
    TicketManager ticketManager(tickets);
    MoviePtr_t movie1(new Movie("Titanic", 220, 2, 5, 1, 10));
    TicketPtr_t ticket(new Ticket(movie1, 20, 40));

    BOOST_AUTO_TEST_CASE(TicketTestCreate){
        TicketPtr_t ticket1(new Ticket(movie1, 15, 10));
        TicketPtr_t ticket2(new Ticket(movie1, 15, 30));
        BOOST_CHECK_EQUAL(ticket1->getMovie(), movie1);
        BOOST_CHECK_EQUAL(ticket2->getMovie(), movie1);
        BOOST_CHECK_EQUAL(ticket1->getPrice(), 15);
        BOOST_CHECK_EQUAL(ticket2->getPrice(), 15);
        BOOST_CHECK_EQUAL(ticket1->getSeat(), 10);
        BOOST_CHECK_EQUAL(ticket2->getSeat(), 30);
    }

    BOOST_AUTO_TEST_CASE(AddTicketToRepository){
        ticketManager.addTicketToRepository(ticket);
        BOOST_CHECK_EQUAL(ticketManager.getTickets().getSize(), 1);
        BOOST_CHECK_EQUAL(ticketManager.getTickets().getElement(ticket), ticket);
    }

    BOOST_AUTO_TEST_CASE(RemoveTicketFromRepository){
        ticketManager.removeTicketFromRepository(ticket);
        BOOST_CHECK_EQUAL(ticketManager.getTickets().getSize(), 0);
        BOOST_CHECK_EQUAL(ticketManager.getTickets().getElement(ticket), nullptr);
    }

BOOST_AUTO_TEST_SUITE_END()