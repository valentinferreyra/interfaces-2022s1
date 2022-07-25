package org.rottenTomatoesApi.controllers.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.unqUi.model.RottenTomatoesSystem
import com.github.unqUi.model.User
import javalinjwt.JWTProvider
import org.rottenTomatoesApi.exceptions.NotFoundTokenException

class JWTController(val rottenTomatoes: RottenTomatoesSystem) {

    val algorithm = Algorithm.HMAC256("very-secret")
    val verfier = JWT.require(algorithm).build()
    val generator = UserGenerator()
    val provider = JWTProvider(algorithm, generator, verfier)

    fun generateToken(user: User): String {
        return provider.generateToken(user)
    }

    fun validate(token: String): User {
        try {
            val token = provider.validateToken(token)
            if (!token.isPresent) throw NotFoundTokenException()
            val userId = token.get().getClaim("id").asString()
            return rottenTomatoes.getUserById(userId)
        } catch (e: Exception) {
            throw NotFoundTokenException()
        }
    }
}
