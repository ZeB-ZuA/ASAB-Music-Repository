package com.udistrital.asab_music_repository.repository

import com.udistrital.asab_music_repository.entity.Cancion

interface CancionRepository {

    fun getAllCanciones(): List<Cancion>
    fun save(cancion: Cancion): Long
    fun delete(id: Int)
    fun update(cancion: Cancion)
    fun findByNombre(nombre : String): Cancion?


}





























