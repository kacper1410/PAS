package pl.pas.adapters;

import pl.pas.data.model.BorrowEntity;
import pl.pas.data.model.exceptions.UserAlreadyExistExceptionEntity;
import pl.pas.data.model.resource.AudioBookEntity;
import pl.pas.data.model.resource.BookEntity;
import pl.pas.data.model.resource.ResourceEntity;
import pl.pas.data.model.user.AdministratorEntity;
import pl.pas.data.model.user.ClientEntity;
import pl.pas.data.model.user.EmployeeEntity;
import pl.pas.data.model.user.UserEntity;
import pl.pas.adapters.interfaces.IBorrowRepository;
import pl.pas.adapters.interfaces.IResourceRepository;
import pl.pas.adapters.interfaces.IUserRepository;

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

    public FillRepositories() {
    }

    public FillRepositories(IResourceRepository resourceRepository, IUserRepository userRepository, IBorrowRepository borrowRepository) {
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
        this.borrowRepository = borrowRepository;
    }

    public void fill(@Observes @Initialized( ApplicationScoped.class ) Object init) {
        fill();
    }

    public void fill() {
        ResourceEntity book1 = new BookEntity(12381273, "Balladyna", "Juliusz Słowacki", 1839);
        ResourceEntity audioBook1 = new AudioBookEntity(12312412, "Janko Muzykant", "Henryk Sienkiewcz", 532);
        ResourceEntity book2 = new BookEntity(534523423, "Kamienie na szaniec", "Aleksander Kamiński", 1943);
        ResourceEntity book3 = new BookEntity(12355231, "Latarnik", "Henryk Sienkiewicz", 1947);
        ResourceEntity audioBook2 = new AudioBookEntity(8123123, "Zemsta", "Aleksander Fredro", 782);
        ResourceEntity book4 = new BookEntity(345235211, "Potop", "Henryk Sienkiewicz", 1828);
        ResourceEntity audioBook3 = new AudioBookEntity(242311287, "W pustyni i w puszczy", "Henryk Sienkiewicz", 1080);
        ResourceEntity book5 = new BookEntity(643532, "Krzyżacy", "Henryk Sienkiewicz", 1900);

        resourceRepository.addResource(book1);
        resourceRepository.addResource(audioBook1);
        resourceRepository.addResource(book2);
        resourceRepository.addResource(book3);
        resourceRepository.addResource(audioBook2);
        resourceRepository.addResource(book4);
        resourceRepository.addResource(audioBook3);
        resourceRepository.addResource(book5);

        UserEntity user1 = new AdministratorEntity("login", "spa", "Jan", "Kowalski");
        UserEntity user2 = new EmployeeEntity("emp", "spa", "Tomasz", "Tomaszewski");
        ClientEntity user3 = new ClientEntity("cli1", "spa", "Adam", "Walczak", 13);
        UserEntity user4 = new ClientEntity("cli2", "spa", "Joanna", "Andrzejewska", 22);
        ClientEntity user5 = new ClientEntity("cli3", "spa", "Hanna", "Strzelec", 15);
        user5.setActive(false);

        try {
            userRepository.addUser(user1);
            userRepository.addUser(user2);
            userRepository.addUser(user3);
            userRepository.addUser(user4);
            userRepository.addUser(user5);
        } catch (UserAlreadyExistExceptionEntity e) {
            e.printStackTrace();
        }

        BorrowEntity borrow1 = new BorrowEntity(user3, audioBook1);
        audioBook1.setAvailable(false);
        borrowRepository.addBorrow(borrow1);


        BorrowEntity borrow2 = new BorrowEntity(user5, book4);
        book4.setAvailable(false);
        borrowRepository.addBorrow(borrow2);

        user5.setActive(true);

        user5.setActive(false);

    }

    public void doNothing() {
    }
}
