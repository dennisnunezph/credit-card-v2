package com.compareglobal.service.creditcard.domain;

/**
 * Created by dennis on 3/19/15.
 */
public class RetrieveList {
    private String locale;
    private Long id;

    public String getLocale() {
        return locale;
    }

    public Long getId() {
        return id;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RetrieveList{" +
                "locale='" + locale + '\'' +
                ", id=" + id +
                '}';
    }


}
