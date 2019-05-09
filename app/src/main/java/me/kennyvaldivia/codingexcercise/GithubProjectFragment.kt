package me.kennyvaldivia.codingexcercise

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_githubproject_list.*

import me.kennyvaldivia.codingexcercise.dummy.DummyContent.DummyItem
import me.kennyvaldivia.codingexcercise.github.Api
import me.kennyvaldivia.codingexcercise.github.ProjectsResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [GithubProjectFragment.OnListFragmentInteractionListener] interface.
 */
class GithubProjectFragment : Fragment() {
    val BASE_URL = "https://api.github.com/"

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var projectList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
        }
    }

    fun getTenKotlinProjects(context: Context) {
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var api = retrofit.create(Api::class.java)
        var call = api.projects("language:kotlin", "stars", "desc", 10)
        val projectList = this.projectList
        call.enqueue(object : Callback<ProjectsResults> {

            override fun onResponse(call: Call<ProjectsResults>?, response: Response<ProjectsResults>?) {
                var results = response?.body()
                var projects = results!!.items
                val interactionListener = activity!! as GithubProjectFragment.OnListFragmentInteractionListener
                projectList.adapter = MyGithubProjectRecyclerViewAdapter(projects, interactionListener)
            }

            override fun onFailure(call: Call<ProjectsResults>?, t: Throwable?) {
                Log.v("Error", t.toString())
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_githubproject_list, container, false)
        this.projectList = view.findViewById(R.id.projectList)
        this.projectList.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            GithubProjectFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
