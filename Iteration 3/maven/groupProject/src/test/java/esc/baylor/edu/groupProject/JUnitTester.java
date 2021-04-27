package esc.baylor.edu.groupProject;

import esc.baylor.edu.groupProject.TransactionObjects.Category;
import esc.baylor.edu.groupProject.TransactionObjects.Transaction;
import esc.baylor.edu.groupProject.TransactionObjects.TransactionLog;
import esc.baylor.edu.groupProject.TransactionObjects.Types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class JUnitTester {
    protected TransactionLog log = null;
    protected Category category = null;
    protected Transaction transaction = null;

    @BeforeEach
    void init() {
        log = new TransactionLog();
    }

    @Test
    void passEmptyLog() {
        int size = log.categoryCount();
        Assertions.assertEquals(size, 0);
    }

    @Test
    void passNonEmptyLog() {
        log.addCategory("Test category", "note");
        int size = log.categoryCount();
        Assertions.assertEquals(size, 1);
    }

    @Test
    void passCategoryExists() {
        log.addCategory("Food", "note");
        boolean result = log.categoryExists("Food");
        Assertions.assertEquals(result, true);
    }

    @Test
    void failCategoryExists() {
        log.addCategory("Food", "note");
        boolean result = log.categoryExists("Entertainment");
        Assertions.assertEquals(result, false);
    }

    @Test
    void failNullCategoryExists() {
        log.addCategory("Food", "note");
        boolean result = log.categoryExists(null);
        Assertions.assertEquals(result, false);
    }

    @Test
    void addCategory() {
        log.addCategory("Food", "note");
        category = log.getCategory(0);
        boolean result = false;
        if(category.getName().equals("Food") && category.getNotes().equals("note")) {
            result = true;
        }
        Assertions.assertEquals(result, true);
    }

    @Test
    void editCategory() {
        log.addCategory("Food", "note");
        log.editCategory(0, "Entertainment", "fun");
        category = log.getCategory(0);
        boolean result = false;
        if(category.getName().equals("Entertainment") && category.getNotes().equals("fun")) {
            result = true;
        }
        Assertions.assertEquals(result, true);
    }

    @Test
    void removeCategory() {
        log.addCategory("Food", "note");
        category = log.getCategory(0);
        log.removeCategory(category);
        int size = log.categoryCount();
        Assertions.assertEquals(size, 0);
    }

    @Test
    void getCategory() {
        log.addCategory("Food", "note");
        category = log.getCategory(0);
        boolean result = false;
        if(category.getName().equals("Food") && category.getNotes().equals("note")) {
            result = true;
        }
        Assertions.assertEquals(result, true);
    }

    @Test
    void categoryCount() {
        log.addCategory("Food", "yum");
        log.addCategory("Entertainment", "fun");
        log.addCategory("Expenses", "money spent");
        int size = log.categoryCount();
        Assertions.assertEquals(size, 3);
    }

    @Test
    void addTransaction() {
        log.addTransaction(Types.Expense, "Food", null, 100.00, -1);
        int size = log.size();
        Assertions.assertEquals(size, 1);
    }

    @Test
    void editTransaction() {
        log.addTransaction(Types.Expense, "Food", null, 100.00, -1);
        transaction = log.getTransaction(0);
        log.editTransaction(0, transaction.getType(), "Entertainment", transaction.getDate(),
                            transaction.getAmount(), transaction.getRecur());
        transaction = log.getTransaction(0);
        Assertions.assertEquals(transaction.getTitle(), "Entertainment");
    }

    @Test
    void removeTransaction() {
        log.addTransaction(Types.Expense, "Food", null, 100.00, -1);
        transaction = log.getTransaction(0);
        log.removeTransaction(transaction);
        int size = log.size();
        Assertions.assertEquals(size, 0);
    }

    @Test
    void getTransaction() {
        log.addTransaction(Types.Expense, "Food", null, 100.00, -1);
        transaction = log.getTransaction(0);
        Assertions.assertEquals(transaction.getTitle(), "Food");
    }
}
