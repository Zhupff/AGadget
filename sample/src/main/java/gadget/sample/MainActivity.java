package gadget.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import gadget.log.GLog;
import gadget.sample.databinding.MainActivityBinding;

/**
 * @Author: Zhupf
 * @E-mail: zhupff@qq.com
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        binding.tvWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GLog.I("欢迎来到夜之城！");
            }
        });
    }
}
