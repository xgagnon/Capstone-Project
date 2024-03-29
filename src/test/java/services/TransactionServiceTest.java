package services;

import dbaccess.TransactionDB;
import exceptions.TransactionException;
import org.junit.Test;


import static org.junit.Assert.*;

public class TransactionServiceTest {
    TransactionDB transactionDB = TransactionDB.getInstance();

    @Test
    public void testTransactionError() {
        String expectedCode = "E1002";
        try {
            throw new TransactionException("");
        } catch (TransactionException e) {
            assertEquals(expectedCode,e.getCode());
        }
    }
    @Test
    public void testInsertTransaction() {
    }


    @Test
    public void testInsertExistingTransaction() {
    }

    @Test
    public void testInsertNullTransaction() {

    }

    @Test
    public void testInsertManyTransactions() {
    }

    @Test
    public void testInsertManyWithExistingTransaction() {
    }

    @Test
    public void testInsertManyWithNullTransaction() {
    }

    @Test
    public void testFindTransaction() {
    }

    @Test
    public void testFindNonExistentTransaction() {
    }

    @Test
    public void testFindInvalidTransactionId() {
    }

    @Test
    public void testFindNullTransactionId() {
    }

    @Test
    public void testUpdateTransaction() {
    }

    @Test
    public void testUpdateToExistingTransactionId() {
    }

    @Test
    public void testUpdateNullTransactionId() {
    }

    @Test
    public void testUpdateTransactionId() {
    }

    @Test
    public void testUpdateNonExistentTransaction() {
    }

    @Test
    public void testUpdateInvalidTransactionInputs() {
    }

    @Test
    public void testDeleteTransaction() {
    }

    @Test
    public void testDeleteNonExistentTransaction() {
    }

    @Test
    public void testDeleteInvalidTransactionId() {
    }



}
