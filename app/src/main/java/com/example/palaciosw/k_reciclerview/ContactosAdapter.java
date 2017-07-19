package com.example.palaciosw.k_reciclerview;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

/**
 * Created by palaciosw on 02/06/2017.
 * 1. creamos la clase ContactosAdapter que extiende de RecyclerView.Adapter<ContactosAdapter.RecyclerViewHolder>
 * 1.1 creamos una lista de la clase Contactos
 * 1.2 Donde ContactosAdapter.RecyclerViewHolder es una clase que se crea dentro de esta clase principal (ContactosAdapter)
 * 1.3 Implementamos los metodos implicitos de RecyclerView (@Override) de la clase ContactosAdapter
 * 2. Esta ultima clase creada extiende de RecyclerView.ViewHolder
 * 3. Creamos su metodo constructor de esta ultima clase creada
 * 3.1 en este metodo constructor inicializamos todos los componentes del esqueleto del recicler
 *
 */

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.RecyclerViewHolder>{

        private List<Contactos> contactosList;
        Contactos obj;
        private View.OnClickListener listener; //este es el objeto Listener para el reciclerView


//la implementacion del Listener del recycler hay que hacerla en esta clase
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name, last, mail, phone, date;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.textView_Nombre);
            last = (TextView)itemView.findViewById(R.id.textView_Apellido);
            mail = (TextView)itemView.findViewById(R.id.textView_Correo);
            phone = (TextView)itemView.findViewById(R.id.textView_Telefono);
            date = (TextView)itemView.findViewById(R.id.textView_Date);


        }

    @Override
    public void onClick(View v) {

    }

    public void removeAt(int position) {
        contactosList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, contactosList.size());
    }
}
    //CREAMOS ESTE METODO CONSTRUCTOR QUE RECIBE COMO PARAMETRO UNA LISTA DE LA CLASE Contactos
    public ContactosAdapter(List<Contactos> contactosList) {
        this.contactosList = contactosList;
        notifyItemRangeChanged(0,contactosList.size());//Linea de Notificaion en caso de cambiar el tamaño de la lista
    }

    //CON ESTE METODO IMPLICITO INFLAMOS EN RECYCLER CON EL XML QUE CONTIEN EN ESQUELETO
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.esqueleto_list_row, parent,false);

        v.setOnClickListener((View.OnClickListener) this);

        return new RecyclerViewHolder(v);
    }

     //ES LLAMADO POR EL RecyclerView PARA MOSTRAR LOS DATOS EN LA POSICION ESPECIFICADA
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        /*
           OPCIONES
        * 1.Podemos crear una objeto de la clase Contactos y por medio de este obtener la posicion de la fila y poner el texto que contien en el TextView
        * 2.Podemos directamente con la lista de la clase Contactos y poner el texto en el textview opteniendo en la misma linea la posicion
        * */
        Contactos c = contactosList.get(position);

        //OPCION1
        holder.name.setText(contactosList.get(position).getNombre());
        holder.last.setText(contactosList.get(position).getApellido());
        holder.mail.setText(contactosList.get(position).getCorreo());
        holder.phone.setText(contactosList.get(position).getTelefono().toString());
        holder.date.setText(getDateTime());
        //OPCION2
        //holder.last.setText(c.getApellido());


       // holder.phone.setText(c.getTelefono().toString());
    }

    @Override
    //ESTE METODO IMPLICITO DEVUELVE LA CANTIDAD DE REGISTROS
    public int getItemCount() {
        return contactosList.size();
    }


    //ESTA FUCNION SE ENCARGA DE RECOLECTAR LA FECHA DEL SISTEMA TAMBIEN SE PUEDE PONER LA HORA.
    private String getDateTime() {
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(); return dateFormat.format(date); }


    //METODO QUE SE IMPLICITO DE LA IMPLEMENTACION DE View.OnClickListener
    //http://www.sgoliver.net/blog/controles-de-seleccion-v-recyclerview/
    //https://stackoverflow.com/questions/32455745/recyclerview-and-cardview-click-listener-implementation BUENO PARA MIRAR



}
