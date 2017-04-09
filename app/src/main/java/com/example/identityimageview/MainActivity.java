package com.example.identityimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.identityimageview.widegt.CircleImageView;
import com.example.identityimageview.widegt.IdentityImageView;

public class MainActivity extends AppCompatActivity {

    private IdentityImageView identityImageView;
    private Button bt1;
    private Button bt2;
    private Button bt4;
    private Button bt3;
    private Button bt5;
    private int i = 20;
    private int angle=30;


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
                identityImageView.setAngle(angle+=10);
                break;
            case R.id.bt4:
                //增加进度条，没按一次加10,以及改变的角度
                identityImageView.setIsprogress(true);
                if (i == 30) {
                    identityImageView.setAngle(315);
                }
                identityImageView.setProgressColor(R.color.colorAccent);

                identityImageView.setProgress(i += 10);

                break;
            case R.id.bt5:
                identityImageView.getSmallCircleImageView().setVisibility(View.GONE);
                identityImageView.getTextView().setBackgroundColor((getResources().getColor(R.color.colorTest)));
                identityImageView.getTextView().setText("我是\n文字");
                break;
            default:
                break;


        }
    }

}
