#include <iostream>
#include <PremiumClient.h>
#include <NormalClient.h>
#include "../include/Client.h"
#include "memory"
#include "../include/TicketManager.h"
#include "../include/MovieManager.h"



using namespace std;

int main() {
    // Tworzenie repozytoriów i managerow
    ClientRepository clients;
    TicketRepository tickets;
    MovieRepository movies;
    ClientManager clientManager(clients);
    TicketManager ticketManager(tickets);
    MovieManager movieManager(movies);

    // Tworzenie filmów  i dodanie ich do repozytoriów
    MoviePtr_t movie1(new Movie("Titanic", 220, 2, 5, 1, 10));
    MoviePtr_t movie2(new Movie("Mgla", 160, 15, 25, 13, 1));
    MoviePtr_t movie3(new Movie("Halo", 180, 1, 45, 5, 5));
    movieManager.addMovieToRepository(movie1);
    movieManager.addMovieToRepository(movie2);
    movieManager.addMovieToRepository(movie3);
    cout << movieManager.report();

    //Tworzenie biletów i dodanie ich do repozytorium
    TicketPtr_t ticket1(new Ticket(movie1, 15, 10));
    TicketPtr_t ticket2(new Ticket(movie2, 15, 10));
    TicketPtr_t ticket3(new Ticket(movie2, 15, 11));
    movieManager.getMovies().getElement(movie1)->takeSeat(10);
    ticketManager.addTicketToRepository(ticket1);
    ticketManager.addTicketToRepository(ticket2);
    ticketManager.addTicketToRepository(ticket3);



    // Tworzenie klientów i dodanie ich do repozytorium
    ClientPtr_t client1(new PremiumClient("Dawid", 20));
    ClientPtr_t client2(new NormalClient("Karolina", 25));
    clientManager.addClientToRepository(client1);
    clientManager.addClientToRepository(client2);

    // Dodanie biletów do clientów
    clientManager.addTicketToClient(client1, ticket1);
    clientManager.addTicketToClient(client1, ticket2);
    clientManager.addTicketToClient(client2, ticket3);
    cout << clientManager.report();


    return 0;
}