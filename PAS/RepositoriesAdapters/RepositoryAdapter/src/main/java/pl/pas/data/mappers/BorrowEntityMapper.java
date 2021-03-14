package pl.pas.data.mappers;

import pl.pas.data.mappers.resource.ResourceEntityMapper;
import pl.pas.data.model.BorrowEntity;
import pl.pas.data.model.user.ClientEntity;
import pl.pas.data.mappers.user.UserEntityMapper;
import pl.pas.domain.model.Borrow;
import pl.pas.domain.model.user.Client;

public class BorrowEntityMapper {

    public static Borrow borrowEntityToBorrow(BorrowEntity borrowEntity) {
        Borrow borrow = new Borrow();
        borrow.setBorrowId(borrowEntity.getBorrowId());
        borrow.setClient((Client) UserEntityMapper.userEntityToUser(borrowEntity.getClient()));
        borrow.setResource(ResourceEntityMapper.resourceEntityToResource(borrowEntity.getResource()));
        borrow.setBorrowDate(borrowEntity.getBorrowDate());
        borrow.setReturnDate(borrowEntity.getReturnDate());
        return borrow;
    }

    public static BorrowEntity borrowToBorrowEntity(Borrow borrow) {
        BorrowEntity borrowEntity = new BorrowEntity();
        borrowEntity.setBorrowId(borrow.getBorrowId());
        borrowEntity.setClient((ClientEntity) UserEntityMapper.userToUserEntity(borrow.getClient()));
        borrowEntity.setResource(ResourceEntityMapper.resourceToResourceEntity(borrow.getResource()));
        borrowEntity.setBorrowDate(borrow.getBorrowDate());
        borrowEntity.setReturnDate(borrow.getReturnDate());
        return borrowEntity;
    }
}
