package com.johnfatso.flashcardmaker.database;

import android.app.Application;
import android.util.Log;

/**
 * class for mediating the transaction between the client and the repository
 */
class CardDatabaseUpdateHandler implements CardDatabaseUpdateHandlerInterface {
    private final CardEntryRepository repository;
    private final String LOG_TAG = "DATABASE";

    public CardDatabaseUpdateHandler(CardEntryRepository repository) {
        this.repository = repository;
        Log.v(LOG_TAG, this.getClass().getName() + " | object created");
    }

    public CardDatabaseUpdateHandler(Application application) {
        this.repository = CardEntryRepository.getInstance(application);
        Log.v(LOG_TAG, this.getClass().getName() + "object created with application context: " + application);
    }

    /**
     * add a new card to the database
     *
     * @param card CardEntriesEntity object
     */
    @Override
    public void addCard(CardEntriesEntity card) {
        Log.v(LOG_TAG, this.getClass().getName() + " | " + "addCard | card: " + card);
        this.repository.addCard(card);
    }

    /**
     * update an existing card in the database
     *
     * @param card new data
     */
    @Override
    public void updateCard(CardEntriesEntity card) {
        Log.v(LOG_TAG, this.getClass().getName() + " | updateCard | card : " + card);
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
        Log.v(LOG_TAG, this.getClass().getName() + " | updateAccessCount | card: "+card+" | increment access from: "+card.getAccess_count());
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
        Log.v(LOG_TAG, this.getClass().getName() + " | updateSuccessCount | card: "+card+" | increment success from: "+card.getSuccess_count());
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
        Log.v(LOG_TAG, this.getClass().getName() + " | updateFailureCount | card: "+card+" | increment failure from: "+card.getAccess_count());
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
        Log.v(LOG_TAG, this.getClass().getName() + " | updateLastResults | card: "+card+" | change result from "+card.getAccess_count() + " to "+lastResult);
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
        Log.v(LOG_TAG, this.getClass().getName() + " | updateCardContents | card: "+card+" | update content to "+frontText + " & "+backText);
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
        Log.v(LOG_TAG, this.getClass().getName() + " | changeCategoryName | update from "+previousName+" to "+newName);
        repository.changeCategoryName(previousName, newName);
    }

    /**
     * delete all cards of the specified category
     *
     * @param categoryName category name string
     */
    @Override
    public void deleteCategory(String categoryName) {
        Log.v(LOG_TAG, this.getClass().getName() + " | deleteCategory | delete all cards from category "+categoryName);
        repository.deleteCategory(categoryName);
    }

    /**
     * Delete a specific card
     *
     * @param card card reference
     */
    @Override
    public void deleteCard(CardEntriesEntity card) {
        Log.v(LOG_TAG, this.getClass().getName() + " | deleteCard | delete card: "+card);
        repository.deleteCard(card);
    }
}
