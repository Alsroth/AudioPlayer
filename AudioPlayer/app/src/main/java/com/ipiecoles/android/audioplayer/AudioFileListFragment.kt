package com.ipiecoles.android.audioplayer

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipiecoles.android.audioplayer.databinding.FragmentAudioFileListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AudioFileListFragment: Fragment() {
    private var viewModel : AudioFilesViewModel = AudioFilesViewModel()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        querySongs()
        val fakeList = listOf(AudioFile(1,"music1","artist1","album1"),
            AudioFile(2,"music2","artist2","album1"),
            AudioFile(3,"music3","artist1","album1"))
        viewModel.audioFiles.postValue(fakeList)

        val binding = FragmentAudioFileListBinding.inflate(
            inflater,
            container,
            false
        )
        binding.audioFileList.layoutManager =
            LinearLayoutManager(binding.root.context)

        val adapter = AudioFileListAdapter(viewLifecycleOwner)

        viewModel.audioFiles
            .observe(viewLifecycleOwner, adapter.submitList(fakeList))
        binding.audioFileList.adapter = adapter

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun querySongs() = GlobalScope.launch(Dispatchers.IO) {

    }

}


private fun <T> MutableLiveData<T>.observe(viewLifecycleOwner: LifecycleOwner, submitList: Unit) {

}





