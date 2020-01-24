package com.avp.musicsearch.common


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Either<out R> {

    data class Success<out T>(val data: T) : Either<T>()
    data class Error(val exception: Exception) : Either<Nothing>()
    object Loading : Either<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
        }
    }
}


/**
 * `true` if [Either] is of type Success & holds non-null [Success.data].
 */
val Either<*>.succeeded
    get() = this is Either.Success && data != null
