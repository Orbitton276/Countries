package com.countries.model;

import java.io.Serializable;
import java.util.Comparator;

public class Country implements Serializable {
    String name;
    String alpha2Code;
    String alpha3Code;
    String capital;
    String region;
    String[] topLevelDomain;
    String[] callingCodes;
    String[] altSpellings;


    public String[] getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(String[] topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public String[] getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(String[] callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String[] getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(String[] altSpellings) {
        this.altSpellings = altSpellings;
    }

    public static Comparator<Country> CountryAZComparator = new Comparator<Country>() {
        private Country country;
        private Country t1;

        @Override
        public int compare(Country country, Country t1) {
            this.country = country;
            this.t1 = t1;
            return country.name.compareTo(t1.name);
        }
    };
    public static Comparator<Country> CountryZAComparator = new Comparator<Country>() {
        @Override
        public int compare(Country country, Country t1) {
            return t1.name.compareTo(country.name);
        }
    };
}
