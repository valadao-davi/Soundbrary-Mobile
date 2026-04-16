package com.example.soundbrary

import android.content.Intent
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val heroSection = findViewById<ViewGroup>(R.id.heroSection)
        val btnBurger = findViewById<LinearLayout>(R.id.icBurguerMenu)
        val waveHero = findViewById<ImageView>(R.id.waveHeroBottom)
        val menuOptions = findViewById<LinearLayout>(R.id.menuOptions)
        val heroTitle = findViewById<View>(R.id.heroTitle)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnEntrar = findViewById<View>(R.id.btn_entrar)

        btnEntrar.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        var isMenuOpen = false

        btnBurger.setOnClickListener {

            val transition = AutoTransition()
            transition.duration = 400
            TransitionManager.beginDelayedTransition(heroSection, transition)

            val params = heroSection.layoutParams

            if (!isMenuOpen) {
                params.height = dpToPx(500)
                menuOptions.visibility = View.VISIBLE
                waveHero.setBackgroundColor(resources.getColor(R.color.roxo_soundbrary, null))

                updateContentAnchor(heroTitle, R.id.menuOptions)
            } else {
                params.height = dpToPx(405)
                menuOptions.visibility = View.GONE
                waveHero.setBackgroundColor(0)

                updateContentAnchor(heroTitle, R.id.topBar)
            }

            heroSection.layoutParams = params
            isMenuOpen = !isMenuOpen
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    private fun updateContentAnchor(view: View, anchorId: Int) {
        val lp = view.layoutParams as android.widget.RelativeLayout.LayoutParams
        lp.addRule(android.widget.RelativeLayout.BELOW, anchorId)
        view.layoutParams = lp
    }
    }