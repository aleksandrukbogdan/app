package com.example.fury;

import android.graphics.Bitmap;
//Используется для связи класса Shell с фрагментами(каждый метод объяснён в классе Shell)
interface ExecuteFragment {
    void NumberTransaction(String fragment);
    void StartAlphabet(int letter_choose);
    void LoadingAlphabet(Bitmap v_story, Bitmap v_char, Bitmap pictures[], String pic_string[], boolean correct[],int pixels);
}
