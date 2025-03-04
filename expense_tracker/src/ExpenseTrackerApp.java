import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * The ExpenseTrackerApp class allows users to add remove daily transactions.
 */
public class ExpenseTrackerApp {

  /**
   * Initializes the view for the application, and sets up event handling for "Add Transaction" button clicks.
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {
    
    // Create MVC components
    DefaultTableModel tableModel = new DefaultTableModel();
    tableModel.addColumn("Serial");
    tableModel.addColumn("Amount");
    tableModel.addColumn("Category");
    tableModel.addColumn("Date");

    ExpenseTrackerView view = new ExpenseTrackerView(tableModel);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      try {
        // Get transaction data from view
        double amount = InputValidation.amountValidation(Double.toString(view.getAmountField()));
        String category = InputValidation.categoryValidation(view.getCategoryField());

        // Create transaction object
        Transaction t = new Transaction(amount, category);

        // Call controller to add transaction
        view.addTransaction(t);
      }
      catch (IllegalArgumentException err) {
        JOptionPane.showMessageDialog(null, err.getMessage(), "Invalid input", 1);
      }
    });

  }

}