// Change this:
data class RegistrationRequestDTO(
    val name: String,
    val age: Int,
    val username: String,
    val password: String
)
// To this:
data class RegistrationRequest(
    val name: String,
    val age: Int,
    val username: String,
    val password: String
)

data class RegistrationResponseDto(
    val message: String,
    val username: String
)