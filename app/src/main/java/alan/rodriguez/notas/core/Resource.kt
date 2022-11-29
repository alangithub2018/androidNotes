package alan.rodriguez.notas.core

import java.lang.Exception

sealed class Resource<out T> {
    class Loading<out T>(): alan.rodriguez.notas.core.Resource<T>()
    data class Success<out T>(val data:T): alan.rodriguez.notas.core.Resource<T>()
    data class Failure(val exception: Exception): alan.rodriguez.notas.core.Resource<Nothing>()
}