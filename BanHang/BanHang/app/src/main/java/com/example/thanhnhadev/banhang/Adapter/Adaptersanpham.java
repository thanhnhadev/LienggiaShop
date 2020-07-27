package com.example.thanhnhadev.banhang.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thanhnhadev.banhang.CustomView.dialog.Dialog_DeatailSanPham;
import com.example.thanhnhadev.banhang.R;
import com.example.thanhnhadev.banhang.interfaceView.ViewSanPham;
import com.example.thanhnhadev.banhang.object.San_pham;

import java.util.ArrayList;
import java.util.List;

public class Adaptersanpham extends RecyclerView.Adapter<Adaptersanpham.ViewHolder> implements Filterable{
    Context mContext;
    List<San_pham> lisanpham;
    ViewSanPham viewSanPham;
    public Adaptersanpham(Context cont, List<San_pham> lisp, ViewSanPham viewSanPham) {//get data
        this.mContext = cont;
        this.lisanpham = lisp;
        this.viewSanPham = viewSanPham;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//khoi tao layout xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout_sanpham, parent, false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {//set data
        San_pham san_pham = lisanpham.get(position);
        byte[] foodImage = san_pham.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage,0,foodImage.length);
        holder.img_sp.setImageBitmap(bitmap);
        holder.tensp.setText(san_pham.getTensp());
        holder.tv_des.setText(san_pham.getDiretion());
        holder.tv_gia.setText(san_pham.getGia() + "");
        holder.tv_id.setText(san_pham.getId() + "");
        holder.ll_itemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("SanPham", "current item: " + position);
                viewSanPham.showDialogDetailSanPham(lisanpham.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return lisanpham.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {// find id control
        private LinearLayout ll_itemSP;
        private ImageView img_sp;
        private TextView tensp;
        private TextView tv_des;
        private TextView tv_gia;

        private TextView tv_id;

        public ViewHolder(View context) {
            super(context);
            ll_itemSP = (LinearLayout) context.findViewById(R.id.ll_itemSP);
            img_sp = (ImageView) context.findViewById(R.id.img_sp);
            tensp = (TextView) context.findViewById(R.id.tensp);
            tv_des = (TextView) context.findViewById(R.id.tv_des);
            tv_gia = (TextView) context.findViewById(R.id.tv_gia);
            tv_id = (TextView) context.findViewById(R.id.tv_id);

        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            //CharSequence chuỗi
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()){

                }else {
                    //tạo mới mảng San_pham
                    List<San_pham> listsp = new ArrayList<>();
                    //vòng lặp
                    //cho cột San_pham thành lisanpham
                    for(San_pham row: lisanpham){
                        //nếu cột tên sp là chuỗi
                        if(row.getTensp().contains(constraint)){
                            //add thêm cot
                            listsp.add(row);
                        }
                    }
                    //lấy từ lisanpham bằng mảng  listsp
                    lisanpham = listsp;
                }
                //tạo mới 1 giá trị đếm được
                FilterResults filterResults = new FilterResults();
                //giá trị bằng lisanpham
                filterResults.values = lisanpham;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //mảng lisanpham = mảng San_pham
                lisanpham = (ArrayList<San_pham>) results.values;
                //thay đổi dữ liệu
                notifyDataSetChanged();
            }
        };
    }

}
