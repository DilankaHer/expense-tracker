package com.example.expense_tracker.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    private Map<String, String> store;

    private static MapUtils instance;

    private MapUtils() {
        this.store = new HashMap<>();

        store.put("category-food", "Food");
        store.put("category-transport", "Transport");
        store.put("category-entertainment", "Entertainment");
        store.put("category-education", "Education");
        store.put("category-health", "Health");
        store.put("category-shopping", "Shopping");
        store.put("category-transport", "Transport");
        store.put("category-bills", "Bills");
    }

    public static MapUtils getInstance() {
        if (instance == null) {
            instance = new MapUtils();
        }
        return instance;
    }

    public String get(String key) {
        return store.get(key);
    }

    public void put(String key, String value) {
        store.put(key, value);
    }
}
