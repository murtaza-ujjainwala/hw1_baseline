import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List; 

/**
 * The ExpenseTrackerView class creates the User Interface for the Expense Tracker application.
 * It provides the methods for the input fields (used for entering transactions), the table displaying transaction history, and the button for adding transactions.
 */
public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private List<Transaction> transactions = new ArrayList<>();

  /**
   * Gets the transaction history table.
   * @return The JTable that displays transaction history.
   */
  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  /**
   * Gets the data that is in the amount field.
   * @return The amount that the user entered (0 if no amount entered).
   */
  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  /**
   * Sets the amount field.
   * @param amountField A JTextField to enter the amount.
   */
  public void setAmountField(JTextField amountField) {
    this.amountField = amountField;
  }

  /**
   * Gets the data that is in the category field.
   * @return The category that the user entered.
   */
  public String getCategoryField() {
    return categoryField.getText();
  }

  /**
   * Sets the category field.
   * @param categoryField A JTextField to enter the category.
   */
  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  /**
   * Gets the "Add Transaction" button.
   * @return The JButton that allows a user to add transactions to the transaction history table.
   */
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }

  /**
   * Gets the table model used for the transaction history.
   * @return The DefaultTableModel that is used for storing the transaction history data.
   */
  public DefaultTableModel getTableModel() {
    return model;
  }

  /**
   * Creates the user interface.
   * @param model The table model used for storing and displaying the user's transaction history.
   */
  public ExpenseTrackerView(DefaultTableModel model) {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger
    this.model = model;

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    amountField = new JTextField(10);
    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
  
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  /**
   * Updates the transaction history table with a new row if a new transaction has been added by the user. Also updates the total sum of transactions.
   * @param transactions The list of transactions that should be shown in the table.
   */
  public void refreshTable(List<Transaction> transactions) {
      // model.setRowCount(0);
      model.setRowCount(0);
      int rowNum = model.getRowCount();
      double totalCost=0;
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
  
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()});

      }
      Object[] totalRow = {"Total", null, null, totalCost};
      model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  

  /**
   * Gets the updated list of transactions and calls the refreshTable method.
   */
  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = getTransactions();
  
    // Pass to view
    refreshTable(transactions);
  
  }

  /**
   * Gets the list of transactions that the user has added.
   * @return The list of transactions.
   */
  public List<Transaction> getTransactions() {
    return transactions;
  }
  
  /**
   * Adds a transaction to the list of transactions and refreshes the table to show the new transaction in a new row.
   * @param t The new transaction that will be added.
   */
  public void addTransaction(Transaction t) {
    transactions.add(t);
    getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
  }

  // Other view methods
}
