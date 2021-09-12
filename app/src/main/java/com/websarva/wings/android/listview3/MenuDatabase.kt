package com.websarva.wings.android.listview3

import androidx.room.Database
import androidx.room.RoomDatabase

//１つ目の引数：entities = [] 一つのファイルにどのテーブルが入るかを指定する。複数入れたい場合は、,で区切ってクラス名を書く。
//2つ目の引数；データベースのバージョン
@Database(entities = [Menu::class],version = 1,exportSchema = false)
abstract class MenuDatabase:RoomDatabase() {
    //menuDaoを取得するメソッドを定義
    abstract fun menuDao():MenuDao
}