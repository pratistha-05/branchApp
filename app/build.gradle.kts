import java.util.Properties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
}

android {
  namespace = "com.example.branchdemo"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.example.branchdemo"
    minSdk = 21
    targetSdk = 35
    versionName = "1.0"
    versionCode = 1

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  val propFile = file("signing.properties")
  val props = if (propFile.exists()) {
    val props = Properties()
    props.load(propFile.inputStream())
    props
  } else {
    null
  }

  if (props != null) {
    val pStoreFile = props["STORE_FILE"] as? String
    val pStorePassword = props["STORE_PASSWORD"] as? String
    val pKeyAlias = props["KEY_ALIAS"] as? String
    val pKeyPassword = props["KEY_PASSWORD"] as? String
    if (!pStoreFile.isNullOrBlank()
      && !pStorePassword.isNullOrBlank()
      && !pKeyAlias.isNullOrBlank()
      && !pKeyPassword.isNullOrBlank()
    ) {
      signingConfigs {
        create("release") {
          storeFile = file(pStoreFile)
          storePassword = pStorePassword
          keyAlias = pKeyAlias
          keyPassword = pKeyPassword
        }
      }
    }
  }


  buildTypes {
    release {
      signingConfig = signingConfigs.findByName("release")
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    compose = true
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  implementation(libs.androidx.media3.common.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.constraintlayout)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)

//  implementation(project(":Branch-SDK"))
  implementation("com.google.android.gms:play-services-ads-identifier:18.0.1")
//  implementation("com.huawei.hms:ads-identifier:3.4.62.300")
//
//  implementation("com.android.billingclient:billing:6.0.1")
//  implementation("com.huawei.hms:ads-installreferrer:3.4.39.302")
//  implementation("store.galaxy.samsung.installreferrer:samsung_galaxystore_install_referrer:4.0.0")
//  implementation("com.miui.referrer:homereferrer:1.0.0.7")
  implementation ("io.branch.sdk.android:library:5.+")

  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test:runner:1.5.2")
  androidTestImplementation("androidx.test:rules:1.5.0")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}