package dev.raidyarenas.minitwitter.common

import android.widget.Toast

object Message {
    fun long(message: String) {
        Toast.makeText(
                App.instance,
                message,
                Toast.LENGTH_LONG
        ).show()
    }
    fun short(message: String) {
        Toast.makeText(
                App.instance,
                message,
                Toast.LENGTH_SHORT
        ).show()
    }
}