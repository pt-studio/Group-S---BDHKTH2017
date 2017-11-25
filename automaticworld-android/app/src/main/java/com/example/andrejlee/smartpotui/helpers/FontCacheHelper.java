package com.example.andrejlee.smartpotui.helpers;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;

/**
 * Created by AnhNDT on 5/11/2016.
 */
public class FontCacheHelper {

//    private static final int A_OTF_SHINMGOPRO_LIGHT = 1;
//    private static final int A_OTF_SHINMGOPRO_MEDIUM = 2;
//    private static final int A_OTF_SHINMGOPRO_REGULAR = 3;
//    private static final int A_OTF_SHINMGOPRO_BOLD = 4;
    private static LruCache<String, Typeface> sFontCache = new LruCache<>(4);

    //    PUBLIC METHODS
    public static Typeface getTypeface(Context context, int font) {
//        switch (font) {
//            case A_OTF_SHINMGOPRO_LIGHT:
//                return getTypeface(context, "A-OTF-ShinMGoPro-Light.otf");
//            case A_OTF_SHINMGOPRO_MEDIUM:
//                return getTypeface(context, "A-OTF-ShinMGoPro-Medium.otf");
//            case A_OTF_SHINMGOPRO_REGULAR:
//                return getTypeface(context, "A-OTF-ShinMGoPro-Regular.otf");
//            case A_OTF_SHINMGOPRO_BOLD:
//                return getTypeface(context, "A-OTF-ShinMGoPro-Bold.otf");
//            default:
//                return getTypeface(context, "A-OTF-ShinMGoPro-Medium.otf");
//        }
        return null;
    }

    public static Typeface getTypeface(Context context, String fontName) {
        Typeface typeface = sFontCache.get(fontName);

        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s", fontName));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            sFontCache.put(fontName, typeface);
        }

        return typeface;
    }
}
