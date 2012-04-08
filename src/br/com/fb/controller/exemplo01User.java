package br.com.fb.controller;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;

public class exemplo01User {
	
	public static void main(String[] args) {

		/* 
		 * Exemplo 01 - Busca dados do usuário do Facebook
		 */
		
		TokenFB token = new TokenFB();
		
		FacebookClient facebookClient = new DefaultFacebookClient(token.getToken());
		
		User user = facebookClient.fetchObject("me", User.class, Parameter.with("metadata", 1));
		System.out.println("Nome do usuário do Facebook: " + user.getName());
		
		Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
		System.out.println(user.getName() + " tem " + myFriends.getData().size() + " amigos!");
		
	}

}
