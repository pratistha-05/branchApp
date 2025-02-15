package com.example.branchdemo.data.model

import java.io.Serializable

data class Item(
  val id: String,
  val name: String,
  val description: String
): Serializable