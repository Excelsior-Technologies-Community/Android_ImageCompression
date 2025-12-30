package com.ext.android_image_compressor

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.ext.android_image_compressor.R
import java.io.File
import java.io.FileOutputStream
import kotlin.math.roundToInt

class ImageCompressorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    var compressQuality = 80
    var compressMaxWidth = 1080
    var compressMaxHeight = 1080

    init {
        // Read XML attributes
        attrs?.let {
            val a = context.obtainStyledAttributes(it, R.styleable.ImageCompressorView, 0, 0)
            compressQuality = a.getInt(R.styleable.ImageCompressorView_compressQuality, compressQuality)
            compressMaxWidth = a.getInt(R.styleable.ImageCompressorView_compressMaxWidth, compressMaxWidth)
            compressMaxHeight = a.getInt(R.styleable.ImageCompressorView_compressMaxHeight, compressMaxHeight)
            a.recycle()
        }
    }

    fun compressImageFromUriSmart(uri: Uri, outputFile: File): File {

        val inputStream = context.contentResolver.openInputStream(uri) ?: return outputFile
        val originalSize = inputStream.available()
        inputStream.close()

        val bitmap = BitmapFactory.decodeStream(
            context.contentResolver.openInputStream(uri)
        ) ?: return outputFile

        val resizedBitmap = resizeKeepingRatio(bitmap, compressMaxWidth, compressMaxHeight)

        var quality = compressQuality
        var compressedSize: Long

        do {
            FileOutputStream(outputFile).use {
                resizedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, it)
            }
            compressedSize = outputFile.length()
            quality -= 10
        } while (compressedSize >= originalSize && quality >= 20)

        setImageBitmap(resizedBitmap)
        return outputFile
    }

    private fun resizeKeepingRatio(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height

        if (width <= maxWidth && height <= maxHeight) return bitmap

        val ratioBitmap = width.toFloat() / height.toFloat()
        val ratioMax = maxWidth.toFloat() / maxHeight.toFloat()

        val finalWidth: Int
        val finalHeight: Int

        if (ratioBitmap > ratioMax) {
            finalWidth = maxWidth
            finalHeight = (maxWidth / ratioBitmap).roundToInt()
        } else {
            finalHeight = maxHeight
            finalWidth = (maxHeight * ratioBitmap).roundToInt()
        }

        return Bitmap.createScaledBitmap(bitmap, finalWidth, finalHeight, true)
    }
}
