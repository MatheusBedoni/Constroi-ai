package com.app.equipe.controiai2.provider;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by Matheus on 13/02/2016.
 */
public class SearchableProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "com.example.matheus.controiai2.provider.SearchableProvider";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public SearchableProvider(){
        setupSuggestions( AUTHORITY, MODE );
    }
}
