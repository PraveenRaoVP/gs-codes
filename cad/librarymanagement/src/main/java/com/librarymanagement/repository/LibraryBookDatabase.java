package com.librarymanagement.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryBookDatabase {
    // store books Id to corresponding library id
    private Map<Integer, List<Integer>> libraryIdToBookIds = new HashMap<>();
    private LibraryBookDatabase() {}

    private static LibraryBookDatabase libraryBookDatabase;

    public static LibraryBookDatabase getInstance() {
        if(libraryBookDatabase == null) {
            libraryBookDatabase = new LibraryBookDatabase();
        }
        return libraryBookDatabase;
    }

    public Map<Integer, List<Integer>> getLibraryIdToBookIds() {
        return libraryIdToBookIds;
    }

    public void setLibraryIdToBookIds(Map<Integer, List<Integer>> libraryIdToBookIds) {
        this.libraryIdToBookIds = libraryIdToBookIds;
    }

    // insert book id to a certain library id
    public void insertBookIdToLibraryId(int libraryId, int bookId) {
        libraryIdToBookIds.get(libraryId).add(bookId);
    }
}
