package br.com.fb.controller;

import java.util.List;

import br.com.fb.controller.exemplo05AmigosFql.FqlUser;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import com.restfb.types.User;

public class Atividade {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*
         * Obter via JSON os seguintes dados de seus amigos do Facebook:
         *         nome, sexo, data de nascimento e cidade;
         */
       
        TokenFB token = new TokenFB();
        FacebookClient facebookClient = new DefaultFacebookClient(token.getToken());
       
        User user = facebookClient.fetchObject("me", User.class, Parameter.with("metadata", 1));       
        System.out.println("Nome do usuário do Facebook: " + user.getName());
       
        Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
        System.out.println(user.getName() + " tem " + myFriends.getData().size() + " amigos!");
       
       
        String queryAmigos = "SELECT name, sex, birthday, current_location FROM user WHERE uid IN (SELECT target_id FROM connection WHERE source_id = " + user.getId() +" )";
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
        String name;
       
        @Facebook
        String sex;
       
        @Facebook
        String birthday;
       
        @Facebook
        String current_location;
       
        @Override
        public String toString() {
            return String.format("%s, %s, %s, %s\n", name, sex, birthday, current_location);
        }

    }

}