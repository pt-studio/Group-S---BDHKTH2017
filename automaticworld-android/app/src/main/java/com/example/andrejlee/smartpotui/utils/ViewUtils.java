package com.example.andrejlee.smartpotui.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewUtils {

    public static void renderImageForHintTextView(Context context, TextView textView, String regex, @DrawableRes int
            resId) {
        String text = textView.getHint().toString();
        SpannableString spannableString = new SpannableString(text);

        // Icon font
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        Bitmap searchIcon = null;
        int size = ((textView.getPaint().getFontMetricsInt().bottom - textView.getPaint().getFontMetricsInt().top) * 3
                / 3);
        while (matcher.find()) {
            if (searchIcon == null) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
                searchIcon = Bitmap.createScaledBitmap(bitmap, size, size, true);
                bitmap.recycle();
            }
            ImageSpan span = new ImageSpan(context, searchIcon, ImageSpan.ALIGN_BASELINE);
            spannableString.setSpan(span, matcher.start(), matcher.end(), 0);
        }
        textView.setHint(spannableString);
    }


}
