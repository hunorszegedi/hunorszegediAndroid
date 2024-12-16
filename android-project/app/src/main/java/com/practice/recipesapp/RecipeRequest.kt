data class RecipeRequest(
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val keywords: String,
    val isPublic: Boolean,
    val userEmail: String,
    val originalVideoUrl: String,
    val country: String,
    val numServings: Int,
    val components: List<Component>,
    val instructions: List<Instruction>,
    val nutrition: Nutrition
)

data class Component(
    val rawText: String,
    val extraComment: String,
    val ingredient: Ingredient,
    val measurement: Measurement,
    val position: Int
)

data class Ingredient(
    val name: String
)

data class Measurement(
    val quantity: String,
    val unit: Unit
)

data class Unit(
    val name: String,
    val displaySingular: String,
    val displayPlural: String,
    val abbreviation: String
)

data class Instruction(
    val instructionID: Int,
    val displayText: String,
    val position: Int
)

data class Nutrition(
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbohydrates: Int,
    val sugar: Int,
    val fiber: Int
)
