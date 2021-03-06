package com.example.projectse.admin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectse.MainActivity;
import com.example.projectse.R;
import com.example.projectse.admin.bohoctap.AdminBoHocTapActivity;
import com.example.projectse.admin.dienkhuyet.AdminBoDienKhuyetActivity;
import com.example.projectse.admin.luyennghe.AdminBoLuyenNgheActivity;
import com.example.projectse.admin.sapxepcau.AdminBoSapXepCauActivity;
import com.example.projectse.admin.tracnghiem.AdminBoTracNghiemActivity;
import com.example.projectse.admin.tuvung.AdminBoTuVungActivity;
import com.example.projectse.database.Database;
import com.example.projectse.database.DatabaseAccess;
import com.example.projectse.singletonpattern.MessageObject;
import com.example.projectse.taikhoan.LoginActivity;
import com.example.projectse.taikhoan.ThongTinTaikhoanActivity;
import com.example.projectse.taikhoan.User;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    final  String DATABASE_NAME = "HocNgonNgu.db";
    SQLiteDatabase database;
    DatabaseAccess DB;
    private MessageObject messageObject = MessageObject.getInstance();
    ArrayList<String> adminList;
    AdminAdapter adapter;
    ListView admins;
    ImageView imgLogout;
    Boolean doubleBack = false;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        admins = (ListView) findViewById(R.id.listviewAdmin);
        imgLogout = (ImageView) findViewById(R.id.imgLogoutAdmin);
        DB = DatabaseAccess.getInstance(getApplicationContext());
        LayUser();
        adminList = new ArrayList<>();
        adminList.add("Th??ng tin t??i kho???n");
        adminList.add("H???c t???p");
        adminList.add("B??? h???c t???p");
        adminList.add("T??? v???ng");
        adminList.add("Tr???c nghi???m");
        adminList.add("S???p x???p c??u");
        adminList.add("Luy???n nghe");
        adminList.add("??i???n khuy???t");
        adapter = new AdminAdapter(AdminActivity.this, R.layout.row_admin, adminList);
        admins.setAdapter(adapter);
        admins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(AdminActivity.this, ThongTinTaikhoanActivity.class));
                }
                else if (position == 1) {
                    Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else if (position == 2) {
                    startActivity(new Intent(AdminActivity.this, AdminBoHocTapActivity.class));
                }
                else if (position == 3) {
                    startActivity(new Intent(AdminActivity.this, AdminBoTuVungActivity.class));
                }
                else if (position == 4) {
                    startActivity(new Intent(AdminActivity.this, AdminBoTracNghiemActivity.class));
                }
                else if (position == 5) {
                    startActivity(new Intent(AdminActivity.this, AdminBoSapXepCauActivity.class));
                }
                else if (position == 6) {
                    startActivity(new Intent(AdminActivity.this, AdminBoLuyenNgheActivity.class));
                }
                else if (position == 7) {
                    startActivity(new Intent(AdminActivity.this, AdminBoDienKhuyetActivity.class));
                }
            }
        });
        adapter.notifyDataSetChanged();

        CountDownTimer countDownTimer=new CountDownTimer(3000,3000) {
            @Override
            public void onTick(long millisUntilFinished) {
                messageObject.ShowDialogMessage(Gravity.CENTER,
                        AdminActivity.this,
                        "KH??NG TH??? TRUY C???P!!T??i kho???n c???a b???n kh??ng ph???i Qu???n L??!!",
                        0);
            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };

        if (user.getRole()==1) {
            countDownTimer.start();

        }



        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, LoginActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (doubleBack) {
            super.onBackPressed();
            finish();
            //tho??t
            moveTaskToBack(true);
        }
        this.doubleBack = true;
        Toast.makeText(getApplicationContext(), "Nh???n l???n n???a ????? tho??t", Toast.LENGTH_SHORT).show();
        //th???i gian ch??? l?? 2s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBack = false;
            }
        }, 2000);
    }

    public void LayUser()
    {
        database = Database.initDatabase(AdminActivity.this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM User WHERE ID_User = ?",new String[]{String.valueOf(DB.iduser)});
        cursor.moveToNext();
        String Iduser = cursor.getString(0);
        String HoTen = cursor.getString(1);
        int Point = cursor.getInt(2);
        String Email = cursor.getString(3);
        String SDT = cursor.getString(4);
        int Role = cursor.getInt(5);
        user = new User(Iduser,HoTen,Point,Email,SDT,Role);
    }
}
