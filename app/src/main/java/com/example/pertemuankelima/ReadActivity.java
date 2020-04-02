package com.example.pertemuankelima;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pertemuankelima.Database.AppDatabase;
import com.example.pertemuankelima.Database.DataDiri;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity{
    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private ArrayList<DataDiri> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        appDatabase = AppDatabase.initDB(getApplicationContext());
        recyclerView = findViewById(R.id.rvMain);
        recyclerView.setHasFixedSize(true);

        read();
    }

    public void read() {
        list.addAll(appDatabase.dao().getData());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DataDiriAdapter adapter = new DataDiriAdapter(this, new DataDiriListerner() {
            @Override
            public void onButtonDelete(DataDiri item) {
                appDatabase.dao().deleteData(item);
                list.clear();
                read();
            }
        });
        adapter.setDataDiris(list);
        recyclerView.setAdapter(adapter);
    }
}
