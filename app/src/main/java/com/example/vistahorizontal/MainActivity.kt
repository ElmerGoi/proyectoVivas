package com.example.vistahorizontal

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.vistahorizontal.MapsActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuButton: Button = findViewById(R.id.menuButton)
        menuButton.setOnClickListener { showPopupMenu(menuButton) }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.menu_options, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_option1 -> {
                    // Acción para Opción 1
                    true
                }
                R.id.action_option2 -> {
                    // Acción para Opción 2
                    // Iniciar la actividad de Google Maps
                    val intent = Intent(this, MapsActivity::class.java) // Mantén la referencia correcta a la clase Kotlin
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}
