package com.example.pertemuankelima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pertemuankelima.Database.AppDatabase;
import com.example.pertemuankelima.Database.DataDiri;

public class MainActivity extends AppCompatActivity {
    private EditText etNama, etAlamat, etJenis;
    private AppDatabase appDatabase;
    private Button btnInput,btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = AppDatabase.initDB(getApplicationContext());
        etNama= findViewById(R.id.etNama);
        etAlamat = findViewById(R.id.etAlamat);
        etJenis = findViewById(R.id.etJenis);
        btnInput = findViewById(R.id.btnInput);
        btnShow = findViewById(R.id.btnRead);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                nextReadActivity();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReadActivity.class);
                startActivity(intent);
            }
        });
    }

    public void nextReadActivity() {
        Intent intent = new Intent(this,ReadActivity.class);
        this.startActivity(intent);
    }

    private void insertData(){
        String nama = etNama.getText().toString();
        String alamat = etAlamat.getText().toString();
        char jk = etJenis.getText().toString().charAt(0);

        DataDiri item = new DataDiri();
        item.setNama(nama);
        item.setAlamat(alamat);
        item.setJk(jk);

        appDatabase.dao().insertData(item);

        etNama.setText("");
        etAlamat.setText("");
        etJenis.setText("");
    }
}
