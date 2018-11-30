package com.bignerdranch.android.centerfinancialtechnology.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.media.Image;

import com.bignerdranch.android.centerfinancialtechnology.Pole;

import java.util.UUID;

public class SpisokCursorWrapper extends CursorWrapper {
    public SpisokCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Pole getPole() {
        String uuidString = getString(getColumnIndex(SpisokDbSchema.SpisokTable.Cols.UUID));
        //Image

        Pole pole = new Pole(UUID.fromString(uuidString));
        //pole.setImage(image);
        return pole;
    }
}
