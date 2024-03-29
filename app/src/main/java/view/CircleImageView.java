package view;

/*
 * Copyright 2013 Csaba Szugyiczki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zp2.myapplication.CircleMenu;
import com.zp2.myapplication.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author Szugyi
 * Custom ImageView for the CircleLayout class.
 * Makes it possible for the image to have an angle, position and a name.
 * Angle is used for the positioning in the circle menu.
 */
public class CircleImageView extends ImageView {

	private String name;
	private int resId;
	private CircleMenu circleMenu;
	public static List<CircleMenu> sMenuList = new ArrayList<>();
	private int menuListIndex;

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}


	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public CircleMenu getCircleMenu() {
		return circleMenu;
	}

	public void setCircleMenu(CircleMenu circleMenu) {
		this.circleMenu = circleMenu;
		setResId(circleMenu.getResId());
		setImageResource(getResId());
		setName(circleMenu.getName());

	}

	public int getMenuListIndex() {
		return menuListIndex;
	}

	public void setMenuListIndex(int menuListIndex) {
		this.menuListIndex = menuListIndex;
		setCircleMenu(sMenuList.get(menuListIndex));
	}

	/**
	 * @param context
	 */
	public CircleImageView(Context context) {
		this(context, null);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CircleImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if (attrs != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs,
					R.styleable.CircleImageView);
			
			name = a.getString(R.styleable.CircleImageView_name);
		}
	}

	/**
	 * 是否是占位空白，注意只根据name判断
	 * @return
	 */
	public boolean isEmpty(){
		return TextUtils.isEmpty(name);
	}

}
