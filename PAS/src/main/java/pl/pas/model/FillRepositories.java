package pl.pas.model;

import pl.pas.model.entities.Borrow;
import pl.pas.model.entities.resource.AudioBook;
import pl.pas.model.entities.resource.Book;
import pl.pas.model.entities.user.Administrator;
import pl.pas.model.entities.user.Client;
import pl.pas.model.entities.user.Employee;
import pl.pas.model.repositories.BorrowRepository;
import pl.pas.model.repositories.ResourceRepository;
import pl.pas.model.repositories.UserRepository;
import pl.pas.model.repositories.interfaces.IBorrowRepository;
import pl.pas.model.repositories.interfaces.IResourceRepository;
import pl.pas.model.repositories.interfaces.IUserRepository;
import java.util.UUID;

public class FillRepositories {
    private IResourceRepository resourceRepository;
    private IUserRepository userRepository;
    private IBorrowRepository borrowRepository;

    public FillRepositories(ResourceRepository resourceRepository, UserRepository userRepository, BorrowRepository borrowRepository) {
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
        this.borrowRepository = borrowRepository;
    }

    public void fill() {
        resourceRepository.addResource(new Book(12381273, "Balladyna",
                "Juliusz Słowacki", 1839), UUID.randomUUID());
        resourceRepository.addResource(new AudioBook(12312412, "Janko Muzykant",
                "Henryk Sienkiewcz", 532), UUID.randomUUID());
        resourceRepository.addResource(new Book(534523423, "Kamienie na szaniec",
                "Aleksander Kamiński", 1943), UUID.randomUUID());
        resourceRepository.addResource(new Book(12355231, "Latarnik",
                "Henryk Sienkiewicz", 1947), UUID.randomUUID());
        resourceRepository.addResource(new AudioBook(8123123, "Zemsta",
                "Aleksander Fredro", 782), UUID.randomUUID());
        resourceRepository.addResource(new Book(345235211, "Potop",
                "Henryk Sienkiewicz", 1828), UUID.randomUUID());
        resourceRepository.addResource(new AudioBook(242311287, "W pustyni i w puszczy",
                "Henryk Sienkiewicz", 1080), UUID.randomUUID());
        resourceRepository.addResource(new Book(643532, "Krzyżacy",
                "Henryk Sienkiewicz", 1900), UUID.randomUUID());

        userRepository.addUser(new Administrator("login", "Jan",
                "Kowalski"), UUID.randomUUID());
        userRepository.addUser(new Employee("tomcio123", "Tomasz",
                "Tomaszewski"), UUID.randomUUID());
        userRepository.addUser(new Client("tomasz_hajto", "Adam",
                "Walczak", 13), UUID.randomUUID());
        userRepository.addUser(new Client("janna", "Joanna",
                "Andrzejewska", 22), UUID.randomUUID());
        userRepository.addUser(new Client("hast", "Hanna",
                "Strzelec", 15), UUID.randomUUID());

        // TODO
        // Tu powinno być dodawanie borrowow najlepiej po kilka do usera, ale to powinien
        // robić manager zeby przy okazji zajmowac te zasoby i weryfikowac conieco
    }


}
