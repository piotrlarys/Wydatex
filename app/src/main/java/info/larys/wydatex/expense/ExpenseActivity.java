package info.larys.wydatex.expense;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import info.larys.wydatex.R;

public class ExpenseActivity extends Activity {

    private static final String PREF_LAST_CATEGORY = "pref.last.category";
    private EditText titleEditText;
    private EditText priceEditText;
    private Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        titleEditText = (EditText) findViewById(R.id.edit_name);
        priceEditText = (EditText) findViewById(R.id.edit_price);
        categorySpinner = (Spinner) findViewById(R.id.expanse_category);

        categorySpinner.setAdapter(new CategoryAdapter());
        loadLastCategory(categorySpinner);


    }

    private void addNewExpanse() {
        String title = titleEditText.getText().toString();
        double price = Double.parseDouble(priceEditText.getText().toString());
        ExpenseCategory category = (ExpenseCategory) categorySpinner.getSelectedItem();
        Expense expense = new Expense(title, price, category);
        ExpenseDatabase.addExpanse(expense);
        saveLastCategory(category);
        finish();
    }

    private void saveLastCategory(ExpenseCategory expenseCategory) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_LAST_CATEGORY, expenseCategory.name());
        editor.commit();
    }

    private void loadLastCategory(Spinner categorySpinner) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String lastCategoryName = preferences.getString(PREF_LAST_CATEGORY, "");
        if (!lastCategoryName.isEmpty()) {
            int id = ExpenseCategory.getId(lastCategoryName);
            categorySpinner.setSelection(id);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.expense_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                addNewExpanse();
                return true;
            default:
                return super.onMenuItemSelected(featureId, item);
        }
    }

    private class CategoryAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return ExpenseCategory.values().length;
        }

        @Override
        public ExpenseCategory getItem(int position) {
            return ExpenseCategory.values()[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(android.R.layout.simple_spinner_item, null);
            }

            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(getItem(position).getName());

            return convertView;
        }
    }

}


