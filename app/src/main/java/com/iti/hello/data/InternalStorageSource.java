package com.iti.hello.data;

import static com.iti.hello.Constants.INTERNAL_FILE_NAME;
import static com.iti.hello.Constants.NAME_KEY;
import static com.iti.hello.Constants.PHONE_KEY;

import android.content.Context;
import android.util.Log;

import com.iti.hello.Constants;
import com.iti.hello.database.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalStorageSource implements UserSource {

    private final Context context;

    public InternalStorageSource(Context context) {
        this.context = context;
    }

    @Override
    public void saveUser(User user) {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(NAME_KEY, user.getUsername());
            jsonObject.put(PHONE_KEY, user.getPhone());
        } catch (JSONException e) {
            Log.e(Constants.TAG, "addToInternalMemory: unable to make json", e);
            return;
        }
        try (FileOutputStream output = context.openFileOutput(INTERNAL_FILE_NAME, Context.MODE_PRIVATE)) {

            output.write(jsonObject.toString().getBytes());

        } catch (IOException e) {
            Log.e(Constants.TAG, "addToInternalMemory: unable to write to file", e);
        }
    }

    @Override
    public User getUser() {
        try (FileInputStream input = context.openFileInput(INTERNAL_FILE_NAME)) {

            int length = input.available();
            byte[] bytes = new byte[length];
            input.read(bytes);

            String json = new String(bytes);

            JSONObject jsonObject = new JSONObject(json);

            String name = jsonObject.getString(NAME_KEY);
            String number = jsonObject.getString(PHONE_KEY);

            return new User(1, name, number);

        } catch (FileNotFoundException e) {
            Log.e(Constants.TAG, "getFromInternalMemory: file not found", e);
        } catch (IOException e) {
            Log.e(Constants.TAG, "getFromInternalMemory: io exception", e);
        } catch (JSONException e) {
            Log.e(Constants.TAG, "getFromInternalMemory: unable to parse json", e);
        }
        return null;
    }
}
