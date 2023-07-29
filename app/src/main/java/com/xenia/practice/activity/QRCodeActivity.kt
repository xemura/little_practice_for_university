package com.xenia.practice.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.xenia.practice.databinding.ActivityQrcodeBinding

class QRCodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQrcodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrcodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("pass_id")) {
            val pass = intent.getStringExtra("pass_id")
            binding.textPassId.text = pass
            if (pass != null) {
                generateQRCode(pass)
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun generateQRCode(text: String) {
        val multiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            binding.imgQRCode.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}