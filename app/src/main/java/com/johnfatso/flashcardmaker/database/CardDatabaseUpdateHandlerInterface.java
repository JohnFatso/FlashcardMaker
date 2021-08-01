package com.johnfatso.flashcardmaker.database;

interface CardDatabaseUpdateHandlerInterface {

    void addCard(CardEntriesEntity card);

    void updateCard(CardEntriesEntity card);
    void updateAccessCount(CardEntriesEntity card);
    void updateSuccess(CardEntriesEntity card);
    void updateFailure(CardEntriesEntity card);
    void updateLastResults(CardEntriesEntity card);
    void updateCardContents(CardEntriesEntity card);
    void changeCategoryName(String previousName, String newName);

    void deleteCategory(String categoryName);
    void deleteCard(CardEntriesEntity card);
}
