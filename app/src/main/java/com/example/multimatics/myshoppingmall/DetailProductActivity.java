package com.example.multimatics.myshoppingmall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailProductActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvName, tvPrice, tvDesc;
    private Button btnAddToChart;
    private ImageView imgDetail;
    private Spinner spnSize;
    private ImageView imgThumbA, imgThumbB, imgThumbC, imgThumbD;

    private int currentImagePosition = 0;

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

        imgDetail.setOnClickListener(this);

        imgThumbA = (ImageView)findViewById(R.id.img_thumb_a);
        imgThumbB = (ImageView)findViewById(R.id.img_thumb_b);
        imgThumbC = (ImageView)findViewById(R.id.img_thumb_c);
        imgThumbD = (ImageView)findViewById(R.id.img_thumb_d);

        imgThumbA.setOnClickListener(this);
        imgThumbB.setOnClickListener(this);
        imgThumbC.setOnClickListener(this);
        imgThumbD.setOnClickListener(this);

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

        Glide.with(DetailProductActivity.this).load(SampleData.thumb[0]).into(imgThumbA);
        Glide.with(DetailProductActivity.this).load(SampleData.thumb[1]).into(imgThumbB);
        Glide.with(DetailProductActivity.this).load(SampleData.thumb[2]).into(imgThumbC);
        Glide.with(DetailProductActivity.this).load(SampleData.thumb[3]).into(imgThumbD);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        String imageUrl = null;
        switch (view.getId()){
            case R.id.img_thumb_a:
                imageUrl = SampleData.thumbLarge[0];
                currentImagePosition = 0;
                break;
            case R.id.img_thumb_b:
                imageUrl = SampleData.thumbLarge[1];
                currentImagePosition = 1;
                break;
            case R.id.img_thumb_c:
                imageUrl = SampleData.thumbLarge[2];
                currentImagePosition = 2;
                break;
            case R.id.img_thumb_d:
                imageUrl = SampleData.thumbLarge[3];
                currentImagePosition = 3;
                break;
            case R.id.img_detail:
                ArrayList<String> list = new ArrayList<>();
                for(int i = 0; i < SampleData.thumbLarge.length; i++){
                    list.add(SampleData.thumbLarge[i]);
                }
                Intent mIntent = new Intent(DetailProductActivity.this, DetailImageActivity.class);
                mIntent.putExtra("url_images",list);
                mIntent.putExtra("position", currentImagePosition);
                startActivity(mIntent);
                break;
            default:
                imageUrl = null;
                break;
        }

        if(imageUrl != null){
            Glide.with(DetailProductActivity.this)
                .load(imageUrl)
                .into(imgDetail);
        }
    }
}
