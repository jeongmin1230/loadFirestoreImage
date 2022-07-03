package com.example.loadfirestoreimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.google.firebase.storage.FirebaseStorage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_next.*

class NextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
    }
/* 일반 함수 */
    fun loadImage(imageView: ImageView) {
        val storage : FirebaseStorage = FirebaseStorage.getInstance("gs://usefirestore-ad858.appspot.com")
        val storageReference = storage.reference
        val pathReference = storageReference.child("/${intent.getStringExtra("fileName")}")

        pathReference.downloadUrl.addOnSuccessListener { uri ->
            Glide.with(ivLoad.context)
                .load(uri)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(ivLoad)
        }
    }
/* onClick 함수 */
    fun onClickLoad(view: View) {
        loadImage(ivLoad)
    }
}