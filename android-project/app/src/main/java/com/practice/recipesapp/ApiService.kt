import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/recipes") // A végpont az alap URL-hez viszonyítva
    fun getRecipes(): Call<List<ApiRecipe>>

    //tovabbi végpontok implementálása
    @GET("api/recipes/{id}") // Részletes lekérés recipeID alapján
    fun getRecipeDetail(@Path("id") id: Int): Call<ApiRecipe>
}
