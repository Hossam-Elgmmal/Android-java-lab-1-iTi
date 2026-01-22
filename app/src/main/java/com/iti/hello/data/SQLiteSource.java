package com.iti.hello.data;

import android.content.Context;

import com.iti.hello.database.DataBaseAdapter;
import com.iti.hello.database.User;

public class SQLiteSource implements UserSource {

    private final DataBaseAdapter adapter;

    public SQLiteSource(Context context) {
        adapter = new DataBaseAdapter(context);
    }

    @Override
    public void saveUser(User user) {
        adapter.insertUser(user);
    }

    @Override
    public User getUser() {
        User[] users = adapter.getAllUsers();
        if (users != null && users.length >= 1) {
            return users[0];
        }
        return null;
    }
}
