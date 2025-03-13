package vcmsa.projects.exhaustle

import android.graphics.Color
<<<<<<< HEAD
import android.net.http.UrlRequest
=======
>>>>>>> master
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import java.net.URL
import java.util.concurrent.Executors

class GameWindow : AppCompatActivity() {
    var guessNum : Number = 1
    lateinit var FinalAnswer : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_window)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val executor2 = Executors.newSingleThreadExecutor()
        executor2.execute {
<<<<<<< HEAD
            try{
                val url = URL("http://10.0.2.2:5045/Word/GetSingle") // /Word/GetSingle
                val json = url.readText()

                //Log.d("GameWindow", "Randomly generated word: $json")
                if (json.isNotEmpty())
                {
                    FinalAnswer = Gson().fromJson(json, String::class.java)
                    Log.d("GameWindow", "Final Answer: $FinalAnswer")
                }
                else
                {
                    Log.e("GameWindow", "API response is empty.")
                }
        } catch (e: Exception) {
            Log.e("GameWindow", "Error in fetching data", e)
        }
    }
        var btnSubmit2 : Button = findViewById(R.id.btnSubmit2)
        btnSubmit2.setOnClickListener() {
            wordleStart()
        }
    }
=======
            val url = URL("https://localhost:32771/Word/GetSingle") // /Word/GetSingle
            val json = url.readText()
            FinalAnswer = Gson().fromJson(json, String::class.java)
        }
        var btnSubmit2 : Button = findViewById(R.id.btnSubmit2)
        btnSubmit2.setOnClickListener() {
            wordleStart()
        }
    }
