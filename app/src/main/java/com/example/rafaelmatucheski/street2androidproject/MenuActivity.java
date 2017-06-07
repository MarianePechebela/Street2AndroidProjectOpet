package com.example.rafaelmatucheski.street2androidproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.login.LoginManager;

import org.w3c.dom.Text;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onLogout(View view) {

        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Exit", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    public void selectMainOpetion(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.btnOnibus:
                intent = new Intent(this,ListOnibusActivity.class);
                break;
            case R.id.btnRotas:
                intent = new Intent(this, ListRotassActivity.class);
        }
        if(intent != null){
            startActivity(intent);
        }
    }
}
