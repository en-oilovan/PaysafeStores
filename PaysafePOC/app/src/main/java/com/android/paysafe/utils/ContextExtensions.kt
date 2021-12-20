package com.android.paysafe.utils

import android.Manifest
import android.content.Context
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager

fun Context.isPermissionGranted(permission: String) =
    this.applicationContext.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED