package com.udistrital.asab_music_repository.service

import android.annotation.SuppressLint
import android.content.ContentValues
import com.udistrital.asab_music_repository.entity.Cancion
import com.udistrital.asab_music_repository.repository.CancionRepository
import com.udistrital.asab_music_repository.repository.DataBaseHelper

class CancionService(private val dbHelper: DataBaseHelper) : CancionRepository {


    @SuppressLint("Range")
    override fun getAllCanciones(): List<Cancion> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("Cancion", null, null, null, null, null, null)
        val canciones = mutableListOf<Cancion>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val nombre = cursor.getString(cursor.getColumnIndex("nombre"))
                val fecha = cursor.getString(cursor.getColumnIndex("fecha"))
                val letra = cursor.getString(cursor.getColumnIndex("letra"))
                val cancion = Cancion(id, nombre, fecha, letra)
                canciones.add(cancion)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return canciones
    }

    override fun save(cancion: Cancion): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", cancion.nombre)
            put("fecha", cancion.fecha)
            put("letra", cancion.letra)
        }
        return db.insert("Cancion", null, values)
    }

    override fun delete(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete("Cancion", "id=?", arrayOf(id.toString()))
    }

    override fun update(cancion: Cancion) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", cancion.nombre)
            put("fecha", cancion.fecha)
            put("letra", cancion.letra)
        }
        db.update("Cancion", values, "id=?", arrayOf(cancion.id.toString()))
    }

    override fun findByNombre(nombre: String): Cancion? {
        val db = dbHelper.readableDatabase
        val projection = arrayOf("id", "nombre", "fecha", "letra")
        val selection = "nombre = ?"
        val selectionArgs = arrayOf(nombre)
        val sortOrder = "nombre DESC"

        val cursor = db.query(
            "Cancion",
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow("id"))
                val nombre = getString(getColumnIndexOrThrow("nombre"))
                val fecha = getString(getColumnIndexOrThrow("fecha"))
                val letra = getString(getColumnIndexOrThrow("letra"))
                return Cancion(itemId.toInt(), nombre, fecha, letra)
            }
        }
        return null
    }


}