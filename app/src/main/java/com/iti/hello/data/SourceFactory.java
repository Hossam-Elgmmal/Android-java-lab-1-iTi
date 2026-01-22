package com.iti.hello.data;

import android.content.Context;

public class SourceFactory {

    public enum SourceType {
        SHARED_PREFERENCES,
        INTERNAL_MEMORY,
        SQLITE
    }

    public static UserSource getDataSource(Context context, SourceType source) {
        switch (source) {
            case SHARED_PREFERENCES : {
                return new SharedPreferencesSource(context);
            }
            case INTERNAL_MEMORY : {
                return new InternalStorageSource(context);
            }
            case SQLITE : {
                return new SQLiteSource(context);
            }
        }
        throw new IllegalArgumentException("Unknown source type");
    }
}
