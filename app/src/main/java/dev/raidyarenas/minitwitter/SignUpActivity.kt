package dev.raidyarenas.minitwitter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class SignUpActivity : AppCompatActivity() {
    lateinit var signUp: Button
    lateinit var goSignIn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        bindActivityWithView()
    }

    private fun bindActivityWithView() {
        signUp = findViewById(R.id.signUp)
        goSignIn = findViewById(R.id.goSignIn)
    }

    fun goToSignIn(view: View) {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}