package com.jesuspinar.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.jesuspinar.todo.model.ToDo;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    private ArrayList<ToDo> items;
    private ArrayAdapter<ToDo> itemsAdapter;
    private ListView listView;
    private Button btnAdd;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);
        btnSave = findViewById(R.id.btnSave);

        items = new ArrayList<ToDo>();
        itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);

        //Event listeners
        listViewListener();
        btnAdd.setOnClickListener(this::addItem);
    }

    /**
     * Sets a listener to remove a to do when long click, and updates list */
    private void listViewListener() {
        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            itemsAdapter.remove(items.get(i));
            itemsAdapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), getString(R.string.deleted_todo), Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    private void addItem(View view){
        EditText tvDescription = findViewById(R.id.tvDescription);
        String description = tvDescription.getText().toString();
        if(!(description.equals(""))){
            ToDo toDo = new ToDo(description);
            itemsAdapter.add(toDo);
            tvDescription.setText("");
            return;
        }
        Toast.makeText(getApplicationContext(),getString(R.string.empty_todo),Toast.LENGTH_SHORT).show();
    }
}
