package com.example.videostreamingsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.videostreamingsample.databinding.ActivityMainBinding
import com.ps.omarmattr.videostreaming.VideoDialog


class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.apply {
            val bundle =Bundle()
            btn1.setOnClickListener {

                bundle.putStringArrayList("url" , arrayListOf(
                        "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"))
                val fm = VideoDialog()
                fm.arguments = bundle
                fm.show(supportFragmentManager,"")
            }
            btn2.setOnClickListener {
                bundle.putStringArrayList("url" , arrayListOf(

                        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
                val fm = VideoDialog()
                fm.arguments = bundle
                fm.show(supportFragmentManager,"")
            }


        }
    }



}