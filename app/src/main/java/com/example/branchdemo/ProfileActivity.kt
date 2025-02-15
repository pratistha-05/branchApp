package com.example.branchdemo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.branch.referral.util.BranchEvent

class ProfileActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_profile)

    var textView:TextView=findViewById(R.id.detail_tv_value)

    val itemId = intent.getStringExtra("ITEM_ID")

    textView.text="Value: $itemId"

  }

  override fun onStart() {
    super.onStart()

    // Track when the main screen is viewed
    BranchEvent("profile_screen_view")
      .addCustomDataProperty("screen_name", "Profile Activity")
      .logEvent(applicationContext)
  }

}