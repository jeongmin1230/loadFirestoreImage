package com.example.loadfirestoreimage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val OPEN_GALLERY = 1
    var uriPhoto : Uri? = null

    var fileName = "IMAGE.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
/* override 함수 */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == OPEN_GALLERY) {
                uriPhoto = data?.data
                ivShow.setImageURI(uriPhoto)
                imageUpload(uriPhoto!!)
            } else {
                Log.d("jeongmin", "something wrong")
            }
        }
    }
/* 일반 함수 */
    // 파이어스토어에 이미지 업로드 하는 함수
    private fun imageUpload(uri : Uri) {
        var storage : FirebaseStorage? = FirebaseStorage.getInstance()
        // 파일 이름 생성
        var imagesRef = storage!!.reference.child(fileName)
        // 이미지 파일 업로드
        imagesRef.putFile(uri!!).addOnSuccessListener {
            Toast.makeText(this, "업로드 성공", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            println(it)
            Toast.makeText(this, "업로드 실패", Toast.LENGTH_SHORT).show()
        }
    }
/* onClick 함수 */
    fun onCLickSelect(view: View) {
        val intent : Intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, OPEN_GALLERY)
    }
    fun onCLickMoveToNext(view: View) {
        val intent = Intent(this, NextActivity::class.java)
        intent.putExtra("fileName", fileName)
        startActivity(intent)
    }
}