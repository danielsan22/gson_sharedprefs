package com.example.danielsanchez.helloworld;

import com.google.gson.Gson;

/**
 * Created by danielsanchez on 01/09/17.
 */

/**
 * Simple model class.
 */
public class User {

    private int id;
    private String name;
    private boolean isAdmin;
    private float height;

    public User() {
    }

    public User(int id, String name, boolean isAdmin, float height) {
        this.id = id;
        this.name = name;
        this.isAdmin = isAdmin;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Aquí es donde se hace la magia. Con la librería de Gson creas un objeto y le dices a partir
     * de que objeto queires que haga el json. toma exactamente los mismos nombres para los campos
     * que tus propiedades. La librería tiene varios métodos pero el que mas he usado es ese,
     * simplemente le pasas la instancia del propio objeto (para evitar tener que hacer clases que
     * se dediquen unicamente a serializar y des-serializar el objeto en json, mejor que lo haga el
     * solo).
     * @return un String con formato json con las propiedades correspondientes.
     */

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);

    }

    /**
     * Este método hace lo opuesto, se le pasas un string (con el correcto formato de un json) y lo
     * convierte a la clase especificada. En este caso pues lo transforma a User. El método toma 2
     * argumentos, el primero el json, que le pasamos como parámetro y el segundo es la clase a la
     * cual se quiere hacer el parse. Recuerda que las propiedades de tu json deben ser las mismas,
     * si varía en lo mas mínimo truena (creo, no lo se a ciencia cierta.) Yo en lo personal hago
     * este método estático para poder acceder directamente desde User.fromJson() y para evitar lo
     * de las clases serializadoras. Cuando tienes el json ya (digamos que desde un WS) puedes usar
     * este métood como 'constructor' del objeto.
     *
     * @param json en formato correcto de json, ej: {"height":1.6,"id":1001,"isAdmin":true,"name":"Jessica"}
     * @return una instancia de tipo User con los valores que estaban en el json.
     */
    public static User fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,User.class);
    }

}
