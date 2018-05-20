package book.prg.com.test;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity{


	FragNav frag;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );


		Toolbar toolbar = (Toolbar) findViewById( R.id.app_bar );
		toolbar.setTitle("قدرتمندترین ماشین پولسازی دنیا");
		setSupportActionBar( toolbar );


		frag = (FragNav)getSupportFragmentManager().findFragmentById( R.id.Nov_frag );
		frag.install((DrawerLayout)findViewById( R.id.draw_layout ), toolbar );
	}
}
