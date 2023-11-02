package com.base.restcountries;

import java.util.List;

public class CountryInfo {
    private NameInfo name;
    private List<String> capital;
    private FlagsInfo flags;
    // Add other fields according to your needs

    public NameInfo getName() {
        return name;
    }

    public List<String> getCapital() {
        return capital;
    }

    public FlagsInfo getFlags() {
        return flags;
    }


    public static class NameInfo {
        private String common;


        public String getCommon() {
            return common;
        }

    }

    public static class FlagsInfo {
        private String png;

        public String getPng() {
            return png;
        }
    }
}
