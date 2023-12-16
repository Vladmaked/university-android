package com.example.labfifth

sealed class ListItem {
    data class Header(val title: String): ListItem()
    data class Item(val title: String, val description: String): ListItem()
}

