package geo.com.example.bo.comp6442_assignment_2_2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private float pressBF;
    private String Operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);

        int BtList[] = {R.id.button0,R.id.button1,R.id.button2,R.id.button3,
                R.id.button4,R.id.button5,R.id.button6,
                R.id.button7,R.id.button8,R.id.button9,
                R.id.button_Cancel,R.id.button_div,R.id.button_dot,R.id.button_Minus,
                R.id.button_multi,R.id.button_plus,
                R.id.button_leftBr,R.id.button_RightBr,};

        for(int Btid : BtList){
            View view = (View)findViewById(Btid);

        }
    }

    //press the button and create the value in the EditText Unfinished
    //https://www.youtube.com/watch?v=_sodmekXeDY
    public void Btn_Create(View sender){
        if(textView.getText().length()>8)return;
        Button bt = (Button)sender;
        textView.setText(bt.getText());
    }
}
