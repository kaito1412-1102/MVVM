package com.example.mvvmdemo;

import android.app.Application;

import com.example.mvvmdemo.databse.NoteDao;
import com.example.mvvmdemo.databse.NoteDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();    //allNotes là live data. allNotes lấy dữ liệu từ csdl nên khi csdl thay đổi thì nó tự động cập nhật dữ liệu
    }

    public void insert(Note note) {
        noteDao.insert(note);
    }

    public void update(Note note) {
        noteDao.update(note);
    }

    public void delete(Note note) {
        noteDao.delete(note);
    }

    public void deleteAllNotes() {
        noteDao.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
