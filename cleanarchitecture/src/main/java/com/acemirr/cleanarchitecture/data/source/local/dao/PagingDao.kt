package com.acemirr.cleanarchitecture.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalGridModel
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalNewsModel

@Dao
interface PagingDao {

    /**
     * <====================================== PAGING ============================================>
     * @param localList domain/model/LocalGridModel     @[LocalNewsModel]
     * @param id domain/model/LocalGridModel:id         @[Long]
     * @param name domain/model/LocalGridModel:name     @[String]
     *
     * @dataclass domain/model/LocalGridModel           @[LocalGridModel]
     *
     * @return Long -1 if false                         @[Long]
     * @return MutableList<domain/model/LocalGridModel> @MutableList<ListLocalModel>
     **/

    @Insert(entity = LocalNewsModel::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPaging(localList: MutableList<LocalNewsModel>)

    @Query("SELECT * FROM paging ORDER BY id DESC LIMIT :page,:pageSize")
    suspend fun getPagedPaging(page:Int, pageSize:Int): List<LocalNewsModel>

    @Query("SELECT COUNT(*) FROM paging")
    fun getPagingCount():Int?

    @Query("SELECT * FROM paging WHERE id LIKE:id")
    suspend fun getOnePagingById(id: Long): LocalNewsModel

    @Query("SELECT * FROM paging WHERE title LIKE:title")
    suspend fun getOnePagingByTitle(title: String): LocalNewsModel

    @Query("DELETE FROM paging")
    suspend fun deleteAllPaging()

    @Query("DELETE FROM paging")
    suspend fun updatePaging()
}