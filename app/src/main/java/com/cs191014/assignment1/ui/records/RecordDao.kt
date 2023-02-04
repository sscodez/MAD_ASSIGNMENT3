package com.cs191014.assignment1.ui.records
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecordDao {
    @Query("SELECT * FROM record")
    suspend fun getAll(): List<Record>

    @Query("SELECT * FROM record WHERE id IN (:recordIds)")
    suspend fun loadAllByIds(recordIds: IntArray): List<Record>

    @Query("SELECT * FROM record WHERE name LIKE :name LIMIT 1")
    suspend fun findByName(name: String): Record

    @Update
    fun update(record: Record)

    @Insert
    fun insertAll(vararg record: Record)

    @Delete
    fun delete(record: Record)
}