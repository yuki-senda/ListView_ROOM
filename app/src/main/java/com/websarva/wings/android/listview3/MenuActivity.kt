package com.websarva.wings.android.listview3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.websarva.wings.android.listview3.databinding.ActivityMainBinding
import com.websarva.wings.android.listview3.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val menuPrice = intent.getStringExtra("menuPrice")
        val imageId = intent.getIntExtra("imageId",R.drawable.karaage_bento)

        binding.menuName.text = name
        binding.menuPrice.text = menuPrice
        binding.menuPic.setImageResource(imageId)

    }
}