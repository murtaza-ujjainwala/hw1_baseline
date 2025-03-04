import org.junit.Before;
import org.junit.Test;

import javax.swing.table.DefaultTableModel;

import static org.junit.Assert.assertEquals;

/**
 * Testing environment for the Expense Tracker application.
 */
public class ExpenseTrackerTest {

    private ExpenseTrackerView view;
    private ExpenseTrackerApp app;

    /**
     * Initializes the MVC components of the application.
     */
    @Before
    public void setup() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Serial");
        tableModel.addColumn("Amount");
        tableModel.addColumn("Category");
        tableModel.addColumn("Date");
        view = new ExpenseTrackerView(tableModel);
        app = new ExpenseTrackerApp();
    }

    /**
     * Tests if adding a transaction with a valid number and category works.
     */
    @Test
    public void testAddTransaction() {
        // Create a new transaction
        double amount = 100.0;
        String category = "Food";
        Transaction transaction = new Transaction(amount, category);

        // Add the transaction to the view
        view.addTransaction(transaction);

        // Get the transactions from the view
        java.util.List<Transaction> transactions = view.getTransactions();

        // Verify that the transaction was added
        assertEquals(1, transactions.size());
        assertEquals(amount, transactions.get(0).getAmount(), 0.001);
        assertEquals(category, transactions.get(0).getCategory());
    }
}