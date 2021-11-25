package com.epam.learn.java.ad.gallery.app.locale;

import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

class ResourceManager {
    ResourceBundle rb;
    boolean isLameMode = false;

    public ResourceManager() {
        rb = new LameResourceBundle();
    }

    public ResourceManager(String name, Locale locale) {
        try {
            rb = ResourceBundle.getBundle( name, locale );
        } catch (MissingResourceException e) {
            isLameMode = true;
            rb = new LameResourceBundle();
        }
    }

    protected boolean containsKey(String key) {
        return rb.containsKey( key );
    }

    protected String getString(String key) {
        if (rb.containsKey( key )) {
            return rb.getString( key );
        }
        return key;
    }

    protected <E extends Enum<E>> String getString(E e) {
        return getString( e.toString() );
    }

    public void setLocale(Locale locale) {
        if (isLameMode) {
            return;
        }
        rb = ResourceBundle.getBundle( rb.getBaseBundleName(), locale );
    }

    public void setLocale(String localeName) {
        setLocale( new Locale( localeName ) );
    }

    private static class LameResourceBundle extends ResourceBundle {
        @Override
        protected Object handleGetObject(String key) {
            return key;
        }

        @Override
        public Enumeration<String> getKeys() {
            throw new UnsupportedOperationException();
        }
    }
}
