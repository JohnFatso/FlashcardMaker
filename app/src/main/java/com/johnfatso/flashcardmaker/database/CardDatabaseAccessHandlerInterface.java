package com.johnfatso.flashcardmaker.database;

interface CardDatabaseAccessHandlerInterface {
    void getAllCards();
    void getAllCardsOfCategory(String category);
    void getListOfCategories();

    void addCard(CardEntriesEntity card);

    void updateCard(CardEntriesEntity card);
    void changeCategoryName(String previousName, String newName);

    void deleteCategory(String categoryName);
    void deleteCard(CardEntriesEntity card);
}
