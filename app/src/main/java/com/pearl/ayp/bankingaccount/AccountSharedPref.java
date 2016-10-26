package com.pearl.ayp.bankingaccount;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

/**
 * Created by Nutdanai on 10/26/2016.
 */

public class AccountSharedPref {
    private static final String PREF_DEPOSIT_AMOUNT ="PREF_DEPOSIT_AMOUNT";
    private static final String PREF_AVAILABLE_BALANCE ="PREF_AVAILABLE_BALANCE";

    public static SharedPreferences mySharedPref(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setDepositAmount(Context context,int depositAmount){
        mySharedPref(context).edit().putInt(PREF_DEPOSIT_AMOUNT,depositAmount).apply();
    }

    public static Integer getDepositAmount(Context context){
       return mySharedPref(context).getInt(PREF_DEPOSIT_AMOUNT,0);
    }

    public static void setAvailableBalance(Context context,int availableBalance){
        mySharedPref(context).edit().putInt(PREF_AVAILABLE_BALANCE,availableBalance).apply();
    }


    public static Integer getAvailableBalance(Context context){
        return mySharedPref(context).getInt(PREF_AVAILABLE_BALANCE,0);
    }
}
