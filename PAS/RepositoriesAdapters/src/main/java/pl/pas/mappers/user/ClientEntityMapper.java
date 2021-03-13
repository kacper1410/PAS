package pl.pas.mappers.user;

import pl.pas.data.model.user.ClientEntity;
import pl.pas.model.user.Client;

public class ClientEntityMapper {

    public static Client clientEntityToClient(ClientEntity clientEntity) {
        Client client = new Client();
        client.setLogin(clientEntity.getLogin());
        client.setPassword(clientEntity.getPassword());
        client.setName(clientEntity.getName());
        client.setLastName(clientEntity.getLastName());
        client.setAge(clientEntity.getAge());
        client.setActive(clientEntity.isActive());
        return client;
    }

    public static ClientEntity clientToClientEntity (Client client) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setLogin(client.getLogin());
        clientEntity.setPassword(client.getPassword());
        clientEntity.setName(client.getName());
        clientEntity.setLastName(client.getLastName());
        clientEntity.setAge(client.getAge());
        clientEntity.setActive(client.isActive());
        return clientEntity;
    }
}
