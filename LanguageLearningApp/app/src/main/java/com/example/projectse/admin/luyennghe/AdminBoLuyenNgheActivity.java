package com.example.projectse.admin.luyennghe;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectse.R;
import com.example.projectse.admin.AdminActivity;
import com.example.projectse.admin.sapxepcau.AdminBoSapXepCauActivity;
import com.example.projectse.admin.sapxepcau.AdminSapXepCauActivity;
import com.example.projectse.bohoctap.BoHocTap;
import com.example.projectse.bohoctap.BoHocTapAdapter;
import com.example.projectse.database.Database;

import java.util.ArrayList;

public class AdminBoLuyenNgheActivity extends AppCompatActivity {
    final String DATABASE_NAME = "HocNgonNgu.db";
    SQLiteDatabase database;
    ImageView imgBack;
    ArrayList<BoHocTap> listBoLuyenNghe;
    BoHocTapAdapter adapter;
    ListView lvBoLuyenNghe;
    int idbo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_boluyennghe);
        lvBoLuyenNghe = (ListView) findViewById(R.id.listviewAdminBLN);
        imgBack = (ImageView) findViewById(R.id.imgBackAdminBLN);
        listBoLuyenNghe = new ArrayList<>();
        getBoLuyenNghe();
        adapter = new BoHocTapAdapter(AdminBoLuyenNgheActivity.this, R.layout.row_boluyennghe, listBoLuyenNghe);
        lvBoLuyenNghe.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminBoLuyenNgheActivity.this, AdminActivity.class));
            }
        });
        lvBoLuyenNghe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idbo = listBoLuyenNghe.get(position).getIdBo();
                Intent intent = new Intent(AdminBoLuyenNgheActivity.this, AdminLuyenNgheActivity.class);
                intent.putExtra("idBoLuyenNghe", idbo);
                startActivity(intent);
            }
        });
    }

    private void getBoLuyenNghe() {
        database = Database.initDatabase(AdminBoLuyenNgheActivity.this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM BoCauHoi", null);
        listBoLuyenNghe.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            int stt = cursor.getInt(1);
            String ten = cursor.getString(2);
            listBoLuyenNghe.add(new BoHocTap(id, stt, ten));
        }
    }
}
