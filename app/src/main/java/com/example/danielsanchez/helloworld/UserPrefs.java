package com.example.danielsanchez.helloworld;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by danielsanchez on 01/09/17.
 */

public class UserPrefs {

    private static final String USER = "USER_INFO";

    private SharedPreferences sharedPreferences;
    private Context context;

    /**
     * Instancias la clase que contendrá todos los métodos de acceso a shared preferences. Piensalo
     * como un acceso a BDD, es como parte de la capa de servicios.
     * @param context EL activity de donde se está instanciando.
     */
    public UserPrefs(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("USER_INFO",Context.MODE_PRIVATE);
    }


    /**
     * Como tu sabes que va a tener el string en formato json, pues lo parseas.
     * El método para recuperar tiene que recibir dos parámetros, el de la 'key' que vas a lerer y
     * un valor por defecto en caso de que no exista. No se si el valor por defecto al parsearlo de
     * error, no alcancé a probarlo por que ya se terminó de actualizar Overwatch :P
     * @return
     */
    public User getUser(){
        Gson gson = new Gson();
        return gson.fromJson(sharedPreferences.getString(USER,"null"), User.class);
    }

    public void saveUser(User user){
        //Creas un editor a partir de las preferencias compartidas, puedes poner esto en un método y
        //abstraerlo para evitar escribirlo tantas veces.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //le dices bajo que nombre vas a meter el dato, como arriba lo leiamos con USER pues acá lo
        // guardamos como USER también. Además le mandamos el valor que vamos a guardar, en este
        // caso nuestro usuario a json.
        editor.putString(USER,user.toJson());
        //para hacer los cambios tienes DOS opciones.
        //editor.commit(); //Este guarda tod0 de jalón. En el momento. Jala por las chelas.
        editor.apply(); //Este guarda tod0 en background. No jala al momento. Chupa tranquilo en su casa.

    }

}
