package com.acemirr.cleanarchitecture.data.source.local.dao

import androidx.room.*
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalGridModel

@Dao
interface GridDao {


    /**
     * <====================================== GRID ============================================>
     * @param localList domain/model/LocalGridModel     @[LocalGridModel]
     * @param id domain/model/LocalGridModel:id         @[Long]
     * @param name domain/model/LocalGridModel:name     @[String]
     *
     * @dataclass domain/model/LocalGridModel           @[LocalGridModel]
     *
     * @return Long -1 if false                         @[Long]
     * @return MutableList<domain/model/LocalGridModel> @MutableList<ListLocalModel>
     **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrid(localList: LocalGridModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGrid(localList: MutableList<LocalGridModel>)

    @Query("SELECT * FROM grid")
    suspend fun getAllGrid(): MutableList<LocalGridModel>

    @Query("SELECT * FROM grid WHERE id LIKE:id")
    suspend fun getOneGridById(id: Long): LocalGridModel

    @Query("SELECT * FROM grid WHERE caption LIKE:cap")
    suspend fun getOneGridByName(cap: String): LocalGridModel

    @Delete
    suspend fun deleteOneGrid(listModel: LocalGridModel)

    @Query("DELETE FROM grid")
    suspend fun deleteAllGrid()

    @Query("DELETE FROM grid")
    suspend fun updateGrid()

}