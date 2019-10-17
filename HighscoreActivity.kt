package com.example.user.guessthenumber

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_highscore.*
import java.io.File

class HighscoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscore)
        val path: File =applicationContext.filesDir
        val file= File(path,"savesss.txt")
        val readtxt= String(file.readBytes())
        var i:Int=0
        var savedHighsc= arrayOf("","","")
        for (v in 0..2) {
            while (i<readtxt.length && readtxt[i] != '/') {
                savedHighsc[v] = savedHighsc[v] + readtxt[i]
                i++
            }
            i++
        }
        if(savedHighsc[0].length==0){
            savedHighsc[1]="N/A"
        }
        textView2.text="easy - "+savedHighsc[0]
        if(savedHighsc[1].length==0){
            savedHighsc[1]="N/A"
        }
        textView3.text="medium - "+savedHighsc[1]
        if(savedHighsc[2].length==0){
            savedHighsc[2]="N/A"
        }
        textView4.text="hard - "+savedHighsc[2]
        buttonBack2.setOnClickListener{
            finish()

        }
    }
}
