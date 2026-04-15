package com.example.soundbrary

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val webView1 = findViewById<WebView>(R.id.web_view_video1)

        webView1.webViewClient = WebViewClient()
        webView1.settings.javaScriptEnabled = true
        webView1.settings.domStorageEnabled = true
        webView1.settings.mediaPlaybackRequiresUserGesture = false

        val videoId = "k49I5m1J6Is"

        val html = """
                    <html>
                    <body style="margin:0;padding:0;">
                    <iframe width="100%" height="100%"
                    src="https://www.youtube.com/embed/$videoId"
                    frameborder="0"
                    allowfullscreen>
                    </iframe>
                    </body>
                    </html>
                    """

        webView1.loadData(html, "text/html", "utf-8")
    }
}