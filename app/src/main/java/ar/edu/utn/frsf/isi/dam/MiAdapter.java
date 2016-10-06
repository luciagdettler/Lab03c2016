package ar.edu.utn.frsf.isi.dam;

import android.annotation.TargetApi;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.test.suitebuilder.TestMethod;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


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

    @TargetApi(Build.VERSION_CODES.N)
    public View getView(int position, View convertView, ViewGroup parent) {
        DecimalFormat df = new DecimalFormat("#.##");
        View itemView=convertView;
        if(itemView==null){
            itemView = inflater.inflate(R.layout.list_row, parent, false);
        }
        ViewHolder holder = (ViewHolder) itemView.getTag();
        if(holder==null){
            holder=new ViewHolder(itemView);
            itemView.setTag(holder);
        }
        TextView cat = (TextView) itemView.findViewById(R.id.categoria);

        holder.tituloProy.setText(trab[position].getDescripcion());

        holder.hsmax.setText("Horas: " + df.format(trab[position].getHorasPresupuestadas()) + " Max");

        holder.preciohs.setText("$/Hora"+df.format(trab[position].getPrecioMaximoHora()));

        holder.fecha.setText("Fecha Fin:"+ trab[position].getFechaEntrega());

        if(trab[position].getRequiereIngles()==true)
            holder.enIngles.setChecked(true);
        else
            holder.enIngles.setChecked(false);

        ImageView moneda=(ImageView) itemView.findViewById(R.id.tipoMoneda);
        if(trab[position].getMonedaPago()==1)
            holder.moneda.setImageResource(R.drawable.us);
        else if(trab[position].getMonedaPago()==2)
            holder.moneda.setImageResource(R.drawable.eu);
        else if(trab[position].getMonedaPago()==3)
            holder.moneda.setImageResource(R.drawable.ar);
        else if(trab[position].getMonedaPago()==4)
            holder.moneda.setImageResource(R.drawable.uk);
        if(trab[position].getMonedaPago()==5)
            holder.moneda.setImageResource(R.drawable.br);

        return itemView;
    }
    class ViewHolder {
        ImageView moneda=null;
        TextView categoria=null;
        TextView tituloProy=null;
        TextView hsmax=null;
        TextView preciohs=null;
        TextView fecha=null;
        CheckBox enIngles=null;

        ViewHolder(View base) {
            this.moneda = (ImageView) base.findViewById(R.id.tipoMoneda);
            this.categoria = (TextView) base.findViewById(R.id.categoria);
            this.tituloProy = (TextView) base.findViewById(R.id.proyecto);
            this.hsmax= (TextView) base.findViewById(R.id.horasmax);
            this.preciohs= (TextView) base.findViewById(R.id.$xhora);
            this.fecha= (TextView) base.findViewById(R.id.fechafin);
            this.enIngles= (CheckBox) base.findViewById(R.id.ingles);
            }
    }
}
