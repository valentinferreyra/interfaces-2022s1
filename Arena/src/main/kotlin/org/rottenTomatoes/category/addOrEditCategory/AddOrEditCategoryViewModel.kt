package org.rottenTomatoes.category.addOrEditCategory

import com.github.unqUi.model.DraftCategory
import org.uqbar.commons.model.annotations.Observable

@Observable
class AddOrEditCategoryViewModel(var category : DraftCategory ) {
    var name = category.name

    fun updateCategory() {
        this.category.name = this.name
    }
}