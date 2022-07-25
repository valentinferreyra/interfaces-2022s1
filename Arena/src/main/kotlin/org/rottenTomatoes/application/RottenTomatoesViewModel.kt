package org.rottenTomatoes.application

import com.github.unqUi.model.getRottenTomatoesSystem
import org.uqbar.commons.model.annotations.Observable

@Observable
class RottenTomatoesViewModel {
    var rottenTomatoesSystem = getRottenTomatoesSystem()
}