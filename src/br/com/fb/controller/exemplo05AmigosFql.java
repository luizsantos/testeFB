package br.com.fb.controller;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import com.restfb.types.User;
import java.util.List;

public class exemplo05AmigosFql {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* 
		 * Exemplo 05 - Busca dados dos Amigos via FQL e JSON
		 */
		
		TokenFB token = new TokenFB();
		FacebookClient facebookClient = new DefaultFacebookClient(token.getToken());
		
		User user = facebookClient.fetchObject("me", User.class, Parameter.with("metadata", 1));		
		System.out.println("Nome do usuário do Facebook: " + user.getName());
		
		Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
		System.out.println(user.getName() + " tem " + myFriends.getData().size() + " amigos!");
		
		
		String queryAmigos = "SELECT uid, name FROM user WHERE uid IN (SELECT target_id FROM connection WHERE source_id = " + user.getId() +" )";
		System.out.println(" busca - > " + queryAmigos);
		List<FqlUser> amigos = facebookClient.executeQuery(queryAmigos, FqlUser.class);
		System.out.println("FQL -: Amigos: ");		
		System.out.println("FQL -: " + amigos);
		
		
		List<JsonObject> queryJson = facebookClient.executeQuery(queryAmigos, JsonObject.class);
		System.out.println("Json da busca dos nomes de amigos e UIDs:\n");
		System.out.println(queryJson);

	}
	
	public static class FqlUser {
		@Facebook
		String uid;
		
		@Facebook
		String name;
		
		@Override
		public String toString() {
			return String.format("%s (%s)\n", uid, name);
		}

	}

}
