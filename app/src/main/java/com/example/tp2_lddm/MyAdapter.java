package com.example.tp2_lddm;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MeuViewHolder> {
    public static RecyclerInterface recyclerInterface;
    Context context;
    private ArrayList<Node> minhaLista;
    private RecyclerView myRecView;

    public MyAdapter(Context ctx, ArrayList<Node> minhaLista, RecyclerInterface clickRecyclerInterface) {
        this.context = ctx;
        this.minhaLista = minhaLista;
        this.recyclerInterface = clickRecyclerInterface;
    }

    @Override
    public void onBindViewHolder(MeuViewHolder viewHolder, final int i) {
        Node node = minhaLista.get(i);
        viewHolder.txtDir.setText(node.content);

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Node noDeletado = minhaLista.get(i);
                minhaLista.remove(noDeletado);
                notifyItemRemoved(i);
                notifyDataSetChanged();
            }
        });

        RecyclerView meuRecyclerView;
        LinearLayoutManager meuLayoutManager;
        final MyAdapter adapter;

        meuLayoutManager = new LinearLayoutManager(context);
        myRecView.setLayoutManager(meuLayoutManager);
        adapter = new MyAdapter(context,minhaLista.get(i).childrens,recyclerInterface);
        myRecView.setAdapter(adapter);

        viewHolder.btnInsert.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                try {
                    String conteudo = minhaLista.get(i).content + "." + Integer.toString(minhaLista.get(i).childrens.size() + 1);
                    Node newNode = new Node(minhaLista.get(i), conteudo, false);
                    minhaLista.get(i).childrens.add( newNode );
                    alerta(minhaLista.get(i).childrens.get( 0 ).content);
                    adapter.notifyItemInserted(i);
                    adapter.notifyDataSetChanged();
                }
                catch(Exception e){
                    alerta(e.toString());
                }
            }
        });
    }

    void alerta(String s){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(s);
        builder.create().show();
    }

    @Override
    public MeuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dir,viewGroup, false);
        return new MeuViewHolder(itemView);
    }
    @Override
    public int getItemCount() {
        return minhaLista.size();
    }

    protected class MeuViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtDir;
        protected ImageButton btnInsert;
        protected ImageButton btnDelete;
        protected ImageButton btnEdit;

        public MeuViewHolder(final View itemView) {
            super(itemView);
            txtDir = itemView.findViewById(R.id.txtDir);
            btnInsert = itemView.findViewById(R.id.btnInsert);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            myRecView = itemView.findViewById(R.id.myRecView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerInterface.onItemClick(minhaLista.get(getLayoutPosition()));
                }
            });
        }
    }
}