package top.yunp.e008donttouchwhiterect;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainView = new MainView(this);
        setContentView(mainView);
    }
}
