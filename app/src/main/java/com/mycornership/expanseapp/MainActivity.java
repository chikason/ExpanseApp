package com.mycornership.expanseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mycornership.expanseapp.database.CurrencyRate;
import com.mycornership.expanseapp.models.CurrencyViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CurrencyViewModel viewModel;
    private TextView label, label2;
    private EditText symbol, symbol2;
    private Spinner spinner1, spinner2;
    private Button btn_convert;
    private List<CurrencyItems> myItems = new ArrayList<>();
    private List<String> currency_symbol = new ArrayList<>();
    private List<CurrencyRate> rateList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize widgets
        init();
        //calls the MVVM model for UI data provision
        viewModel = ViewModelProviders.of(this).get(CurrencyViewModel.class);
        viewModel.fetchRateData();
        viewModel.getSymbols().observe(this, new Observer<List<CurrencyRate>>() {
            @Override
            public void onChanged(List<CurrencyRate> currencyRates) {
                rateList = currencyRates;
                for(int i = 0; i < currencyRates.size(); i++){
                    currency_symbol.add(currencyRates.get(i).getSymbol());
                    String sbl = currencyRates.get(i).getSymbol();
                    myItems.add(new CurrencyItems(sbl,getSymbolIcon(sbl)));
                }
                MySpinnerAdapter adapter1 = new MySpinnerAdapter(getBaseContext(),myItems);
              //  ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, currency_symbol);
                spinner1.setAdapter(adapter1);
                spinner2.setAdapter(adapter1);
            }
        });

        //listen to the spinner selection action
        addSpinnerListener();

        //adds actionListener to the button convert
        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //reads text on the input fields
               //the conversion is based on the value in the input field

                if (symbol.getText().length() != 0 && symbol2.getText().length() != 0) {
                    double value1 = Double.parseDouble(symbol.getText().toString().trim());
                    double value2 = Double.parseDouble(symbol2.getText().toString().trim());

                  //calls the function that will perform the convertion
                  // and pass the convert from value and convert to value 
                  // the selected position here is used to read the value of the selected symbol 

                    double result = convertValues(value1, value2, spinner1.getSelectedItemPosition(), spinner2.getSelectedItemPosition());

                    symbol2.setText(String.valueOf(result));
                }
            }
        });
    }

    private void init(){
        symbol = findViewById(R.id.currency1);
        symbol2 = findViewById(R.id.currency2);
        label = findViewById(R.id.currency_symbol);
        label2 = findViewById(R.id.currency_symbol2);
        spinner1 = findViewById(R.id.currency_selector1);
        spinner2 = findViewById(R.id.currency_selector2);

        btn_convert = findViewById(R.id.btn_convert);
    }

    private void addSpinnerListener() {
           //Reads the symbol on the spinner each time it is selected
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int position = spinner1.getSelectedItemPosition();
                String item = myItems.get(position).getSymbol();
                double val = rateList.get(position).getRate();
                label.setText(item);
                symbol.setText(String.valueOf(val));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int position = spinner2.getSelectedItemPosition();
                String item = myItems.get(position).getSymbol();
                label2.setText(item);
                double val = rateList.get(position).getRate();
                symbol2.setText(String.valueOf(val));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private double convertValues(double val1, double val2, int x1, int x2) {
        //val1 = 1EUR = val2
        double val1_rate =  rateList.get(x1).getRate();
        double val2_rate = rateList.get(x2).getRate();
        double val2_one_unit = val2_rate/val1_rate;

        return val1 * val2_one_unit;
    }

    private int getSymbolIcon(String symbol){
        switch (symbol){
            case "EUR":
                return R.drawable.eur;
            case "GBP":
                return R.drawable.gbp;
            case "NGN":
                return R.drawable.ngn;
            case "AFN":
                return R.drawable.afn;
            case "BBD":
                return R.drawable.bbd;
            case "USD":
                return R.drawable.usd;
            case "CAD":
                return R.drawable.cad;
            case "JEP":
                return R.drawable.jep;
            case "JPY":
                return R.drawable.jpy;
            case "CHF":
                return R.drawable.chf;
            case "PHP":
                return R.drawable.php;
            case "RUB":
                return R.drawable.rub;
                default:
                    return R.drawable.eur;
        }
    }
}
