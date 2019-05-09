package me.kennyvaldivia.codingexcercise

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment
import android.util.Log
import me.kennyvaldivia.codingexcercise.dummy.DummyContent
import me.kennyvaldivia.codingexcercise.github.Api
import me.kennyvaldivia.codingexcercise.github.Project
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, GithubProjectFragment.OnListFragmentInteractionListener {
    val BASE_URL = "https://api.github.com/search/"

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportFragmentManager.fragments.forEach {
            val fmt = supportFragmentManager.beginTransaction()
            if (it.id == R.id.welcomeFragment)
                fmt.show(it)
            else
                fmt.hide(it)
            fmt.commit()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fm = supportFragmentManager
        when (item.itemId) {
            R.id.nav_google -> {
                this.launchGoogleWebsite()
            }
            R.id.nav_buttons -> {
                val buttonsFragment = fm.findFragmentById(R.id.buttonsFragment)!!
                this.switchFragments(buttonsFragment)
            }
            R.id.nav_github -> {
                val githubFragment = fm.findFragmentById(R.id.githubFragment)!!
                this.switchFragments(githubFragment)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun launchGoogleWebsite() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
        startActivity(browserIntent)
    }

    fun switchFragments(fragmentToShow: Fragment) {
        val fm = supportFragmentManager
        fm.fragments.forEach {
            val fmt = fm.beginTransaction()
            if (it.id == fragmentToShow.id)
                fmt.show(fragmentToShow)
            else
                fmt.hide(it)
            fmt.commit()
        }
    }
}
