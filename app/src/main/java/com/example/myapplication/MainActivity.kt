package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlin.time.times

class MainActivity : AppCompatActivity() {

    val Tag = "MainActivity"
    val Egp = "EGP"
    val jor = "JD"
    val Ammercian = "USD"
    val Qatar = "Reyal"
    lateinit var frot_drop_down: AutoCompleteTextView
    lateinit var auto_text_view: AutoCompleteTextView
    lateinit var convert_btn: Button
    lateinit var amount_et: TextInputEditText
    lateinit var result_et: TextInputEditText
    lateinit var toolbar: Toolbar
    val values = mapOf(
        Egp to 15.73, jor to 0.7, Ammercian to 1, Qatar to .72
    )
    val listOFCurrncey = listOf(Egp, jor, Ammercian, Qatar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        intializviews()
        populateDropDownMenu()
        itemclicklisnerforDropDownMenu()
        toolbar.inflateMenu(R.menu.option_menu)
        toolbar.setOnMenuItemClickListener { menuitem ->
          when(menuitem.itemId){
              R.id.share_action -> Toast.makeText(this,"Share action clicked",Toast.LENGTH_SHORT).show()
              R.id.setting_action -> Toast.makeText(this,"Setting action clicked",Toast.LENGTH_SHORT).show()
              R.id.contact_action -> Toast.makeText(this,"Contact action clicked",Toast.LENGTH_SHORT).show()
          }
            true


        }


    }

    /**
     * This is My function i wrote to made my code more readable *_-
     * @author : Mostafa Tarawneh
     */

    private fun calclateResult() {
        if (amount_et.text.toString().isNotEmpty()) {
            var amount = amount_et.text.toString().toDouble()
            var toValue = values[auto_text_view.text.toString()]
            var fromvalue = values[frot_drop_down.text.toString()]
            var result =
                amount.times(
                    toValue!!.toString().toDouble()!!.div(fromvalue.toString().toDouble())
                )
            var formatedResult = String.format("%.2f", result)
            result_et.setText(formatedResult)

        } else {
            var snac = Snackbar.make(amount_et, "insert the amount ", Snackbar.LENGTH_SHORT)
            snac.show()
            snac.setAction("Ok") {
                amount_et.setError("insert value")
            }
        }
    }

    private fun populateDropDownMenu() {

        val adapter = ArrayAdapter(this, R.layout.dorp_down_text_item, listOFCurrncey)
        auto_text_view.setAdapter(adapter)
        frot_drop_down.setAdapter(adapter)
    }

    private fun intializviews() {
        frot_drop_down = findViewById(R.id.frontautoCompleteTextView)
        auto_text_view = findViewById(R.id.two_currncy)
        convert_btn = findViewById(R.id.convert_btn)
        amount_et = findViewById(R.id.edit_text_amount)
        result_et = findViewById(R.id.result_et)
        toolbar = findViewById(R.id.toolbar)
    }

    private fun itemclicklisnerforDropDownMenu() {
        amount_et.addTextChangedListener {
            calclateResult()
        }
        frot_drop_down.setOnItemClickListener { adapterView, view, i, l ->
            calclateResult()
        }
        auto_text_view.setOnItemClickListener { adapterView, view, i, l ->
            calclateResult()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share_action -> Toast.makeText(this, "share action clicked", Toast.LENGTH_SHORT)
                .show()

            R.id.setting_action -> Toast.makeText(
                this,
                "setting action clicked",
                Toast.LENGTH_SHORT
            ).show()

            R.id.contact_action -> Toast.makeText(
                this,
                "contact action clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
        return super.onOptionsItemSelected(item)
    }


}
