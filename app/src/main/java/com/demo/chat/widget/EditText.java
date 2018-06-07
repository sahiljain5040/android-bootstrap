package com.demo.chat.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class EditText extends AppCompatEditText {

    protected KeyboardListener keyboardListener;

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditText(Context context) {
        super(context);
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        onTextChanged(text.length());
    }

    public void clear(){
        this.setText("");
    }

    public void setKeyboardListener(KeyboardListener listener) {
        this.keyboardListener = listener;
    }

    public void onTextChanged(int length) {
        if (keyboardListener != null) {
            keyboardListener.onTextChanged(length);
        }
    }

    public interface KeyboardListener {
        void onTextChanged(int length);
    }
}
