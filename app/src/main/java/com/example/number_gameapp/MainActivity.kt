package com.example.number_gameapp

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var input = ArrayList<String>()
        var quess =findViewById<Button>(R.id.button)
        var inin =findViewById<EditText>(R.id.input)
        val rv= findViewById<RecyclerView>(R.id.rvMain)
        var qgue=(0..11).random()
        var limit=3


        val dialogBuilder = AlertDialog.Builder(this)

        val dialogBuilderwin = AlertDialog.Builder(this)

        val dialogBuilderlose = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Enter numbers ONLY")

            .setPositiveButton("i understand", DialogInterface.OnClickListener {
                    dialog, id -> null })

            .setNegativeButton("NO", DialogInterface.OnClickListener {
                    dialog, id -> exitProcess(0) })

        dialogBuilderwin.setMessage("Congrats you got the correct answer do you want to play again? ")
            .setPositiveButton("play again",DialogInterface.OnClickListener {
                dialog, id ->  input.clear(); limit=3 ;qgue=(0..11).random(); rv.adapter = RVAdapter(input) })
            .setNegativeButton("NO",DialogInterface.OnClickListener{
                dialog, id -> exitProcess(0)})

        dialogBuilderlose.setMessage("do you want to play again? ")
            .setPositiveButton("play again",DialogInterface.OnClickListener{
                    dialog, id ->  input.clear(); limit=3 ;qgue=(0..11).random(); rv.adapter = RVAdapter(input) })
            .setNegativeButton("NO",DialogInterface.OnClickListener{
                    dialog ,id -> exitProcess(0)})

        val nullinput = dialogBuilder.create()
        val wind=dialogBuilderwin.create()
        val lossd=dialogBuilderlose.create()

        nullinput.setTitle("wrong input")
        wind.setTitle("you win")
        lossd.setTitle("you lost")





        rv.adapter = RVAdapter(input)
        rv.layoutManager = LinearLayoutManager(this)


        quess.setOnClickListener{

            if(inin.text.toString().toIntOrNull()==null ){

                nullinput.show()
            }else{
            input.add("you guessed ${inin.text}")
                rv.adapter = RVAdapter(input)
                if(inin.text.toString().toInt()==qgue){
                    input.add("your guess is correct")
                    rv.adapter = RVAdapter(input)
                    wind.show()
                }else{
                    limit--
                    input.add("you have $limit guesses left")
                    rv.adapter = RVAdapter(input)
                    if (limit==0){
                        lossd.show()
                    }
                }
            }
            inin.text.clear()
        }
    }
}