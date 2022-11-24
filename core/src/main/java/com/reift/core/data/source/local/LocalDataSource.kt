package com.reift.core.data.source.local

import com.reift.core.data.source.local.sharedpref.PreferenceHelper

class LocalDataSource(
    val preferenceHelper: PreferenceHelper
) {

    fun add(key: String, value: String){
        preferenceHelper.add(key, value)
    }

    fun add(key: String, value: Boolean){
        preferenceHelper.add(key, value)
    }

    fun getString(key: String): String? {
        return preferenceHelper.getString(key)
    }

    fun getBoolean(key: String): Boolean {
        return preferenceHelper.getBoolean(key)
    }

}