package com.example.thema

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class ThemaView : AppCompatActivity() {
    var imageView: ImageView? = null
    var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById<View>(R.id.image) as ImageView
        button = findViewById<View>(R.id.image_thema) as Button
        button!!.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    val `in` = contentResolver.openInputStream(data!!.data!!)
                    val img = BitmapFactory.decodeStream(`in`)
                    `in`!!.close()
                    // 이미지 표시
                    imageView!!.setImageBitmap(img)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}