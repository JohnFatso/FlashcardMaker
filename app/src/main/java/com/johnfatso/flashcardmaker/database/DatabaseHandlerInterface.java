package com.johnfatso.flashcardmaker.database;

public interface DatabaseHandlerInterface {
    void writeCardEntryToDatabase(String frontText, String backText);
    void writeCardEntryToDatabase(CardEntriesEntity cardEntry);
    void updateReadCount(CardEntriesEntity cardEntry);
    void updateCardEntry(CardEntriesEntity cardEntry);
    void deleteCardEntry(CardEntriesEntity cardEntry);
    void requestRandomCards(int countOfCardsRequired);
}
