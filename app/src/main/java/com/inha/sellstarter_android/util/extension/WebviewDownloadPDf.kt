package com.inha.sellstarter_android.util.extension

import android.app.Activity
import android.content.Context
import android.os.CancellationSignal
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.print.PrintManager
import android.webkit.WebView
import android.widget.Toast
import java.io.File

fun createWebViewPdf(context: Context, webView: WebView, fileName: String) {
    val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager
    val printAdapter = webView.createPrintDocumentAdapter(fileName)

    val jobName = "$fileName Document"
    val printAttributes = PrintAttributes.Builder()
        .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
        .setResolution(PrintAttributes.Resolution("id", "name", 600, 600))
        .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
        .build()

    val outputFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)

    val pdfPrintJob = printManager.print(
        jobName,
        printAdapter,
        printAttributes
    )

    Toast.makeText(context, "PDF 저장 중...", Toast.LENGTH_SHORT).show()
}