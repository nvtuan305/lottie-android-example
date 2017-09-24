package com.blueeagle.lottieexample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView mLottieAnimationView;
    Button mBtnChangeAnim;
    String[] mAnimFiles = new String[]{"empty_status.json", "letter_b_monster.json", "permission.json", "ice_cream_animation.json"};
    int mCurrentAnim = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLottieAnimationView = findViewById(R.id.lottie_animation_view);
        mBtnChangeAnim = findViewById(R.id.btn_change_animation);

        mBtnChangeAnim.setOnClickListener(button -> {
            mBtnChangeAnim.setEnabled(false);
            changeAnim();
        });
    }

    private void changeAnim() {
        new Handler().post(() -> {
            String animFileName = mAnimFiles[(++mCurrentAnim % mAnimFiles.length)];
            LottieComposition.Factory.fromAssetFileName(this, animFileName, composition -> {
                mLottieAnimationView.setComposition(composition);
                mLottieAnimationView.playAnimation();
                mBtnChangeAnim.setEnabled(true);
            });
        });
    }

    @Override
    protected void onDestroy() {
        mLottieAnimationView.cancelAnimation();
        mLottieAnimationView = null;
        super.onDestroy();
    }
}
