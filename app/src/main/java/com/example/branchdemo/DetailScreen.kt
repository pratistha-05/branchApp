package com.example.branchdemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.branchdemo.data.model.Item
import io.branch.indexing.BranchUniversalObject
import io.branch.referral.util.BRANCH_STANDARD_EVENT
import io.branch.referral.util.BranchContentSchema
import io.branch.referral.util.BranchEvent
import io.branch.referral.util.ContentMetadata
import io.branch.referral.util.CurrencyType

class DetailScreen : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail_screen)

    // Retrieve the item passed from the MainActivity
    val item = intent.getSerializableExtra("ITEM") as? Item

    // Find the TextViews to display the item details
    val itemNameTextView: TextView = findViewById(R.id.item_name_detail)
    val itemDescriptionTextView: TextView = findViewById(R.id.item_description_detail)

    // Display item details if the item is not null
    item?.let {
      itemNameTextView.text = it.name
      itemDescriptionTextView.text = it.description
    }

    val addToCartButton = findViewById<Button>(R.id.btn_cart)
    addToCartButton.setOnClickListener {
      addItemToCart(item?.id, item?.name, item?.description)
    }
  }

  private fun addItemToCart(id: String?, name: String?, description: String?) {
    val buo = BranchUniversalObject()
      .setCanonicalIdentifier("myprod/1234")
      .setCanonicalUrl("https://test_canonical_url")
      .setTitle("test_title")
      .setContentMetadata(
        ContentMetadata()

          .setProductName(name)
          .setProductCondition(ContentMetadata.CONDITION.EXCELLENT)
          .setProductVariant(description)
          .setContentSchema(BranchContentSchema.COMMERCE_PRODUCT)
      )
      .addKeyWord("keyword1")
      .addKeyWord("keyword2")


    //  Create an event
    BranchEvent(BRANCH_STANDARD_EVENT.ADD_TO_CART)

      .addCustomDataProperty("id", id)
      .addCustomDataProperty("description", description)
      .addContentItems(buo)
      .logEvent(applicationContext)
  }
}
