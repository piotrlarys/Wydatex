package info.larys.wydatex.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import info.larys.wydatex.expense.ExpenseCategory;

/**
 * Created by Piotr on 2016-04-06.
 */
@DatabaseTable(tableName = "expense")
public class Expense {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private String name;

    @DatabaseField
    private double price;

    @DatabaseField
    private ExpenseCategory category;

    @SuppressWarnings("unused")
    public Expense() {

    }

    public Expense(String name, double price, ExpenseCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }
}
