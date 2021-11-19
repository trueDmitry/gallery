package com.epam.learn.java.ad.gallery.api;

import com.epam.learn.java.ad.gallery.app.exception.AuthenticationException;
import com.epam.learn.java.ad.gallery.web.WebCommand;

public interface SecurityServiceI {

	boolean userHasRole(String string);

	boolean checkAccess(Class<? extends WebCommand> class1);

	boolean authenticate(String name, String password) throws AuthenticationException;

}
