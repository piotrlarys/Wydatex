package info.larys.wydatex.expense;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotr on 2016-04-06.
 */
public class ExpenseDatabase {

    private static List<Expense> expenses = new ArrayList<>();

    public static List<Expense> getExpenses() {
        return expenses;
    }

    static {
        expenses.add(new Expense("Jaka", 4.2, ExpenseCategory.FOOD));
        expenses.add(new Expense("Kino", 32, ExpenseCategory.ENTERTAIMENT));
        expenses.add(new Expense("Szampon", 9.99, ExpenseCategory.HYGIENE));
    }

    public static void addExpanse(Expense expense) {
        expenses.add(expense);
    }
}
