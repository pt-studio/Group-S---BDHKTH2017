package com.example.andrejlee.smartpotui.ui.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.example.andrejlee.smartpotui.R;


public class CommonDialogUtil {

    public static boolean sIsShowed;

    public static AlertDialog showOkDialogAction(Activity activity, String content, View.OnClickListener listener) {
        AlertDialog.Builder diaglogBuilder = new AlertDialog.Builder(activity, R.style.CommonDialogStyle);
        View popupView = activity.getLayoutInflater().inflate(R.layout.layout_with_ok_action_dialog, null);
        ((TextView) popupView.findViewById(R.id.txt_content)).setText(content);
        popupView.findViewById(R.id.text_ok).setOnClickListener(listener);
        diaglogBuilder.setView(popupView);
        AlertDialog mAlertDialog = diaglogBuilder.create();
        mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor
                (activity, R.color.transpanrent)));
        mAlertDialog.setOnCancelListener(dialog -> {
            sIsShowed = false;
            mAlertDialog.dismiss();
        });
        mAlertDialog.setCanceledOnTouchOutside(false);
        mAlertDialog.show();
        return mAlertDialog;
    }

    public static AlertDialog showOkCancelDialogAction(Activity activity, String content, View.OnClickListener
            okAction, View.OnClickListener cancelAction) {
        AlertDialog.Builder diaglogBuilder = new AlertDialog.Builder(activity, R.style.CommonDialogStyle);
        View popupView = activity.getLayoutInflater().inflate(R.layout.layout_with_ok_cancel_action_dialog, null);
        ((TextView) popupView.findViewById(R.id.txt_content)).setText(content);
        popupView.findViewById(R.id.txt_cancel).setOnClickListener(cancelAction);
        popupView.findViewById(R.id.text_ok).setOnClickListener(okAction);
        diaglogBuilder.setView(popupView);
        AlertDialog mAlertDialog = diaglogBuilder.create();
        mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor
                (activity, R.color.transpanrent)));
        mAlertDialog.setOnCancelListener(dialog -> {
            sIsShowed = false;
            mAlertDialog.dismiss();
        });
        mAlertDialog.setCanceledOnTouchOutside(false);
        mAlertDialog.show();
        return mAlertDialog;
    }
}
