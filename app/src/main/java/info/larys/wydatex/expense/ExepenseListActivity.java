package info.larys.wydatex.expense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import info.larys.wydatex.R;

public class ExepenseListActivity extends AppCompatActivity {

    private ListView expenseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        expenseListView = (ListView) findViewById(R.id.listView);
        expenseListView.setAdapter(new ExpenseListAdapter());

        Button newExpensButton = (Button) findViewById(R.id.add_expense);
        newExpensButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ExpenseActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        expenseListView.invalidateViews();
    }

    private class ExpenseListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ExpenseDatabase.getExpenses().size();
        }

        @Override
        public Object getItem(int i) {
            return ExpenseDatabase.getExpenses().get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.list_item_expense, null);
            }
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView price = (TextView) view.findViewById(R.id.price);
            TextView category = (TextView) view.findViewById(R.id.category);
            Expense item = (Expense) getItem(position);
            title.setText(item.getName());

            price.setText(String.format("%.2f", item.getPrice()) + "zł");
            category.setText(item.getCategory().getName());
            return view;
        }
    }

}
