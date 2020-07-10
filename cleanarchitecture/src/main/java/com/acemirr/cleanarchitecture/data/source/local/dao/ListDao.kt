package com.acemirr.cleanarchitecture.data.source.local.dao

import androidx.room.*
import com.acemirr.cleanarchitecture.domain.model.LocalGridModel
import com.acemirr.cleanarchitecture.domain.model.LocalListModel

@Dao
interface ListDao {
/**
 * <====================================== LIST ============================================>
 * @param localList domain/model/LocalListModel     @[LocalListModel]
 * @param id domain/model/LocalListModel:id         @[Long]
 * @param name domain/model/LocalListModel:name     @[String]
 *
 * @dataclass domain/model/LocalListModel           @[LocalListModel]
 *
 * @return Long -1 if false                         @[Long]
 * @return MutableList<domain/model/LocalListModel> @MutableList<ListLocalModel>
**/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(localList: LocalListModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllList(localList: MutableList<LocalListModel>)

    @Query("SELECT * FROM list")
    suspend fun getAllList(): MutableList<LocalListModel>

    @Query("SELECT * FROM list WHERE id LIKE:id")
    suspend fun getOneListById(id: Long): LocalListModel

    @Query("SELECT * FROM list WHERE name LIKE:name")
    suspend fun getOneListByName(name: String): LocalListModel

    @Delete
    suspend fun deleteOneList(listModel: LocalListModel)

    @Query("DELETE FROM list")
    suspend fun deleteAllList()

    @Query("DELETE FROM list")
    suspend fun update()

    /**
     * <====================================== GRID ============================================>
     * @param localList domain/model/LocalListModel     @[LocalListModel]
     * @param id domain/model/LocalListModel:id         @[Long]
     * @param name domain/model/LocalListModel:name     @[String]
     *
     * @dataclass domain/model/LocalListModel           @[LocalListModel]
     *
     * @return Long -1 if false                         @[Long]
     * @return MutableList<domain/model/LocalListModel> @MutableList<ListLocalModel>
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