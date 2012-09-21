package com.warriormill.warriorengine;

import android.app.Activity;
import android.os.Bundle;

public class WarriorEngine extends Activity {
    private GameEngineView stv;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stv = new GameEngineView(this);
        setContentView(stv);
        stv.requestFocus();
    }
}