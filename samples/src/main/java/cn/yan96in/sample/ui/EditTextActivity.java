package cn.yan96in.sample.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;

import cn.yan96in.ildesign.widget.TextLabel;
import cn.yan96in.sample.R;


public class EditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_label);
        TextLabel customEdit = (TextLabel) findViewById(R.id.textView1);
        customEdit.addTextChangedListener(editTextChanged());
    }

    private TextLabel.TextChangedListener editTextChanged() {
        return new TextLabel.TextChangedListener() {

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
