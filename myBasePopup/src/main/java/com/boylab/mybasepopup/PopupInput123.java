package com.boylab.mybasepopup;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import razerdp.basepopup.BasePopupWindow;

public class PopupInput123 extends BasePopupWindow {

    EditText mEdInput;

    public PopupInput123(Context context) {
        super(context);
        //setContentView(R.layout.popup_input);
    }

    @Override
    public View onCreateContentView() {

        //mEdInput = contentView.findViewById(R.id.ed_input);
        /*mTvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.toast(mEdInput.getText().toString());
                dismiss();
            }
        });*/

        return createPopupById(R.layout.popup_input);
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

    public void showPopupWindow(boolean autoOpen){
        boolean adjust = true;
        int alignMode = BasePopupWindow.FLAG_KEYBOARD_ALIGN_TO_ROOT | BasePopupWindow.FLAG_KEYBOARD_FORCE_ADJUST;

        setAdjustInputMethod(adjust)
                .setAutoShowInputMethod(mEdInput, autoOpen)
                .setAdjustInputMode(R.id.ed_input, alignMode)
                .showPopupWindow();
    }
}
