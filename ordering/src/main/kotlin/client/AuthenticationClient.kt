package client

import authentication.CheckTokenResponse
import jakarta.inject.Named
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange

@Named
class AuthenticationClient {

    fun checkToken(token: String): CheckTokenResponse {
        val restTemplate = RestTemplate()
        val url = "http://localhost:8082/authentication/check-token"

        // Create headers with token
        val headers = HttpHeaders()
        headers.set("Authorization", "Bearer $token")

        // Create request entity with headers
        val requestEntity = HttpEntity<String>(headers)

        val response = restTemplate.exchange<CheckTokenResponse>(
            url = url,
            method = HttpMethod.POST,
            requestEntity = requestEntity,
            object : ParameterizedTypeReference<CheckTokenResponse>() {}
        )
        return response.body ?: throw IllegalStateException("Check token response has no body ...")
    }

}