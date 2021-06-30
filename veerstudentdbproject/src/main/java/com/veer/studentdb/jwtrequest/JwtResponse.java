package com.veer.studentdb.jwtrequest;

import com.veer.studentdb.entity.User;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtResponse {

	private User user;
	private String jwtToken;

	public JwtResponse(User user, String jwtToken) {
		log.info(this.getClass().getSimpleName()+" - jwt response");
		this.user = user;
		this.jwtToken = jwtToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
