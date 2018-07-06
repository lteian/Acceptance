package com.yanshou.lteian.acceptance;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

class BottomNavigationViewHelper {
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView navigationView) {

        BottomNavigationView menuView = (BottomNavigationView) navigationView.getChildAt(0);
        try{
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for(int i = 0;i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
