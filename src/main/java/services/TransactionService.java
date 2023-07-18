package services;


import dbaccess.TransactionDB;
import exceptions.TransactionException;
import models.Transaction;


import java.util.List;

public class TransactionService {

    TransactionDB transactionDb = TransactionDB.getInstance();

    public Transaction insert(Transaction transaction) throws TransactionException {

        if(transactionDb.find(transaction.getTransactionId()) != null) {
            throw new TransactionException("A transaction with the ID " +transaction.getTransactionId()+ " already exists");
        }
        transactionDb.insert(transaction);
        return transaction;
    }

    public Transaction find(int transactionId) {
        return TransactionDB.find(transactionId);
    }

    public List<Transaction> findAll(String email) {
        return TransactionDB.findAll(email);
    }
}
