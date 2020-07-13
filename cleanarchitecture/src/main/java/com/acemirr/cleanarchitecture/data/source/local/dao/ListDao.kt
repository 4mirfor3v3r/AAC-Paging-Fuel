package com.acemirr.cleanarchitecture.data.source.local.dao

import androidx.room.*
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalListModel

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
}