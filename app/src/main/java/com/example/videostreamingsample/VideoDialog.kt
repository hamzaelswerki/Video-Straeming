package com.ps.omarmattr.videostreaming

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import cn.jzvd.JZVideoPlayer
import com.example.videostreamingsample.R
import com.example.videostreamingsample.databinding.VideoDialogBinding
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.video_dialog.*

class VideoDialog : DialogFragment() {

    private var simpleExoplayer: SimpleExoPlayer? = null
    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(requireContext(), "exoplayer-sample")
    }
    var url: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return LayoutInflater.from(context).inflate(R.layout.video_dialog,null)
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireArguments().getStringArrayList("url")?.let {
            url = it[0]
            uriVideo(it[0])
        }
    }
    private fun uriVideo(uri: String) {

        simpleExoplayer = SimpleExoPlayer.Builder(requireContext()).build()
        preparePlayer(uri)
        video.player = simpleExoplayer
        simpleExoplayer?.playWhenReady = true
    }
    override fun onResume() {
        super.onResume()
        if (url != null)
            uriVideo(url!!)
    }

    override fun onStop() {
        super.onStop()
        simpleExoplayer?.release()
    }
    private fun preparePlayer(videoUrl: String) {
        simpleExoplayer?.prepare(ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(videoUrl)))
    }



}