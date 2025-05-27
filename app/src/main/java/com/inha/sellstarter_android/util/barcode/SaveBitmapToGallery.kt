package com.inha.sellstarter_android.util.barcode

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream

fun saveBitmapToGallery(context: Context, bitmap: Bitmap, fileName: String?): Boolean {
    val name = (fileName ?: "barcode_image") + ".jpg"
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, name)
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/SellStarter")
        put(MediaStore.Images.Media.IS_PENDING, 1)
    }

    val resolver = context.contentResolver
    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    return try {
        uri?.let {
            resolver.openOutputStream(it).use { out ->
                out?.let { it1 -> bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it1) }
            }
            contentValues.clear()
            contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
            resolver.update(uri, contentValues, null, null)
            true
        } ?: false
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}