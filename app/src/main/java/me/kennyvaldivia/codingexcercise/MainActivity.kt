package me.kennyvaldivia.codingexcercise

import android.os.Bundle
import android.support.design.widget.Snackbar
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
import android.support.v4.app.FragmentManager
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

    fun getTenKotlinProjects() {
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var api = retrofit.create(Api::class.java)
        var call = api.users
        call.enqueue(object : Callback<List<Project>> {

            override fun onResponse(call: Call<List<Project>>?, response: Response<List<Project>>?) {
                var projects = response?.body()
                var length = projects!!.size
                var str = ""

                for (i in 0 until length) {
                    str = str + "\n" + projects.get(i).name + " " + projects.get(i).owner
                }
            }

            override fun onFailure(call: Call<List<Project>>?, t: Throwable?) {
                Log.v("Error", t.toString())
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val fm = supportFragmentManager
        val buttonsFragment = ButtonsFragment()
        fm.beginTransaction()
            .add(buttonsFragment, "buttons")
            .commit()
        val githubFragment = GithubProjectFragment()
        fm.beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .add(githubFragment, "github")
            .commit()


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
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_google -> {
                this.launchGoogleWebsite()
            }
            R.id.nav_buttons -> {
                this.showButtonsFragment()
            }
            R.id.nav_github -> {
                this.showGithubFragment()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun launchGoogleWebsite() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
        startActivity(browserIntent)
    }

    fun showButtonsFragment() {
        val fm = supportFragmentManager
        val buttonsFragment = fm.findFragmentByTag("buttons")
        fm.beginTransaction()
            .show(buttonsFragment!!)
            .commit()
    }

    fun showGithubFragment() {
        val fm = supportFragmentManager
        val githubFragment = GithubProjectFragment()
        fm.beginTransaction()
            .add(githubFragment, "github")
            .commit()
    }
}
