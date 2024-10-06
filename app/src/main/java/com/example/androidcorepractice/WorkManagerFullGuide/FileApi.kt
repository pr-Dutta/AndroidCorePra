package com.example.androidcorepractice.WorkManagerFullGuide

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

// - (04-10-2024)
// Interface: In Kotlin, an interface defines a contract that a
// class can implement. It contains methods but does not provide
// their implementation.

// FileApi: This is the name of the interface. The purpose of this
// interface is to define a method to download an image from a specific URL.
interface FileApi {

    // @GET: This annotation comes from Retrofit (a popular library
    // for handling HTTP requests). It specifies the HTTP method (GET)
    // and the relative URL endpoint from which the file will be fetched.
    @GET("/wp-content/uploads/2022/02/220849-scaled.jpg")
    suspend fun downloadImage() : Response<ResponseBody>
    // Response: This is a wrapper class from Retrofit that contains
    // both the data returned from the server and metadata
    // (like status code, headers, etc.).

    // ResponseBody: Represents the body of the HTTP response.
    // In this case, it will contain the image data.

    // companion object: This is a special object in Kotlin that
    // allows the FileApi interface to have static-like behavior.

    // --------------------------------------------------------------------
    // It allows you to define functions or properties that belong
    // to the interface itself, not to any instance of the interface.
    // This will create a single retrofit instance
    companion object {
        // val instance: This defines a property named instance
        // that will be used to access the API service.

        // by lazy: This means the instance will be created
        // (initialized) the first time it is accessed, ensuring
        // that resources are only used when necessary.
        val instance by lazy {
            // Retrofit.Builder(): This is used to create a Retrofit
            // instance, which is responsible for managing network
            // requests in Android.

            Retrofit.Builder()
                .baseUrl("https://pl-coding.com")
                // build(): This finalizes the Retrofit configuration
                // and builds the instance.
                .build()
                .create(FileApi::class.java)
            // create(FileApi::class.java): This tells Retrofit to create
            // an implementation of the FileApi interface. The actual
            // network calls will be handled by this automatically generated
            // implementation.
        }
    }
}









