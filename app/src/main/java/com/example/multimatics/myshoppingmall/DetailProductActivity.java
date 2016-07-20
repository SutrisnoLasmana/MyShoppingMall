package com.example.multimatics.myshoppingmall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailProductActivity extends AppCompatActivity {

    private TextView tvName, tvPrice, tvDesc;
    private Button btnAddToChart;
    private ImageView imgDetail;
    private Spinner spnSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        tvName = (TextView)findViewById(R.id.tv_name);
        tvPrice = (TextView)findViewById(R.id.tv_price);
        btnAddToChart = (Button)findViewById(R.id.btn_add_to_cart);
        imgDetail = (ImageView)findViewById(R.id.img_detail);
        spnSize = (Spinner)findViewById(R.id.spn_size);
        tvDesc = (TextView)findViewById(R.id.tv_desc);

        getSupportActionBar().setTitle("Detail Produk");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Product selectedProduct = getIntent().getParcelableExtra("product");
        tvName.setText(selectedProduct.getName());
        tvPrice.setText(selectedProduct.getPrice());
        Glide.with(DetailProductActivity.this)
                .load(selectedProduct.getImageUrl())
                .into(imgDetail);

        String[] size = new String[]{
                "Pilih Ukuran",
                "38","39","40","41","42","43","44","45"
        };



        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(DetailProductActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                android.R.id.text1, size);
        spnSize.setAdapter(sizeAdapter);

        String desc = "Jangan Batasi Petualangan\n" +
                "\n" +
                "Tambora diciptakan untuk teman berpetualang, apapun aktivitasnya. Sesuai namanya yang gahar, Tambora siap menapaki jalan bebatuan, kerikil terjal, tanpa perlu khawatir. Tambora melindungi kaki Brothers dengan baik. Desainnya yang hi-cut akan menutupi mata kaki dengan lapisan dalam yang lembut sehingga lebih aman dan nyaman dalam setiap kegiatan. Toe-box yang besar sehingga ujung kaki memiliki ruang gerak yang lebih besar, menggunakan material kulit asli yang berkualitas sehingga awet sampe ke anak-cucu. Tambora menggunakan sole TPR (thermoplastic rubber) agar ringan dan tidak cepat abrasi. Eyelet-nya dikombinasikan dengan hook agar Tambora mudah diikat.\n" +
                "\n" +
                "Ada ratusan gunung untuk didaki, ada puluhan ribu pulau untuk dijelajahi. Kita memang harus bersyukur Bro, tinggal di Indonesia. Petualangan selalu memanggil. Jangan batasi petualangan. Mulai lah dari sekarang!";

        tvDesc.setText(desc);
    }
}
