package com.sv.quackathon.repository

import com.sv.quackathon.model.Image

interface ImageRepository {
    fun updateImage(image:Image)
    fun addComment(comment: String)
    fun like(imageId: String)
    fun dislike(imageId: String)
    fun addImage(image: Image)
    fun getImages(): List<Image>
    fun getImageDetails(imageId: String): Image
}