package com.example.niggamachine.myapplication2;

        import android.speech.tts.TextToSpeech;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener {
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToSpeech= new TextToSpeech(MainActivity.this,MainActivity.this);
        final Button Spkbutton = (Button) findViewById(R.id.button);
        final TextView textView = (TextView) findViewById(R.id.textView);

        Spkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!textToSpeech.isSpeaking()){
                    HashMap<String,String> stringStringHashMap = new HashMap<String, String>();
                    stringStringHashMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"Hello how are you");
                    textToSpeech.speak(textView.getText().toString(),TextToSpeech.QUEUE_ADD,stringStringHashMap);
                    Spkbutton.setVisibility(Button.GONE);
                }else{
                    textToSpeech.stop();
                }
            }
        });

    }

    @Override
    public void onInit(int i) {
        textToSpeech.setOnUtteranceCompletedListener(this);

    }

    @Override
    public void onUtteranceCompleted(String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"Utterence completed",Toast.LENGTH_LONG).show();
                Button button = (Button) findViewById(R.id.button);
                button.setVisibility(Button.VISIBLE);
            }
        });{

        }

    }

    protected void onDestroy(){
        if (textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
            textToSpeech=null;
        }
        super.onDestroy();
    }

}