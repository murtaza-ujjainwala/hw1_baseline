import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Class that handles the validation of the inputs. The amount field should be a number between 0 and 1000, and the category field should either be "food", "travel", "bills", "entertainment", or "other".
 */
public class InputValidation {
    private static final List<String> categories = Arrays.asList("food", "travel", "bills", "entertainment", "other");

    /**
     * Validates the amount field. This number should be between 0 and 1000.
     * @param amount_str The user's input of the amount.
     * @return The amount if it is valid.
     * @throws IllegalArgumentException If the amount is less than or equal to 0, greater than or equal to 1000, or not a number.
     */
    public static double amountValidation(String amount_str) throws IllegalArgumentException {
        try {
            double amount = Double.parseDouble(amount_str);
            if (amount <= 0 || amount >= 1000) {
                throw new IllegalArgumentException("Not a valid number. Amount must be a number between 0 and 1000.");
            }
            return amount;
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not a valid number. Amount must be a number between 0 and 1000.");
        }
    }

    /**
     * Validates the category field. This value should be "food", "travel", "bills", "entertainment", or "other".
     * @param category_str The user's input of the category.
     * @return The category if it is valid.
     * @throws IllegalArgumentException If the category is not one of the categories specified in the valid category list.
     */
    public static String categoryValidation(String category_str) throws IllegalArgumentException {
        String category = category_str.toLowerCase();
        if (!categories.contains(category)) {
            throw new IllegalArgumentException("Not a valid category. Category must be either " + String.join(", ", categories));
        }
        return category;
    }
}
