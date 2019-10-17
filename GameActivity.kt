package com.example.user.guessthenumber

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_game.*
import java.io.File
import java.io.IOException
import java.util.*

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val diff:Int=intent.getIntExtra("Difficulty",0)
        val randNum: Random = Random()
        var num:Int=0
        var guessNum: Int =0
        var tries:Int=0
        val fadeIn: AlphaAnimation = AlphaAnimation(0f,1f)
        val fadeOut: AlphaAnimation = AlphaAnimation(1f,0f)
        val diffHints= arrayOf("Guess a number between 1 and 100","Guess a number between 1 and 10000","Guess a number between 1 and 1000000")
        val path: File = applicationContext.filesDir
        val file: File = File(path,"savesss.txt")
        var readtxt:String
        var won:Boolean=false
        val savedHighsc=arrayOf("","","")
        var i:Int
        when(diff){
            0 -> num=randNum.nextInt(100)
            1 -> num=randNum.nextInt(10000)
            2 -> num=randNum.nextInt(1000000)
        }
        textViewGameHint.text=diffHints[diff]

        guessButton.setOnClickListener{
            if(!won) {
                if (editText.text[0] > '1' && editText.text.length == 7) {
                    textViewGame.text = "Guess a valid number"
                } else {
                    guessNum = editText.text.toString().toInt()
                    tries++
                    if (guessNum > num) {
                        textViewGame.text = "You guessed higher"

                    } else if (guessNum < num) {
                        textViewGame.text = "You guessed lower"

                    } else {
                        textViewGame.text = "Congratulations you guessed right in $tries tries"
                        try {
                            readtxt= String(file.readBytes())
                            i=0
                            for (v in 0..2) {
                                while (i<readtxt.length && readtxt[i] != '/') {
                                    savedHighsc[v] = savedHighsc[v] + readtxt[i]
                                    i++
                                }
                                i++
                            }

                            if (savedHighsc[diff].length==0||savedHighsc[diff].toInt() > tries) {
                                savedHighsc[diff] = tries.toString()
                            }
                            file.writeBytes((savedHighsc[0]+"/"+savedHighsc[1]+"/"+savedHighsc[2]).toByteArray())

                        }catch ( e: IOException){
                            textViewGame.text=e.message
                        }



                    }



                }
                textViewGame.startAnimation(fadeOut)
                textViewGame.startAnimation(fadeIn)
                fadeIn.duration = 1200
                fadeIn.fillAfter = true
                fadeOut.duration = 1200
                fadeOut.fillAfter = true
                fadeOut.startOffset = 4200 + fadeIn.startOffset
            }


        }
        buttonBack.setOnClickListener{
            finish()
        }
        buttonReset.setOnClickListener {
            when(diff){
                0 -> num=randNum.nextInt(100)
                1 -> num=randNum.nextInt(10000)
                2 -> num=randNum.nextInt(1000000)
            }
            tries=0
            textViewGame.text=""
            won=false
        }

    }
}
