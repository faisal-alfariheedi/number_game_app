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

        //dialog builder here
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)
        //////////////////////////////////
        val dialogBuilderwin = AlertDialog.Builder(this)
        ////////////////////////////////
        val dialogBuilderlose = AlertDialog.Builder(this)
        // then we set up the input
//        val inp = Button(this)  not needed
        ///////////////////////////////////////
//        val inpw = Button(this) not needed
        ////////////////////////////////////
//        val inpl = Button(this) not needed
        // here we set the message of our alert dialog
        dialogBuilder.setMessage("Enter numbers ONLY")
            // positive button text and action
            .setPositiveButton("i understand", DialogInterface.OnClickListener {
                    dialog, id -> null })
            // negative button text and action
            .setNegativeButton("NO", DialogInterface.OnClickListener {
                    dialog, id -> exitProcess(0) })
        ////////////////////////////////
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
        // create dialog box
        val nullinput = dialogBuilder.create()
        val wind=dialogBuilderwin.create()
        val lossd=dialogBuilderlose.create()
        // set title for alert dialog box
        nullinput.setTitle("wrong input")
        wind.setTitle("you win")
        lossd.setTitle("you lost")

        // add the button
//        nullinput.setView(inp) not needed
//        wind.setView(inpw)
//        lossd.setView(inpl)



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