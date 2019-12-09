package br.ifsc.edu.seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    NumberFormat formatacaoPercentual = NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formatacaoPercentual.setMaximumFractionDigits(3);
        formatacaoPercentual.setMaximumIntegerDigits(3);
        seekBar =(SeekBar) findViewById(R.id.seekbar);
        textView = (TextView) findViewById(R.id.text);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sincronizaTextView();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                atualiza();
            }
        });
    }

    public void sincronizaTextView(){
        int valor= seekBar.getProgress();
        this.textView.setText(formatacaoPercentual.format(Double.parseDouble(String.valueOf(valor))/100));
    }



    @SuppressLint("SetTextI18n")
    public void atualiza() {
        int valor = seekBar.getProgress();

        double porcentagem = valor;
        porcentagem = porcentagem/100.0;

        EditText editText = (EditText) findViewById(R.id.textValorServico);
        double valorServico = Double.parseDouble(editText.getText().toString());

        double taxa = porcentagem * valorServico;
        TextView textView = (TextView) findViewById(R.id.textTaxa);
        textView.setText(Double.toString(taxa));

        textView = (TextView) findViewById(R.id.textPrecoFinal);
        textView.setText(Double.toString(porcentagem * valorServico));

        textView = (TextView) findViewById(R.id.textPrecoFinal);
        textView.setText(Double.toString(taxa + valorServico));
    }
}
