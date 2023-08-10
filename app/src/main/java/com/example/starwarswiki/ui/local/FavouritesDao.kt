package com.example.starwarswiki.ui.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarswiki.core.Film
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Planet
import com.example.starwarswiki.core.Starship
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {
    @Query("SELECT * FROM Person")
    fun getAllPeople(): Flow<List<Person>>

    @Query("SELECT * FROM Starship")
    fun getAllStarships(): Flow<List<Starship>>

    @Query("SELECT * FROM Film")
    fun getAllFilms(): Flow<List<Film>>

    @Query("SELECT * FROM Planet")
    fun getAllPlanets(): Flow<List<Planet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Person): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarship(starship: Starship): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: Film): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanet(planet: Planet): Long

    @Delete
    suspend fun deletePerson(person: Person): Int

    @Delete
    suspend fun deleteStarship(starship: Starship): Int

    @Delete
    suspend fun deleteFilm(film: Film): Int

    @Delete
    suspend fun deletePlanet(planet: Planet): Int

}