package cn.yan96in.sample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import cn.yan96in.ildesign.DatePickerPopWin;
import cn.yan96in.sample.R;

public class WheelViewActivity extends AppCompatActivity {

//    private ArrayList<ProvinceModel> mProvinceList = null; // 省份列表
//    private String mProvince = null; // 省份
//    private String mCity = null; // 城市

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_view);

        findViewById(R.id.date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  DatePickerPopWin pickerPopWin = new DatePickerPopWin(WheelViewActivity.this, new DatePickerPopWin.OnDatePickedListener() {
                      @Override
                      public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                          Toast.makeText(WheelViewActivity.this,dateDesc,Toast.LENGTH_SHORT).show();
                      }
                  });
                pickerPopWin.showPopWin(WheelViewActivity.this);
            }
        });

        findViewById(R.id.province).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(WheelViewActivity.this,"Working on...",Toast.LENGTH_SHORT).show();
//                if(null != mProvinceList) {
//                    ProvincePickPopWin pickPopWin = new ProvincePickPopWin(WheelViewActivity.this,
//                            mProvince, mCity, mProvinceList, WheelViewActivity.this);
//                    pickPopWin.showPopWin(WheelViewActivity.this);
//                }
            }
        });
//        ((new ProvinceInfoParserTask(this, mHandler))).execute();// 解析本地地址信息文件
    }


//    private Handler mHandler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            switch (msg.what) {
//                case ProvinceInfoParserTask.MSG_PARSE_RESULT_CALLBACK: // 解析地址完成
//                    mProvinceList = (ArrayList<ProvinceModel>) msg.obj;
//                    break;
//            }
//            return false;
//        }
//    });

//    @Override
//    public void onAddressPickCompleted(String province, String provinceId, String city, String cityId) {
////        Toast.makeText(this,province+"-"+provinceId+"-"+city+"-"+cityId,Toast.LENGTH_SHORT).show();
//        Toast.makeText(this,ProvinceInfoUtils.matchAddress(this,provinceId,cityId,mProvinceList),Toast.LENGTH_SHORT).show();
//        ProvinceInfoUtils.matchAddress(this,provinceId,cityId,mProvinceList);
//    }
}
