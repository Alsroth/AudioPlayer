package com.ipiecoles.android.audioplayer

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {

    private val hasPermission
        get() = ContextCompat.checkSelfPermission(
            this,
           READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPerm() = ActivityCompat.requestPermissions(
        this,
        arrayOf(READ_EXTERNAL_STORAGE),
        0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(hasPermission) showAppFragment(AudioFileListFragment())
        else {
            showAppFragment(Settings { requestPerm() } )
        }
        showAppFragment(AudioFileListFragment())
    }



    private fun showAppFragment(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onResume() {
        super.onResume()
        val currentFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container)
        if(currentFragment !is AudioFileListFragment && hasPermission) {
            showAppFragment(AudioFileListFragment())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        val permIdx = permissions.indexOf(
            READ_EXTERNAL_STORAGE
        )
        val result = grantResults.getOrElse(permIdx) {
            PackageManager.PERMISSION_DENIED
        }
        // Si nous avons la permission, nous affichons l'application
        if(result == PackageManager.PERMISSION_GRANTED) {
            showAppFragment(AudioFileListFragment())
        }
        // Sinon, si le système nous indique que nous
        // devons afficher un message alors nous l'affichons
        else {
            val showRequestRationnale = ActivityCompat
                .shouldShowRequestPermissionRationale(
                    this,
                    READ_EXTERNAL_STORAGE
                )
            if(showRequestRationnale) AskPermissionsDialog { requestPerm() }.show(
                supportFragmentManager,
                AskPermissionsDialog::class.simpleName
            )
        }
    }
}