package com.ipiecoles.android.audioplayer

import androidx.lifecycle.MutableLiveData
import kotlin.reflect.KProperty

class AudioFilesViewModel {
    val audioFiles = MutableLiveData<List<AudioFile>>()
}