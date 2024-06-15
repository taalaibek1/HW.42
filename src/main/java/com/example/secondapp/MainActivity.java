package com.example.secondapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;


import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private EditText editText2;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.password);
        editText2 = findViewById(R.id.email);
        textView = findViewById(R.id.forgot_password);
        textView2 = findViewById(R.id.enter);
        textView3 = findViewById(R.id.text);
        textView4 = findViewById(R.id.welcome);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) button.getLayoutParams();
            ConstraintLayout.LayoutParams editTextLayoutParams = (ConstraintLayout.LayoutParams) editText.getLayoutParams();
            ConstraintLayout.LayoutParams editText2LayoutParams = (ConstraintLayout.LayoutParams) editText2.getLayoutParams();
            if (insets.isVisible(WindowInsetsCompat.Type.ime())) {
                layoutParams.verticalBias = 0.53f;
                editTextLayoutParams.verticalBias = 0.33f;
                editText2LayoutParams.verticalBias = 0.43f;
                textView.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
            } else {
                editTextLayoutParams.verticalBias = 0.63f;
                editText2LayoutParams.verticalBias = 0.73f;
                layoutParams.verticalBias = 0.83f;
                textView4.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
            }
            button.setLayoutParams(layoutParams);

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText.getText().toString().trim().isEmpty() || editText2.getText().toString().trim().isEmpty()) {
                    button.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.gray));
                } else {
                    button.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.orange));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        editText.addTextChangedListener(textWatcher);
        editText2.addTextChangedListener(textWatcher);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = editText.getText().toString();
                String email = editText2.getText().toString();
                if (password.equals("admin") && email.equals("admin")) {
                    Toast.makeText(MainActivity.this, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show();
                    editText.setVisibility(View.GONE);
                    editText2.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    textView2.setVisibility(View.GONE);
                    textView3.setVisibility(View.GONE);
                } else {
                    Toast.makeText(MainActivity.this, "Неверный пароль или логин", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
