package com.c1rcle.weatherapp.Utility;

import android.content.Context;

import com.c1rcle.weatherapp.R;

public class WeatherIcons
{
    public static String translateCode(int code)
    {
        switch (code)
        {
            case 0: return "\uf056";
            case 1: return "\uf056";
            case 2: return "\uf056";
            case 3: return "\uf01e";
            case 4: return "\uf01e";
            case 5: return "\uf017";
            case 6: return "\uf017";
            case 7: return "\uf017";
            case 8: return "\uf015";
            case 9: return "\uf01c";
            case 10: return "\uf015";
            case 11: return "\uf01a";
            case 12: return "\uf01a";
            case 13: return "\uf01b";
            case 14: return "\uf01b";
            case 15: return "\uf01b";
            case 16: return "\uf01b";
            case 17: return "\uf015";
            case 18: return "\uf015";
            case 19: return "\uf014";
            case 20: return "\uf014";
            case 21: return "\uf014";
            case 22: return "\uf014";
            case 23: return "\uf011";
            case 24: return "\uf012";
            case 25: return "\uf053";
            case 26: return "\uf013";
            case 27: return "\uf031";
            case 28: return "\uf002";
            case 29: return "\uf031";
            case 30: return "\uf002";
            case 31: return "\uf02e";
            case 32: return "\uf00d";
            case 33: return "\uf02e";
            case 34: return "\uf00c";
            case 35: return "\uf017";
            case 36: return "\uf00d";
            case 37: return "\uf01e";
            case 38: return "\uf01e";
            case 39: return "\uf01e";
            case 40: return "\uf01e";
            case 41: return "\uf01b";
            case 42: return "\uf01b";
            case 43: return "\uf01b";
            case 44: return "\uf002";
            case 45: return "\uf01d";
            case 46: return "\uf01b";
            case 47: return "\uf01e";
            case 3200: return "\uf041";
            default: return "";
        }
    }

    public static String translateDescription(int code, Context context)
    {
        switch (code)
        {
            case 0: return context.getString(R.string.condition_0);
            case 1: return context.getString(R.string.condition_1);
            case 2: return context.getString(R.string.condition_2);
            case 3: return context.getString(R.string.condition_3);
            case 4: return context.getString(R.string.condition_4);
            case 5: return context.getString(R.string.condition_5);
            case 6: return context.getString(R.string.condition_6);
            case 7: return context.getString(R.string.condition_7);
            case 8: return context.getString(R.string.condition_8);
            case 9: return context.getString(R.string.condition_9);
            case 10: return context.getString(R.string.condition_10);
            case 11: return context.getString(R.string.condition_11_12);
            case 12: return context.getString(R.string.condition_11_12);
            case 13: return context.getString(R.string.condition_13);
            case 14: return context.getString(R.string.condition_14);
            case 15: return context.getString(R.string.condition_15);
            case 16: return context.getString(R.string.condition_16);
            case 17: return context.getString(R.string.condition_17);
            case 18: return context.getString(R.string.condition_18);
            case 19: return context.getString(R.string.condition_19);
            case 20: return context.getString(R.string.condition_20);
            case 21: return context.getString(R.string.condition_21);
            case 22: return context.getString(R.string.condition_22);
            case 23: return context.getString(R.string.condition_23);
            case 24: return context.getString(R.string.condition_24);
            case 25: return context.getString(R.string.condition_25);
            case 26: return context.getString(R.string.condition_26);
            case 27: return context.getString(R.string.condition_27_28);
            case 28: return context.getString(R.string.condition_27_28);
            case 29: return context.getString(R.string.condition_29_30_44);
            case 30: return context.getString(R.string.condition_29_30_44);
            case 31: return context.getString(R.string.condition_31);
            case 32: return context.getString(R.string.condition_32);
            case 33: return context.getString(R.string.condition_33_34);
            case 34: return context.getString(R.string.condition_33_34);
            case 35: return context.getString(R.string.condition_35);
            case 36: return context.getString(R.string.condition_36);
            case 37: return context.getString(R.string.condition_37);
            case 38: return context.getString(R.string.condition_38_39);
            case 39: return context.getString(R.string.condition_38_39);
            case 40: return context.getString(R.string.condition_40);
            case 41: return context.getString(R.string.condition_41_43);
            case 42: return context.getString(R.string.condition_42);
            case 43: return context.getString(R.string.condition_41_43);
            case 44: return context.getString(R.string.condition_29_30_44);
            case 45: return context.getString(R.string.condition_45);
            case 46: return context.getString(R.string.condition_46);
            case 47: return context.getString(R.string.condition_47);
            case 3200: return context.getString(R.string.condition_3200);
            default: return "";
        }
    }
}
