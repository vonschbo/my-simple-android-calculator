package geo.com.example.bo.comp6442_assignment_2_2016;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**This is the main function and user interface
 *
 * @author Bo
 *
 */
public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    String total = "";
    FileInputStream inputStream;
    FileOutputStream outputStream;
    File persistentFile;
    static final String filename = "MyCalculator";
    TextView textSaved;
    String syntaxHelper = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.textView);
        textView.setText("0");
        textSaved = (TextView)findViewById(R.id.saved_textView);


        /* Read data from the persistent file */
        persistentFile = new File(getFilesDir(),filename);
        if (persistentFile.exists()){
            try{
                inputStream = openFileInput(filename);
                BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder buffer = new StringBuilder();
                while ((line = input.readLine()) != null){
                    buffer.append(line);
                }
                textSaved.setText(buffer.toString());
            }catch(Exception e){
                e.printStackTrace();
            }
        }


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

    //press "C", clear all the input history
    public void cleanData(View view){
        //editText.getText().clear();
        editText.setText("");
        total = "";
    }

    //press "back",move back to modify the input
    public void Btn_Back(View view){
        String text = editText.getText().toString();
        if(total=="")
            editText.setText(total);
        else {
            total = total.substring(0, text.length() - 1);
            editText.setText(total);
        }
    }

    //press "=", show the expression result
    public void getResult(View view) throws ParserException {
        ParserTree pt = new ParserTree();
        textView.setText((""+pt.evaluate(editText.getText().toString())));

        //save the text persistent
        String textBuffer = textSaved.getText().toString();
        String appendText = editText.getText().toString();
        textBuffer += ":";
        textBuffer += appendText;
        String exp = textBuffer+ " = "+ pt.evaluate(editText.getText().toString()) +"\n";
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(exp.getBytes());
            outputStream.close();
            textSaved.setText(exp);
        }catch(Exception e){
            e.printStackTrace();
        }

        total = "";
    }

//clear the input history
    public void clearSaved(View view){
        textSaved.setText("");
        persistentFile.delete();
    }


    //save the text expression
    public void saveText(View view){
        String textBuffer = textSaved.getText().toString();
        String appendText = editText.getText().toString();
        textBuffer += ":";
        textBuffer += appendText;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(textBuffer.getBytes());
            outputStream.close();
            textSaved.setText(textBuffer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //check syntax error
    public void check(View view){
        syntaxHelper = "";
        String t = editText.getText().toString();
        if(t.contains("(*"))
            syntaxHelper += "* can not begin with (";
        if(t.contains("(/"))
            syntaxHelper += "/ can not begin with (";
        if(t.contains("(+"))
            syntaxHelper += "+ can not begin with ( ";
        if(t.contains("/0"))
            syntaxHelper += "0 can not be divided";
        if(count("(",t)!=count(")",t))
            syntaxHelper += "brackets missing";

        Toast.makeText(this, syntaxHelper, Toast.LENGTH_LONG).show();

    }


    //count the character occurrences
    public int count(String y,String x){
        int counter = 0;
        for( int i=0; i<x.length(); i++ ) {
            if(x.charAt(i) == y.charAt(0)) {
                counter++;
            }
        }
        return counter;
    }


}
