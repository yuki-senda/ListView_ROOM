package com.websarva.wings.android.listview3

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text
import java.text.FieldPosition

class CustomAdapter (private val context : Activity,private val arrayList : ArrayList<Menu>) : ArrayAdapter<Menu>(context,
                    R.layout.list_item,arrayList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item,null)

        val imageView : ImageView = view.findViewById(R.id.menu_pic)
        val menuName : TextView = view.findViewById(R.id.menuName)
        val menuprice : TextView = view.findViewById(R.id.menuPrice)

        imageView.setImageResource(arrayList[position].imageId)
        menuName.text = arrayList[position].menuName
        menuprice.text = arrayList[position].menuPrice

        return view
    }
}