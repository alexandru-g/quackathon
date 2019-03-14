package com.sv.quackathon.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.sv.quackathon.model.Image

class ImageRepositoryImpl: ImageRepository {
    val LOG_TAG = ImageRepositoryImpl::class.java.simpleName
    val imageCollection = FirebaseFirestore.getInstance().collection("images")

    override fun addImage(image: Image) {
        imageCollection.document(image.id).set(image).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.i(LOG_TAG, "Image uploaded")
            } else {
                Log.e(LOG_TAG, "Image upload error")
            }
        }
    }

    override fun updateImage(image: Image) {
        imageCollection.document(image.id).set(image).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.i(LOG_TAG, "Image uploaded")
            } else {
                Log.e(LOG_TAG, "Image upload error")
            }
        }

    }

    override fun addComment(comment: String) {

    }

    override fun like(imageId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dislike(imageId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getImages(): List<Image> {
        val imageList = mutableListOf<Image>()

        imageCollection.get().addOnCompleteListener {
            if (it.isComplete && it.isSuccessful) {
                it.result?.documents?.forEach { imageDocument ->
                    if (imageDocument.exists()) {
                        imageList.add(Image.buildFromSnapshot(imageDocument.data!!))
                    } else {
                        Log.e(LOG_TAG, "Error no such document")
                    }
                }
            } else {
                Log.e(LOG_TAG, "Error downloading images")
            }
        }
        return imageList
    }

    override fun getImageDetails(imageId: String): Image {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}