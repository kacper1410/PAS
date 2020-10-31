#include <boost/test/unit_test.hpp>
#include <NormalClient.h>
#include <PremiumClient.h>
#include "Client.h"

BOOST_AUTO_TEST_SUITE(ClientTest)

    ClientRepository clients;
    ClientManager clientManager(clients);
    ClientPtr_t client(new PremiumClient("Daniel", 21));
    MoviePtr_t movie1(new Movie("Titanic", 220, 2, 5, 1, 10));
    TicketPtr_t ticket(new Ticket(movie1, 20, 40));

    BOOST_AUTO_TEST_CASE(ClientCreateTest){
        ClientPtr_t client1(new NormalClient("Dawid", 10));
        ClientPtr_t client2(new PremiumClient("Kamil", 70));
        BOOST_CHECK_EQUAL(client1->getName(), "Dawid");
        BOOST_CHECK_EQUAL(client2->getName(), "Kamil");
        BOOST_CHECK_EQUAL(client1->getAge(), 10);
        BOOST_CHECK_EQUAL(client2->getAge(), 70);
    }

    BOOST_AUTO_TEST_CASE(AddClientToRepository) {
        clientManager.addClientToRepository(client);
        BOOST_CHECK_EQUAL(clientManager.getClients().getSize(), 1);
        BOOST_CHECK_EQUAL(clientManager.getClients().getElement(client), client);
    }

    BOOST_AUTO_TEST_CASE(AddTicketToClient){
        clientManager.addTicketToClient(client, ticket);
        BOOST_CHECK_EQUAL(clientManager.getClients().getElement(client)->getClientTicket(ticket), ticket);
        BOOST_CHECK_EQUAL(clientManager.getClients().getElement(client)->getClientTickets().size(), 1);
    }

    BOOST_AUTO_TEST_CASE(RemoveTicketFromClient){
        clientManager.removeTicketFromClient(client, ticket);
        BOOST_CHECK_EQUAL(clientManager.getClients().getElement(client)->getClientTicket(ticket), nullptr);
        BOOST_CHECK_EQUAL(clientManager.getClients().getElement(client)->getClientTickets().size(), 0);
    }

    BOOST_AUTO_TEST_CASE(RemoveClientFromRepository) {
        clientManager.removeClientFromRepository(client);
        BOOST_CHECK_EQUAL(clientManager.getClients().getSize(), 0);
        BOOST_CHECK_EQUAL(clientManager.getClients().getElement(client), nullptr);
    }

BOOST_AUTO_TEST_SUITE_END()