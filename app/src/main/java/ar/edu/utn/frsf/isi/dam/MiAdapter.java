package ar.edu.utn.frsf.isi.dam;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Usuario on 5/10/2016.
 */
public class MiAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    Trabajo[] trab;
    LayoutInflater inflater;

    public MiAdapter(Context context,Trabajo[] trabajos) {
        this.context = context;
        this.trab = trabajos;
    }
    public Context getContext() {
        return context;
    }

    public void setContext(Context c) {
        context = c;
    }

    public Trabajo[] getTrabajos() {
        return trab;
    }

    public void setTrabajos(Trabajo[] trabajos) {
        trab = trabajos;
    }

   @Override
    public int getCount() {
        return trab.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Trabajo trabajo = (Trabajo) trab[position];
        ViewHolder holder;
        View item = convertView;

        if(item==null){
            item = LayoutInflater.from(context).inflate(R.layout.list_row,null);
            holder=new ViewHolder();

            holder.moneda = (ImageView) item.findViewById(R.id.tipoMoneda);
            holder.categoria = (TextView) item.findViewById(R.id.categoria);
            holder.tituloProy = (TextView) item.findViewById(R.id.proyecto);
            holder.hsmax= (TextView) item.findViewById(R.id.horasmax);
            holder.preciohs= (TextView) item.findViewById(R.id.$xhora);
            holder.fecha= (TextView) item.findViewById(R.id.fechafin);
            holder.enIngles= (CheckBox) item.findViewById(R.id.ingles);

            item.setTag(holder);

        }
        else
            holder = (ViewHolder)item.getTag();


        holder.categoria.setText(trabajo.getCategoria().getDescripcion());

        holder.tituloProy.setText(trabajo.getDescripcion());

        holder.hsmax.setText("Horas: " + trabajo.getHorasPresupuestadas() + " Max");

        holder.preciohs.setText("$/Hora"+String.format("%.2f",trabajo.getPrecioMaximoHora()));

        holder.fecha.setText("Fecha Fin:"+ DateFormat.format("yyyy-MM-dd",trabajo.getFechaEntrega()));

        if(trabajo.getRequiereIngles()==true)
            holder.enIngles.setChecked(true);
        else
            holder.enIngles.setChecked(false);

        if(trabajo.getMonedaPago()==1)
            holder.moneda.setImageResource(R.drawable.us);
        else if(trabajo.getMonedaPago()==2)
            holder.moneda.setImageResource(R.drawable.eu);
        else if(trabajo.getMonedaPago()==3)
            holder.moneda.setImageResource(R.drawable.ar);
        else if(trabajo.getMonedaPago()==4)
            holder.moneda.setImageResource(R.drawable.uk);
        else if(trabajo.getMonedaPago()==5)
            holder.moneda.setImageResource(R.drawable.br);

        //Implementamos el método OnLongClickListener que capturará la opción laboral seleccionada
        item.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {

               Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();
                return true;
            }

        });



        return item;
    }
    class ViewHolder {
        ImageView moneda;
        TextView categoria;
        TextView tituloProy;
        TextView hsmax;
        TextView preciohs;
        TextView fecha;
        CheckBox enIngles;


    }

}
