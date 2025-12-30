package com.ext.android_imagecompressor

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ext.android_image_compressor.ImageCompressorView
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val compressorView = findViewById<ImageCompressorView>(R.id.compressorView)
        val btnCompress = findViewById<Button>(R.id.btnCompress)
        val tvInfo = findViewById<TextView>(R.id.tvInfo)

        // Image URI
        val imageUri = Uri.parse("android.resource://$packageName/${R.drawable.seen}")
        val outputFile = File(cacheDir, "compressed.jpg")

        tvInfo.text = "Click 'Compress Image' to start"

        btnCompress.setOnClickListener {

            // Original Image Info
            val originalInput = contentResolver.openInputStream(imageUri)
            val originalSize = originalInput?.available() ?: 0
            originalInput?.close()

            val originalOptions = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri), null, originalOptions)
            val originalWidth = originalOptions.outWidth
            val originalHeight = originalOptions.outHeight

            Log.d("COMPRESS", "Original → size=$originalSize bytes, dim=${originalWidth}x${originalHeight}")

            // Compress using smart function
            compressorView.compressImageFromUriSmart(imageUri, outputFile)

            // Compressed Image Info
            val compressedSize = outputFile.length()
            val compressedOptions = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeFile(outputFile.absolutePath, compressedOptions)
            val compressedWidth = compressedOptions.outWidth
            val compressedHeight = compressedOptions.outHeight

            Log.d("COMPRESS", "Compressed → size=$compressedSize bytes, dim=${compressedWidth}x${compressedHeight}")

            // Update UI
            tvInfo.text = """
                ORIGINAL
                Size   : ${originalSize / 1024} KB
                Dim    : ${originalWidth} × ${originalHeight}
                
                COMPRESSED
                Size   : ${compressedSize / 1024} KB
                Dim    : ${compressedWidth} × ${compressedHeight}
            """.trimIndent()
        }
    }
}
