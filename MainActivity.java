package com.example.mvvmdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    EditText edtTitle;
    EditText edtDesc;
    Button buttonAdd;

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update recyclerview
                Toast.makeText(MainActivity.this, "onChange: " + notes.size(), Toast.LENGTH_SHORT).show();
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String desc = edtDesc.getText().toString();
                Note note = new Note(title, desc);
                noteViewModel.insert(note);
            }
        });
    }

    private void initViews() {
        edtDesc = findViewById(R.id.edt_desc);
        edtTitle = findViewById(R.id.edt_title);
        buttonAdd = findViewById(R.id.button_add);
    }
}