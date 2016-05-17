package geo.com.example.bo.comp6442_assignment_2_2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 * @author Bo and Xiaochen
 *
 */
public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private float pressBF;
    private String Operation;
    String total = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.textView);
        textView.setText("0");

        int BtList[] = {R.id.button0,R.id.button1,R.id.button2,R.id.button3,
                R.id.button4,R.id.button5,R.id.button6,
                R.id.button7,R.id.button8,R.id.button9,
                R.id.button_Cancel,R.id.button_div,R.id.button_dot,R.id.button_Minus,
                R.id.button_multi,R.id.button_plus,
                R.id.button_leftBr,R.id.button_RightBr,};

//        for(int Btid : BtList){
//            View view = (View)findViewById(Btid);
//        }
    }

    //press the button and create the value in the EditText Unfinished
    public void Btn_Create(View sender){
        Button bt = (Button)sender;
        String str = bt.getText().toString();
        total += str;
        editText.setText(total);
        if(editText.getText().length()>8)return;
        //textView.setText(bt.getText());
    }

    //press "C"
    public void cleanData(View view){
        //editText.getText().clear();
        editText.setText("");
        total = "";
    }

    //press "<=",deletLast input
    public void Btn_Back(View view){
        String text = editText.getText().toString();
        editText.setText(total.substring(0, text.length() - 1));

    }

    //press "="
    public void getResult(View view) throws ParserException {
        ParserTreeNew pt = new ParserTreeNew();
        textView.setText(("The result is: "+pt.evaluate(editText.getText().toString())));
        total = "";
    }
}
