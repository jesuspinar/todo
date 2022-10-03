package com.jesuspinar.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.jesuspinar.todo.model.ToDo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ToDo> items;
    private ArrayAdapter<ToDo> itemsAdapter;
    private ListView listView;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);

        //Event listeners
        listViewListener();
        btnAdd.setOnClickListener(this::addItem);
    }

    /**
     * Sets a listener to remove a to do when long click, and updates list
     */
    private void listViewListener() {
        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Toast.makeText(getApplicationContext(), getString(R.string.deleted_todo),
                Toast.LENGTH_LONG).show();
            items.remove(i);
            //Updating Listing
            itemsAdapter.notifyDataSetChanged();
            return true;
        });
    }

    private void addItem(View view){
        EditText input = findViewById(R.id.descriptionTodo);
        String itemText = input.getText().toString();
        if(!(itemText.equals(""))){
            ToDo toDo = new ToDo(itemText);
            itemsAdapter.add(toDo);
            input.setText("");
        }else{
            Toast.makeText(getApplicationContext(),getString(R.string.empty_todo),
                Toast.LENGTH_LONG).show();
        }
    }
}
