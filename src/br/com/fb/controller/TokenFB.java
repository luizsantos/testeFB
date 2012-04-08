package br.com.fb.controller;

public class TokenFB {
	// https://developers.facebook.com/tools/explorer?method=GET&path=1314662168
	
	private String Token = "AAACEdEose0cBAEbvMY3Y25MPSZA8SKm06ppC0ZBqxZAX9SB4B0eZAYi31lapfnIGrjCgZALANvB6m7E3LZCdy47VAp0v72zDr1V10sxN9yq7g0WcP5jfZBz";

	public String getToken() {

		System.out.println("\nCaso não funcione lembre de ver se o Token não expirou!\n Veja também se o Token tem permissão de acessar os dados...");
		System.out.println("\n Para pegar ou atualizar o Token acesse:\nhttps://developers.facebook.com/tools/explorer?method=GET&path=1314662168");
		
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

}
