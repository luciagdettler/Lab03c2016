package ar.edu.utn.frsf.isi.dam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;



public class MainActivity extends AppCompatActivity {
    MiAdapter miAdapter;
    ListView lv;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView) findViewById(R.id.lv);
        miAdapter = new MiAdapter(getApplicationContext(),Trabajo.TRABAJOS_MOCK);
        lv.setAdapter(miAdapter);
        registerForContextMenu(lv);
    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
         getMenuInflater().inflate(R.menu.mimenuprincipal, menu);
         return true;
     }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuinfo){
        super.onCreateContextMenu(menu,v,menuinfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menucontextual,menu);
        menu.setHeaderTitle("Acciones");

    }

    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.opc1){
            Toast.makeText(MainActivity.this,"Se registro la postulación",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.mp1) {
            Intent i = new Intent(this,alta_trabajo.class);
            startActivityForResult(i,0);
        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        if (resultCode == RESULT_OK){
            Trabajo res = (Trabajo) data.getSerializableExtra("resultado");
            Trabajo[] nuevo = new Trabajo[miAdapter.getTrabajos().length+1];
            int i;
            for (i=0;i<miAdapter.getTrabajos().length;i++) {
                nuevo[i]=miAdapter.getTrabajos()[i];
            }
            nuevo[i]=res;
            miAdapter.setTrabajos(nuevo);
            miAdapter.notifyDataSetChanged();
        }
    }
}
