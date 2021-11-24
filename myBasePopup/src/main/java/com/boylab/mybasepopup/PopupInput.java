package com.boylab.mybasepopup;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

import razerdp.basepopup.BasePopupWindow;

public class PopupInput extends BasePopupWindow implements View.OnClickListener {

    private OnPopupInputListener inputListener;

    private TextView tv_confirm;
    private EditText ed_input;

    public PopupInput(Context context) {
        super(context);
        //View rootView = createPopupById(R.layout.popup_input);

        //setContentView(R.layout.popup_input);
    }

    protected void setInput(String text){
        ed_input.setText(text);
        ed_input.setSelection(text.length());
    }

    public PopupInput numberType(String text, int max){
        setInput(text);
        return numberType(max);
    }

    public PopupInput decimalType(String text, final int numberLen, int point){
        setInput(text);
        return decimalType(numberLen, point);
    }

    public PopupInput textType(String text, int max){
        setInput(text);
        return textType(max);
    }

    /**
     * 数字输入模式
     * @param max
     */
    public PopupInput numberType(int max){
        ed_input.setInputType(InputType.TYPE_CLASS_NUMBER);
        ed_input.setKeyListener(DigitsKeyListener.getInstance(false, false));
        ed_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max)});

        return this;
    }

    /**
     * 小数点输入模式
     * @param numberLen
     * @param point
     */
    public PopupInput decimalType(final int numberLen, int point){
        int max = numberLen + 1 + point;

        ed_input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        ed_input.setKeyListener(DigitsKeyListener.getInstance(false, true));
        ed_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max){
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (!source.equals(".")){
                    int indexOfPoint = dest.toString().indexOf(".");
                    if (indexOfPoint != -1){
                        if (dend - indexOfPoint > 2){
                            return "";
                        }
                    }else {
                        if (dest.length() == numberLen){
                            return "";
                        }
                    }
                }
                return super.filter(source, start, end, dest, dstart, dend);
            }
        }});
        return this;
    }

    /**
     * 文字输入模式
     * @param max
     */
    public PopupInput textType(int max){
        ed_input.setInputType(InputType.TYPE_CLASS_TEXT);
        ed_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max){
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                String regEx = "[a-zA-Z0-9\u4E00-\u9FA5]+";
                boolean matches = Pattern.matches(regEx, source);
                if (matches){
                    return super.filter(source, start, end, dest, dstart, dend);
                }
                return "";
            }
        }});
        return this;
    }

    public void showPopupWindow(boolean autoOpen, OnPopupInputListener inputListener){
        this.inputListener = inputListener;

        boolean adjust = true;
        int alignMode = BasePopupWindow.FLAG_KEYBOARD_ALIGN_TO_ROOT
                /*| BasePopupWindow.FLAG_KEYBOARD_IGNORE_OVER
                | BasePopupWindow.FLAG_KEYBOARD_ANIMATE_ALIGN*/
                | BasePopupWindow.FLAG_KEYBOARD_FORCE_ADJUST;

        /*setAdjustInputMethod(adjust);
        setAutoShowInputMethod(ed_input, autoOpen);
        setAdjustInputMode(getContentView(), alignMode);*/

        setAdjustInputMethod(true)
            .setAutoShowInputMethod(ed_input, autoOpen)
            .setAdjustInputMode(R.id.ed_input, alignMode)
            .showPopupWindow();
    }

    /*@Override
    protected Animation onCreateShowAnimation() {
        return AnimationHelper.asAnimation()
                .withTranslation(TranslationConfig.FROM_BOTTOM)
                .toShow();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return AnimationHelper.asAnimation()
                .withTranslation(TranslationConfig.TO_BOTTOM)
                .toDismiss();
    }*/

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_confirm){
            if (inputListener != null){
                inputListener.onInputText(ed_input.getText().toString().trim());
            }
            dismiss();
        }
    }

    @Override
    public View onCreateContentView() {
        View rootView = createPopupById(R.layout.popup_input);

        tv_confirm = rootView.findViewById(R.id.tv_confirm);
        ed_input = rootView.findViewById(R.id.ed_input);
        tv_confirm.setOnClickListener(this);
        return rootView;
    }

    public interface OnPopupInputListener{

        void onInputText(String text);
    }

}
