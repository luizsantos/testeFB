package br.com.fb.controller;

import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;

public class exemplo03Amigos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* 
		 * Exemplo 03 - Mostrar no e ID dos amidos
		 */
		
		TokenFB token = new TokenFB();
		
		FacebookClient facebookClient = new DefaultFacebookClient(token.getToken());
		
		User user = facebookClient.fetchObject("me", User.class, Parameter.with("metadata", 1));
		System.out.println("Nome do usuário do Facebook: " + user.getName());
		
		Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
		System.out.println(user.getName() + " tem " + myFriends.getData().size() + " amigos!");
		
		//buscar amigos e sexo pelo método
		System.out.println("Os amigos do Facebook são:");

		for (List<User> meusAmigos : myFriends)
			for (User amigos : meusAmigos){
				System.out.println( "ID: " + amigos.getId() +" Nome: " + amigos.getName() + "local "+ amigos.getHometownName());
			}

	}

}
