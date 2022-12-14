package com.example.limasmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.limasmart.model.registeredDocs;
import com.example.limasmart.retfrofitUtil.APIClient;
import com.example.limasmart.retfrofitUtil.Adapter;
import com.example.limasmart.retfrofitUtil.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocDisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<registeredDocs> regDocs;
    private Adapter adapter;
    private ApiInterface apiInterface;
    ProgressBar progressBar;
    private Adapter.RecyclerViewClickListener listener,listener1;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_display);

        setOnClickListener();

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        fetchUsers("");
    }



    public void setOnClickListener() {
        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(),profileActivity.class);
                intent.putExtra("username",regDocs.get(position).getName());
                intent.putExtra("address",regDocs.get(position).getAddress());
                startActivity(intent);
            }
        };
    }
    public void fetchUsers(String key){
        apiInterface = APIClient.getApiClient().create(ApiInterface.class);
        Call<List<registeredDocs>> call = apiInterface.getUser(key);
        call.enqueue(new Callback<List<registeredDocs>>() {
            @Override
            public void onResponse(Call<List<registeredDocs>> call, Response<List<registeredDocs>> response) {
                progressBar.setVisibility(View.GONE);
                regDocs = response.body();
                adapter = new Adapter(regDocs, DocDisplayActivity.this,listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<registeredDocs>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DocDisplayActivity.this,"Error on" + t.toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                fetchUsers(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                fetchUsers(s);
                return false;
            }
        });

        //inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.edit_details){
            Toast.makeText(getApplicationContext(),"Touched",Toast.LENGTH_SHORT).show();
            openDocRegActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    public void openDocRegActivity(){
        Intent intent = new Intent(this,verifyDoctor.class);
        startActivity(intent);
    }


}