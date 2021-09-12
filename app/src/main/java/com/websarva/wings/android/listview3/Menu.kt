package com.websarva.wings.android.listview3

import androidx.room.Entity
import androidx.room.PrimaryKey

//一行分のデータを表すクラス
@Entity
data class Menu(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var menuName: String,
    var menuPrice: String,
    var imageId: Int
    )
