package com.example.calculatorev;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculatorev.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        List<MaterialButton> allButtons = findAllButtons(binding.getRoot());
        System.out.println(allButtons);

        assignButtons(allButtons);

        binding.buttonSum.setOnClickListener(this);

    }

    private List<MaterialButton> findAllButtons(ViewGroup view) {
        List<MaterialButton> buttons = new ArrayList<>();
        int childCount = view.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = view.getChildAt(i);

            if(child instanceof MaterialButton) {
                buttons.add((MaterialButton) child);
            } else if (child instanceof ViewGroup) {
                buttons.addAll(findAllButtons((ViewGroup) child));
            }
        }

        return buttons;
    }

    public void assignButtons(List<MaterialButton> buttons) {
        for (MaterialButton btn: buttons) {
            btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "=":
                String result = tokenCalcualtion(parseNumericExpression());
                binding.historyTv.setText(result);
                break;
            case "AC":
                binding.calcTv.setText("");
                break;
            case "c":
                String calcText = binding.calcTv.getText().toString();

                try {
                    binding.calcTv.setText(calcText.substring(0, calcText.length()-1));
                } catch (Exception e) {
                    binding.calcTv.setText("");
                }

                break;
            default:
                String tvString = binding.calcTv.getText().toString().equals("0") ? "" : binding.calcTv.getText().toString();
                String concatenateChars = tvString + buttonText;
                binding.calcTv.setText(concatenateChars);
        }
    }

    public List<String> parseNumericExpression() {
        List<String> tokens = new ArrayList<>();

        // Регулярное выражение для поиска:
        // \\d+(\\.\\d+)?   - чисел (целых или с плавающей точкой)
        // [+\\-*/^%]      - математических операторов
        // [()]            - скобок
        // \\s+            - пробелов (которые мы игнорируем)
        Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?|[+\\-*/^%()]|\\s+)");
        Matcher matcher = pattern.matcher(binding.calcTv.getText().toString());

        while (matcher.find()) {
            String token = matcher.group().trim();
            // Игнорируем пробелы
            if (!token.isEmpty()) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    public String tokenCalcualtion(List<String> tokens) {
        int result = 0;

        for (int i = 0; i < tokens.toArray().length; i++) {
            if (tokens.get(i).equals("+")) {
                result += Integer.parseInt(tokens.get(i-1)) + Integer.parseInt(tokens.get(i+1));
            }
        }

        return Integer.toString(result);
    }
}