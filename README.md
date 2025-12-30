# **Android Image Compressor**

---

A simple Android image compression library/app using a custom ImageCompressorView.

It allows developers or users to:

- Compress images from URI (drawable, gallery, etc.)

- Resize images while maintaining aspect ratio

- Adjust JPEG quality from 0 (lowest) to 100 (highest) to reduce file size

- Automatically ensure the compressed file is smaller than the original

- Display before and after image info (dimensions and size) in UI


---

## ✨ **Features**

- Smart compression: automatically reduces file size below the original by resizing and iteratively lowering quality.

- Maintain aspect ratio: prevents distorted images.

- XML attribute support: configure compression values directly in layout XML.

- Dynamic quality: can be changed at runtime in Kotlin/Java code.

- UI integration: works with buttons and info TextView to show compression results.

  ---


# **Preview**
---
<p align="center">
  <img src="https://github.com/user-attachments/assets/cf8ab807-e596-462f-b29a-16f6273aed14"
       alt="Demo GIF"
       width="200">


</p>

## ⚡ **Installation**

**Step 1:** Add JitPack repository to your root build.gradle:

```
gradle
maven { url = uri("https://jitpack.io") }
```

**Step 2:** Add the dependency in your app `build.gradle` (example if hosted on JitPack):  

```gradle
dependencies {
	        implementation 'com.github.Excelsior-Technologies-Community:Android_ObservableScrollView:1.0.0'

}
```


## ⚡ **Usage**

1. Add in XML

```
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:padding="16dp"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

<com.ext.android_image_compressor.ImageCompressorView
    android:id="@+id/compressorView"
    android:layout_width="match_parent"
    android:layout_height="300dp"
    android:scaleType="centerCrop"
    android:background="#E0E0E0"
    android:src="@drawable/seen"

    app:compressQuality="30"          <!-- 0 to 100 -->
    app:compressMaxWidth="1080"       <!-- in pixels -->
    app:compressMaxHeight="1080"      <!-- in pixels -->
    app:compressFormat="jpeg"
    app:autoRotate="true"/>


    <Button
        android:id="@+id/btnCompress"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Compress Image"
        android:layout_marginTop="16dp"/>

    <TextView
        android:id="@+id/tvInfo"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Click 'Compress Image' to start"
        android:padding="12dp"
        android:layout_marginTop="16dp"
        android:textColor="#000000"
        android:background="#80FFFFFF"
        android:fontFamily="monospace"
        android:textSize="14sp"/>
</LinearLayout>



```



## **2. Setup in Activity**

```
val btnCompress = findViewById<Button>(R.id.btnCompress)
val tvInfo = findViewById<TextView>(R.id.tvInfo)
val imageUri = Uri.parse("android.resource://$packageName/${R.drawable.seen}")
val outputFile = File(cacheDir, "compressed.jpg")

btnCompress.setOnClickListener {
    compressorView.compressImageFromUriSmart(imageUri, outputFile)
    tvInfo.text = "Compressed size: ${outputFile.length() / 1024} KB"
}


```






## **📄 License**

**MIT License**  
```
Copyright (c) 2025 Excelsior Technologies

Permission is hereby granted, free of charge, to any person obtaining a copy  
of this software and associated documentation files (the "Software"), to deal  
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
copies of the Software, and to permit persons to whom the Software is  
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all  
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED **"AS IS"**, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
```



  
