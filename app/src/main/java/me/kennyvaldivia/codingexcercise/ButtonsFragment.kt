package me.kennyvaldivia.codingexcercise

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ButtonsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ButtonsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ButtonsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buttons, container, false)!!
        val showToastButton = view.findViewById<Button>(R.id.showToastlButton)!!
        showToastButton.setOnClickListener {
            Toast.makeText(context, R.string.toast_message, Toast.LENGTH_LONG).show()
        }
        val showAlertButton = view.findViewById<Button>(R.id.showAlertButton)!!
        showAlertButton.setOnClickListener {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context!!)
            alertDialog.setMessage(R.string.alert_message)
            alertDialog.show()
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ButtonsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ButtonsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
