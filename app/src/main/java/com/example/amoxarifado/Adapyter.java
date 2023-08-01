package com.example.amoxarifado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapyter extends BaseAdapter {

    Context context;

    List<String> Nome ;
    List<String> Quantidade ;

    LayoutInflater inflater;

    public Adapyter(Context context, List<String> nome, List<String> quantidade ){
        this.context = context;
        this.Nome = nome;
        this.Quantidade = quantidade;
        inflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return Nome.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapyter,null);

        TextView nometv = convertView.findViewById(R.id.textViewNome);
        TextView quantidadetv = convertView.findViewById(R.id.textViewQuantidade);


        nometv.setText(Nome.get(position));

        quantidadetv.setText(Quantidade.get(position));
        return convertView;
    }
}
