package dev.raidyarenas.minitwitter.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.raidyarenas.minitwitter.R
import dev.raidyarenas.minitwitter.common.SharedPreferencesManager
import dev.raidyarenas.minitwitter.requests.SignUpRequest
import dev.raidyarenas.minitwitter.responses.AuthResponse
import dev.raidyarenas.minitwitter.retrofit.MiniTwitterClient
import dev.raidyarenas.minitwitter.services.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {
    lateinit var username: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var signUp: Button
    lateinit var goSignIn: TextView

    val authService: AuthService =  MiniTwitterClient.buildService(AuthService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        bindActivityWithView()
    }

    private fun bindActivityWithView() {
        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        signUp = findViewById(R.id.signUp)
        goSignIn = findViewById(R.id.goSignIn)
    }

    fun goToSignIn(view: View) {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    fun submitFormRegister(view: View) {
        val _username: String = username.text.toString()
        val _email: String = email.text.toString()
        val _password: String = password.text.toString()
        if (_username.isEmpty())
            username.error = resources.getString(R.string.username_required)
        if (_email.isEmpty())
            email.error = resources.getString(R.string.email_required)
        if (_password.isEmpty())
            password.error = getString(R.string.password_required)
        if (_username.isNotEmpty() && _email.isNotEmpty() && _password.isNotEmpty()) {
            val singUpRequest = SignUpRequest(_username, _email, _password, "UDEMYANDROID")
            val call: Call<AuthResponse> = authService.doSignUp(singUpRequest)
            call.enqueue(SignUpCallback())
        }
    }

    inner class SignUpCallback: Callback<AuthResponse> {
        override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
            if (response.isSuccessful) {
                SharedPreferencesManager.setAuthResponse(response.body()!!)
                Toast.makeText(
                        this@SignUpActivity,
                        R.string.register_success,
                        Toast.LENGTH_LONG
                ).show()

                startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                        this@SignUpActivity,
                        R.string.register_failed,
                        Toast.LENGTH_LONG
                ).show()
            }
        }

        override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
            Toast.makeText(
                    this@SignUpActivity,
                    R.string.bad_connection,
                    Toast.LENGTH_LONG
            ).show()
        }

    }
}