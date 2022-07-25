package org.rottenTomatoesApi

import com.github.unqUi.model.getRottenTomatoesSystem
import io.javalin.*
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.RouteRole
import io.javalin.core.validation.ValidationException
import org.rottenTomatoesApi.accessManager.TokenAccessManager
import org.rottenTomatoesApi.controllers.CategoryController
import org.rottenTomatoesApi.controllers.ContentController
import org.rottenTomatoesApi.controllers.UserController
import org.rottenTomatoesApi.controllers.jwt.JWTController
import org.rottenTomatoesApi.exceptions.ValidationErrorMessage

fun main() {
        RottenTomatoesAPI().app()
}

enum class Roles: RouteRole {
        ANYONE, USER
}


val rottenTomatoes = getRottenTomatoesSystem()

class RottenTomatoesAPI {
        fun app() {
                val jwtController = JWTController(rottenTomatoes)
                val app = Javalin.create {
                        it.defaultContentType = "application/json"
                        it.accessManager(TokenAccessManager(jwtController))
                        it.enableCorsForAllOrigins()
                }.start(7070)
                val userController = UserController(rottenTomatoes, jwtController)
                val contentController = ContentController(rottenTomatoes)
                val categoryController = CategoryController(rottenTomatoes)

                app.before {
                        it.header("Access-Control-Expose-Headers", "*")
                }

                app.routes {
                        path("login") {
                                post(userController::login, Roles.ANYONE)
                        }
                        path("register") {
                                post(userController::register, Roles.ANYONE)
                        }
                        path("user") {
                                get(userController::getUser, Roles.USER)
                                path("{id}") {
                                        get(userController::getUserById, Roles.ANYONE)
                                }
                        }
                        path("content") {
                                path("latest") {
                                        get(contentController::getContentLatest, Roles.ANYONE)
                                }
                                path("top") {
                                        get(contentController::getContentTop, Roles.ANYONE)
                                }
                                path("{id}") {
                                        post(contentController::addReview, Roles.USER)
                                        get(contentController::getContentWithId, Roles.ANYONE)
                                }
                        }
                        path("categories") {
                                get(categoryController::getCategories, Roles.ANYONE)
                                path("{id}") {
                                        get(categoryController::getCategoryById, Roles.ANYONE)
                                }
                        }
                }

                app.exception(ValidationException::class.java) { e, ctx ->
                        if (e.errors.keys.contains("REQUEST_BODY")) {
                                ctx.json(ValidationErrorMessage(e.errors["REQUEST_BODY"])).status(400)
                        } else {
                                ctx.json(e.errors).status(400)
                        }
                }
        }
}
