package com.example.palaciosw.k_reciclerview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private RecyclerView recy;
    private List<Contactos> lista = new ArrayList<>();
    // private RecyclerView recyclerView;
    ContactosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String s  = getDateTime();
        //Log.d("msn", s);

        final RecyclerView recy = (RecyclerView) findViewById(R.id.recicler);

        recy.setHasFixedSize(true);
        adapter = new ContactosAdapter(lista);
        LinearLayoutManager miLayoutManager = new LinearLayoutManager(getApplicationContext());
        recy.setLayoutManager(miLayoutManager);
        recy.setItemAnimator(new DefaultItemAnimator());
        recy.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));// Esta linea es de una clase que se creo para que se vea una linea divisoria entre cada contacto

        recy.addOnItemTouchListener(new RecyclerTouchListener(this, recy, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                      Toast.makeText(MainActivity.this, "Se hizo click en: "+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        recy.setAdapter(adapter);
        //recy.setHasFixedSize(true);

        addContacts();
    }

    private void addContacts() {

        lista.add(new Contactos("Wilson", "palacios pulido", "Wilpapuk@gmail.com", (long) 3423423));
        lista.add(new Contactos("Nubia", "pulido Estrada", "Nubia@gmail.com", (long) 304530864));
        lista.add(new Contactos("Gustavo", "palacios pulido", "Gustavo@hotmail.com", (long) 3423423));
        lista.add(new Contactos("Alexa", "velez prieto", "AlexaLucas@hotmail.com", (long) 3423423));
        lista.add(new Contactos("TAvo", "tricito pulido", "TavoTrici@yahoo.com", (long) 3423423));


        adapter.notifyDataSetChanged();

    }


    /*private String getDateTime() {
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(); return dateFormat.format(date); }*/

    //IMPLEMENTACION DE LISTENER EN EL RECYCLER
//1. CREACION DE LA INTERFAZ

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }


    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recy, final ClickListener clicklistener) {

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recy.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recy.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
