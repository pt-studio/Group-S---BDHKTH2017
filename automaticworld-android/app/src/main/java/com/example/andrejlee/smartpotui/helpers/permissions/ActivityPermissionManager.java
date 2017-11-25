/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 The Brown Arrow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.example.andrejlee.smartpotui.helpers.permissions;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({"MissingPermission"})
public class ActivityPermissionManager extends AppCompatActivity {

    private final int KEY_PERMISSION = 999;
    private final int REQUEST_CODE_PERMISSION = 72;
    private PermissionResult mPermissionResult;
    private String mPermissionsAsk[];
    private AlertDialog mAlertPermissionDialog;

    //    OVERRIDE METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_PERMISSION) {
            // for each permission check if the user granted/denied them
            // you may want to group the rationale in a single dialog,
            // this is just an example
            for (int i = 0, len = permissions.length; i < len; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    // user rejected the permission
                    boolean showRationale = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permission);
                    }
                    if (!showRationale) {
                        // user also CHECKED "never ask again"
                        // you can either enable some fall back,
                        // disable features of your app
                        // or open another dialog explaining
                        // again the permission and directing to
                        // the app setting
//                        mAlertPermissionDialog = CommonDialogUtil.showOkCancelDialogAction(this,
//                                "Writing storage permission is required for this application. " +
//                                        "Please go to Setting > Apps > P+Kachi > Permissions to enable Storage Permission!"
//                                , v -> {
//                                    CommonDialogUtil.sIsShowed = false;
//                                    openSettingsApp(this);
//                                    mAlertPermissionDialog.dismiss();
//                                }, v -> {
//                                    CommonDialogUtil.sIsShowed = false;
//                                    mAlertPermissionDialog.dismiss();
//                                });
//                        PkachiSettings.sIsDeniedPermission = true;
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission)) {
//                        showRationale(permission, R.string.permission_denied_contacts);
                        // user did NOT check "never ask again"
                        // this is a good place to explain the user
                        // why you need the permission and ask if he wants
                        // to accept it (the rationale)
                    }
                }
            }
        }

        if (requestCode != KEY_PERMISSION) {
            return;
        }

        List<String> permissionDenied = new LinkedList<>();
        boolean granted = true;
        for (int i = 0; i < grantResults.length; i++) {
            if (!(grantResults[i] == PackageManager.PERMISSION_GRANTED)) {
                granted = false;
                permissionDenied.add(permissions[i]);
            }
        }

        if (mPermissionResult != null) {
            if (granted) {
                mPermissionResult.permissionGranted();
            } else {
                for (String s : permissionDenied) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, s)) {
                        mPermissionResult.permissionForeverDenied();
                        return;
                    }
                }
                mPermissionResult.permissionDenied();
            }
        }
    }

    //    PRIVATE METHODS
    private void internalRequestPermission(String[] permissionAsk) {
        String arrayPermissionNotGranted[];
        ArrayList<String> permissionsNotGranted = new ArrayList<>();

        for (int i = 0; i < permissionAsk.length; i++) {
            if (!isPermissionGranted(ActivityPermissionManager.this, permissionAsk[i])) {
                permissionsNotGranted.add(permissionAsk[i]);
            }
        }

        if (permissionsNotGranted.isEmpty()) {
            if (mPermissionResult != null)
                mPermissionResult.permissionGranted();
        } else {
            arrayPermissionNotGranted = new String[permissionsNotGranted.size()];
            arrayPermissionNotGranted = permissionsNotGranted.toArray(arrayPermissionNotGranted);
            ActivityCompat.requestPermissions(ActivityPermissionManager.this, arrayPermissionNotGranted, KEY_PERMISSION);
        }
    }

    //    PUBLIC METHODS

    /**
     * @param context    current Context
     * @param permission String permission to ask
     * @return boolean      true/false
     */
    public boolean isPermissionGranted(Context context, String permission) {
        return ((Build.VERSION.SDK_INT < Build.VERSION_CODES.M) || (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED));
    }

    /**
     * @param context     current Context
     * @param permissions String[] permission to ask
     * @return boolean      true/false
     */
    public boolean isPermissionsGranted(Context context, String permissions[]) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        boolean granted = true;
        for (String permission : permissions) {
            if (!(ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED))
                granted = false;
        }
        return granted;
    }

    /**
     * @param permission       String permission ask
     * @param permissionResult callback PermissionResult
     */
    public void askCompactPermission(String permission, PermissionResult permissionResult) {
        mPermissionsAsk = new String[]{permission};
        this.mPermissionResult = permissionResult;
        internalRequestPermission(mPermissionsAsk);
    }

    /**
     * @param permissions      String[] permissions ask
     * @param permissionResult callback PermissionResult
     */
    public void askCompactPermissions(String permissions[], PermissionResult permissionResult) {
        mPermissionsAsk = permissions;
        this.mPermissionResult = permissionResult;
        internalRequestPermission(mPermissionsAsk);
    }

    public void openSettingsApp(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        startActivity(intent);
    }

    public void checkWritingPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // permission wasn't granted
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, KEY_PERMISSION);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, KEY_PERMISSION);
            }
        }
    }

    public void askWriteSettingPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" +
                        this.getPackageName()));
                startActivityForResult(intent, 200);
            }
        }
    }
}