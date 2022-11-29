package alan.rodriguez.notas.data.remote

import alan.rodriguez.notas.data.model.Note
import alan.rodriguez.notas.data.model.NoteList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("note")
    suspend fun getNotes(): NoteList

    @POST("note")
    suspend fun saveNote(@Body note:Note?): Note?
}