package mds.dogwalker.activities;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import mds.dogwalker.utils.AlarmReceiver;
import mds.dogwalker.R;

public class AlarmeActivity extends AppCompatActivity {

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;

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

        // cria uma instancia de um calendario
        final Calendar calendario = Calendar.getInstance();

        //inicializa o botão de setar
        Button alarm_set = (Button) findViewById(R.id.alarm_set);

        // Cria o intent para a classe Alarm Receiver
        final Intent intent1 = new Intent(this.context, AlarmReceiver.class);


        // OnClickListener para o botão de setar alarme
        alarm_set.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                // Setando o calendario para o tempo selecionado
                calendario.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendario.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                // pega os valores das horas e minutos e converte para strings
                int hour = alarm_timepicker.getHour();
                int minutes = alarm_timepicker.getMinute();

                String s_hour = String.valueOf(hour);
                String s_minutes = String.valueOf(minutes);

                if (minutes < 10) {
                    s_minutes = "0" + String.valueOf(minutes);
                }
                if (hour < 10) {
                    s_hour = "0" + String.valueOf(hour);
                }
                set_alarm_text("Alarme Ligado: " + s_hour + ":" + s_minutes);

                // Coloca string extra no intent1 para dizer ao relógio que o alarme foi setado
                intent1.putExtra("extra", "alarm on");

                // Cria intent pendente que atrasa o intent até a hora marcada
                pending_intent = PendingIntent.getBroadcast(AlarmeActivity.this, 0,
                        intent1, PendingIntent.FLAG_UPDATE_CURRENT);

                // seta o alarm manager
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendario.getTimeInMillis(), pending_intent);

            }
        });

        // Botão de desligar
        Button alarm_off = (Button) findViewById(R.id.alarm_off);
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_alarm_text("Alarme Desligado");

                // cancela o alarme
                alarm_manager.cancel(pending_intent);

                // coloca string extra no intent1 e avisa o relógio que foi pressionado o botao de desligar alarme
                intent1.putExtra("extra", "alarm off");

                // Para o ringtone
                sendBroadcast(intent1);
            }
        });
    }

    private void set_alarm_text(String s) {
        update_text.setText(s);
    }

}