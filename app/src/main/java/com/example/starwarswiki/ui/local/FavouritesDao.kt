package com.example.starwarswiki.ui.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Starship
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {
    @Query("SELECT * FROM Person")
    suspend fun getAllPeople(): Flow<List<Person>>

    @Query("SELECT * FROM Starship")
    suspend fun getAllStarships(): Flow<List<Starship>>

    @Insert
    suspend fun insertPeople(person: Person)

    @Insert
    suspend fun insertStarship(starship: Starship)

    @Delete
    suspend fun deletePerson(person: Person)

    @Delete
    suspend fun deleteStarship(starship: Starship)

}