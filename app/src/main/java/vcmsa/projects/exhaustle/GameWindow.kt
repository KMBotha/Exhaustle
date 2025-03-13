package vcmsa.projects.exhaustle

import android.graphics.Color
import android.net.http.UrlRequest
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class GameWindow : AppCompatActivity() {
    var guessNum: Number = 1
    lateinit var FinalAnswer: String
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
            try {
                val url =
                    URL("https://prjwordleice20250313112255-gxewe9bya6h5a3as.ukwest-01.azurewebsites.net/Word/GetSingle") // /Word/GetSingle
                val json = url.readText()

                //Log.d("GameWindow", "Randomly generated word: $json")
                if (json.isNotEmpty()) {
                    FinalAnswer = Gson().fromJson(json, String::class.java)
                    Log.d("GameWindow", "Final Answer: $FinalAnswer")
                } else {
                    Log.e("GameWindow", "API response is empty.")
                }
            } catch (e: Exception) {
                Log.e("GameWindow", "Error in fetching data", e)
            }
        }
        var btnSubmit2: Button = findViewById(R.id.btnSubmit2)
        btnSubmit2.setOnClickListener() {
            wordleStart()
        }
    }

    fun wordleStart() {
        val guessedWord = findViewById<TextView>(R.id.txtGuess).text.toString()
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            try {
                val url =
                    URL("https://prjwordleice20250313112255-gxewe9bya6h5a3as.ukwest-01.azurewebsites.net/Word/ValidateWord?enteredWord=$guessedWord")
                val jsonInputString = Gson().toJson(guessedWord)

                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.doOutput = true
                connection.setRequestProperty("Content-Type", "application/json")

                // Send the request body
                connection.outputStream.use { os ->
                    os.write(jsonInputString.toByteArray())
                    os.flush()
                }

                // Read the response
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                Log.d("GameWindow", "Answer: $response")

                val validationArr: Array<String> =
                    Gson().fromJson(response, Array<String>::class.java)
                val colourArray = validationF(validationArr)

                runOnUiThread {
                    displayValidation(colourArray, guessedWord.toCharArray())
                }
            } catch (e: Exception) {
                Log.e("GameWindow", "Error in sending POST request", e)
            }
        }
    }


    fun validationF(valid: Array<String>): Array<Int> {
        val colourNumbers = Array(5) { Color.GRAY }  // Default color is gray for all letters

        for (i in valid.indices) {
            when (valid[i].trim()) {
                "T" -> colourNumbers[i] = Color.GREEN
                "P" -> colourNumbers[i] = Color.YELLOW
                "F" -> colourNumbers[i] = Color.GRAY
            }
        }
        return colourNumbers
    }

    fun displayValidation(valid: Array<Int>, letters: CharArray) {
        lateinit var trackNum: Number

        val numLetters = valid.size
        if (guessNum == 1) {
            // First word guess
            findViewById<TextView>(R.id.editW1Letter1).setBackgroundColor(valid[0].toInt())
            findViewById<TextView>(R.id.editW1Letter1).text = letters[0].toString()

            findViewById<TextView>(R.id.editW1Letter2).setBackgroundColor(valid[1].toInt())
            findViewById<TextView>(R.id.editW1Letter2).text = letters[1].toString()

            findViewById<TextView>(R.id.editW1Letter3).setBackgroundColor(valid[2].toInt())
            findViewById<TextView>(R.id.editW1Letter3).text = letters[2].toString()

            findViewById<TextView>(R.id.editW1Letter4).setBackgroundColor(valid[3].toInt())
            findViewById<TextView>(R.id.editW1Letter4).text = letters[3].toString()

            findViewById<TextView>(R.id.editW1Letter5).setBackgroundColor(valid[4].toInt())
            findViewById<TextView>(R.id.editW1Letter5).text = letters[4].toString()
            trackNum = 2
        }
        else if (guessNum == 2) {
            // Second word guess
            findViewById<TextView>(R.id.editW2Letter1).setBackgroundColor(valid[0].toInt())
            findViewById<TextView>(R.id.editW2Letter1).text = letters[0].toString()

            findViewById<TextView>(R.id.editW2Letter2).setBackgroundColor(valid[1].toInt())
            findViewById<TextView>(R.id.editW2Letter2).text = letters[1].toString()

            findViewById<TextView>(R.id.editW2Letter3).setBackgroundColor(valid[2].toInt())
            findViewById<TextView>(R.id.editW2Letter3).text = letters[2].toString()

            findViewById<TextView>(R.id.editW2Letter4).setBackgroundColor(valid[3].toInt())
            findViewById<TextView>(R.id.editW2Letter4).text = letters[3].toString()

            findViewById<TextView>(R.id.editW2Letter5).setBackgroundColor(valid[4].toInt())
            findViewById<TextView>(R.id.editW2Letter5).text = letters[4].toString()
            trackNum = 3
        }
        else if (guessNum == 3) {
            // Third word guess
            findViewById<TextView>(R.id.editW3Letter1).setBackgroundColor(valid[0].toInt())
            findViewById<TextView>(R.id.editW3Letter1).text = letters[0].toString()

            findViewById<TextView>(R.id.editW3Letter2).setBackgroundColor(valid[1].toInt())
            findViewById<TextView>(R.id.editW3Letter2).text = letters[1].toString()

            findViewById<TextView>(R.id.editW3Letter3).setBackgroundColor(valid[2].toInt())
            findViewById<TextView>(R.id.editW3Letter3).text = letters[2].toString()

            findViewById<TextView>(R.id.editW3Letter4).setBackgroundColor(valid[3].toInt())
            findViewById<TextView>(R.id.editW3Letter4).text = letters[3].toString()

            findViewById<TextView>(R.id.editW3Letter5).setBackgroundColor(valid[4].toInt())
            findViewById<TextView>(R.id.editW3Letter5).text = letters[4].toString()
            trackNum = 4
        }
        else if (guessNum == 4) {
            // Fourth word guess
            findViewById<TextView>(R.id.editW4Letter1).setBackgroundColor(valid[0].toInt())
            findViewById<TextView>(R.id.editW4Letter1).text = letters[0].toString()

            findViewById<TextView>(R.id.editW4Letter2).setBackgroundColor(valid[1].toInt())
            findViewById<TextView>(R.id.editW4Letter2).text = letters[1].toString()

            findViewById<TextView>(R.id.editW4Letter3).setBackgroundColor(valid[2].toInt())
            findViewById<TextView>(R.id.editW4Letter3).text = letters[2].toString()

            findViewById<TextView>(R.id.editW4Letter4).setBackgroundColor(valid[3].toInt())
            findViewById<TextView>(R.id.editW4Letter4).text = letters[3].toString()

            findViewById<TextView>(R.id.editW4Letter5).setBackgroundColor(valid[4].toInt())
            findViewById<TextView>(R.id.editW4Letter5).text = letters[4].toString()
            trackNum = 5
        }
        else if (guessNum == 5) {
            // Fifth word guess
            findViewById<TextView>(R.id.editW5Letter1).setBackgroundColor(valid[0].toInt())
            findViewById<TextView>(R.id.editW5Letter1).text = letters[0].toString()

            findViewById<TextView>(R.id.editW5Letter2).setBackgroundColor(valid[1].toInt())
            findViewById<TextView>(R.id.editW5Letter2).text = letters[1].toString()

            findViewById<TextView>(R.id.editW5Letter3).setBackgroundColor(valid[2].toInt())
            findViewById<TextView>(R.id.editW5Letter3).text = letters[2].toString()

            findViewById<TextView>(R.id.editW5Letter4).setBackgroundColor(valid[3].toInt())
            findViewById<TextView>(R.id.editW5Letter4).text = letters[3].toString()

            findViewById<TextView>(R.id.editW5Letter5).setBackgroundColor(valid[4].toInt())
            findViewById<TextView>(R.id.editW5Letter5).text = letters[4].toString()
            trackNum = 6
        }
        else if (guessNum == 6) {
            // Show final answer
            findViewById<TextView>(R.id.txtResponse).text = FinalAnswer
        }

        guessNum = trackNum
    }
}