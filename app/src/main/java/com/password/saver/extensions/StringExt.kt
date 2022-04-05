package com.password.saver.extensions

import com.password.saver.Constants.HASH_CHAR
import com.password.saver.Constants.SAFE_HASH_CODE

fun String.toSafeNavString(): String = this.replace(HASH_CHAR, SAFE_HASH_CODE)

fun String.fromSafeNavString(): String = this.replace(SAFE_HASH_CODE, HASH_CHAR)