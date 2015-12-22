package cn.yan96in.sample.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;

import cn.yan96in.ildesign.widget.IEditText;
import cn.yan96in.sample.R;


public class IEditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_edit_text);
        IEditText customEdit = (IEditText) findViewById(R.id.textView1);
        customEdit.addTextChangedListener(editTextChanged());
    }

    private IEditText.TextChangedListener editTextChanged() {
        return new IEditText.TextChangedListener() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}
