package com.sv.quackathon.ml

import android.content.Context
import android.net.Uri
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import java.io.IOException

class ImageLabel {

    private var labeler = FirebaseVision.getInstance().onDeviceImageLabeler

    companion object {
        const val CONFIDENCE_THRESHOLD = 0.5
    }

    fun fromUri(context: Context, uri: Uri, onLabelListener: OnLabelListener) {
        try {
            val image = FirebaseVisionImage.fromFilePath(context, uri)
            processImage(image, onLabelListener)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun processImage(image: FirebaseVisionImage, onLabelListener: OnLabelListener) {
        labeler.processImage(image)
            .addOnSuccessListener { labels ->
                val labelList: ArrayList<String> = ArrayList()
                labels.forEach { label ->
                    if (label.confidence >= CONFIDENCE_THRESHOLD) {
                        labelList.add(label.text)
                    }
                }
                onLabelListener.onSuccess(labelList)
            }
            .addOnFailureListener {
                onLabelListener.onFailure(it)
            }
    }
}

interface OnLabelListener {
    fun onSuccess(labels: List<String>)
    fun onFailure(exception: Exception)
}