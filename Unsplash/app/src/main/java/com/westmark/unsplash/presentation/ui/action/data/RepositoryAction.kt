package com.westmark.unsplash.presentation.ui.action.data

interface RepositoryAction {
    fun addFlagAfterFirstEntry()
    fun isFirstEntry(): Boolean
    fun containsAccessToken(): Boolean
}