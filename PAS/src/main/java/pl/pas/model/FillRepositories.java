package pl.pas.model;

import pl.pas.model.entities.resource.AudioBook;
import pl.pas.model.entities.resource.Book;
import pl.pas.model.entities.resource.Resource;
import pl.pas.model.entities.user.Administrator;
import pl.pas.model.entities.user.Client;
import pl.pas.model.entities.user.Employee;
import pl.pas.model.entities.user.User;
import pl.pas.model.managers.BorrowManager;
import pl.pas.model.repositories.interfaces.IBorrowRepository;
import pl.pas.model.repositories.interfaces.IResourceRepository;
import pl.pas.model.repositories.interfaces.IUserRepository;

import java.util.UUID;

public class FillRepositories {
    private IResourceRepository resourceRepository;
    private IUserRepository userRepository;
    private IBorrowRepository borrowRepository;
    private BorrowManager borrowManager;

    public FillRepositories(IResourceRepository resourceRepository, IUserRepository userRepository, IBorrowRepository borrowRepository) {
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
        this.borrowRepository = borrowRepository;
        this.borrowManager = new BorrowManager(this.resourceRepository, this.borrowRepository, this.userRepository);
    }

    public void fill() {
        Resource book1 = new Book(12381273, "Balladyna", "Juliusz Słowacki", 1839);
        Resource audioBook1 = new AudioBook(12312412, "Janko Muzykant", "Henryk Sienkiewcz", 532);
        Resource book2 = new Book(534523423, "Kamienie na szaniec", "Aleksander Kamiński", 1943);
        Resource book3 = new Book(12355231, "Latarnik", "Henryk Sienkiewicz", 1947);
        Resource audioBook2 = new AudioBook(8123123, "Zemsta", "Aleksander Fredro", 782);
        Resource book4 = new Book(345235211, "Potop", "Henryk Sienkiewicz", 1828);
        Resource audioBook3 = new AudioBook(242311287, "W pustyni i w puszczy", "Henryk Sienkiewicz", 1080);
        Resource book5 = new Book(643532, "Krzyżacy", "Henryk Sienkiewicz", 1900);

        resourceRepository.addResource(book1, UUID.randomUUID());
        resourceRepository.addResource(audioBook1, UUID.randomUUID());
        resourceRepository.addResource(book2, UUID.randomUUID());
        resourceRepository.addResource(book3, UUID.randomUUID());
        resourceRepository.addResource(audioBook2, UUID.randomUUID());
        resourceRepository.addResource(book4, UUID.randomUUID());
        resourceRepository.addResource(audioBook3, UUID.randomUUID());
        resourceRepository.addResource(book5, UUID.randomUUID());

        User user1 = new Administrator("login", "Jan", "Kowalski");
        User user2 = new Employee("tomcio123", "Tomasz", "Tomaszewski");
        User user3 = new Client("tomasz_hajto", "Adam", "Walczak", 13);
        User user4 = new Client("janna", "Joanna", "Andrzejewska", 22);
        User user5 = new Client("hast", "Hanna", "Strzelec", 15);

        userRepository.addUser(user1, UUID.randomUUID());
        userRepository.addUser(user2, UUID.randomUUID());
        userRepository.addUser(user3, UUID.randomUUID());
        userRepository.addUser(user4, UUID.randomUUID());
        userRepository.addUser(user5, UUID.randomUUID());

        borrowManager.borrowResource(book1.getResourceId(), user3.getUserId());
        borrowManager.borrowResource(audioBook1.getResourceId(), user3.getUserId());
        borrowManager.borrowResource(book2.getResourceId(), user3.getUserId());

        borrowManager.borrowResource(book3.getResourceId(), user4.getUserId());
        borrowManager.borrowResource(audioBook2.getResourceId(), user4.getUserId());

        borrowManager.borrowResource(book4.getResourceId(), user5.getUserId());
    }
}
