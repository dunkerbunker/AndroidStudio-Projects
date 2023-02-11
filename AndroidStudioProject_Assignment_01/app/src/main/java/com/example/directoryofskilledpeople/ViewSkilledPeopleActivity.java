package com.example.directoryofskilledpeople;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewSkilledPeopleActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_skilled_people);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Skilled_People> skilled_people = databaseHelper.getSkilled_people_List();

        if(skilled_people.size() > 0){
            SkilledPeopleAdapter skilledPeopleAdapter = new SkilledPeopleAdapter(skilled_people ,ViewSkilledPeopleActivity.this);

        }else{
            Toast.makeText(this, "There is No Skilled People in the Database", Toast.LENGTH_SHORT).show();
        }
    }
}