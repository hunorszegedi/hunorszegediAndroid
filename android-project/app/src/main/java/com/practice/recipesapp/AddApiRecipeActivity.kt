import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.practice.recipesapp.ApiClient
import com.practice.recipesapp.databinding.ActivityAddApiRecipeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddApiRecipeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddApiRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddApiRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmitRecipe.setOnClickListener {
            submitRecipe()
        }
    }

    private fun submitRecipe() {
        val apiService = ApiClient.getApiService()
        val authToken = "Bearer YOUR_ACCESS_TOKEN" // Cseréld ki a friss tokenre

        // Példa adatok a recepthez
        val recipeRequest = RecipeRequest(
            name = "One-Pot Lemon Garlic Shrimp Pasta",
            description = "This easy 30-minute pasta recipe transforms ingredients...",
            thumbnailUrl = "https://img.buzzfeed.com/video-api-prod/assets/9ee2dadcbfcb4095872e6cdbaa24ff14/Thumb_A_FB.jpg",
            keywords = "pasta, shrimp, lemon, garlic",
            isPublic = false,
            userEmail = "codespringmentor@gmail.com",
            originalVideoUrl = "https://s3.amazonaws.com/video-api-prod/assets/3de913d586424e189521e13eeee0222d/BFV10181_One-Pot_Lemon_Garlic_Shrimp_Pasta_FB1080SQ.mp4",
            country = "US",
            numServings = 4,
            components = listOf(
                Component("8 oz linguine", "", Ingredient("linguine"), Measurement("8", Unit("ounce", "oz", "oz", "oz")), 1),
                Component("2 Tbsp. olive oil", "", Ingredient("olive oil"), Measurement("2", Unit("tablespoon", "tablespoon", "tablespoons", "tbsp")), 2),
                Component("8 Tbsp. (1 stick) unsalted butter", "1 stick", Ingredient("unsalted butter"), Measurement("8", Unit("tablespoon", "tablespoon", "tablespoons", "tbsp")), 3)
            ),
            instructions = listOf(
                Instruction(0, "In a large pot, boil water and add pasta. Cook (stirring frequently) until al dente.", 1),
                Instruction(0, "Drain and set pasta aside.", 2),
                Instruction(0, "In the same pan, heat olive oil and 2 tablespoons of butter. Add garlic and crushed red pepper, cook until fragrant.", 3)
            ),
            nutrition = Nutrition(567, 31, 28, 44, 1, 2)
        )

        apiService.addRecipe(authToken, recipeRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AddApiRecipeActivity, "Recept sikeresen hozzáadva!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@AddApiRecipeActivity, "Hiba a recept hozzáadásakor: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@AddApiRecipeActivity, "Hálózati hiba: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}