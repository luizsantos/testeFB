package br.com.fb.controller;

import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import com.restfb.types.User;

public class exemplo04UserFql {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* 
		 * Exemplo 04 - Busca dados do usuário do Facebook via FQL e JSON
		 */
		
		TokenFB token = new TokenFB();
		
		FacebookClient facebookClient = new DefaultFacebookClient(token.getToken());
		
		User user = facebookClient.fetchObject("me", User.class, Parameter.with("metadata", 1));
		
		System.out.println("Nome do usuário do Facebook: " + user.getName());
		
		Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
		
		System.out.println(user.getName() + " tem " + myFriends.getData().size() + " amigos!");
		
		//buscar meu nome e sexo via FQL
		String query = "SELECT name, sex FROM user WHERE uid = " + user.getId();
	
		List<FqlUser> usuarios = facebookClient.executeQuery(query, FqlUser.class);
			
		System.out.println("FQL -: Meu Nome e sexo:");		
		System.out.println("FQL -: " + usuarios);
		
		//Gerando um Json de um Objeto
		JsonObject my = facebookClient.fetchObject("me", JsonObject.class);
		System.out.println("Json do usuário:\n" + my);

	}

	public static class FqlUser {
		@Facebook
		String name;
		
		@Facebook
		String sex;
		
		@Override
		public String toString() {
			return String.format("%s (%s)\n", name, sex);
		}

	}	
	

}
