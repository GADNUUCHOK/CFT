package com.bignerdranch.android.centerfinancialtechnology;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.centerfinancialtechnology.database.SpisokBaseHelper;
import com.bignerdranch.android.centerfinancialtechnology.database.SpisokCursorWrapper;
import com.bignerdranch.android.centerfinancialtechnology.database.SpisokDbSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PoleLab {
    private static PoleLab sPoleLab;
    private List<Pole> mPoles;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private File mFile;

    public static PoleLab get(Context context) {
        if (sPoleLab == null) {
            sPoleLab = new PoleLab(context);
        }
        return sPoleLab;
    }

    private PoleLab(Context context) {
        mPoles = new ArrayList<>();
        if (mPoles.size() == 0) {
            Pole pole = new Pole();
            mPoles.add(pole);
        }
    }
    public void addPole(Pole c) {
        Pole pole = new Pole();
        mPoles.add(c);
    }

    public List<Pole> getPoles() {
        return mPoles;

    }

    public Pole getPole(UUID id) {
        for (Pole pole : mPoles) {
            if (pole.getId().equals(id)) {
                return pole;
            }
        }
        return null;
    }
}
