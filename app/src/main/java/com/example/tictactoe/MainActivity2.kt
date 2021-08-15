package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val name1 = "Name1"
        const val name2 = "Name2"
    }

    var PLAYER = true
    var TURN_COUNT = 0
    var boardStatus = Array(3){ IntArray(3)}
    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        board = arrayOf(
            arrayOf(btn11, btn12, btn13),
            arrayOf(btn21, btn22, btn23),
            arrayOf(btn31, btn32, btn33)
        )
        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        btnReset.setOnClickListener{
            PLAYER = true
            TURN_COUNT = 0
            initializeBoardStatus()
            updateDisplay("New Game")
        }
    }

    private fun initializeBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1
            }
        }
        for(i in board){
            for(button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean){
        val text = if (player) "X" else "O"
        val value = if (player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value
    }

    private fun updateDisplay(string: String){
        DisplayTv.text = string
        if(string.contains("Winner")){
            disableButton()
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn11 -> {
                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.btn12 -> {
                updateValue(row = 0, col = 1, player = PLAYER)
            };
            R.id.btn13 -> {
                updateValue(row = 0, col = 2, player = PLAYER)
            }
            R.id.btn21 -> {
                updateValue(row = 1, col = 0, player = PLAYER)
            }
            R.id.btn22 -> {
                updateValue(row = 1, col = 1, player = PLAYER)
            }
            R.id.btn23 -> {
                updateValue(row = 1, col = 2, player = PLAYER)
            }
            R.id.btn31 -> {
                updateValue(row = 2, col = 0, player = PLAYER)
            }
            R.id.btn32 -> {
                updateValue(row = 2, col = 1, player = PLAYER)
            }
            R.id.btn33 -> {
                updateValue(row = 2, col = 2, player = PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER = !PLAYER

        if(PLAYER){
            updateDisplay("Person X Turn")
        }
        else{
            updateDisplay("Person O Turn")
        }

        if(TURN_COUNT == 9){
            updateDisplay("Game Over")
        }
        checkWinner()
    }

    private fun checkWinner() {
        //Rows
        for(i in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
                if(boardStatus[i][0] == 1){
                    updateDisplay("Person X Winner")
                    break
                }
                else if(boardStatus[i][0] == 0){
                    updateDisplay("Person O Winner")
                    break
                }
            }
        }
        //columns
        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
                if(boardStatus[0][i] == 1){
                    updateDisplay("Player X Winner")
                    break
                }
                else if(boardStatus[0][i] == 0){
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }
        //First diagonal
        for(i in 0..2){
            if((boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2])){
                if(boardStatus[0][0] == 1){
                    updateDisplay("Player X Winner")
                    break
                }
                else if(boardStatus[0][0] == 0){
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }
        //Second Diagonal
        for(i in 0..2){
            if((boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0])){
                if(boardStatus[0][2] == 1){
                    updateDisplay("Player X Winner")
                    break
                }
                else if(boardStatus[0][2] == 0){
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }
    }

    private fun disableButton(){
        for(i in board){
            for(button in i){
                button.isEnabled = false
            }
        }
    }
}