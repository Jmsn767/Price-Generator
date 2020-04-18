package com.example.pricegeneratorjava;

import android.annotation.SuppressLint;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    Spinner spinner4;
    Spinner spinner5;
    Spinner spinner6;
    EditText et1;
    EditText et2;
    Button btn;
    TextView tv1;
    TextView tv2;
    String numstring1;
    String numstring2;

    double Heightinput = 0.0d;
    double Lengthinput = 0.0d;
    String TOWinput ;
    String Backinput;
    String Doorsinput;
    String Drawsinput;
    String Complexityinput;
    String Finishinput;


    double TOWvalue;
    double Backvalue;
    double Doorsvalue;
    double Drawsvalue;
    double Complexityvalue;
    double Finishvalue;
    double Heightvalue;
    double Lengthvalue;


    double unitprice;
    double totalprice;
    int Morethan5draws = 0;
    Vibrator vibe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = findViewById(R.id.Spinner1);
        spinner2 = findViewById(R.id.Spinner2);
        spinner3 = findViewById(R.id.Spinner3);
        spinner4 = findViewById(R.id.Spinner4);
        spinner5 = findViewById(R.id.Spinner5);
        spinner6 = findViewById(R.id.Spinner6);
        btn = findViewById(R.id.button);

        arraymodifier(R.array.TOW, spinner1);
        arraymodifier(R.array.Back, spinner2);
        arraymodifier(R.array.Doors, spinner3);
        arraymodifier(R.array.Draws, spinner4);
        arraymodifier(R.array.Complexity, spinner5);
        arraymodifier(R.array.Finish, spinner6);

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);
        spinner5.setOnItemSelectedListener(this);
        spinner6.setOnItemSelectedListener(this);








        vibe = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);

        btn.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override

            public void onClick(View view) {



                et1 = findViewById(R.id.etHeight);
                et2 = findViewById(R.id.etLength);
                tv1 = findViewById(R.id.text9);
                tv2 = findViewById(R.id.text10);

                numstring1 = et1.getText().toString();
                numstring2 = et2.getText().toString();


                Heightinput = Double.parseDouble(numstring1) ;
                Lengthinput = Double.parseDouble(numstring2) ;

                TOWvalue = TOWFinishIF(TOWinput).doubleValue();
                Backvalue = BackIF(Backinput, Heightinput).doubleValue();
                Doorsvalue = DoorsIF(Doorsinput, Heightinput).doubleValue();
                Drawsvalue = DrawsIF(Drawsinput, Doorsinput).doubleValue();
                Complexityvalue = ComplexityIF(Complexityinput).doubleValue();
                Finishvalue = FinishIF(Finishinput).doubleValue();
                Heightvalue = HeightIF(Heightinput);


                unitprice = (((((60d *
                        Heightvalue) * Doorsvalue)
                        + (30d * (Backvalue)))
                        * Complexityvalue)+ Drawsvalue) * (Finishvalue)
                        * (TOWvalue) * 1.35;

                totalprice = unitprice * Lengthinput;

                tv1.setText("Unit Price: $" + String.format( "%.2f", unitprice));
                tv2.setText("Total Cost: $" + String.format("%.2f", totalprice));
                vibe.vibrate(10);



            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                TOWinput = spinner1.getSelectedItem().toString();


                Backinput = spinner2.getSelectedItem().toString();

                Doorsinput = spinner3.getSelectedItem().toString();

                Drawsinput = spinner4.getSelectedItem().toString();

                Complexityinput = spinner5.getSelectedItem().toString();

                Finishinput = spinner6.getSelectedItem().toString();
 return;

        }





    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void arraymodifier(int stringarrayname, Spinner spinner) {
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, stringarrayname, R.layout.spinner_layout);
        adapter1.setDropDownViewResource(R.layout.spinner_select);
        spinner.setAdapter(adapter1);
    }


  public Double FinishIF( String Finishinput ) {
     if (Finishinput.equals("1")) {
          Finishvalue = 0.82d;
      } else if (Finishinput.equals("3")) {
          Finishvalue = 1.08d;
      } else Finishvalue = 1d;

      return Finishvalue;
  }
  public Double TOWFinishIF(String TOWinput) {
      if (TOWinput.equals("Local Wood")) {
          TOWvalue = 1.07d;
      } else TOWvalue = 1d;

      return  TOWvalue;
  }

  public Double BackIF(String Backinput, Double Heightinput) {
    if (Backinput.equals("Includes Back Panel") && Heightinput <= 48) {
          Backvalue = 1d;
      } else if (Backinput.equals("Includes Back Panel") && Heightinput >= 36) {
          Backvalue = (1.0 / 6.0);
      }
      if (Backinput.equals("Includes Back Panel") && Heightinput < 36) {
          Backvalue = (1.0 / 5.0);

      } else Backvalue = 0.0;

      return Backvalue;
  }

  public Double DoorsIF(String Doorsinput, Double Heightinput) {
     if (Doorsinput.equals("Flat Panel Doors") && Heightinput >= 48) {
          Doorsvalue = 4.0 / 3.0;
      } else if (Doorsinput.equals("Flat Panel Doors") && Heightinput >= 36) {
          Doorsvalue = 1.5625;
      } else if (Doorsinput.equals("Flat Panel Doors") && Heightinput < 36) {
          Doorsvalue = 7.0 / 6.0;
      } else if (Doorsinput.equals("Raised Panel Doors") && Heightinput >= 48) {
          Doorsvalue = 18.0 / 10.5;
      } else if (Doorsinput.equals("Raised Panel Doors") && Heightinput >= 36) {
          Doorsvalue = 1.6875;
      } else if (Doorsinput.equals("Raised Panel Doors") && Heightinput < 36) {
          Doorsvalue = 1.75;
      } else Doorsvalue = 1.0;

      return Doorsvalue;
  }
  public Double DrawsIF(String Drawsinput, String Doorsinput) {

      if (Drawsinput.equals("No draws")) {
          Drawsvalue = 0d;
      } else if (Drawsinput.equals("5 or less draws") && (Doorsinput.equals("Flat Panel Doors") || Doorsinput.equals("Raised Panel Doors")) ) {
          Drawsvalue = 5d;

      } else if (Double.parseDouble(Drawsinput) >= 6 && (Doorsinput.equals("Flat Panel Doors") || Doorsinput.equals("Raised Panel Doors"))) {
          Drawsvalue = 5 + (Double.parseDouble(Drawsinput) -5) * 2d;
      } else if (Drawsinput.equals("5 or less draws") && Doorsinput.equals("No Doors")) {
          Drawsvalue = 60d;
      } else if (Double.parseDouble(Drawsinput)>= 6 && Doorsinput.equals("No Doors")) {
          Drawsvalue = 60d + (Double.parseDouble(Drawsinput) - 5) * 2d;
      } else Drawsvalue = 0d;
      return Drawsvalue;

  }
public Double ComplexityIF(String Complexityinput) {
    if (Complexityinput.equals("2")) {
        Complexityvalue = 1.04;
    } else if (Complexityinput.equals("3")) {
        Complexityvalue = 1.08;
    } else Complexityvalue = 1d;

    return Complexityvalue;

}
public Double HeightIF(Double Heightinput){
        if (Heightinput >= 48) {
            Heightvalue = 7.0 / 4d;
        } else if (Heightinput >= 36) {
            Heightvalue = 4d / 3d;

        } else Heightvalue = 1d;
        return Heightvalue;



    }
}
