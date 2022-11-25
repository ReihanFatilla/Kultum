package com.reift.core.di

import com.google.firebase.database.FirebaseDatabase
import com.reift.core.data.source.local.LocalDataSource
import com.reift.core.data.source.local.sharedpref.PreferenceHelper
import com.reift.core.data.source.remote.FirebaseDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val firebaseSourceModule = module {
    single {
        FirebaseDataSource(FirebaseDatabase.getInstance())
    }
}

val localSourceModule = module {
    single {
        LocalDataSource(get())
    }
}

val preferenceModule = module {
    single {
        PreferenceHelper(androidContext())
    }
}