package com.example.pertemuankelima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pertemuankelima.Database.AppDatabase;
import com.example.pertemuankelima.Database.DataDiri;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private EditText etId, etNama,etAlamat,etKelamin;
    private Button btnUpdate;
    private ArrayList<DataDiri> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        appDatabase = AppDatabase.initDB(getApplicationContext());

        etId = findViewById(R.id.etId);
        etNama = findViewById(R.id.et_namaUp);
        etAlamat = findViewById(R.id.et_alamatUp);
        etKelamin = findViewById(R.id.et_jeniskelUp);
        btnUpdate = findViewById(R.id.btn_updateUp);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
                nextReadActivity();
            }
        });
        readEach();
    }

    private void readEach() {
        int id= getIntent().getIntExtra("id",0);
        String nama= getIntent().getStringExtra("nama");
        String alamat= getIntent().getStringExtra("alamat");
        char jk= getIntent().getCharExtra("jk",' ');
        etId.setText(""+id);
        etNama.setText(nama);
        etAlamat.setText(alamat);
        etKelamin.setText(""+jk);
    }

    private void nextReadActivity() {
        Intent intent = new Intent(this,ReadActivity.class);
        this.startActivity(intent);
    }

    private void edit() {
        String id= etId.getText().toString();
        int Id= Integer.parseInt(id);
        String nama= etNama.getText().toString();
        String alamat = etAlamat.getText().toString();
        char jk= etKelamin.getText().toString().charAt(0);

        DataDiri item = new DataDiri();
        item.setId(Id);
        item.setNama(nama);
        item.setAlamat(alamat);
        item.setJk(jk);

        appDatabase.dao().updateData(item);
    }
}
