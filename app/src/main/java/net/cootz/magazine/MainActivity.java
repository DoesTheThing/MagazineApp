package net.cootz.magazine;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnCalc(View view)
    {
        boolean isError = false;
        RadioButton dialog = findViewById(R.id.dialog_radio_button);
        RadioButton toast = findViewById(R.id.toast_radio_button);

        CheckBox[] boxes = new CheckBox[4];
        EditText[] nums = new EditText[4];
        EditText[] prices = new EditText[4];

        //Apple
        boxes[0] = findViewById(R.id.apple_check_box);
        nums[0] = findViewById(R.id.amount_of_apple_edit_text);
        prices[0] = findViewById(R.id.apple_price_edit_text);

        //Strawberry
        boxes[1] = findViewById(R.id.strawberry_check_box);
        nums[1] = findViewById(R.id.amount_of_strawberry_edit_text);
        prices[1] = findViewById(R.id.strawberry_price_edit_text);

        //Blueberry
        boxes[2] = findViewById(R.id.blueberry_check_box);
        nums[2] = findViewById(R.id.amount_of_blueberry_edit_text);
        prices[2] = findViewById(R.id.blueberry_price_edit_text);

        //Potato
        boxes[3] = findViewById(R.id.potato_check_box);
        nums[3] = findViewById(R.id.amount_of_potato_edit_text);
        prices[3] = findViewById(R.id.potato_price_edit_text);

        float sum = 0;
        String return_message = "";
        for (int i = 0; i < 4; i++)
        {
            if (boxes[i].isChecked() && !isError)
            {
                try {
                    int amount = Integer.parseInt(nums[i].getText().toString());
                    float price = Float.parseFloat(prices[i].getText().toString());
                    float cost = amount*price;
                    sum += cost;
                    return_message += String.format("%s: %d * %.2f = %.1f\n", boxes[i].getText().toString(), amount, price, cost);
                }
                catch (Exception e) {
                    isError = true;

                    AlertDialog error = new AlertDialog.Builder(this).create();
                    error.setTitle("Error");
                    error.setMessage(e.getMessage());
                    error.show();
                }
            }
        }

        return_message += String.format("Sum: %.1f", sum);

        if(!isError)
        {
            if (dialog.isChecked())
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Result cost");
                builder.setMessage(return_message);
                builder.show();
            }
            else if (toast.isChecked())
            {
                int duration = Toast.LENGTH_LONG;
                Toast result = Toast.makeText(this, return_message, duration);
                result.show();
            }
        }

        isError = false;
    }
}