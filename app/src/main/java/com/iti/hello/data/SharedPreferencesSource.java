package com.iti.hello.data;

import static android.content.Context.MODE_PRIVATE;
import static com.iti.hello.Constants.NAME_KEY;
import static com.iti.hello.Constants.PHONE_KEY;
import static com.iti.hello.Constants.PREFERENCES_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import com.iti.hello.database.User;

public class SharedPreferencesSource implements UserSource {

    private final Context context;

    public SharedPreferencesSource(Context context) {
        this.context = context;
    }

    @Override
    public void saveUser(User user) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(NAME_KEY, user.getUsername());
        editor.putString(PHONE_KEY, user.getPhone());
        editor.apply();
    }

    @Override
    public User getUser() {
        SharedPreferences sharedPrefs = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        String name = sharedPrefs.getString(NAME_KEY, "");
        String number = sharedPrefs.getString(PHONE_KEY, "");
        return new User(1, name, number);
    }
}
