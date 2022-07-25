package org.rottenTomatoesApi.controllers.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.unqUi.model.User
import javalinjwt.JWTGenerator

class UserGenerator : JWTGenerator<User> {
    override fun generate(user: User, algorithm: Algorithm): String {
        val token = JWT.create().withClaim("id", user.id)
        return token.sign(algorithm)
    }
}