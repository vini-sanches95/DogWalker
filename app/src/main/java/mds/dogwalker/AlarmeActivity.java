package mds.dogwalker;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class AlarmeActivity extends AppCompatActivity {

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarme);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;

        // Inicializa o alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Inicializa o TimePicker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        // Inicializa o texto ligado/desligado
        update_text = (TextView) findViewById(R.id.update_text);

    }

}
