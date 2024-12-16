import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("api/recipes") // A végpont az alap URL-hez viszonyítva
    fun getRecipes(): Call<List<ApiRecipe>>

    //tovabbi végpontok implementálása
    @GET("api/recipes/{id}") // Részletes lekérés recipeID alapján
    fun getRecipeDetail(@Path("id") id: Int): Call<ApiRecipe>

    @DELETE("recipes/{id}")
    fun deleteRecipe(
        @Header("Authorization") authToken: String,
        @Path("id") recipeId: Int
    ): Call<Void>

    @POST("api/recipes")
    fun addRecipe(
        @Header("Authorization") token: String,
        @Body recipeRequest: RecipeRequest
    ): Call<Void>
}
