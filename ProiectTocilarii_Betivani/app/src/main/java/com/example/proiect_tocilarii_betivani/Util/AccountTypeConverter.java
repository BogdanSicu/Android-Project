package com.example.proiect_tocilarii_betivani.Util;

import androidx.room.TypeConverter;

public class AccountTypeConverter {

    @TypeConverter
    public static int fromHealthToInt(AccountType value) {
        return value.ordinal();
    }

    /**
     * Convert an integer to Health
     */
    @TypeConverter
    public static AccountType fromIntToHealth(int value) {
        return (AccountType.values()[value]);
    }
}
