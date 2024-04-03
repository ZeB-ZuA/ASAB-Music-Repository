package com.udistrital.asab_music_repository.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "asab_music_repository_Database"
        private const val TABLE_NAME = "Cancion"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "nombre"
        private const val KEY_DATE = "fecha"
        private const val KEY_LETTER = "letra"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, "
                + KEY_DATE + " TEXT, "
                + KEY_LETTER + " TEXT" + ")")
        db.execSQL(CREATE_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


}