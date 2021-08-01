package com.johnfatso.flashcardmaker.database;

interface CardDatabaseUpdateHandlerInterface {

    /**
     * add a new card to the database
     * @param card CardEntriesEntity object
     */
    void addCard(CardEntriesEntity card);

    /**
     * update an existing card in the database
     * @param card new data
     */
    void updateCard(CardEntriesEntity card);

    /**
     * update the accessed count of the card object.
     * The implementation should take care of incrementing the counter and writing to te databsse
     * @param card the card for reference
     */
    void updateAccessCount(CardEntriesEntity card);

    /**
     * update the counter for successful reading counter
     * The implementation should take care of incrementing logic
     * @param card the card fo reference
     */
    void updateSuccess(CardEntriesEntity card);
    /**
     * update the counter for failure of reading counter
     * The implementation should take care of incrementing logic
     * @param card the card fo reference
     */
    void updateFailure(CardEntriesEntity card);
    /**
     * update the success or failure of reading flag
     * The implementation should take care of flag update logic
     * @param card the card fo reference
     * @param lastResult the last result from the user
     */
    void updateLastResults(CardEntriesEntity card, boolean lastResult);

    /**
     * update the text details of the card in the database
     * @param card card reference
     * @param frontText text in the front
     * @param backText text in the rear
     */
    void updateCardContents(CardEntriesEntity card, String frontText, String backText);

    /**
     * updates name of a category in all associated cards
     * @param previousName the old name
     * @param newName the new proposed name
     */
    void changeCategoryName(String previousName, String newName);

    /**
     * delete all cards of the specified category
     * @param categoryName category name string
     */
    void deleteCategory(String categoryName);

    /**
     * Delete a specific card
     * @param card card reference
     */
    void deleteCard(CardEntriesEntity card);
}
