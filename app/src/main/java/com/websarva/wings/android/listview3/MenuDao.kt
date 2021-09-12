package com.websarva.wings.android.listview3

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MenuDao {

    //メニューを追加する
    //ROOMがデータを追加するための処理　SQLコードを書いてくれる。
    @Insert
    fun insert(menu: Menu)

    //全てのメニューを取得する
    @Query("select * from menu")
    //戻り値　メニューのリストが入ったデータ
    fun getAll():List<Menu>

    //全てのメニューを削除する
    @Query("delete from menu")
    fun deleteAll()
}