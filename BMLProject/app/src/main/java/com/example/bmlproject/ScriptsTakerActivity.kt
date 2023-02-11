package com.example.bmlproject

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.bmlproject.Models.Scripts
import java.util.*

class ScriptsTakerActivity : AppCompatActivity() {

    lateinit var scripts: Scripts
    var isOldScript : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scripts_taker)

        val spinner_type = findViewById<Spinner>(R.id.spinner_type)
        val spinner_regularity = findViewById<Spinner>(R.id.spinner_regularity)

        val editText_title = findViewById<EditText>(R.id.editText_title)
        val editText_script = findViewById<EditText>(R.id.editText_script)
        val editText_date = findViewById<Button>(R.id.editText_date)
        val imageView_back = findViewById<ImageView>(R.id.imageView_back)
        val button_save = findViewById<Button>(R.id.button_save)

        scripts = Scripts()

        val typeItems = arrayOf("Call", "E-Mail", "Text")
        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, typeItems)
        spinner_type.adapter = typeAdapter

        val regularityItems = arrayOf("Rare", "Regular", "Frequent")
        val regularityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, regularityItems)
        spinner_regularity.adapter = regularityAdapter

        // Calender
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        // var to store date
        var date = "     Last used - $day/${month+1}/$year"

        editText_date.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                // Display Selected date in textbox
                date = "     Last used - $mDay/${mMonth+1}/$mYear"
                editText_date.setText(date)
            }, year, month, day)
            dpd.show()
        }

        // check if the intent has any data under the name "old_note"
        // as when editing, data is sent under that name to this activity
        try {
            // get notes object from the intent
            scripts = intent.getSerializableExtra("old_script") as Scripts
            // set the text in the layout using data from intent
            editText_title.setText(scripts.getTitle())
            spinner_type.setSelection(typeAdapter.getPosition(scripts.getType()))
            editText_script.setText(scripts.getScript())
            spinner_regularity.setSelection(regularityAdapter.getPosition(scripts.getRegularity()))
            editText_date.setText(scripts.getDate())
            // set is old note to true so that the data is updated instead of added
            // this var will be used later
            isOldScript = true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // change button text if it is an old note
        if (isOldScript) {
            button_save.text = "Update"
        }

        button_save.setOnClickListener{
            var title: String = editText_title.text.toString()
            var type: String = spinner_type.selectedItem.toString()
            var script: String = editText_script.text.toString()
            var regularity: String = spinner_regularity.selectedItem.toString()
            var date: String = editText_date.text.toString()

            if (!isOldScript) {
                scripts = Scripts()
            }

            scripts.setTitle(title)
            scripts.setType(type)
            scripts.setScript(script)
            scripts.setRegularity(regularity)
            scripts.setDate(date)

            val intent = Intent()
            intent.putExtra("scripts", scripts)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        imageView_back.setOnClickListener{
            finish()
        }
    }
}