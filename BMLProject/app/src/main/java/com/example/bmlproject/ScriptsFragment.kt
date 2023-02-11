package com.example.bmlproject

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bmlproject.Adapters.ScriptsAdapter
import com.example.bmlproject.Database.RoomDB
import com.example.bmlproject.Models.Scripts
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScriptsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScriptsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var scripts : MutableList<Scripts>
    private lateinit var recyclerView : RecyclerView
    private lateinit var scriptsAdapter : ScriptsAdapter
    private lateinit var emptyView: TextView
    private lateinit var database : RoomDB

    override fun onCreate(savedInstanceState: Bundle?) {

        // gets the saved instance of DB
        database = RoomDB.getInstance(requireContext())!!

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

        val view =  inflater.inflate(R.layout.fragment_scripts, container, false)

        val fab_add = view.findViewById<FloatingActionButton>(R.id.fab_add)
        recyclerView = view.findViewById(R.id.recycler_home)!!

        scripts = database.mainDAO().getAll() as MutableList<Scripts>

        // func we create
        updateRecycler(scripts)

        // hide fab
        //  fab_add.hide()

        fab_add?.setOnClickListener{
            val intent = Intent(activity, ScriptsTakerActivity::class.java)
            startActivityForResult(intent, 101)
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 1)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                var new_scripts : Scripts = data?.getSerializableExtra("scripts") as Scripts
                database.mainDAO().insert(new_scripts)
                scripts.clear()
                scripts.addAll(database.mainDAO().getAll())
                scriptsAdapter.updateList(scripts)
            }
        } else if (requestCode == 102) {
            if (resultCode == RESULT_OK) {
                var new_scripts : Scripts = data?.getSerializableExtra("scripts") as Scripts
                database.mainDAO().update(new_scripts.getId(), new_scripts.getType(), new_scripts.getTitle(),
                    new_scripts.getScript(), new_scripts.getRegularity(), new_scripts.getDate())
                scripts.clear()
                scripts.addAll(database.mainDAO().getAll())
                scriptsAdapter.updateList(scripts)
            }
        }
    }

    private fun updateRecycler(scripts: List<Scripts>) {
        // setting the layout manager for the recycler view
        recyclerView.setHasFixedSize(true)

        // use staggered grid layout manager as the notes will be off different heights
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        scriptsAdapter = ScriptsAdapter(requireContext(), scripts, scriptsClickListener)

        // set the adapter for the recycler view
        recyclerView.adapter = scriptsAdapter
    }


    private val scriptsClickListener = object : ScriptsClickListener {

        // when a note is clicked it will identify the note and
        // open the edit note activity with the note as an extra
        override fun onClick(scripts: Scripts) {
            val intent = Intent(activity, ScriptsTakerActivity::class.java)
            intent.putExtra("old_script", scripts)
            startActivityForResult(intent, 102)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScriptsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScriptsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}