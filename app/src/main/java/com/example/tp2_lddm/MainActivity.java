package com.example.tp2_lddm;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements RecyclerInterface {
    RecyclerView meuRecyclerView;
    LinearLayoutManager meuLayoutManager;
    MyAdapter adapter;
    EditText editText;
    private Arvore arvList = new Arvore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meuRecyclerView = findViewById(R.id.myRecView);

        meuLayoutManager = new LinearLayoutManager(this);
        meuRecyclerView.setLayoutManager(meuLayoutManager);
        adapter = new MyAdapter(this, arvList.root.childrens, this);
        meuRecyclerView.setAdapter(adapter);

        ImageButton inserir = findViewById(R.id.btnInsert2);
        ImageButton deletar = findViewById(R.id.btnDelete2);
        editText = findViewById(R.id.editText);

        try{
            inserir.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int pos = (arvList.root.childrens.size() + 1);
                    arvList.insert("root",  editText.getText().toString() );
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                }
            });

            deletar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    arvList.root.childrens.clear();
                    adapter.notifyDataSetChanged();
                }
            }); }catch(Exception e){alerta(e.toString());}
    }

    @Override
    public void onItemClick(Object object) {}

    void alerta(String s){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(s);
        builder.create().show();
    }
}
