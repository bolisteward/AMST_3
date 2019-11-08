package Objetos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Tweet {

    private String autor;
    private String tweet;
    private String fecha;
    private String phone;
    private String providerId;

    Tweet(String autor, String tweet, String phone, String providerId) {
        this.autor = autor;
        this.tweet = tweet;
        this.phone = phone;
        this.providerId = providerId;
    }
    public void publicarTweet(){
        String fecha_actual = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        this.fecha = fecha_actual;
        }

}