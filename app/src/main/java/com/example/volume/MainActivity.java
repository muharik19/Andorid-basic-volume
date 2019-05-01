package com.example.volume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String STATE_RESULT = "state_result";
    Button btnCalculate;
    EditText edLength, edHeigth, edWidth;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculate = findViewById(R.id.btn_calculate);
        edHeigth = findViewById(R.id.ed_heigth);
        edLength = findViewById(R.id.ed_length);
        edWidth = findViewById(R.id.ed_width);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {
            String inputLength = edLength.getText().toString().trim();
            String inputWidth = edWidth.getText().toString().trim();
            String inputHeigth = edHeigth.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputHeigth)) {
                isEmptyFields =  true;
                edHeigth.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields =  true;
                edWidth.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields =  true;
                edLength.setError("Field ini tidak boleh kosong");
            }

            Double length = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double heigth = toDouble(inputHeigth);

            if (length == null) {
                isInvalidDouble = true;
                edLength.setError("Field ini harus berupa nomor yang valid");
            }

            if (width == null) {
                isInvalidDouble = true;
                edWidth.setError("Field ini harus berupa nomor yang valid");
            }

            if (heigth == null) {
                isInvalidDouble = true;
                edHeigth.setError("Field ini harus berupa nomor yang valid");
            }

            if ((!isEmptyFields) && (!isInvalidDouble)) {
                double volume = length * width * heigth;
                tvResult.setText(String.valueOf(volume));
            }
        }
    }

    Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
