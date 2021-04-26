package esc.baylor.edu.groupProject.demo.app;

import esc.baylor.edu.groupProject.TransactionObjects.Category;
import esc.baylor.edu.groupProject.TransactionObjects.Transaction;
import esc.baylor.edu.groupProject.TransactionObjects.TransactionLog;
import esc.baylor.edu.groupProject.TransactionObjects.Types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class Tester {
    protected TransactionLog log = null;
    protected Category category = null;
    protected Transaction transaction = null;

    @BeforeEach
    void init() {
        log = new TransactionLog();
    }

    @Test
    void test() {
        int size = log.size();
    }
}
