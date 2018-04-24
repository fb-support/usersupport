package com.facebank.usersupport.util;

import com.facebank.usersupport.model.UserModel;

import javax.servlet.http.HttpSession;

public class SessionUtil {
	public static final String USER = "user";
	public static final String ASSOCIATOR = "associator";
	public static final String USERID = "userId";

	/**
	 * 设置用户到session
	 */
	public static void setUser(HttpSession session, UserModel user) {
		session.setAttribute(USER, user);
		setUserId(session, user.getUserId().toString());
	}

	/**
	 * 从Session获取当前用户信息
	 */
	public static UserModel getUser(HttpSession session) {
		Object user = session.getAttribute(USER);
		return user == null ? null : (UserModel) user;
	}

	/**
	 * 设置用户到session
	 */
	public static void setUserId(HttpSession session, String userId) {
		session.setAttribute(USERID, userId);
	}

	/**
	 * 从Session获取当前用户信息
	 */
	public static String getUserId(HttpSession session) {
		Object userId = session.getAttribute(USERID);
		return userId == null ? null : (String) userId;
	}

	public static void removeAttribute(HttpSession session) {
		session.removeAttribute(USER);
		session.removeAttribute(USERID);
	}

}
