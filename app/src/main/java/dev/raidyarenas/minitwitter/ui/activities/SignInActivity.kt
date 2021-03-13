package dev.raidyarenas.minitwitter.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import dev.raidyarenas.minitwitter.R
import dev.raidyarenas.minitwitter.common.SharedPreferencesManager
import dev.raidyarenas.minitwitter.requests.SignInRequest
import dev.raidyarenas.minitwitter.responses.AuthResponse
import dev.raidyarenas.minitwitter.retrofit.MiniTwitterClient
import dev.raidyarenas.minitwitter.services.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var signIn: Button
    lateinit var goSignUp: TextView

    private val authService: AuthService =  MiniTwitterClient.buildService(AuthService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()
        bindActivityWithView()
    }

    private fun bindActivityWithView() {
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        signIn = findViewById(R.id.signUp)
        goSignUp = findViewById(R.id.goSignUp)
    }

    fun submitCredentials(view: View) {
        val _email: String = email.text.toString()
        val _password: String = password.text.toString()
        if (_email.isEmpty())
            email.error = resources.getString(R.string.email_required)
        if (_password.isEmpty())
            password.error = getString(R.string.password_required)
        if (_email.isNotEmpty() && _password.isNotEmpty()) {
            val singInRequest = SignInRequest(_email, _password)
            val call: Call<AuthResponse> = authService.doSignIn(singInRequest)
            call.enqueue(SignInCallback())
        }
    }

    fun goToSignUp(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    inner class SignInCallback: Callback<AuthResponse> {
        override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
            if (response.isSuccessful) {
                SharedPreferencesManager.setAuthResponse(response.body()!!)
                Toast.makeText(
                    this@SignInActivity,
                    R.string.login_success,
                    Toast.LENGTH_LONG
                ).show()
                startActivity(Intent(this@SignInActivity, DashboardActivity::class.java ))
                finish()
            } else {
                Toast.makeText(
                    this@SignInActivity,
                    R.string.login_error,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
            Toast.makeText(
                this@SignInActivity,
                R.string.bad_connection,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
