package com.example.user.guessthenumber

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var difficulty: Int=0

        val file: File = File(applicationContext.filesDir,"savesss.txt")
        if(!file.exists())file.createNewFile()

        val options = arrayOf("easy","medium","hard")

        diffSpinner.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        diffSpinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                difficulty=0
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                difficulty=p2
            }
        }
        playButton.setOnClickListener{
            val intent = Intent(this,GameActivity()::class.java)
            intent.putExtra("Difficulty",difficulty)
            startActivity(intent)
        }
        highscoresButton.setOnClickListener{
            val intent2 = Intent(this,HighscoreActivity()::class.java)
            startActivity(intent2)
        }
        exitButton.setOnClickListener{
            finish()
            System.exit(0)
        }
    }
}
