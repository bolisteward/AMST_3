package com.example.grupo4_ta3_iniciosesionfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PerfilUsuario extends AppCompatActivity {

    TextView txt_id, txt_name, txt_email, txt_phone, txt_providerId;
    ImageView imv_photo;
    Button btn_logout;
    DatabaseReference db_reference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        Intent intent = getIntent();
        HashMap<String, String> info_user = (HashMap<String, String>)intent.getSerializableExtra("info_user");

        txt_id = findViewById(R.id.txt_userId);
        txt_name = findViewById(R.id.txt_nombre);
        txt_email = findViewById(R.id.txt_correo);
        imv_photo = findViewById(R.id.imv_foto);
        txt_phone = findViewById(R.id.txt_phone);
        txt_providerId = findViewById(R.id.txt_providerId);

        txt_id.setText(info_user.get("user_id"));
        txt_name.setText(info_user.get("user_name"));
        txt_email.setText(info_user.get("user_email"));
        txt_phone.setText(info_user.get("user_phone"));
        txt_providerId.setText(info_user.get("user_providerId"));

        String photo = info_user.get("user_photo");
        Picasso.get().load(photo).resize(300,300).into(imv_photo);

        iniciarBaseDeDatos();
        leerTweets();
        escribirTweets(info_user.get("user_name"), info_user.get("user_phone"), info_user.get("user_providerId"));
    }


    public void cerrarSesion(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("msg", "cerrarSesion");
        startActivity(intent);
    }
    public void iniciarBaseDeDatos() {
        db_reference = FirebaseDatabase.getInstance().getReference().child("Grupo");
    }
    public void leerTweets(){
        db_reference.child("Grupo 4").child("tweets").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    System.out.println(snapshot);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.toException());
            }
        });
    }

    public void escribirTweets(String autor, String phone, String providerId) {


        String tweet = "hola mundo firebase";
        String fecha = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Map<String, String> hola_tweet = new HashMap<String, String>();
        hola_tweet.put("autor", autor);
        hola_tweet.put("fecha", fecha);
        hola_tweet.put("Telefono", phone);
        hola_tweet.put("Provider ID", providerId);
        DatabaseReference tweets = db_reference.child("Grupo 4").child("tweets");
        tweets.setValue(tweet);
        tweets.child(tweet).child("autor").setValue(autor);
        tweets.child(tweet).child("fecha").setValue(fecha);
        tweets.child(tweet).child("Telefono").setValue(phone);
        tweets.child(tweet).child("Provider Id").setValue(providerId);
    }
    public void irRegistros(View view){
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }



}

