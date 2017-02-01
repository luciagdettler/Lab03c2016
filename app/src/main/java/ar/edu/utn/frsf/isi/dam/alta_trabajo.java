package ar.edu.utn.frsf.isi.dam;

/**
 * Created by Usuario on 12/10/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class alta_trabajo extends AppCompatActivity {
    private Spinner spinner;
    private RadioGroup radioGroup;
    private Button btnGuardar;
    private EditText nombreEditText;
    private Trabajo trabajoNuevo;
    private Categoria categoria;
    private int moneda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_trabajo);
        spinner = (Spinner)findViewById(R.id.categ);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        nombreEditText = (EditText) findViewById(R.id.descripcion);
        btnGuardar = (Button) findViewById(R.id.botonGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoria = new Categoria((int) spinner.getSelectedItemId(),spinner.getSelectedItem().toString());
                trabajoNuevo = new Trabajo(Trabajo.TRABAJOS_MOCK.length+1,nombreEditText.getText().toString(),categoria);
                trabajoNuevo.setMonedaPago(moneda);

                EditText tv = (EditText) findViewById(R.id.descripcion);
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);
                int idx = radioGroup.indexOfChild(radioButton);
                trabajoNuevo.setMonedaPago(moneda);
                Intent i = getIntent();
                // seteamos el resultado a enviar a la actividad principal.
                i.putExtra("resultado",trabajoNuevo);
                // invocamos al método de activity setResult
                setResult(RESULT_OK, i);
                // Finalizamos la Activity para volver a la anterior
                finish();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, buscaNombresCategorias());
        spinner.setAdapter(adapter);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.us:
                        moneda=1;
                        break;
                    case R.id.eu:
                        moneda=2;
                        break;
                    case R.id.ar:
                        moneda=3;
                        break;
                    case R.id.lib:
                        moneda=4;
                        break;
                    case R.id.re:
                        moneda=4;
                        break;
                    default:
                        moneda=0;
                }


            }
        });





    }
    private String[] buscaNombresCategorias(){
        String[] categorias = new String[Categoria.CATEGORIAS_MOCK.length];

        for(int i=0;i<Categoria.CATEGORIAS_MOCK.length;i++){
            categorias[i]=Categoria.CATEGORIAS_MOCK[i].getDescripcion();
        }
        return categorias;
    }
}