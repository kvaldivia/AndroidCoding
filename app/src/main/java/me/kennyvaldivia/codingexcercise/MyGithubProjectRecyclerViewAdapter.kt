package me.kennyvaldivia.codingexcercise

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import me.kennyvaldivia.codingexcercise.GithubProjectFragment.OnListFragmentInteractionListener
import me.kennyvaldivia.codingexcercise.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_githubproject.view.*
import me.kennyvaldivia.codingexcercise.models.Project

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyGithubProjectRecyclerViewAdapter(
    private val mProjects: List<Project>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyGithubProjectRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_githubproject, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mProjects[position]
        holder.mProjectNameTextView.text = item.name
        holder.mProjectOwnerTextView.text = item.owner.login

        with(holder.mView) {
            tag = item
        }
    }

    override fun getItemCount(): Int = mProjects.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mProjectNameTextView: TextView = mView.name
        val mProjectOwnerTextView: TextView = mView.owner

        override fun toString(): String {
            return super.toString() + " '" + mProjectOwnerTextView.text + "'"
        }
    }
}
