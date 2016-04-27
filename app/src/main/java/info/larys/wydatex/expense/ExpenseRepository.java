package info.larys.wydatex.expense;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

import info.larys.wydatex.dao.DaoHelper;
import info.larys.wydatex.dao.Expense;

/**
 * Created by Piotr on 2016-04-06.
 */
public class ExpenseRepository {

    private static List<Expense> expenses = new ArrayList<>();

    public static List<Expense> getExpenses(Context context) {
        RuntimeExceptionDao<Expense, Long> dao = DaoHelper.getInstance(context).getExpenseDao();
        return dao.queryForAll();
    }

    public static void addExpanse(Context context, Expense expense) {
        RuntimeExceptionDao<Expense, Long> dao = DaoHelper.getInstance(context).getExpenseDao();
        dao.create(expense);
    }
}
