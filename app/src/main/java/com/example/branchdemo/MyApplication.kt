package com.example.branchdemo

import android.app.Application
import io.branch.referral.Branch

class MyApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    Branch.getAutoInstance(this)
  }
}