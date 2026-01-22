package com.iti.hello.data;

import androidx.annotation.Nullable;

import com.iti.hello.database.User;

public interface UserSource {

    void saveUser(User user);

    @Nullable
    User getUser();
}