>>>>>>> master

    fun wordleStart(){
        val guessedWord = findViewById<TextView>(R.id.txtGuess).text.toString()
        val guessedWordArray = guessedWord.toCharArray()
<<<<<<< HEAD

        Log.e("GameWindow", "Word guessed:" + guessedWord)
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            //val url = UrlRequest.Builder(){"Post", "http://10.0.2.2:5045/Word/ValidateWord", guessedWord }//insert the actual url for the api and then add /Word/ValidateWord?enteredWord=" + guessedWord inside the ""
            val url = URL("http://10.0.2.2:5045/Word/ValidateWord/$guessedWord")
            val json = url.readText()
            Log.d("GameWindow", "Word here:" + json.toString())
=======
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val url = URL("https://localhost:32771/Word/ValidateWord?enteredWord=" + guessedWord) //insert the actual url for the api and then add /Word/ValidateWord?enteredWord=" + guessedWord inside the ""
            val json = url.readText()
            Log.d("GameWindow", json.toString())
>>>>>>> master
            val ValidationArr = Gson().fromJson(json, Array<String>::class.java)
            var colourArray = validationF(ValidationArr)
            displayValidation(colourArray, guessedWordArray)
        }
    }



    fun validationF(valid : Array<String>): Array<String> {
        var colourNumbers = arrayOf("","","","","");
        for (i in 0 .. colourNumbers.size-1){
            if (valid[i].equals(" T "))
            {
                colourNumbers[i] = Color.GREEN.toString();

            } else if (valid[i].equals(" P "))
            {
                colourNumbers[i] = Color.YELLOW.toString();

            } else if (valid[i].equals(" F "))
            {
                colourNumbers[i] = Color.GRAY.toString();
            }
        }
        return colourNumbers;
    }
    fun displayValidation(valid: Array<String>, letters: CharArray){
        lateinit var trackNum : Number
        if (guessNum==1){
            findViewById<TextView>(R.id.editW1Letter1).setBackgroundColor(valid[1].toInt())
            findViewById<TextView>(R.id.editW1Letter1).setText(letters[1].toString())
            findViewById<TextView>(R.id.editW1Letter2).setBackgroundColor(valid[2].toInt())
            findViewById<TextView>(R.id.editW1Letter2).setText(letters[2].toString())
            findViewById<TextView>(R.id.editW1Letter3).setBackgroundColor(valid[3].toInt())
            findViewById<TextView>(R.id.editW1Letter3).setText(letters[3].toString())
            findViewById<TextView>(R.id.editW1Letter4).setBackgroundColor(valid[4].toInt())
            findViewById<TextView>(R.id.editW1Letter4).setText(letters[4].toString())
            findViewById<TextView>(R.id.editW1Letter5).setBackgroundColor(valid[5].toInt())
            findViewById<TextView>(R.id.editW1Letter5).setText(letters[5].toString())
            trackNum= 2
        } else if (guessNum==2){
            findViewById<TextView>(R.id.editW2Letter1).setBackgroundColor(valid[1].toInt())
            findViewById<TextView>(R.id.editW2Letter1).setText(letters[1].toString())
            findViewById<TextView>(R.id.editW2Letter2).setBackgroundColor(valid[2].toInt())
            findViewById<TextView>(R.id.editW2Letter2).setText(letters[2].toString())
            findViewById<TextView>(R.id.editW2Letter3).setBackgroundColor(valid[3].toInt())
            findViewById<TextView>(R.id.editW2Letter3).setText(letters[3].toString())
            findViewById<TextView>(R.id.editW2Letter4).setBackgroundColor(valid[4].toInt())
            findViewById<TextView>(R.id.editW2Letter4).setText(letters[4].toString())
            findViewById<TextView>(R.id.editW2Letter5).setBackgroundColor(valid[5].toInt())
            findViewById<TextView>(R.id.editW2Letter5).setText(letters[5].toString())
            trackNum= 3
        } else if (guessNum==3){
            findViewById<TextView>(R.id.editW3Letter1).setBackgroundColor(valid[1].toInt())
            findViewById<TextView>(R.id.editW3Letter1).setText(letters[1].toString())
            findViewById<TextView>(R.id.editW3Letter2).setBackgroundColor(valid[2].toInt())
            findViewById<TextView>(R.id.editW3Letter2).setText(letters[2].toString())
            findViewById<TextView>(R.id.editW3Letter3).setBackgroundColor(valid[3].toInt())
            findViewById<TextView>(R.id.editW3Letter3).setText(letters[3].toString())
            findViewById<TextView>(R.id.editW3Letter4).setBackgroundColor(valid[4].toInt())
            findViewById<TextView>(R.id.editW3Letter4).setText(letters[4].toString())
            findViewById<TextView>(R.id.editW3Letter5).setBackgroundColor(valid[5].toInt())
            findViewById<TextView>(R.id.editW3Letter5).setText(letters[5].toString())
            trackNum= 4
        } else if (guessNum==4){
            findViewById<TextView>(R.id.editW4Letter1).setBackgroundColor(valid[1].toInt())
            findViewById<TextView>(R.id.editW4Letter1).setText(letters[1].toString())
            findViewById<TextView>(R.id.editW4Letter2).setBackgroundColor(valid[2].toInt())
            findViewById<TextView>(R.id.editW4Letter2).setText(letters[2].toString())
            findViewById<TextView>(R.id.editW4Letter3).setBackgroundColor(valid[3].toInt())
            findViewById<TextView>(R.id.editW4Letter3).setText(letters[3].toString())
            findViewById<TextView>(R.id.editW4Letter4).setBackgroundColor(valid[4].toInt())
            findViewById<TextView>(R.id.editW4Letter4).setText(letters[4].toString())
            findViewById<TextView>(R.id.editW4Letter5).setBackgroundColor(valid[5].toInt())
            findViewById<TextView>(R.id.editW4Letter5).setText(letters[5].toString())
            trackNum=5
        } else if (guessNum==5){
            findViewById<TextView>(R.id.editW5Letter1).setBackgroundColor(valid[1].toInt())
            findViewById<TextView>(R.id.editW5Letter1).setText(letters[1].toString())
            findViewById<TextView>(R.id.editW5Letter2).setBackgroundColor(valid[2].toInt())
            findViewById<TextView>(R.id.editW5Letter2).setText(letters[2].toString())
            findViewById<TextView>(R.id.editW5Letter3).setBackgroundColor(valid[3].toInt())
            findViewById<TextView>(R.id.editW5Letter3).setText(letters[3].toString())
            findViewById<TextView>(R.id.editW5Letter4).setBackgroundColor(valid[4].toInt())
            findViewById<TextView>(R.id.editW5Letter4).setText(letters[4].toString())
            findViewById<TextView>(R.id.editW5Letter5).setBackgroundColor(valid[5].toInt())
            findViewById<TextView>(R.id.editW5Letter5).setText(letters[5].toString())
            trackNum==6

        } else if (guessNum==6){
            findViewById<TextView>(R.id.txtResponse).setText(FinalAnswer)
        }
        guessNum=trackNum
    }


}