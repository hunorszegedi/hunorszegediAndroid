data class ApiRecipe(
    val recipeID: Int,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val keywords: String,
    val isPublic: Boolean,
    val userEmail: String,
    val originalVideoUrl: String,
    val country: String,
    val numServings: Int,
    val components: List<String>, // Ha a `components` részletezett adatokat tartalmaz, pontosítsd!
    val instructions: List<String> // Ha az `instructions` részletezett adatokat tartalmaz, pontosítsd!
)
