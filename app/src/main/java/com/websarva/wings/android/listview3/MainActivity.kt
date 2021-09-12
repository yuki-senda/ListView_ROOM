package com.websarva.wings.android.listview3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.websarva.wings.android.listview3.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var menuArrayList: ArrayList<Menu>

    /*ROOMを使用する
    MenuDatabaseとMenuDAOを取得*/
    lateinit var db: MenuDatabase
    lateinit var dao: MenuDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageId = intArrayOf(
            R.drawable.karaage_bento, R.drawable.hamburger_set, R.drawable.mix_piza,
            R.drawable.kaisendon, R.drawable.yakiniku_bento
        )

        val name = arrayListOf(
            "唐揚げ弁当", "チーズバーガーセット", "ミックスピザ", "海鮮丼", "焼肉弁当"
        )

        val menuPrice = arrayListOf(
            "800円", "900円", "1000円", "1100円", "1200円",
        )

        menuArrayList = ArrayList()

        for (i in 0 until 20) {
            for (i in name.indices) {
                val menu = Menu(1, name[i], menuPrice[i], imageId[i])
               menuArrayList.add(menu)
            }
        }

        //データベースを初期化　ROOM.databaseBuilderメソッド
        db = Room.databaseBuilder(
            //引数１つ目：Context
            this,
            /*引数２つ目：どのデータベースのオブジェクトを作るか。
            ここではMenuDatabaseを作りたいので、Menudatabaseクラスを渡す。*/
            MenuDatabase::class.java,
            //引数３つ目：Stringでどのファイルを保存するか
            "menu.db"
            //build()を呼ぶ
        ).build()

        //データベースをインスタンス化した。定義したmemoDAOを取ってくる。
        dao = db.menuDao()

        GlobalScope.launch {
            /*メインスレッドで実行すると、例外が起きる（クラッシュする）
            ワーカースレッドで実施する必要があるため、kotlinのコルーチンを使用する。*/
            withContext(Dispatchers.IO) {
                //起動時に入っているデータを全て削除
                dao.deleteAll()

                for (i in 0 until 20) {
                    for (i in name.indices) {
                        val menu = Menu(0, name[i], menuPrice[i], imageId[i])
                        //データを保存
                        dao.insert(menu)
                    }
                }
                //データを取得
                val datas = dao.getAll()
                println(datas)

                //メインスレッドでアダプターへデータをセット
                withContext(Dispatchers.Main){
                    binding.listView.adapter = CustomAdapter(this@MainActivity,
                        datas as ArrayList<Menu>)
                }
            }
        }

        binding.listView.isClickable = true

//        binding.listView.adapter = CustomAdapter(this, menuArrayList)

        binding.listView.setOnItemClickListener { parent, view, position, id ->

            val name = name[position % name.size]
            val imageId = imageId[position % imageId.size]
            val menuPrice = menuPrice[position % menuPrice.size]

            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("name", name)
            i.putExtra("menuPrice", menuPrice)
            i.putExtra("imageId", imageId)

            startActivity(i)
        }
    }
}