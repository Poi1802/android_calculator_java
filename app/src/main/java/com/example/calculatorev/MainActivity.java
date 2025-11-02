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
import java.util.List;

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
        binding.calcTv.setText(buttonText);
    }
}