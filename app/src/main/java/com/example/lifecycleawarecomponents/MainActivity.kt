package com.example.lifecycleawarecomponents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    private val quoteText: TextView
        get() = findViewById(R.id.quotes_textView)

    private val quoteAuthor: TextView
        get() = findViewById(R.id.author_textView)

    private val shareBtn: FloatingActionButton
        get() = findViewById(R.id.share_btn)

    private val previousBtn: TextView
        get() = findViewById(R.id.previous_btn)

    private val nextBtn: TextView
        get() = findViewById(R.id.next_btn)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())

        previousBtn.setOnClickListener {
            setQuote(mainViewModel.previousQuote())
        }

        nextBtn.setOnClickListener {
            setQuote(mainViewModel.nextQuote())
        }

        shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
            startActivity(intent)
        }

    }

    fun setQuote(quote:Quotes){
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }




}