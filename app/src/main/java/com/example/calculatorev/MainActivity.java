package com.example.calculatorev;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculatorev.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    protected TextView historyTv, calcTv, resultTv;
    protected Button buttonAc, buttonLeftBracket, buttonRightBracket, butonDivide;
    protected Button button7, button8, button9, butonX;
    protected Button button4, button5, button6, butonMinus;
    protected Button button1, button2, button3, butonPlus;
    protected Button buttonC, button0, buttonDot, butonSum;
    private ActivityMainBinding binding;

    /**
     * TODO GO TO GITHUB!!!!!!
     */
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.historyTv.setText("hello");
            }
        });
    }
}