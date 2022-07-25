package org.rottenTomatoesApi.controllers

import com.github.unqUi.model.DraftUser
import com.github.unqUi.model.RottenTomatoesSystem
import com.github.unqUi.model.User
import com.github.unqUi.model.UserError
import io.javalin.http.Context
import org.rottenTomatoesApi.controllers.jwt.JWTController
import org.rottenTomatoesApi.dto.*
import org.rottenTomatoesApi.rottenTomatoes

class UserController(val rottenTomatoesSystem: RottenTomatoesSystem, val jwtController: JWTController) {

    fun login(ctx : Context){
        var userDTO = UserLoginDTO("","")
           if(userLoginBodyIsNotEmpty(ctx)) {
            userDTO = ctx.bodyValidator<UserLoginDTO>()
                   .check({ userLogin -> userLogin.email.contains("@") }, "Invalid Email")
                   .check({ userLogin -> userLogin.password.isNotBlank() }, "Invalid Password")
                   .get()
           } else { throwBadRequestException(ctx) }

        val user = rottenTomatoes.users.firstOrNull { it.email == userDTO.email && it.password == userDTO.password }

        if(user !== null) {
            val token = jwtController.generateToken(user)
            ctx.header("Authorization", token)
            ctx.json(UserDTO(user))
        } else { throwNotFoundException(ctx, "There is not user with that email/password") }
    }

    fun register(ctx : Context) {
        var userRegister = UserRegisterDTO("","","","")
        // ##### Comprueba que el body tenga al menos estructura para UserRegisterDTO #####
        if(registerBodyIsNotEmpty(ctx)) {
               //##### Comprueba que los valores de los campos sean acorde a lo esperado
               userRegister= ctx.bodyValidator<UserRegisterDTO>()
                .check({ userRegister -> userRegister.email.contains("@")}, "Invalid Email")
                .check({ userRegister -> userRegister.email.isNotBlank() }, "Email cannot be empty")
                .check({ userRegister -> userRegister.password.isNotBlank() }, "Password cannot be empty")
                .check({ userRegister -> userRegister.name.isNotBlank() }, "Name cannot be empty")
                .check({ userRegister -> userRegister.image.isNotBlank() }, "Image cannot be empty")
                .get()
            } else { throwBadRequestException(ctx) }
        try {
                val newUser = rottenTomatoes.addUser(
                    DraftUser(userRegister.name, userRegister.image, userRegister.email, userRegister.password)
                )
                ctx.header("Authorization", jwtController.generateToken(newUser))
                ctx.json(UserDTO(newUser))
        } catch (e: UserError) { throwBadRequestException(ctx, e.message!!) }
    }

    fun getUser(ctx: Context) {
        val user = ctx.attribute<User>("user")
        ctx.json(UserWithReviewsDTO(user!!))
    }

    fun getUserById(ctx : Context){
        val id = ctx.pathParam("id")
        try {
            val user = rottenTomatoes.getUserById(id)
            ctx.json(UserWithReviewsDTO(user))
        } catch (e : UserError) { throwNotFoundException(ctx, e.message!!) }
    }

    private fun userLoginBodyIsNotEmpty(ctx: Context): Boolean{
        return ctx.body().contains("email") && ctx.body().contains("password")
    }

    private fun registerBodyIsNotEmpty(ctx: Context) : Boolean{
        return ctx.body().contains("email") && ctx.body().contains("password")&&
                ctx.body().contains("name") && ctx.body().contains("image")
    }

    private fun throwBadRequestException(ctx: Context, message: String = "Missing information") {
        ctx.status(400).json(ErrorDTO(message))
    }

    private fun throwNotFoundException(ctx: Context, message: String) {
        ctx.status(404).json(ErrorDTO(message))
    }
}