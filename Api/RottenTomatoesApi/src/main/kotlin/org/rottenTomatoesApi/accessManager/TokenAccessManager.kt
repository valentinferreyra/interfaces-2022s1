package org.rottenTomatoesApi.accessManager

import com.github.unqUi.model.User
import io.javalin.core.security.AccessManager
import io.javalin.core.security.RouteRole
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import org.rottenTomatoesApi.Roles
import org.rottenTomatoesApi.controllers.jwt.JWTController
import org.rottenTomatoesApi.exceptions.NotFoundTokenException

class TokenAccessManager(private val jwtController: JWTController): AccessManager {

    private fun getUser(token: String): User {
        return try {
            jwtController.validate(token)
        } catch (e: NotFoundTokenException) {
            throw UnauthorizedResponse("Token invalid")
        }
    }

    override fun manage(handler: Handler, ctx: Context, roles: MutableSet<RouteRole>) {
        val token = ctx.header("Authorization")
        when {
            roles.contains(Roles.ANYONE) -> handler.handle(ctx)
            token == null -> throw UnauthorizedResponse("Token not found")
            roles.contains(Roles.USER) -> {
                val user = getUser(token)
                ctx.attribute("user", user)
                handler.handle(ctx)
            }
        }
    }
}