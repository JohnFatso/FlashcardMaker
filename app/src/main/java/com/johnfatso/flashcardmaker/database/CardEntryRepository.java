package com.johnfatso.flashcardmaker.database;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Implementation of CardEntries Database Repository.
 *
 * It is a singleton and the repo object to be acquired by getInstance()
 */
public class CardEntryRepository implements CardRepositoryInterface{

    static private volatile CardEntryRepository INSTANCE;

    private final CardEntriesDao cardEntriesDao;
    private final CategoryEntityDao categoryEntityDao;

    private CardEntryRepository(Application application) {
        CardDatabase db = CardDatabase.getInstance(application);
        this.cardEntriesDao = db.cardEntriesDao();
        this.categoryEntityDao = db.categoryEntityDao();
    }

    public static CardEntryRepository getInstance(Application application){
        if (INSTANCE == null){
            synchronized (CardEntryRepository.class){
                if (INSTANCE == null) {
                    INSTANCE = new CardEntryRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * function to extract all cards from all categories. It is an Asynchronous call.
     * <p>
     * The implementing function should make provisions to intimate listener classes when data is available.
     * The accessor should subscribe to the specific Observer to get the data
     */
    @Override
    public void getAllCards() {
        CardDatabase.databaseWriteExecutor.execute(()->{
            ArrayList<CardEntriesEntity> cards = (ArrayList<CardEntriesEntity>) cardEntriesDao.getAllCards();
            getListenerInfo().onCardListUpdateListener.onCardListUpdated(cards);
        });
    }

    /**
     * function to extract all cards from given categories. It is an Asynchronous call.
     * <p>
     * The implementing function should make provisions to intimate listener classes when data is available.
     * The accessor should subscribe to the specific Observer to get the data
     *
     * @param category category from which cards to be retrieved
     */
    @Override
    public void getAllCardsOfCategory(String category) {
        CardDatabase.databaseWriteExecutor.execute(()->{
            ArrayList<CardEntriesEntity> cards = (ArrayList<CardEntriesEntity>) cardEntriesDao.getAllCardsOfCategory(category);
            getListenerInfo().onCardListOfCategoryUpdateListener.OnCardListOfCategoryUpdated(category, cards);
        });
    }

    /**
     * function to all categories. It is an Asynchronous call.
     * <p>
     * The implementing function should make provisions to intimate listener classes when data is available.
     * The accessor should subscribe to the specific Observer to get the data
     */
    @Override
    public void getListOfCategories() {
        CardDatabase.databaseWriteExecutor.execute(()->{
            ArrayList<String> categories = (ArrayList<String>) categoryEntityDao.getAllCategoryNames();
            getListenerInfo().onCategoryListUpdatedListener.onCategoryUpdated(categories);
        });
    }

    /**
     * function to add a card data to the Database. It is an asynchronous call
     *
     * @param card card to be added
     */
    @Override
    public void addCard(CardEntriesEntity card) {
        CardDatabase.databaseWriteExecutor.execute(()->{
            cardEntriesDao.insert(card);
            notifyCardContentChanged();
            notifyCardContentChangedForCategory(card.getCategory());
        });
    }

    /**
     * function to add a new category. It is an asynchronous call
     *
     * @param category category name to be added
     */
    @Override
    public void addCategory(String category) {
        CardDatabase.databaseWriteExecutor.execute(()->{
            CategoryEntity categoryEntity = new CategoryEntity(category);
            categoryEntityDao.insert(categoryEntity);
            notifyCategoryContentChanged();
        });
    }

    /**
     * Update an existing card data. It is an aynchronous call.
     *
     * @param card updated card object
     */
    @Override
    public void updateCard(CardEntriesEntity card) {
        CardDatabase.databaseWriteExecutor.execute(()->{
            cardEntriesDao.insert(card);
            notifyCardContentChanged();
            notifyCardContentChangedForCategory(card.getCategory());
        });
    }

    /**
     * Update the new accesscount entry in the database. It is an asynchronous call
     *
     * @param card updated card object
     */
    @Override
    public void updateAccessCount(CardEntriesEntity card) {
        CardDatabase.databaseWriteExecutor.execute(()->{
            cardEntriesDao.updateCardEntryAccessCount(card.getUniqueid(), card.getAccess_count(), card.getSuccess_count(), card.getFailure_count());
            notifyCardContentChanged();
            notifyCardContentChangedForCategory(card.category);
        });
    }

    /**
     * Update last reading results. It is an asynchronous call
     *
     * @param card updated card object
     */
    @Override
    public void updateLastResults(CardEntriesEntity card) {
        CardDatabase.databaseWriteExecutor.execute(()->{
            cardEntriesDao.updateCardEntryLastResult(card.getUniqueid(), card.last_test_result);
            notifyCardContentChanged();
            notifyCardContentChangedForCategory(card.category);
        });
    }

    /**
     * Update front ot rear side data. It is an asynchronous call
     *
     * @param card updated card object
     */
    @Override
    public void updateCardContents(CardEntriesEntity card) {
        CardDatabase.databaseWriteExecutor.execute(()->{
            cardEntriesDao.insert(card);
            notifyCardContentChanged();
            notifyCardContentChangedForCategory(card.category);
        });
    }

    /**
     * Update category name of set of cards
     *
     * @param previousName old name
     * @param newName      new name
     */
    @Override
    public void changeCategoryName(String previousName, String newName) {
        CardDatabase.databaseWriteExecutor.execute(()->{
            cardEntriesDao.updateCardCategoryName(previousName, newName);
            categoryEntityDao.updateCategoryName(previousName, newName);
            notifyCategoryContentChanged();
        });
    }

    /**
     * Delete all cards from the specified category
     *
     * @param categoryName cards to be deleted
     */
    @Override
    public void deleteCategory(String categoryName) {
        CardDatabase.databaseWriteExecutor.execute(()->{
            cardEntriesDao.deleteCategory(categoryName);
            categoryEntityDao.deleteCategoryByName(categoryName);
            notifyCategoryContentChanged();
            notifyCardContentChangedForCategory(categoryName);
        });
    }

    /**
     * Delete the specified card
     *
     * @param card card to be deleted
     */
    @Override
    public void deleteCard(CardEntriesEntity card) {
        CardDatabase.databaseWriteExecutor.execute(()->{
            cardEntriesDao.deleteEntry(card.uniqueid);
            notifyCardContentChanged();
            notifyCardContentChangedForCategory(card.getCategory());
        });
    }

    /**
     * Called after writing a new data to the database, if new card has been added
     */
    private void notifyCardContentChanged(){
        this.getAllCards();
    }

    /**
     * called after writing a new card to database of category,
     * @param category category of the card added
     */
    private void notifyCardContentChangedForCategory(String category){
        this.getAllCardsOfCategory(category);
    }

    /**
     * when a new category added or category name changed
     */
    private void notifyCategoryContentChanged(){
        getListOfCategories();
    }

    /**
     * class for holding the listener info
     */
    static class ListenerInfo {
        protected OnCardListUpdateListener onCardListUpdateListener;
        protected OnCategoryListUpdatedListener onCategoryListUpdatedListener;
        protected OnCardListOfCategoryUpdateListener onCardListOfCategoryUpdateListener;
    }

    /**
     * variable for ListenerInfo to hold the current listeners
     */
    ListenerInfo listenerInfo;

    /**
     * checks and returns if a listener info is available
     * @return ListnerInfo object
     */
    ListenerInfo getListenerInfo(){
        if (listenerInfo==null){
            listenerInfo = new ListenerInfo();
        }
        return listenerInfo;
    }

    /**
     * Interface for listening for card of any category being added to the Database
     */
    public interface OnCardListUpdateListener{
        void onCardListUpdated(List<CardEntriesEntity> cards);
    }

    /**
     * Interface for listening for card of specific category being added to the Database
     * the calling function shall give the category reference.
     * The defining class should compare and decide if needed to act
     */
    public interface OnCardListOfCategoryUpdateListener{
        void OnCardListOfCategoryUpdated(String Category, List<CardEntriesEntity> cards);
    }

    /**
     * Interface for listening for change in the category data of any cards
     */
    public interface OnCategoryListUpdatedListener{
        void onCategoryUpdated(List<String> categories);
    }

    /**
     * function for subscribing for card data addition event
     * @param listener listener object defined
     */
    public void setOnCardListUpdateListener(OnCardListUpdateListener listener){
        getListenerInfo().onCardListUpdateListener = listener;
    }

    /**
     * function for subscribing for Category change / update event
     * @param listener listener object defined
     */
    public void setOnCategoryListUpdateListener(OnCategoryListUpdatedListener listener){
        getListenerInfo().onCategoryListUpdatedListener = listener;
    }

    /**
     * function for subscribing for card data change event with category info
     * @param listener listener object defined
     */
    public void setOnCardListOfCategoryUpdateListener(OnCardListOfCategoryUpdateListener listener){
        getListenerInfo().onCardListOfCategoryUpdateListener = listener;
    }
}
