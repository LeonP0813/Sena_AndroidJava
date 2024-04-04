package com.example.senamobilapp;

/*import android.content.Context;*/
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultVw, historyVw;
    MaterialButton buttonAC, buttonDot;
    MaterialButton button0, button9, button8, button7, button6, button5, button4, button3, button2, button1;
    MaterialButton buttonMult, buttonSum, buttonDiv, buttonRes, buttonIgual;
    MaterialButton buttonC, buttonOB, buttonCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        resultVw = findViewById(R.id.result_viewer);
        historyVw = findViewById(R.id.historial_viewer);

        assignId(buttonC, R.id.button_c);
        assignId(buttonAC, R.id.button_ac);
        assignId(buttonOB, R.id.button_open_bracket);
        assignId(buttonCB, R.id.button_close_bracket);
        assignId(buttonDot, R.id.button_decimal);
        assignId(buttonDiv, R.id.button_divide);
        assignId(buttonMult, R.id.button_multiplicar);
        assignId(buttonSum, R.id.button_sumar);
        assignId(buttonRes, R.id.button_resta);
        assignId(buttonIgual, R.id.button_igual);
        assignId(button0, R.id.button_0);
        assignId(button9, R.id.button_9);
        assignId(button8, R.id.button_8);
        assignId(button7, R.id.button_7);
        assignId(button6, R.id.button_6);
        assignId(button5, R.id.button_5);
        assignId(button4, R.id.button_4);
        assignId(button3, R.id.button_3);
        assignId(button2, R.id.button_2);
        assignId(button1, R.id.button_1);


    }

    void assignId(MaterialButton btn, int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view ) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String ToCalculate = resultVw.getText().toString();

        if(buttonText.equals("AC")){
            resultVw.setText("");
            historyVw.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            resultVw.setText(historyVw.getText());
            return;
        }
        if(buttonText.equals("C")){
            ToCalculate = ToCalculate.substring(0, ToCalculate.length()-1);
            historyVw.setText("0");
            return;
        } else {
            ToCalculate = ToCalculate+buttonText;
        }

        resultVw.setText(ToCalculate);

        String finalResult = getResult(ToCalculate);

        if (!finalResult.equals("Err")){
            historyVw.setText(finalResult
            );
        }
    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            return finalResult;
        } catch (Exception e){
            return  "Err";
        }
    }

}