package com.example.test.homework_1.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wenlin on 2015/11/6.
 */
public class MyDataResult {
    private Result result;

    public Result getResult() {
        return result;
    }

    // Inner Classes
    public static class Result {
        private String offset;

        @SerializedName("limit")
        private String limitation;

        private String count;

        private String sort;

        private List<ResultItem> results;

        public List<ResultItem> getResults() {
            return results;
        }

    }

    public static class ResultItem {
        private String _id;
        private String S104NAME;
        private String RESULT;

        public String get_id() {
            return _id;
        }

        public String S104NAME() {
            return S104NAME;
        }

        public String RESULT() {
            return RESULT;
        }

    }
}
