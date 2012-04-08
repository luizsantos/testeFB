package br.com.fb.controller;

import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.restfb.types.User;

public class exemplo02Posts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* 
		 * Exemplo 02 - Mostrar posts do usuário do Facebook
		 */
		
		TokenFB token = new TokenFB();
		FacebookClient facebookClient = new DefaultFacebookClient(token.getToken());
		
		User user = facebookClient.fetchObject("me", User.class, Parameter.with("metadata", 1));
		System.out.println("Nome do usuário do Facebook: " + user.getName());
		
		Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
		System.out.println(user.getName() + " tem " + myFriends.getData().size() + " amigos!");
		
		// buscar posts
		Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);
		
		for (List<Post> myFeedConnectionPage : myFeed)
			for (Post post : myFeedConnectionPage)
					System.out.println("Post: " + post);

	}

}
