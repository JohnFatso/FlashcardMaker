package com.johnfatso.flashcardmaker.database;

import android.app.Application;

class CardDatabaseUpdateHandler implements CardDatabaseUpdateHandlerInterface {
    private final CardEntryRepository repository;

    public CardDatabaseUpdateHandler(CardEntryRepository repository) {
        this.repository = repository;
    }

    public CardDatabaseUpdateHandler(Application application) {
        this.repository = CardEntryRepository.getInstance(application);
    }

    /**
     * add a new card to the database
     *
     * @param card CardEntriesEntity object
     */
    @Override
    public void addCard(CardEntriesEntity card) {
        this.repository.addCard(card);
    }

    /**
     * update an existing card in the database
     *
     * @param card new data
     */
    @Override
    public void updateCard(CardEntriesEntity card) {
        this.repository.updateCard(card);
    }

    /**
     * update the accessed count of the card object.
     * The implementation should take care of incrementing the counter and writing to te databsse
     *
     * @param card the card for reference
     */
    @Override
    public void updateAccessCount(CardEntriesEntity card) {
        CardEntriesEntity temp = new CardEntriesEntity(card);
        temp.incrementAccessCount();
        repository.updateAccessCount(temp);
    }

    /**
     * update the counter for successful reading counter
     * The implementation should take care of incrementing logic
     *
     * @param card the card fo reference
     */
    @Override
    public void updateSuccess(CardEntriesEntity card) {
        CardEntriesEntity temp = new CardEntriesEntity(card);
        temp.incrementSuccessCount();
        repository.updateAccessCount(temp);
    }

    /**
     * update the counter for failure of reading counter
     * The implementation should take care of incrementing logic
     *
     * @param card the card fo reference
     */
    @Override
    public void updateFailure(CardEntriesEntity card) {
        CardEntriesEntity temp = new CardEntriesEntity(card);
        temp.incrementFailureCount();
        repository.updateAccessCount(temp);
    }

    /**
     * update the success or failure of reading flag
     * The implementation should take care of flag update logic
     *
     * @param card       the card for reference
     * @param lastResult the last result from the user
     */
    @Override
    public void updateLastResults(CardEntriesEntity card, boolean lastResult) {
        CardEntriesEntity temp = new CardEntriesEntity(card);
        temp.setLast_test_result(lastResult);
        repository.updateAccessCount(temp);
    }

    /**
     * update the text details of the card in the database
     *
     * @param card      card reference
     * @param frontText text in the front
     * @param backText  text in the rear
     */
    @Override
    public void updateCardContents(CardEntriesEntity card, String frontText, String backText) {
        CardEntriesEntity temp = new CardEntriesEntity(card);
        temp.setFront_text(frontText);
        temp.setBack_text(backText);
        repository.updateAccessCount(temp);
    }

    /**
     * updates name of a category in all associated cards
     *
     * @param previousName the old name
     * @param newName      the new proposed name
     */
    @Override
    public void changeCategoryName(String previousName, String newName) {
        repository.changeCategoryName(previousName, newName);
    }

    /**
     * delete all cards of the specified category
     *
     * @param categoryName category name string
     */
    @Override
    public void deleteCategory(String categoryName) {
        repository.deleteCategory(categoryName);
    }

    /**
     * Delete a specific card
     *
     * @param card card reference
     */
    @Override
    public void deleteCard(CardEntriesEntity card) {
        repository.deleteCard(card);
    }
}
