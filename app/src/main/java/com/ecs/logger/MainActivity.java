package com.ecs.logger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    // public class MainActivity extends Activity {
    ArrayList<HashMap<String, String>> subjectsList;
    DbHandler db = null;
    ListView listView = null;
    SimpleAdapter listAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_subjects);

        db = new DbHandler(MainActivity.this);

        // ArrayList<HashMap<String, String>> subjectsList = db.getSubjects();
        subjectsList = db.getSubjects();
        listView = (ListView) findViewById(R.id.subject_list);
        listAdapter = new SimpleAdapter(MainActivity.this, subjectsList,
                R.layout.subjects_list_row, new String[]{"id", "name", "comment", "date_added", "date_accessed"},
                new int[]{R.id.id, R.id.name, R.id.comment, R.id.date_added, R.id.date_accessed});
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
                // String value = (String)adapter.getItemAtPosition(position);
                TextView textView = (TextView) view.findViewById(R.id.id);
                String str = textView.getText().toString();
                long id = Long.parseLong(str);
                // Toast toast = Toast.makeText(getApplicationContext(), str, 5);
                // toast.show();
                Intent intent = new Intent(getApplicationContext(), SingleActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    public void refreshData() {
        subjectsList.clear();
        subjectsList = db.getSubjects();
        listAdapter = null;
        listAdapter = new SimpleAdapter(MainActivity.this, subjectsList,
                R.layout.subjects_list_row, new String[]{"id", "name", "comment", "date_added", "date_accessed"},
                new int[]{R.id.id, R.id.name, R.id.comment, R.id.date_added, R.id.date_accessed});
        listView.setAdapter(listAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.clean:
                db.cleanDatabase();
                refreshData();
                return true;
            case R.id.help:
                // showHelp();
                return true;
            case R.id.menu_new_subject:
                // newSubject();
                return true;
            case R.id.populate:
                db.enterSomeSubjectData();
                refreshData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_options_menu, menu);
        return true;
    }
}
