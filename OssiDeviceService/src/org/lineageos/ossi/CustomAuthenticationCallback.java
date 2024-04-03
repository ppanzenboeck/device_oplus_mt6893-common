//
//  Copyright (C) 2024 The LineageOS Project
//  SPDX-License-Identifier: Apache-2.0
//

package org.lineageos.ossi;

import android.hardware.biometrics.BiometricPrompt;
import android.content.Context;
import android.content.ContentResolver;
import android.provider.Settings;
import android.util.Log;

import java.util.ArrayList;

public class CustomAuthenticationCallback extends BiometricPrompt.AuthenticationCallback {
    private static final String TAG = "CustomAuthenticationCallback";
    private Context mContext;
    private ContentResolver mContentResolver;

    public CustomAuthenticationCallback(Context context) {
        mContext = context;
        mContentResolver = mContext.getContentResolver();
    }

    @Override
    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        Log.d(TAG, "onAuthenticationSucceeded");

        // Reset brightness value to prevent high brightness after unlock
        int brightness = Settings.System.getInt(mContentResolver, Settings.System.SCREEN_BRIGHTNESS, -1);
        Settings.System.putInt(mContentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
    }
}
