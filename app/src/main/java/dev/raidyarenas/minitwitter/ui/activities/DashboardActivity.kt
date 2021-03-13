package dev.raidyarenas.minitwitter.ui.activities

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dev.raidyarenas.minitwitter.R

class DashboardActivity : AppCompatActivity() {
    lateinit var navView: BottomNavigationView
    lateinit var navCtrl: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        bindActivityWithView()
        supportActionBar?.hide()
        setupActionBarWithNavController(navCtrl, appBarConfiguration)
        navView.setupWithNavController(navCtrl)
    }

    private fun bindActivityWithView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_ctrl_fragment) as NavHostFragment
        navCtrl = navHostFragment.navController
        navView = findViewById(R.id.nav_view)
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_tweet_likes, R.id.navigation_profile))
    }
}