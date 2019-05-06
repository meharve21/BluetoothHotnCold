package com.example.hotncold

import android.graphics.Color
import android.graphics.Color.rgb
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.support.v7.app.AlertDialog
import android.widget.SeekBar

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity() {

    var pings = 0
    var playersW =0
    var playersL = 0
    var seekVal =0
    var color1 = arrayListOf(0, 219, 255)
    var color2 = arrayListOf(255, 0, 0)
    //this array is for the merged one
    var color3 = intArrayOf(0, 0, 0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val filename = ".file.txt"
        val filesDir = "."
        val fin = File(filesDir, filename)
        val sc = Scanner(fin)
        var line:String
        val fout = File(filesDir, filename)
        val out = fout.printWriter()
        var file = File(filename)
        //file.createNewFile()
       val isNewFileCreated :Boolean = file.createNewFile()
        if (isNewFileCreated) {


            ping.setOnClickListener {
                if (pings < 5) {
                    pings++
                    pingCount.text = pings.toString()
                } else {
                    pingCount.text = "LOSER"
                    playersL++
                    Toast.makeText(
                        applicationContext,
                        "You've lost " + playersL + " times", Toast.LENGTH_SHORT
                    ).show()
                    heatView.setBackgroundColor(Color.rgb(170, 0, 0))
                    smile.setImageResource(R.drawable.loser)
                    pings = 0
                    out.println("loss" + playersL)
                    out.close()

                }
                if (seekVal > 99) {
                    heatView.setBackgroundColor(Color.rgb(105, 190, 40))
                    smile.setImageResource(R.drawable.trophy)
                    playersW++
                    out.println("wins" + playersW)
                    out.close()
                }

            }

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onStopTrackingTouch(seekBarBlue: SeekBar?) {
                }

                override fun onStartTrackingTouch(seekBarBlue: SeekBar?) {
                }


                override fun onProgressChanged(seekBarBlue: SeekBar, i: Int, b: Boolean) {
                    seekVal = i
                    mergeValues()
                    heatView.setBackgroundColor(Color.rgb(color3[0], color3[1], color3[2]))
                    if (seekVal < 25) {
                        smile.setImageResource(R.drawable.smile3)
                    } else if (seekVal > 75) {
                        smile.setImageResource(R.drawable.smile1)
                    } else {
                        smile.setImageResource(R.drawable.smile2)
                    }
                    pingCount.text = seekVal.toString()


                }

            })
        }
    }

    fun mergeValues(){
        //0,50,100
        //50,150,200
        val percent1 = (100 - seekVal)
        val percent2 = seekVal
        color3[0] = ((percent2 * color2[0]) + (percent1 * color1[0])) / 100
        color3[1] = ((percent2 * color2[1]) + (percent1 * color1[1])) / 100
        color3[2] = ((percent2 * color2[2]) + (percent1 * color1[2])) / 100
        //For proof that the percents are right for color merging
        //val coast= Toast.makeText(applicationContext, "$percent1 , $percent2", Toast.LENGTH_LONG)
        // coast.show()
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
