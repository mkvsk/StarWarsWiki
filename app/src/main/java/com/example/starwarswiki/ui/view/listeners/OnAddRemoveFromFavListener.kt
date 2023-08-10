package com.example.starwarswiki.ui.view.listeners

interface OnAddRemoveFromFavListener {
    fun onItemAddToFav(item: Any, position: Int)

    fun onItemRemoveFromFav(item: Any, position: Int)

}
