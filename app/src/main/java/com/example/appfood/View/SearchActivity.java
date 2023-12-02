package com.example.appfood.View;

import static java.security.AccessController.getContext;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.Adapter.SanPhamAdapter;
import com.example.appfood.Model.SanPhamModels;
import com.example.appfood.Presenter.SanPhamPreSenter;
import com.example.appfood.Presenter.SanPhamView;
import com.example.appfood.R;
import com.example.appfood.View.Admin.ProductActivity;
import com.example.appfood.View.Admin.ProductAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import android.app.Dialog;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.SearchView;

public class SearchActivity  extends AppCompatActivity implements SanPhamView {

    RecyclerView recyclerView;
    SearchView searchView;
    SanPhamAdapter sanPhamAdapter;
    ArrayList<SanPhamModels> arrayList;
    SanPhamModels sanPhamModels;
    FirebaseFirestore db;
    ProgressDialog dialog;
    SanPhamPreSenter sanPhamPreSenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbarSearch);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView = findViewById(R.id.recyclerviewSearch);
        searchView = findViewById(R.id.searchViewMain);
        searchView.clearFocus();
        arrayList = new ArrayList<>();
        sanPhamPreSenter = new SanPhamPreSenter(this);
        sanPhamPreSenter.HandlegetDataSanPham();
        sanPhamPreSenter.HandlegetDataSanPhamNB();
        sanPhamPreSenter.HandlegetDataSanPhamTU();
        sanPhamPreSenter.HandlegetDataSanPhamHQ();
        sanPhamPreSenter.HandlegetDataSanPhamMC();
        sanPhamPreSenter.HandlegetDataSanPhamYT();
        sanPhamPreSenter.HandlegetDataSanPhamLau();
        sanPhamPreSenter.HandlegetDataSanPhamGY();
        sanPhamAdapter = new SanPhamAdapter(SearchActivity.this, arrayList);
        recyclerView.setAdapter(sanPhamAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchList(s);
                return true;
            }
        });
    }

    public void searchList(String text) {
        ArrayList<SanPhamModels> searchList = new ArrayList<>();
        for (SanPhamModels sanPhamModels1 : arrayList) {
            if (sanPhamModels1.getTensp().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(sanPhamModels1);
            }
        }
        sanPhamAdapter.searchSPList(searchList);
    }

    public void getDataSanPham(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong,
                               String nhasanxuat, Long type,String trongluong) {
        arrayList.add(new SanPhamModels(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamAdapter = new SanPhamAdapter(this,arrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(sanPhamAdapter);

    }

    @Override
    public void OnEmptyList() {

    }

    @Override
    public void getDataSanPhamNB(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arrayList.add(new SanPhamModels(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamAdapter = new SanPhamAdapter(this,arrayList,2);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(sanPhamAdapter);
    }

    @Override
    public void getDataSanPhamTU(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arrayList.add(new SanPhamModels(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamAdapter = new SanPhamAdapter(this,arrayList,3);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(sanPhamAdapter);
    }

    @Override
    public void getDataSanPhamHQ(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arrayList.add(new SanPhamModels(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamAdapter = new SanPhamAdapter(this,arrayList,4);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(sanPhamAdapter);
    }

    @Override
    public void getDataSanPhamMC(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arrayList.add(new SanPhamModels(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamAdapter = new SanPhamAdapter(this,arrayList,5);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(sanPhamAdapter);
    }

    @Override
    public void getDataSanPhamYT(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arrayList.add(new SanPhamModels(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamAdapter = new SanPhamAdapter(this,arrayList,6);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(sanPhamAdapter);
    }

    @Override
    public void getDataSanPhamLau(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arrayList.add(new SanPhamModels(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamAdapter = new SanPhamAdapter(this,arrayList,7);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(sanPhamAdapter);
    }

    @Override
    public void getDataSanPhamGY(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arrayList.add(new SanPhamModels(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamAdapter = new SanPhamAdapter(this,arrayList,8);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(sanPhamAdapter);
    }


}
