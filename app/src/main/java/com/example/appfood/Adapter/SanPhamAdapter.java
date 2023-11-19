package com.example.appfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.example.appfood.Model.SanPhamModels;
import com.example.appfood.Presenter.SetOnItemClick;
import com.example.appfood.R;
import com.example.appfood.View.Bill.ContentProDuctActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;

public class SanPhamAdapter  extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private ArrayList<SanPhamModels> arrayList;
    private  int type = 0;

    public SanPhamAdapter(Context context, ArrayList<SanPhamModels> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    public SanPhamAdapter(Context context, ArrayList<SanPhamModels> arrayList,int type) {
        this.context = context;
        this.arrayList = arrayList;
        this.type= type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(type==0){
             view = LayoutInflater.from(context).inflate(R.layout.dong_sanpham,parent,false);
        }else if(type ==2){
            view = LayoutInflater.from(context).inflate(R.layout.dong_sanpham_noibat,parent,false);
        }else if(type ==3){
            view = LayoutInflater.from(context).inflate(R.layout.dongsanphamthucuong,parent,false);
        }else if(type ==4){
            view = LayoutInflater.from(context).inflate(R.layout.dong_sanphamhanquoc,parent,false);
        }else if(type ==5){
            view = LayoutInflater.from(context).inflate(R.layout.dong_sanphammicay,parent,false);
        }else if(type ==6){
            view = LayoutInflater.from(context).inflate(R.layout.dong_sanphamyeuthich,parent,false);
        }else if(type ==7){
            view = LayoutInflater.from(context).inflate(R.layout.dong_sanphamlau,parent,false);
        }else if(type ==8){
            view = LayoutInflater.from(context).inflate(R.layout.dong_sanphamgoiy,parent,false);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.dong_giohang,parent,false);
        }


        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SanPhamModels sanPhamModels = arrayList.get(position);

        holder.txttensp.setText(sanPhamModels.getTensp());

        holder.txtgiasp.setText(NumberFormat.getInstance().format(sanPhamModels.getGiatien())+" Đ");
        Picasso.get().load(sanPhamModels.getHinhanh()).into(holder.hinhanh);
        holder.SetOnItem(new SetOnItemClick() {
            @Override
            //chi tiet san phẩm
            public void SetItemClick(View view, int pos) {
                Intent intent = new Intent(context, ContentProDuctActivity.class);
                intent.putExtra("SP",sanPhamModels);
                context.startActivity(intent);
            }
        });
        if(type==1){
            holder.txtbaohanh.setText(sanPhamModels.getTrongluong());
            holder.txtsoluong.setText(sanPhamModels.getSoluong()+"");
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public void searchSPList(ArrayList<SanPhamModels> searchList){
        arrayList = searchList;
        notifyDataSetChanged();
    }


}
class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
    TextView txttensp,txtgiasp,txtbaohanh,txtsoluong;
    ImageView hinhanh;
    SetOnItemClick itemClick;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        txtgiasp= itemView.findViewById(R.id.txtgiatien);
        txttensp= itemView.findViewById(R.id.txttensp);
        hinhanh= itemView.findViewById(R.id.hinhanh);
        itemView.setOnClickListener(this);
    }
    public  void  SetOnItem(SetOnItemClick itemClick){
        this.itemClick = itemClick;
    }

    @Override
    public void onClick(View v) {
        itemClick.SetItemClick(v,getAdapterPosition());
    }
}