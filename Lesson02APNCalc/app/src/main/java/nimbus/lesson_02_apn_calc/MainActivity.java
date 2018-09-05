package nimbus.lesson_02_apn_calc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] numericButtons = {R.id.angkaNol, R.id.angkaSatu, R.id.angkaDua, R.id.angkaTiga, R.id.angkaEmpat, R.id.angkaLima, R.id.angkaEnam, R.id.angkaTujuh, R.id.angkaDelapan, R.id.angkaSembilan};
    int[] operatorButtons = {R.id.tambah, R.id.kurang, R.id.kali, R.id.bagi};
    TextView hasilHitung, num1, operator, num2;
    boolean lastNumeric;
    boolean lastDot = true;
    double  hsxl = 0.0;
    Button kali, bagi, tambah, kurang, hasil, backSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasilHitung = findViewById(R.id.hasilHitung);
        num1= findViewById(R.id.num1);
        operator = findViewById(R.id.operator);
        num2 = findViewById(R.id.num2);
        setNumeric();
        setOperator();
        onEqual();
        addDot();
        onDelete();
        copyright();
    }

    public void setNumeric() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set text from buttons
                    if (operator.getText() + "" != ""){
                        if (! (num2.getText()+"").equals("0")) {
                            Button button = (Button) v;
                            num2.append(button.getText());
                        }
                    }else {
                        if (! (num1.getText()+"").equals("0")) {
                            Button button = (Button) v;
                            num1.append(button.getText());
                        }
                    }
                lastNumeric = true;
            }
        };
        // Assign the listener to all the numeric buttons
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    /**
     * Find and set OnClickListener to operator buttons, equal button and decimal point button.
     */
    public void setOperator() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasilHitung.getText() + "" != "") {
                    if (num1.getText() + "" != ""){
//                        hasilHitung.setText("");
                    } else {
                        num1.setText(hasilHitung.getText() + "");
                    }
                } else {
//                    num1.setText(num1.getText() + "");
                }

                if (operator.getText() + "" == ""){
                    if (num1.getText() + "" != "") {
                        Button button = (Button) v;
                        operator.append(button.getText());
                        lastNumeric = false;
                        lastDot = true;
                    } else {
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_SHORT;
                        CharSequence text = "Nilai 1 kosong";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                } else {
                    lastNumeric = false;
                    lastDot = false;
                }
            }
        };

        // Assign the listener to all the operator buttons
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    public void addDot() {

        // Penambahan titik
        findViewById(R.id.titik).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && lastDot) {
                    if (operator.getText() + "" == ""){
                        num1.append(".");
                    } else {
                        num2.append(".");
                    }
                    lastNumeric = true;
                    lastDot = false;
                }
            }
        });
    }

    public void onDelete() {

        // Button Hapus
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1.setText("");
                operator.setText("");
                num2.setText("");
                hasilHitung.setText("");
                lastNumeric = false;
                lastDot = true;
            }
        });

        // Button Backspace
        findViewById(R.id.backSpace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator.getText() + "" != ""){
                    if (num2.getText() + "" == ""){
                        operator.setText("");
                    } else {
                        String numView2 = num2.getText() + "";
                        num2.setText(numView2.substring(0,numView2.length()-1));
                        if (numView2.substring(numView2.length()-1).equals(".")){
                            lastDot = true;
                        }
                    }
                } else {
                    if (num1.getText() + "" == ""){
                        num2.setText("");
                    } else {
                        String numView1 = num1.getText() + "";
                        num1.setText(numView1.substring(0,numView1.length()-1));
                        if (numView1.substring(numView1.length()-1).equals(".")){
                            lastDot = true;
                        }
                    }
                }
//                lastNumeric = false;
//                lastDot = true;
            }
        });
    }

    /**
     * Logic to calculate the solution.
     */
    public void onEqual() {
        findViewById(R.id.hasil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1x = num1.getText() + "";
                String num2x = num2.getText() + "";
                String ope = operator.getText() + "";
                if (num1x.equalsIgnoreCase("")){
//                        hasilHitung.setText("Nilai 1 Kosong");
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    CharSequence text = "Nilai 1 Kosong !";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    if (num2x.equalsIgnoreCase("")){
//                            hasilHitung.setText("Nilai 2 Kosong");
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_SHORT;
                        CharSequence text = "Nilai 2 Kosong !";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        double dobNum1 = Double.parseDouble(num1x);
                        double dobNum2 = Double.parseDouble(num2x);

                        if (ope.equalsIgnoreCase("x")) {
                            hsxl = dobNum1 * dobNum2;
                        } else if (ope.equalsIgnoreCase("/")) {
                            hsxl = dobNum1 / dobNum2;
                        } else if (ope.equalsIgnoreCase("+")) {
                            hsxl = dobNum1 + dobNum2;
                        } else if (ope.equalsIgnoreCase("-")) {
                            hsxl = dobNum1 - dobNum2;
                        }

                        hasilHitung.setText(String.valueOf(hsxl));
                        num1.setText("");
                        operator.setText("");
                        num2.setText("");
                        lastNumeric = false;
                        lastDot = true;
                    }
                }
            }
        });
    }

    public void copyright () {
        findViewById(R.id.clearEnd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                CharSequence text = "APN-2018";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}
