package book.prg.com.test;

import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    FragNav frag;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showNotificationAndToolbar();

    }
















    private void showNotificationAndToolbar() {



        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("قدرتمندترین ماشین پولسازی دنیا");
        setSupportActionBar(toolbar);

        frag = (FragNav) getSupportFragmentManager().findFragmentById(R.id.Nov_frag);
        frag.install((DrawerLayout) findViewById(R.id.draw_layout), toolbar);

    }
}
