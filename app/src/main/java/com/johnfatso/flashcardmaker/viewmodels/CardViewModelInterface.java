package com.johnfatso.flashcardmaker.viewmodels;

import androidx.lifecycle.LiveData;

import com.johnfatso.flashcardmaker.database.CardEntriesEntity;

import java.util.ArrayList;

interface CardViewModelInterface {
    /**
     * request for the LiveData of All cards
     * @return LiveData object of AllCards list
     */
    LiveData<ArrayList<CardEntriesEntity>> getAllCards();

    /**
     * request for the livedata of all cards from the specified category
     * @param category category required
     * @return LiveData of List of cards from category
     */
    LiveData<ArrayList<CardEntriesEntity>> getAllCardsOfCategory(String category);

    /**
     * Request for LiveData object of Current category read
     * @return LiveData of Category name string
     */
    LiveData<String> getCurrentCategory();

    /**
     * request for LiveData object of list of Categories
     * @return LiveData of list of categories
     */
    LiveData<ArrayList<String>> getListOfCategories();
}
