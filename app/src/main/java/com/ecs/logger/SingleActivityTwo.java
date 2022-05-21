package com.ecs.logger;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class SingleActivityTwo extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_subjects);
        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> subjectList = db.getSubjects();
        ListView lv = (ListView) findViewById(R.id.subject_list);
        ListAdapter adapter = new SimpleAdapter(SingleActivityTwo.this, subjectList, R.layout.subjects_list_row,
                new String[]{"name", "comment", "date_added", "date_accessed"},
                new int[]{R.id.name, R.id.comment, R.id.date_added, R.id.date_accessed});
        lv.setAdapter(adapter);

        /*
        Button back = (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        */
    }
}
