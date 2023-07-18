package dbaccess;

import models.Transaction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionDBTest {

    TransactionDB transactionDb = TransactionDB.getInstance();

    @Test
    public void testInsertTransaction() {

        long transactionId = 123456789030L;
        String date = "Thu May 24 2023 18:12:41 GMT-0600 (Mountain Daylight Saving Time)";
        double price = 35.98;
        ArrayList<Long> purchasedImages;
        String buyerEmail = "buyer1@example.com";
        String status =  "Completed";



        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setDate(date);
        transaction.setPrice(price);
        transaction.setBuyerEmail(buyerEmail);
        transaction.setStatus(status);

        transaction.getPurcahsedImages().add(300215084920L);
        transaction.getPurcahsedImages().add(301025011012L);

        transactionDb.insert(transaction);

        Transaction foundTransaction = transactionDb.find(transaction.getTransactionId());

        assertEquals(transaction.getTransactionId(),foundTransaction.getTransactionId());
        assertEquals(transaction.getDate(),foundTransaction.getDate());
        assertEquals(transaction.getPrice(),foundTransaction.getPrice());
        assertEquals(transaction.getPurcahsedImages(),foundTransaction.getPurcahsedImages());
        assertEquals(transaction.getBuyerEmail(),foundTransaction.getBuyerEmail());
        assertEquals(transaction.getStatus(),foundTransaction.getStatus());
    }

    @Test
    public void testFindTransaction() {
        long id = 1000000002L;

        Transaction transaction = TransactionDB.find(id);

        assertNotNull(transaction);
    }

    @Test
    public void testFindAllTransactions() {
        String buyerEmail = "buyer1@example.com";
        int historyLength = 3;

        List<Transaction> transactions = new ArrayList<>();

        transactions = TransactionDB.findAll(buyerEmail);

        assertTrue(transactions.size() > 0);
        assertTrue(transactions.size() > 1);
        assertEquals(historyLength,transactions.size());
    }




}
