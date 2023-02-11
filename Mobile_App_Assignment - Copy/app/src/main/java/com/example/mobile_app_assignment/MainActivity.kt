/*
    * Created by Yoosuf Sayyid CYX27785 on 21/09/2022
    * This file is part of the project "Notes App" as part of the bachelors degree assignment
    * All rights reserved to Yoosuf Sayyid CYX27785
    *
    * This file is the main activity of the app
    * It shows all the notes, and allows the user to add, edit, delete and view notes
    * It also allows the user to search for notes
*/

package com.example.mobile_app_assignment

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mobile_app_assignment.Adapters.NotesListAdapter
import com.example.mobile_app_assignment.Database.RoomDB
import com.example.mobile_app_assignment.Models.Notes
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

// Main Activity Class extends appcompat activity which is default
// popup menu listener is used to handle the popup menu, it needs to be extended/implemented for it to work
class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    // creating variable that will be required for multiple functions
    // only selected note is initialized here, the rest are initialized in other functions
    lateinit var notes : MutableList<Notes>
    private lateinit var recyclerView : RecyclerView
    private lateinit var notesListAdapter : NotesListAdapter
    private lateinit var emptyView: TextView
    private lateinit var database : RoomDB
    private var selectedNote : Notes = Notes()

    // runs when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {

        // gets the saved instance of DB
        database = RoomDB.getInstance(this)

        // get layout from xml to display
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get the recycler view from the layout
        recyclerView = findViewById(R.id.recycler_home)
        // get search view and button
        val fab_add = findViewById<FloatingActionButton>(R.id.fab_add)
        val searchView_home = findViewById<SearchView>(R.id.searchView_home)
        emptyView = findViewById(R.id.empty_view)

        val searchEditText = searchView_home.findViewById<SearchView.SearchAutoComplete>(androidx.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(resources.getColor(R.color.white))
        searchEditText.setHintTextColor(resources.getColor(R.color.white))

        val searchIcon: ImageView = searchView_home.findViewById(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.setColorFilter(resources.getColor(R.color.white))

        // running database query to get all notes and store in notes variable
        notes = database.mainDAO().getAll() as MutableList<Notes>

        // updating the recycler view with the notes
        updateRecycler(notes)

        // when add button is clicked it will open the add note activity with a request code to tell
        // it to add a note
        fab_add.setOnClickListener {
            val intent = Intent(this@MainActivity, NotesTakerActivity::class.java)
            startActivityForResult(intent, 101)
        }

        // when search view is clicked it will open the search view
        searchView_home.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // default function
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            // when text is changed it will search for the text in the notes using filter function
            // and update the recycler view with the results
            // default function
            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return false
            }
        })
    }

    // function to update the recycler view with the filtered notes
    private fun filter(newText: String?) {
        // creating a new list to store the filtered notes
        val filteredList = mutableListOf<Notes>()
        // looping through all the notes
        for (note in notes) {
            // ignoring case and checking if the note title or note content contains the text
            if (note.getTitle().lowercase(Locale.getDefault()).contains(newText.toString().lowercase(Locale.getDefault()), true)
                || note.getNotes().lowercase(Locale.getDefault()).contains(newText.toString().lowercase(Locale.getDefault()), true)) {
                // if it does then add it to the filtered list
                filteredList.add(note)
            }
        }

        // if the filtered list is empty then show the empty view
        if (filteredList.isEmpty()) {
            emptyView.visibility = TextView.VISIBLE
        } else {
            emptyView.visibility = TextView.GONE
        }

        // update the recycler view with the filtered list using the adapter
        notesListAdapter.filterList(filteredList)
    }

    // override onActivityResult function to update recycler view
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // check if request code is 101
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                // get extras send from the add note activity
                var new_notes : Notes = data?.getSerializableExtra("notes") as Notes
                // add the new note to the database
                database.mainDAO().insert(new_notes)
                // clear list and get all notes from database
                notes.clear()
                notes.addAll(database.mainDAO().getAll())
                // notify adapter so it updates the recycler view
                notesListAdapter.updateList(notes)

                // if the list is empty then show the empty view
                if (notes.isEmpty()) {
                    emptyView.visibility = TextView.VISIBLE
                } else {
                    emptyView.visibility = TextView.GONE
                }
            }
        } else if (requestCode == 102) { // check if request code is 102
            if (resultCode == RESULT_OK) {
                // get extras send from the edit note activity
                var new_notes : Notes = data?.getSerializableExtra("notes") as Notes
                // update the note in the database
                database.mainDAO().update(new_notes.getID(), new_notes.getTitle(), new_notes.getNotes())
                // clear list and get all notes from database
                notes.clear()
                notes.addAll(database.mainDAO().getAll())
                // notify adapter so it updates the recycler view
                notesListAdapter.updateList(notes)
            }
        }
    }

    // function to update the recycler view with the notes
    // called when the activity is created
    private fun updateRecycler(notes: List<Notes>) {
        // setting the layout manager for the recycler view
        recyclerView.setHasFixedSize(true)

        // use staggered grid layout manager as the notes will be off different heights
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        // create a new adapter with the notes
        notesListAdapter = NotesListAdapter(this@MainActivity, notes, notesClickListener)
        // set the adapter for the recycler view
        recyclerView.adapter = notesListAdapter

        // if the list is empty then show the empty view
        if (notes.isEmpty()) {
            emptyView.visibility = TextView.VISIBLE
        } else {
            emptyView.visibility = TextView.GONE
        }
    }

    // notesClickListener
    private val notesClickListener = object : NotesClickListener {

        // when a note is clicked it will identify the note and
        // open the edit note activity with the note as an extra
        override fun onClick(notes: Notes) {
            val intent = Intent(this@MainActivity, NotesTakerActivity::class.java)
            intent.putExtra("old_note", notes)
            startActivityForResult(intent, 102)
        }

        // when a note is long clicked it will identify the note and
        // open the popup menu
        // selected view is set so that the popup menu can be displayed in the correct location
        override fun onLongClick(notes: Notes, cardView: CardView) {
            selectedNote = notes
            showPopup(cardView)
        }
    }

    // function to show the popup menu
    private fun showPopup(cardView: CardView) {
        // create a new popup menu object with the card view it was long clicked on
        val popupMenu = PopupMenu(this, cardView)
        // get ready for menu items to be clicked / AKA listen for clicks
        popupMenu.setOnMenuItemClickListener(this)
        // inflate the menu with the menu items
        popupMenu.inflate(R.menu.popup_menu)
        // ensure that icons are showed in the menu
        popupMenu.setForceShowIcon(true)

        // if the note is not pinned then change the menu item to say save
        // else change it to say unsave
        if (selectedNote.isPinned()) {
            popupMenu.menu.getItem(0).title = "Un-save"
        } else {
            popupMenu.menu.getItem(0).title = "Save"
        }

        // show the popup menu
        popupMenu.show()
    }

    // function to handle menu item clicks
    // default function by popup menu
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        // similar to switch statement that checks the id of the menu item clicked
        when (item?.itemId) {
            // if the pin menu item is clicked
            R.id.pin -> {
                if (selectedNote.isPinned()) {
                    // if the note is pinned then unpin it
                    database.mainDAO().pin(selectedNote.getID(), false)
                    // tell user that the note is unpinned
                    Toast.makeText(this, "Note has been unpinned", Toast.LENGTH_SHORT).show()
                } else {
                    // if the note is not pinned then pin it
                    database.mainDAO().pin(selectedNote.getID(), true)
                    // tell user that the note is pinned
                    Toast.makeText(this, "Note has been pinned!", Toast.LENGTH_SHORT).show()
                }
                // clear list and get all notes from database
                notes.clear()
                notes.addAll(database.mainDAO().getAll())
                // notify adapter so it updates the recycler view
                notesListAdapter.updateList(notes)
                return true
            }
            // if the delete menu item is clicked
            R.id.delete -> {
                // delete the note from the database
                database.mainDAO().delete(selectedNote)
                // remove from list
                notes.remove(selectedNote)
                // notify adapter so it updates the recycler view
                notesListAdapter.updateList(notes)
                // tell user that the note is deleted
                Toast.makeText(this, "Note has been deleted!", Toast.LENGTH_SHORT).show()

                // if the list is empty then show the empty view
                if (notes.isEmpty()) {
                    emptyView.visibility = TextView.VISIBLE
                } else {
                    emptyView.visibility = TextView.GONE
                }

                return true
            }
            // default case
            else -> return false
        }
    }
}

