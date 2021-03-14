package pl.pas.data.adapters;

import pl.pas.data.model.exceptions.NotFoundExceptionEntity;
import pl.pas.data.exceptions.NotFoundException;
import pl.pas.data.mappers.BorrowEntityMapper;
import pl.pas.data.model.Borrow;
import pl.pas.data.repositories.BorrowRepository;
import pl.pas.ports.infrastructure.borrow.CreateBorrowPort;
import pl.pas.ports.infrastructure.borrow.DeleteBorrowPort;
import pl.pas.ports.infrastructure.borrow.ReadBorrowPort;
import pl.pas.ports.infrastructure.borrow.UpdateBorrowPort;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class BorrowRepositoryAdapter implements UpdateBorrowPort, DeleteBorrowPort, ReadBorrowPort, CreateBorrowPort {

    @Inject
    private BorrowRepository borrowRepository;

    public BorrowRepositoryAdapter() {

    }

    public BorrowRepositoryAdapter(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    @Override
    public void createBorrow(Borrow borrow) {
        borrowRepository.addBorrow(BorrowEntityMapper.borrowToBorrowEntity(borrow));
    }

    @Override
    public void deleteBorrow(long id) {
        borrowRepository.deleteBorrow(id);
    }

    @Override
    public List<Borrow> readAllBorrows() {
        return borrowRepository.getAllBorrows()
                .stream()
                .map(BorrowEntityMapper::borrowEntityToBorrow)
                .collect(Collectors.toList());
    }

    @Override
    public List<Borrow> readBorrowsByResource(long uuid) {
        return borrowRepository.getBorrowsByResource(uuid)
                .stream()
                .map(BorrowEntityMapper::borrowEntityToBorrow)
                .collect(Collectors.toList());
    }

    @Override
    public List<Borrow> readBorrowsByUser(long uuid) {
        return borrowRepository.getBorrowsByUser(uuid)
                .stream()
                .map(BorrowEntityMapper::borrowEntityToBorrow)
                .collect(Collectors.toList());
    }

    @Override
    public Borrow readBorrow(long uuid) throws NotFoundException {
        try {
            return BorrowEntityMapper.borrowEntityToBorrow(borrowRepository.getBorrow(uuid));
        } catch (NotFoundExceptionEntity notFoundExceptionEntity) {
            throw new NotFoundException();
        }
    }

    @Override
    public void endBorrow(long uuid) throws NotFoundException {
        try {
            borrowRepository.endBorrow(uuid);
        } catch (NotFoundExceptionEntity notFoundExceptionEntity) {
            throw new NotFoundException();
        }
    }

    @Override
    public void updateBorrow(long uuid, Borrow newBorrow) {
        borrowRepository.updateBorrow(uuid, BorrowEntityMapper.borrowToBorrowEntity(newBorrow));
    }
}
