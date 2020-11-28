package gadget.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import gadget.common.view.listener.GMultiClickListener;

/**
 * @Author: Zhupf
 * @E-mail: zhupff@qq.com
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findViewById(R.id.tvWelcome).setOnClickListener(new GMultiClickListener(3, 2000) {
            @Override
            public void onClick(View v, int count, boolean isLastClick) {
                super.onClick(v, count, isLastClick);
                Log.i("wtfu", String.format("count:%s isLastClick:%s", count, isLastClick));
            }
        });
    }
}
