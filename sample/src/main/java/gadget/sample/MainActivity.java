package gadget.sample;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import gadget.sample.databinding.MainActivityBinding;

public class MainActivity extends FragmentActivity {

    private MainActivityBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
    }
}