package com.ext.android_image_compressor


import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

object ImageCompressor {

    fun compressBitmap(
        bitmap: Bitmap,
        outputFile: File,
        quality: Int = 80
    ): File {

        FileOutputStream(outputFile).use {
            bitmap.compress(
                Bitmap.CompressFormat.JPEG,
                quality,
                it
            )
        }
        return outputFile
    }
}
