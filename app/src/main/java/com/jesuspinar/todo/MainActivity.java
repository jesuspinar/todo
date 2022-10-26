package com.jesuspinar.todo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jesuspinar.todo.model.ToDo;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    private ArrayList<ToDo> items;
    private ArrayAdapter<ToDo> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        Button btnAdd = findViewById(R.id.btnAdd);

        loadData();
        itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);

        //Event listeners
        btnAdd.setOnClickListener(v -> addItem());
        listView.setOnItemLongClickListener((p, v, i, id) -> removeItem(i));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        saveData();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("todos",null);
        Type type = new TypeToken<ArrayList<ToDo>>() {}.getType();
        items = gson.fromJson(json,type);
        if (items == null) items = new ArrayList<ToDo>();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString("todos", json);
        editor.apply();
    }

    private boolean removeItem(int i){
        itemsAdapter.remove(items.get(i));
        itemsAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), getString(R.string.deleted_todo), Toast.LENGTH_SHORT).show();
        return true;
    }

    private void addItem(){
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
