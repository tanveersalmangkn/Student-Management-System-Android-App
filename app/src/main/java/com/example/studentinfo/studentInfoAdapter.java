package com.example.studentinfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class studentInfoAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<student_info_model> datalist;
    Context context;


    public studentInfoAdapter(Context context, ArrayList<student_info_model> datalist){
        this.context = context;
        this.datalist = datalist;
    }



    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final TextView textName, textClass, textAge, textGender, textid;
        View itemView = LayoutInflater.from(context).inflate(R.layout.student_info_layout, null);
        LinearLayout linearLayout;
        ImageView imageView;

        textid = itemView.findViewById(R.id.stdid);
        textName = itemView.findViewById(R.id.stdName);
        textClass = itemView.findViewById(R.id.stdClass);
        textAge = itemView.findViewById(R.id.stdAge);
        textGender = itemView.findViewById(R.id.stdGender);
        imageView = itemView.findViewById(R.id.setImageView);

        linearLayout = itemView.findViewById(R.id.listdata);


        student_info_model data_model = datalist.get(position);


        textid.setText("" +data_model.getStudent_id());
        textName.setText(data_model.getStudent_name());
        textClass.setText(data_model.getStudent_class());
        textAge.setText(data_model.getStudent_age());
        textGender.setText(data_model.getStudent_gender());

        Bitmap bitmap1 = StringToBitmap(data_model.getStudent_img());
        imageView.setImageBitmap(bitmap1);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String std_id = textid.getText().toString();
                /*String std_name = textName.getText().toString();
                String std_class = textClass.getText().toString();
                String std_age = textAge.getText().toString();
                String std_gender = textGender.getText().toString();*/

                Intent disp = new Intent(context, StudentDetailsActivity.class);
                disp.putExtra("std_id", std_id);
               /* disp.putExtra("std_name", std_name);
                disp.putExtra("std_class", std_class);
                disp.putExtra("std_age", std_age);
                disp.putExtra("std_gender", std_gender);*/
                context.startActivity(disp);

            }
        });

        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String ids = textid.getText().toString();
                dbHandler handler = new dbHandler(context);
                int dels = handler.deleteStudentInfo(Integer.parseInt(ids));
                if (dels ==1 ){
                    Intent disp = new Intent(context, StudentInfo.class);
                    context.startActivity(disp);
                }else
                {
                    Toast.makeText(context, "Data Not deleted", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        return itemView;
    }

    public Bitmap StringToBitmap(String encodedString){
        try{
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch (Exception e){
            e.getMessage();
            return null;
        }
    }
}
