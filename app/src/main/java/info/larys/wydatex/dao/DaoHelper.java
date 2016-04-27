package info.larys.wydatex.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by piotr on 27.04.16.
 */
public class DaoHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "wydatex.db";
    private static final int DATABASE_VERSION = 1;

    private static DaoHelper instance;
    private RuntimeExceptionDao<Expense, Long> expenseDao;

    public static DaoHelper getInstance(Context context) {
        if (instance == null)
            instance = new DaoHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        return instance;
    }

    private DaoHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        recreateDao(connectionSource);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        recreateDao(connectionSource);
    }

    public void recreateDao(ConnectionSource connectionSource) {
        try {
            TableUtils.dropTable(connectionSource, Expense.class, true);
            TableUtils.createTableIfNotExists(connectionSource, Expense.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RuntimeExceptionDao<Expense, Long> getExpenseDao() {
        if (expenseDao == null)
            expenseDao = getRuntimeExceptionDao(Expense.class);
        return expenseDao;
    }
}
