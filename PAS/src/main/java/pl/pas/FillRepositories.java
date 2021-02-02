package pl.pas;

import pl.pas.exceptions.NotFoundException;
import pl.pas.exceptions.NotValidException;
import pl.pas.exceptions.UserAlreadyExistException;
import pl.pas.managers.BorrowManager;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.resource.Resource;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;
import pl.pas.repositories.interfaces.IBorrowRepository;
import pl.pas.repositories.interfaces.IResourceRepository;
import pl.pas.repositories.interfaces.IUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ApplicationScoped
public class FillRepositories implements Serializable {
    @Inject
    private IResourceRepository resourceRepository;
    @Inject
    private IUserRepository userRepository;
    @Inject
    private IBorrowRepository borrowRepository;
    @Inject
    private BorrowManager borrowManager;

    public FillRepositories() {
    }

    public FillRepositories(IResourceRepository resourceRepository, IUserRepository userRepository, IBorrowRepository borrowRepository) {
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
        this.borrowRepository = borrowRepository;
        this.borrowManager = new BorrowManager(this.resourceRepository, this.borrowRepository, this.userRepository);
    }

    public void fill(@Observes @Initialized( ApplicationScoped.class ) Object init) {
        fill();
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

        resourceRepository.addResource(book1);
        resourceRepository.addResource(audioBook1);
        resourceRepository.addResource(book2);
        resourceRepository.addResource(book3);
        resourceRepository.addResource(audioBook2);
        resourceRepository.addResource(book4);
        resourceRepository.addResource(audioBook3);
        resourceRepository.addResource(book5);

        User user1 = new Administrator("login", "spa", "Jan", "Kowalski");
        User user2 = new Employee("emp", "spa", "Tomasz", "Tomaszewski");
        User user3 = new Client("cli1", "spa", "Adam", "Walczak", 13);
        User user4 = new Client("cli2", "spa", "Joanna", "Andrzejewska", 22);
        User user5 = new Client("cli3", "spa", "Hanna", "Strzelec", 15);
        user5.setActive(false);

        try {
            userRepository.addUser(user1);
            userRepository.addUser(user2);
            userRepository.addUser(user3);
            userRepository.addUser(user4);
            userRepository.addUser(user5);
        } catch (UserAlreadyExistException e) {
            e.printStackTrace();
        }

        try {
            borrowManager.borrowResource(audioBook1.getResourceId(), user3.getUserId());
            borrowManager.borrowResource(audioBook2.getResourceId(), user4.getUserId());

            user5.setActive(true);
            borrowManager.borrowResource(book4.getResourceId(), user5.getUserId());
            user5.setActive(false);
        } catch (NotFoundException | NotValidException e) {
            e.printStackTrace();
        }
    }

    public void doNothing() {
    }
}
