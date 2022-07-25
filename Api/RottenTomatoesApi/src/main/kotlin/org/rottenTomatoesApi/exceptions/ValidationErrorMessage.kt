package org.rottenTomatoesApi.exceptions

import io.javalin.core.validation.ValidationError

class ValidationErrorMessage(errors: List<ValidationError<Any>>?) {
    val messages = errors?.map { it.message } ?: listOf()
}