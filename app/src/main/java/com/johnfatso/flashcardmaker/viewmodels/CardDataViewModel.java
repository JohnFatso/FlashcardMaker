package com.johnfatso.flashcardmaker.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.johnfatso.flashcardmaker.database.CardEntriesEntity;
import com.johnfatso.flashcardmaker.database.CardEntryRepository;

import java.util.ArrayList;
import java.util.List;

public class CardDataViewModel extends AndroidViewModel implements CardViewModelInterface{

    private CardEntryRepository repository;
    private MutableLiveData<ArrayList<CardEntriesEntity>> listOfAllCards;
    private MutableLiveData<ArrayList<CardEntriesEntity>> listOfCardsOfCurrentCategory;
    private MutableLiveData<ArrayList<String>> listOfCategories;
    private MutableLiveData<String> currentCategory;

    public CardDataViewModel(@NonNull Application application) {
        super(application);
        repository = CardEntryRepository.getInstance(application);

        repository.setOnCardListUpdateListener(new CardEntryRepository.OnCardListUpdateListener() {
            @Override
            public void onCardListUpdated(List<CardEntriesEntity> cards) {
                listOfAllCards.postValue((ArrayList<CardEntriesEntity>) cards);
            }
        });

        repository.setOnCardListOfCategoryUpdateListener(new CardEntryRepository.OnCardListOfCategoryUpdateListener() {
            @Override
            public void OnCardListOfCategoryUpdated(String Category, List<CardEntriesEntity> cards) {
                currentCategory.postValue(Category);
                listOfCardsOfCurrentCategory.postValue((ArrayList<CardEntriesEntity>) cards);
            }
        });

        repository.setOnCategoryListUpdateListener(new CardEntryRepository.OnCategoryListUpdatedListener() {
            @Override
            public void onCategoryUpdated(List<String> categories) {
                listOfCategories.postValue((ArrayList<String>) categories);
            }
        });
    }

    /**
     * request for the LiveData of All cards
     *
     * @return LiveData object of AllCards list
     */
    @Override
    public LiveData<ArrayList<CardEntriesEntity>> getAllCards() {
        if (listOfAllCards == null){
            listOfAllCards = new MutableLiveData<>(new ArrayList<>());
            repository.getAllCards();
        }
        else if (listOfAllCards.getValue().size() == 0){
            repository.getAllCards();
        }
        return listOfAllCards;
    }

    /**
     * request for the livedata of all cards from the specified category
     *
     * @param category category required
     * @return LiveData of List of cards from category
     */
    @Override
    public LiveData<ArrayList<CardEntriesEntity>> getAllCardsOfCategory(String category) {
        if (listOfCardsOfCurrentCategory == null){
            listOfCardsOfCurrentCategory = new MutableLiveData<>(new ArrayList<>());
            repository.getAllCardsOfCategory(category);
        }
        else if (listOfCardsOfCurrentCategory.getValue().size() == 0){
            repository.getAllCardsOfCategory(category);
        }
        return listOfCardsOfCurrentCategory;
    }

    /**
     * Request for LiveData object of Current category read
     *
     * @return LiveData of Category name string
     */
    @Override
    public LiveData<String> getCurrentCategory() {
        return currentCategory;
    }

    /**
     * request for LiveData object of list of Categories
     *
     * @return LiveData of list of categories
     */
    @Override
    public LiveData<ArrayList<String>> getListOfCategories() {
        if (listOfCategories == null){
            listOfCategories = new MutableLiveData<>(new ArrayList<>());
            repository.getListOfCategories();
        }
        else if (listOfCategories.getValue().size() == 0){
            repository.getListOfCategories();
        }
        return listOfCategories;
    }
}
