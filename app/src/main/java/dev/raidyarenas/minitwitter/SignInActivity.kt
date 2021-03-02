package dev.raidyarenas.minitwitter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class SignInActivity : AppCompatActivity() {
    lateinit var signIn: Button
    lateinit var goSignUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()
        bindActivityWithView()
    }

    private fun bindActivityWithView() {
        signIn = findViewById(R.id.signUp)
        goSignUp = findViewById(R.id.goSignUp)
    }

    fun goToSignUp(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}