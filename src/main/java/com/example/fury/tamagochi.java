package com.example.fury;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class tamagochi {
    private int hungriness = 0;
    private int happiness = 5;
    private int strength = 0;
    private int cleanliness = 10;

    public tamagochi(int hungriness, int happiness, int strength, int cleanliness) {
        this.hungriness = hungriness;
        this.happiness = happiness;
        this.strength = strength;
        this.cleanliness = cleanliness;
    }

    public void feed() {
        hungriness +=5;
        if (hungriness<0) {
            hungriness = 0;
        }
    }
    public void walk () {
        happiness += 3;
        strength -= 2;
        if (happiness > 10) {
            happiness = 10;
        }
        if (strength<0) {
            strength=0;
        }
    }
    public void clean() {
        cleanliness = 10;
    }
    public void pet() {
        happiness+=5;
        if (happiness>10) {
            happiness = 10;
        }
    }
    public void sleep() {
        strength = 10;
        if (strength>10) {
            strength = 10;
        }
    }

    public int getHungriness() {
        return hungriness;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getStrength() {
        return strength;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public String getMood() {
        if (strength==0)
            return "Наелся и спит";
        else if(strength<=2)
            return "Устал";
        else if (hungriness>=7)
            return "Голоден";
        else if (cleanliness< 3)
            return "ужно помыться";
        return null;
    }
    public void passTime() {
        hungriness++;
        cleanliness--;
        if (strength==0) {
            strength = 10;
        } else strength --;
        if (hungriness>=7)
            happiness--;
        if (cleanliness < 3)
            happiness--;
        if (hungriness>100)
            hungriness = 100;
        if (cleanliness<0)
            cleanliness=0;
        if (happiness<0)
            happiness =0;
    }







}