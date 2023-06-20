package services;


import dbaccess.TransactionDB;
import exceptions.TransactionException;
import models.Transaction;


import java.util.List;

public class TransactionService {

    TransactionDB transactionDb = TransactionDB.getInstance();

    public void insert(Transaction transaction) throws TransactionException {

        if(transactionDb.find(transaction.getTransactionId()) != null) {
            throw new TransactionException("A transaction with the ID " +transaction.getTransactionId()+ " already exists");
        }
        transactionDb.insert(transaction);
    }
    public void insertMany(List<Transaction> transactions) throws TransactionException {

        for(Transaction transaction : transactions) {
            if(TransactionDB.find(transaction.getTransactionId()) != null) {
                throw new TransactionException("A transaction with the ID \" +transaction.getTransactionId()+ \" already exists");
            }
        }
        TransactionDB.insertMany(transactions);
    }

    public Transaction find(int transactionId) {
        return TransactionDB.find(transactionId);
    }

    public void update(Transaction transaction) {
        TransactionDB.update(transaction);
    }

    public void delete(Transaction transaction) {
        TransactionDB.delete(transaction);
    }
}
