package com.example.proiect_tocilarii_betivani.Util;

import androidx.room.TypeConverter;

public class AccountTypeConverter {

    @TypeConverter
    public static int fromTypeToInt(AccountType value) {
        return value.ordinal();
    }

    /**
     * Convert an integer to ACCOUNT
     */
    @TypeConverter
    public static AccountType fromIntToType(int value) {
        return (AccountType.values()[value]);
    }
}
