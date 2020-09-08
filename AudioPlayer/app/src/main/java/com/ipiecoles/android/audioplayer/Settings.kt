package com.ipiecoles.android.audioplayer

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.core.content.ContextCompat


class Settings(   private val requestPerm: () -> Unit) : Fragment() {

    private val hasPermission
        get() = activity?.let {
            ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        } == PackageManager.PERMISSION_GRANTED

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val switch : Switch? = getView()?.findViewById(R.id.switchSettings)

        switch?.setOnCheckedChangeListener { _, isChecked ->

            if(isChecked) {
                requestPerm()
                if(hasPermission) {
                    getActivity()?.finish()
                } else {
                    switch.switchPadding
                }
            }

        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

}