package example.com.models

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val message: String,
    val data: T? = null
)