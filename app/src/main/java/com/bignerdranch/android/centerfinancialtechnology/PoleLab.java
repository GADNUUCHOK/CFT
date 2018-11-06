package com.bignerdranch.android.centerfinancialtechnology;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PoleLab {
    private static PoleLab sPoleLab;
    private List<Pole> mPoles;

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
        /**for (int i=0; i<100; i++) {
            Pole pole = new Pole();
            pole.setTitle("Name #" + i);
            mPoles.add(pole);
        }*/
    }
    public void addPole(Pole c) {
        Pole pole = new Pole();
        mPoles.add(pole);

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
