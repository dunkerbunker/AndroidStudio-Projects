package com.example.bmlproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val cardView = view.findViewById<CardView>(R.id.card_view)
        // hide card view
        cardView.visibility = View.GONE

        val searchView = view.findViewById<SearchView>(R.id.searchView_search)
        val searchEditText : EditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(resources.getColor(R.color.bml_gray))
        searchEditText.setHintTextColor(resources.getColor(R.color.bml_gray))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filter(query)
                cardView.visibility = View.VISIBLE
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return view
    }

    private fun filter(text: String?) {
        val cardView = view?.findViewById<CardView>(R.id.card_view)
        val empty_text = view?.findViewById<TextView>(R.id.empty_text)

        if (text.isNullOrEmpty()) {
            cardView?.visibility = View.GONE
            empty_text?.visibility = View.VISIBLE
        } else {
            cardView?.visibility = View.VISIBLE
            empty_text?.visibility = View.GONE
        }

        // list with 5 users, represented by their name
        // each user has account_no, phone_no
        // filter users based on account_no, phone_no
        // display filtered users in card view

        var usersList = listOf(
            User("John Doe", "7770000091419", "9544565", "Under suspicion"),
            User("Jane Doe", "7770000091218", "9577564", "No reports"),
            User("Jaden Doe", "7770000091217", "9577563", "Suspended"),
            User("Ana Amari", "7770000080016", "7743562", "No reports"),
            User("Reaper", "7770000080015", "7743561", "No reports")
        )

        // filter users based on account_no, phone_no
        val filteredUser = usersList.filter {
            it.account_no == text || it.phone_no == text
        }

        // display filtered users in card view
        val textView_name = cardView?.findViewById<TextView>(R.id.textView_name)
        val textView_account = cardView?.findViewById<TextView>(R.id.textView_account)
        val textView_phone = cardView?.findViewById<TextView>(R.id.textView_phone)
        val textView_status = cardView?.findViewById<TextView>(R.id.textView_status)

        if (filteredUser.isNotEmpty()) {
            textView_name?.text = filteredUser[0].name
            textView_account?.text = "Account No: ${filteredUser[0].account_no}"
            textView_phone?.text = "Phone No: ${filteredUser[0].phone_no}"
            textView_status?.text = filteredUser[0].status

            empty_text?.visibility = View.GONE

        } else if (filteredUser.isEmpty()) {
            textView_name?.text = ""
            textView_account?.text = ""
            textView_phone?.text = ""
            textView_status?.text = "No user found"

            // hide card view after 3 seconds
            cardView?.postDelayed({
                cardView.visibility = View.GONE
            }, 3000)

            empty_text?.text = "No user found. Try again!"

            empty_text?.visibility = View.VISIBLE

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

class User(name: String, account_no: String, phone_np: String, status: String) {

    var name: String = name
    var account_no: String = account_no
    var phone_no: String = phone_np
    var status: String = status

}
