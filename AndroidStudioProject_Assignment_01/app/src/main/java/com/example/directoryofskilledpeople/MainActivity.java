package com.example.directoryofskilledpeople;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id, name, address, title, phone, email, nationality;
    Button btn_addRecord, btn_ViewRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.ET_id);
        name = findViewById(R.id.ET_name);
        address = findViewById(R.id.ET_address);
        title = findViewById(R.id.ET_title);
        phone = findViewById(R.id.ET_phone);
        email = findViewById(R.id.ET_email);
        nationality =findViewById(R.id.ET_nationality);
        btn_addRecord = findViewById(R.id.btn_add);
        btn_ViewRecords = findViewById(R.id.btn_view);


        btn_addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String S_Id = id.getText().toString();
                String S_name = name.getText().toString();
                String S_address = address.getText().toString();
                String S_title = title.getText().toString();
                String S_phone = phone.getText().toString();
                String S_email = email.getText().toString();
                String S_nationality = nationality.getText().toString();
                if (S_Id.length()<=0 || S_name.length()<=0 || S_title.length()<=0 || S_phone.length()<=0 || S_email.length()<=0 || S_nationality.length()<=0){
                    Toast.makeText(MainActivity.this, "Enter All the Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                    Skilled_People skilled_people = new Skilled_People(S_Id, S_name, S_address, S_title, S_phone, S_email, S_nationality);
                    databaseHelper.addExperts(skilled_people);
                    Toast.makeText(MainActivity.this, "New Experts is Successfully Added to Database", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(getIntent());

                }
            }
        });

        btn_ViewRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ViewSkilledPeopleActivity.class);
                startActivity(intent);
            }
        });
    }
}