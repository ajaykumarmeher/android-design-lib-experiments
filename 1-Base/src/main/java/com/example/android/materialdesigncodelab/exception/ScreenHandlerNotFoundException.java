package com.example.android.materialdesigncodelab.exception;

/**
 * Created by ajayk on 9/20/2016.
 */

public class ScreenHandlerNotFoundException extends NullPointerException {

    @Override
    public String getMessage() {
        return "Screen Handler is found NULL.";
    }
}
