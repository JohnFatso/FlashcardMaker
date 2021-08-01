package com.johnfatso.flashcardmaker.database;

/**
 * Interface for Database repository
 *
 */
interface CardRepositoryInterface {

    /**
     * function to extract all cards from all categories. It is an Asynchronous call.
     *
     * The implementing function should make provisions to intimate listener classes when data is available.
     * The accessor should subscribe to the specific Observer to get the data
     */
    void getAllCards();
    /**
     * function to extract all cards from given categories. It is an Asynchronous call.
     *
     * The implementing function should make provisions to intimate listener classes when data is available.
     * The accessor should subscribe to the specific Observer to get the data
     *
     * @param category category from which cards to be retrieved
     */
    void getAllCardsOfCategory(String category);
    /**
     * function to all categories. It is an Asynchronous call.
     *
     * The implementing function should make provisions to intimate listener classes when data is available.
     * The accessor should subscribe to the specific Observer to get the data
     */
    void getListOfCategories();

    /**
     * function to add a card data to the Database. It is an asynchronous call
     * @param card card to be added
     */
    void addCard(CardEntriesEntity card);
    /**
     * function to add a new category. It is an asynchronous call
     * @param category category name to be added
     */
    void addCategory(String category);

    /**
     * Update an existing card data. It is an aynchronous call.
     *
     * @param card updated card object
     */
    void updateCard(CardEntriesEntity card);
    /**
     * Update the new accesscount entry in the database. It is an asynchronous call
     *
     * @param card updated card object
     */
    void updateAccessCount(CardEntriesEntity card);
    /**
     * Update last reading results. It is an asynchronous call
     * @param card updated card object
     */
    void updateLastResults(CardEntriesEntity card);
    /**
     * Update front ot rear side data. It is an asynchronous call
     * @param card updated card object
     */
    void updateCardContents(CardEntriesEntity card);

    /**
     * Update category name of set of cards
     * @param previousName old name
     * @param newName new name
     */
    void changeCategoryName(String previousName, String newName);

    /**
     * Delete all cards from the specified category
     * @param categoryName cards to be deleted
     */
    void deleteCategory(String categoryName);

    /**
     * Delete the specified card
     * @param card card to be deleted
     */
    void deleteCard(CardEntriesEntity card);
}
