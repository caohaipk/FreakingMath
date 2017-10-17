package com.wordpress.grayfaces.freakingmath.ui;

import java.util.Random;

/**
 * Project FreakingMath
 * Created by Gray on 10/17/2017.
 */

public class BackgroundColor {
    Color[] listColor;
    public BackgroundColor(){
        initListColor();
    }
    private void initListColor(){
        listColor = new Color[]{new Color("purple","#8f3e97"), new Color("deep_purple","#65499d"), new Color("red","#ef4437"), new Color("pink","#e71f63"),
                new Color("indigo","#4554a4"), new Color("blue","#478fcc"), new Color("light_blue","#38a4dc"), new Color("cyan","#09bcd3"),
                new Color("teal","#009688"), new Color("green","#4cae4e"), new Color("light_green","#8bc248"), new Color("lime","#cddc37"),
                new Color("light_yellow","#feea39"), new Color("yellow","#f9ce1e"), new Color("orange","#f8971c"), new Color("deep_orange","#f0592b"),
                new Color("blue_grey","#607d8b"), new Color("brown","#795548"),};
    }
    public String getRandomColor(){
        int length = listColor.length;
        Random random = new Random();
        int ranNumber = random.nextInt(length);
        return listColor[ranNumber].HEXValue;
    }
    private class Color{
        private String Name;
        private String HEXValue;
        Color(String name, String value){
            Name = name;
            HEXValue = value;
        }
    }
}
