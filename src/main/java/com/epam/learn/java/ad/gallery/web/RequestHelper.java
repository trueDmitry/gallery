package com.epam.learn.java.ad.gallery.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private HttpServletRequest request;

	public RequestHelper(HttpServletRequest request) {
		this.request = request;
	}

	public int getInt(String name) {
		return Integer.parseInt(request.getParameter(name));
	}

	public Date getDate(String name) throws ParseException {
		return formatter.parse(request.getParameter(name));
	}

	public static void printParameters(HttpServletRequest request) {
		request.getParameterMap().forEach((k, v) -> System.out.println(k + " " + Arrays.toString(v)));
	}

	public String getString(String name) {
		return request.getParameter(name);
	}

	public List<Integer> getIds(String name) {
		List<Integer> res = new ArrayList<>();
		String[] ids = request.getParameterValues(name);
		if (ids == null) {
			return res;
		}
		for (String s : ids) {
			res.add(Integer.valueOf(s));
		}
		return res;
	}

	public boolean getBoolean(String name) {
		System.out.println(request.getParameter(name));
		String val = request.getParameter(name);
		return !(val == null || Objects.equals("false", val));
	}

	/**
	 * id = 1 ... Integer.MAX_VALUE
	 * 
	 * @param string
	 * @return integer id value or 0 for unacceptable state
	 */
	public int getId(String name) {
		try {
			return Integer.parseInt(request.getParameter(name));
		} catch (NumberFormatException e) {
			return 0;
		}
	}

}
