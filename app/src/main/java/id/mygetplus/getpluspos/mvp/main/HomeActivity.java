package id.mygetplus.getpluspos.mvp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ScanQR;
import id.mygetplus.getpluspos.mvp.cekpoint.CekPointActivity;
import id.mygetplus.getpluspos.mvp.main.adapter.HomeAdapter;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.ItemClickListener {

    @BindView(R.id.rec_home)
    RecyclerView recHome;

    private String titleMain[] = {
            "Cek Jumlah Point", "Tukar Point", "Beri Poin",
            "Pembayaran poin", "eVoucher"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
    }

    void init() {
        recHome.setHasFixedSize(true);
        recHome.setLayoutManager(new LinearLayoutManager(this));
        HomeAdapter mainAdapter = new HomeAdapter(this, titleMain);
        mainAdapter.setClickListener(this);
        recHome.setAdapter(mainAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent();
        switch (position){
            case 0:
                intent =  new Intent(this, CekPointActivity.class);
//                Toast.makeText(this, "click oii 0", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                intent =  new Intent(this, ScanQR.class);
//                Toast.makeText(this, "click oii 1", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "click oii 2", Toast.LENGTH_SHORT).show();
//                intent =  new Intent(this, SecondActivity.class);
                break;
            case 3:
                Toast.makeText(this, "click oii 3", Toast.LENGTH_SHORT).show();
//                intent =  new Intent(this, SecondActivity.class);
                break;
            case 4:
                Toast.makeText(this, "click oii 4", Toast.LENGTH_SHORT).show();
//                intent =  new Intent(this, SecondActivity.class);
                break;

        }
        this.startActivity(intent);
    }
}
