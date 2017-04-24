package com.example.identityimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.exampleenen.ruedy.imagelib.widget.IdentityImageView;

public class MainActivity extends AppCompatActivity {

    private IdentityImageView identityImageView;
    private Button bt1;
    private Button bt2;
    private Button bt4;
    private Button bt3;
    private Button bt5;
    private int i = 10;
    private int angle = 30;
    private boolean flag;//文字是否出现


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        identityImageView = ((IdentityImageView) findViewById(R.id.iiv));
        bt1 = ((Button) findViewById(R.id.bt1));
        bt2 = ((Button) findViewById(R.id.bt2));
        bt3 = ((Button) findViewById(R.id.bt3));
        bt4 = ((Button) findViewById(R.id.bt4));
        bt5 = ((Button) findViewById(R.id.bt5));
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.bt1://填充头像
                identityImageView.getBigCircleImageView().setImageResource(R.mipmap.guojia);

                break;
            case R.id.bt2:
                //改变图片比例大小，
                identityImageView.setRadiusScale(0.1f);
                break;
            case R.id.bt3:
                //增加边框
                identityImageView.setBorderWidth(100);
                identityImageView.setBorderColor(R.color.colorTest);
                identityImageView.setAngle(angle += 7);
                if (angle >= 58) {
                    angle = 18;
                }
                break;
            case R.id.bt4:
                //增加进度条，没按一次加10,以及改变的角度
                identityImageView.setIsprogress(true);
                identityImageView.setProgressColor(R.color.colorAccent);

                identityImageView.setProgress(i += 10);

                break;
            case R.id.bt5:
                identityImageView.getSmallCircleImageView().setImageResource(R.mipmap.v);
                if (flag) {
                    identityImageView.getTextView().setVisibility(View.VISIBLE);
                    identityImageView.getSmallCircleImageView().setVisibility(View.GONE);
                    flag=false;
                } else {
                    identityImageView.getTextView().setVisibility(View.GONE);
                    identityImageView.getSmallCircleImageView().setVisibility(View.VISIBLE);
                flag=true;
                }
                identityImageView.getTextView().setBackgroundColor((getResources().getColor(R.color.colorTest)));
                identityImageView.getTextView().setText("V");
                break;
            default:
                break;


        }
    }

}
