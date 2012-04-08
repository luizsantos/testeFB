package br.com.fb.controller;

public class TokenFB {
	// https://developers.facebook.com/tools/explorer?method=GET&path=1314662168
	
	private String Token = "SeuToken";

	public String getToken() {

		System.out.println("\nCaso n�o funcione lembre de ver se o Token n�o expirou!\n Veja tamb�m se o Token tem permiss�o de acessar os dados...");
		System.out.println("\n Para pegar ou atualizar o Token acesse:\nhttps://developers.facebook.com/tools/explorer?method=GET&path=1314662168");
		
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

}
