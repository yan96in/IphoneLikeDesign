package cn.yan96in.sample.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import cn.yan96in.ildesign.listener.OnDismissListener;
import cn.yan96in.ildesign.listener.OnItemClickListener;
import cn.yan96in.ildesign.widget.AlertView;
import cn.yan96in.sample.R;


public class AlertViewActivity extends Activity implements OnItemClickListener, OnDismissListener {

    private AlertView mAlertView;
    private AlertView mAlertViewExt;
    private EditText etName;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout .activity_alert_view);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mAlertView = new AlertView("title", "content", "cancel", new String[]{"sure"}, null,
                this, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this);
        mAlertViewExt = new AlertView("hint", "plz complete your info", "cancel", null,
                new String[]{"complete"}, this, AlertView.Style.Alert, this);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(this)
                .inflate(R.layout.alertext_form, null);
        etName = (EditText) extView.findViewById(R.id.etName);
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                boolean isOpen = imm.isActive();
                mAlertViewExt.setMarginBottom(isOpen && focus ? 120 : 0);
                System.out.println(isOpen);
            }
        });
        mAlertViewExt.addExtView(extView);
    }

    public void alertShow1(View view) {
        mAlertView.show();
    }

    public void alertShow2(View view) {
        new AlertView("title", "content", null, new String[]{"sure"}, null, this,
                AlertView.Style.Alert, this).show();
    }

    public void alertShow3(View view) {
        new AlertView(null, null, null,
                new String[]{"highlightbutton1", "highlightbutton2", "highlightbutton3"},
                new String[]{"otherbutton1", "otherbutton2", "otherbutton3",
                        "otherbutton4", "otherbutton5", "otherbutton6",
                        "otherbutton7", "otherbutton8", "otherbutton9",
                        "otherbutton10", "otherbutton11", "otherbutton12"},
                this, AlertView.Style.Alert, this).show();
    }

    public void alertShow4(View view) {
        new AlertView("title", null, "cancel", new String[]{"highlightbutton1"},
                new String[]{"otherbutton1", "otherbutton2", "otherbutton3"},
                this, AlertView.Style.ActionSheet, this).show();
    }

    public void alertShow5(View view) {
        new AlertView("title", "content", "cancel",
                null, null, this, AlertView.Style.ActionSheet, this)
                .setCancelable(true).show();
    }

    public void alertShow6(View view) {
        new AlertView("upload avatar", null, "cancel", null,
                new String[]{"shot", "select from album"},
                this, AlertView.Style.ActionSheet, this).show();
    }

    public void alertShowExt(View view) {
        mAlertViewExt.show();
    }

    private void closeKeyboard() {

        imm.hideSoftInputFromWindow(etName.getWindowToken(), 0);

        mAlertViewExt.setMarginBottom(0);
    }

    @Override
    public void onItemClick(Object o, int position) {
        closeKeyboard();

        if (o == mAlertViewExt && position != AlertView.CANCELPOSITION) {
            String name = etName.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(this, "none inputed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "hello," + name, Toast.LENGTH_SHORT).show();
            }
            return;
        }
        Toast.makeText(this, "clicked " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDismiss(Object o) {
        closeKeyboard();
        Toast.makeText(this, "dismissed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mAlertView != null && mAlertView.isShowing()) {
                mAlertView.dismiss();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
