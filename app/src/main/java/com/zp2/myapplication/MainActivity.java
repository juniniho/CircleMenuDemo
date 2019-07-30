package com.zp2.myapplication;


import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import view.CircleImageView;
import view.CircleLayout;

public class MainActivity extends Activity implements CircleLayout.OnItemSelectedListener {

    private CircleLayout circleLayout;
    public static final int CHILD_COUNT = 6;
    private GestureDetector gestureDetector;

    private float offsetX;
    private float offsetY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleLayout = findViewById(R.id.main_circle_layout);
        circleLayout.setOnItemSelectedListener(this);
        circleLayout.setOnRotationFinishedListener(new CircleLayout.OnRotationFinishedListener() {
            @Override
            public void onRotationFinished(View view) {
                setNextMenu();
            }
        });

        circleLayout.post(new Runnable() {
            @Override
            public void run() {
                int circleWidth = circleLayout.getWidth();
                int circleHeight = circleLayout.getHeight();
                offsetX = circleWidth/2f + circleLayout.getLeft();
                offsetY = circleHeight/2f + circleLayout.getTop();
            }
        });

        gestureDetector = new GestureDetector(this,new MyGestureListener());

        findViewById(R.id.button3).performClick();


    }

    private void setNextMenu(){
        if(CircleImageView.sMenuList.size() >= 3){
            int secondPosIndex = ((CircleImageView)circleLayout.getMenuByPos(1)).getMenuListIndex() + 1;
            secondPosIndex = normalization(secondPosIndex);
            ((CircleImageView)circleLayout.getMenuByPos(2)).setMenuListIndex(secondPosIndex);

            int fourthPosIndex = ((CircleImageView)circleLayout.getMenuByPos(5)).getMenuListIndex() - 1;
            fourthPosIndex = normalization(fourthPosIndex);
            ((CircleImageView)circleLayout.getMenuByPos(4)).setMenuListIndex(fourthPosIndex);
        }
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

    private int normalization(int index){
        if(index < 0){
            index += CircleImageView.sMenuList.size();
        }
        index %= CircleImageView.sMenuList.size();
        return index;
    }

    private void initMenu(){
        circleLayout.removeAllViews();
        for(int i = 0; i< CHILD_COUNT; i++){
            addStub();
        }

        for(int i = -2;i<=2;i++){
            int index = i;
            if(index < 0){
                index += CHILD_COUNT;
            }
            CircleImageView civ = (CircleImageView) circleLayout.getChildAt(index);
            civ.setMenuListIndex(normalization(i+1));
        }
    }

    public void onClick3menu(View view) {
        CircleImageView.sMenuList.clear();
        CircleImageView.sMenuList.add(CircleMenu.ALBUM);
        CircleImageView.sMenuList.add(CircleMenu.VIDEO);
        CircleImageView.sMenuList.add(CircleMenu.VR);
        initMenu();
    }

    public void onClick4menu(View view) {
        CircleImageView.sMenuList.clear();
        CircleImageView.sMenuList.add(CircleMenu.ALBUM);
        CircleImageView.sMenuList.add(CircleMenu.VIDEO);
        CircleImageView.sMenuList.add(CircleMenu.VR);
        CircleImageView.sMenuList.add(CircleMenu.LIVE);
        initMenu();


    }

    public void onClick2menu(View view) {
        CircleImageView.sMenuList.clear();
        CircleImageView.sMenuList.add(CircleMenu.ALBUM);
        CircleImageView.sMenuList.add(CircleMenu.VIDEO);

        circleLayout.removeAllViews();
        addChild(CircleMenu.ALBUM);
        addChild(CircleMenu.VIDEO);
        addStub();
        addStub();
        addStub();
        addStub();

    }

    public void onClick1menu(View view) {
        CircleImageView.sMenuList.clear();
        CircleImageView.sMenuList.add(CircleMenu.ALBUM);

        circleLayout.removeAllViews();
        addChild(CircleMenu.ALBUM);
        addStub();
        addStub();
        addStub();
        addStub();
        addStub();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean isClock = CircleLayout.isClockRotate(e1.getX() - offsetX,e1.getY() - offsetY,e2.getX() - offsetX,e2.getY() - offsetY);
            circleLayout.dealRotate(isClock);
            return true;
        }
    }

}
