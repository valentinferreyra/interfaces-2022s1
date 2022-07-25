package org.rottenTomatoes.category

import com.github.unqUi.model.Category
import org.uqbar.commons.model.annotations.Observable

@Observable
class CategoryViewModel(category : Category) {
    var id = category.id
    var name = category.name
}