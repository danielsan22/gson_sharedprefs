package com.example.danielsanchez.helloworld;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity-EXAMPLE";
    private TextView mTextMessage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //un ususrio normal
        User userToJson = new User();
        //asignamos los datos normal.
        userToJson .setId(1001);
        userToJson .setName("Jessica");
        userToJson .setAdmin(true);
        userToJson .setHeight(1.60f);
        //creamos el json con el método que está en la clase User.
        String json = userToJson.toJson();
        //imprimimos el valor pa' poder verlo.
        Log.d(TAG,json);

        //Invertimos el proceso, creamos una instancia con el método estático fromJson.
        User userFromJson = User.fromJson(json);

        //al tener el objeto imprimimos los valores, deberían de ser los mismos que el objeto original.
        Log.d(TAG,userFromJson.getName() +"-"+ userFromJson.getId()+"-" +userFromJson.getHeight()
                +"-"+ userFromJson.isAdmin());


        UserPrefs prefs = new UserPrefs(this);

        if(prefs.getUser() != null){
            Log.d(TAG, "El usuario no fue nulo. ");
        }else {
            Log.d(TAG, "El usuario fue nulo. ");
        }


        prefs.saveUser(userFromJson);

        if(prefs.getUser() != null){
            Log.d(TAG, "El usuario no fue nulo. ");
        }else {
            Log.d(TAG, "El usuario fue nulo. ");
        }

    }

}
