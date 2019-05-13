package br.feevale.trabalho1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import static br.feevale.trabalho1.MainActivity.CODE_MAIN_ACTIVITY;

public class ClientListAdapter extends BaseAdapter {

    DatabaseStructure db;
    Context ctx;
    LayoutInflater inflater;

    public ClientListAdapter(Context ctx, DatabaseStructure db){
        inflater = LayoutInflater.from(ctx);
        this.db = db;
    }

    @Override
    public int getCount() {
        return db.getClients().size();
    }

    @Override
    public Object getItem(int i) {
        return db.getClients().get(i);
    }

    @Override
    public long getItemId(int i) {
        return db.getClients().get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.clients_list_item, viewGroup, false);
        TextView txtClientAddress = (TextView) v.findViewById(R.id.txtClientAddress);
        TextView txtCourseName = (TextView) v.findViewById(R.id.txtClientName);
        TextView txtCourseContact = (TextView) v.findViewById(R.id.txtClientEmail);

        Client c = db.getClients().get(i);
        txtCourseName.setText(c.getName());
        txtCourseContact.setText(c.getContactEmail());
        txtClientAddress.setText(c.getAddress());

        return v;
    }

}
