package vcmsa.projects.exhaustle

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import java.net.URL
import java.util.concurrent.Executors

class GameWindow : AppCompatActivity() {
    val guessNum : Number = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_window)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun wordleStart(){
        val guessedWord = findViewById<TextView>(R.id.txtGuess).text
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val url = URL("https:") //insert the actual url for the api later
            val json = url.readText()
            Log.d("GameWIndow", json.toString())
            val ValidationArr = Gson().fromJson(json, Array<String>::class.java)
        }

    }
}