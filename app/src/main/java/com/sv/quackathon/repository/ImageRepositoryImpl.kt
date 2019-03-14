package com.sv.quackathon.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.sv.quackathon.model.Image
import java.nio.InvalidMarkException

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

    override fun like(image: Image) {
        val newLikesCount =+ image.likesCount + 1
        imageCollection.document(image.id).update("likes", newLikesCount)
    }

    override fun dislike(image: Image) {
        val newDislikesCount =+ image.dislikesCount + 1
        imageCollection.document(image.id).update("dislikesCount", newDislikesCount)
    }

    override fun getImages(completion: (List<Image>) -> Unit) {
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
                completion(imageList)
            } else {
                Log.e(LOG_TAG, "Error downloading images")
            }
        }
    }

    override fun getImageDetails(imageId: String): Image {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}