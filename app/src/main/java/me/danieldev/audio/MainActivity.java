package me.danieldev.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button playSong;
    private TextInputEditText txtX;
    private TextInputEditText txtY;
    private TextInputEditText txtZ;
    private TextView lblHeader;
    private ImageView imgArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playSong = findViewById(R.id.btnPlay);
        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        txtZ = findViewById(R.id.txtZ);
        lblHeader = findViewById(R.id.lblOrientation);
        imgArrow = findViewById(R.id.arrowView);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.beep1);

        playSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Integer xValue, yValue, zValue;
                String xValueS, yValueS, zValueS;
                xValueS  = txtX.getText().toString();
                yValueS  = txtY.getText().toString();
                zValueS  = txtZ.getText().toString();
                if(xValueS.length() == 0) xValue = 0;
                else xValue = Integer.parseInt(xValueS);
                if(yValueS.length() == 0) yValue = 0;
                else yValue = Integer.parseInt(yValueS);
                if(zValueS.length() == 0) zValue = 0;
                else zValue = Integer.parseInt(zValueS);
                double angle = Math.atan2(yValue, xValue) * 180 / Math.PI;
                // lblHeader.setText(Double.toString(angle)); yes, just debugging stuff
                double rotation = 0;
                if( Math.abs(angle - 90) < 1e-5 ) rotation = 0;
                else if( angle > 90) rotation = 270 + angle - 90;
                else rotation = angle;
                imgArrow.setRotation((float)rotation) ;

            }
        });

    }
}
