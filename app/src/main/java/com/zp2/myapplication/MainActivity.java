package com.zp2.myapplication;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import view.CircleImageView;
import view.CircleLayout;

public class MainActivity extends Activity implements CircleLayout.OnItemSelectedListener {

    private CircleLayout circleLayout;
    public int menuCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleLayout = findViewById(R.id.main_circle_layout);
        circleLayout.setOnItemSelectedListener(this);
        circleLayout.setOnRotationFinishedListener(new CircleLayout.OnRotationFinishedListener() {
            @Override
            public void onRotationFinished(View view) {
                if(menuCount == 4){
                    CircleMenu fouthMenu = find4thMenu();
                    ((CircleImageView)circleLayout.getMenuByPos(2)).setCircleMenu(fouthMenu);
                    ((CircleImageView)circleLayout.getMenuByPos(4)).setCircleMenu(fouthMenu);
                }

            }
        });

    }

    private CircleMenu find4thMenu(){
        CircleImageView leftMenu = (CircleImageView) circleLayout.getLeftMenu();
        CircleImageView selectedMenu = (CircleImageView) circleLayout.getSelectedItem();
        CircleImageView rightMenu = (CircleImageView) circleLayout.getRightMenu();
        for(CircleMenu c : CircleMenu.values()){
            if(c != leftMenu.getCircleMenu() && c != selectedMenu.getCircleMenu() && c != rightMenu.getCircleMenu()){
                return c;
            }
        }
        return CircleMenu.LIVE;
    }

    @Override
    public void onItemSelected(View mview) {
        CircleImageView civ = (CircleImageView) mview;
//        Toast.makeText(this,civ.getName(),Toast.LENGTH_SHORT).show();
    }

    public void addChild(CircleMenu circleMenu) {
        CircleImageView newMenu = new CircleImageView(this);
        newMenu.setCircleMenu(circleMenu);
        circleLayout.addView(newMenu);
    }

    public void addStub(){
        CircleImageView stub = new CircleImageView(this);
        circleLayout.addView(stub);
    }


    public void onClick3menu(View view) {
        menuCount = 3;
        circleLayout.setTwoMenuMode(false);
        circleLayout.removeAllViews();
        addChild(CircleMenu.ALBUM);
        addChild(CircleMenu.VIDEO);
        addChild(CircleMenu.VR);
        addChild(CircleMenu.ALBUM);
        addChild(CircleMenu.VIDEO);
        addChild(CircleMenu.VR);
    }

    public void onClick4menu(View view) {
        circleLayout.setTwoMenuMode(false);
        menuCount = 4;
        circleLayout.removeAllViews();
        addChild(CircleMenu.ALBUM);
        addChild(CircleMenu.VIDEO);
        addChild(CircleMenu.LIVE);
        addChild(CircleMenu.LIVE);
        addChild(CircleMenu.LIVE);
        addChild(CircleMenu.VR);


    }

    public void onClick2menu(View view) {
        menuCount = 2;
        circleLayout.setTwoMenuMode(true);
        circleLayout.removeAllViews();
        addChild(CircleMenu.ALBUM);
        addStub();
        addStub();
        addStub();
        addStub();
        addChild(CircleMenu.VIDEO);
    }

    public void onClickClear(View view) {
        circleLayout.removeAllViews();
    }
}
