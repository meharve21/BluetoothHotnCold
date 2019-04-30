package com.example.hotncold

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.support.v7.app.AlertDialog

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var pings = 0
    var playersW =0
    var playersL = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)



        ping.setOnClickListener{
            pings++
            pingCount.text = pings.toString()
            if (pings > 5){
                playersL++
                Toast.makeText(applicationContext,
                    "You've lost " + playersL + " times", Toast.LENGTH_SHORT).show()
                pings = 0


            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_About ->{
                //val toast= Toast.makeText(applicationContext, "Buzzzzzz", Toast.LENGTH_LONG)
                //toast.show()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("About Hot n Cold")
                builder.setMessage("1. Connect to your friend's device via bluetooth\n"  +
                        "2. Find companion following beeps and colors\n" +
                        "3. Win by turning the box green")
                //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                //builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    //Toast.makeText(applicationContext,
                  //      android.R.string.yes, Toast.LENGTH_SHORT).show()
                //}

                //builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    //Toast.makeText(applicationContext,
                  //      android.R.string.no, Toast.LENGTH_SHORT).show()
                //}

                builder.setNeutralButton("Continue") { dialog, which ->
                    Toast.makeText(applicationContext,
                        "Alright", Toast.LENGTH_SHORT).show()
                }
                builder.show()
            true}
            else -> super.onOptionsItemSelected(item)
        }
    }
}
