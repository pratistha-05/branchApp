package com.example.branchdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.branchdemo.adapter.ItemAdapter
import com.example.branchdemo.data.model.Item
import io.branch.referral.Branch

class MainActivity : AppCompatActivity() {


  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: ItemAdapter
  private lateinit var itemList: List<Item>


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)
    itemList = listOf(
      Item("1", "Item 1", "Description for item 1"),
      Item("2", "Item 2", "Description for item 2"),
      Item("3", "Item 3", "Description for item 3"),
      Item("4", "Item 5", "Description for item 4"),
      Item("6", "Item 6", "Description for item 6")


    )

    // Initialize RecyclerView
    recyclerView = findViewById(R.id.recycler_view)
    recyclerView.layoutManager = LinearLayoutManager(this)
    adapter = ItemAdapter(itemList)
    recyclerView.adapter = adapter
  }

  override fun onStart() {
    super.onStart()

    Branch.sessionBuilder(this).withCallback { referringParams, error ->
      if (error == null) {
        if (referringParams != null) {
          val screen = referringParams.getString("screen")
          val id = referringParams.getString("id")

          if (screen == "profile_screen") {
            openDetailScreen(id)
          }
        }
      } else {
        Log.e("BRANCH_SDK", "Error: ${error.message}")
      }
    }.withData(this.intent?.data).init()
  }



  private fun openDetailScreen(id: String?) {
    val intent = Intent(this, ProfileActivity::class.java).apply {
      putExtra("ITEM_ID", id)
    }
    startActivity(intent)
  }
}
