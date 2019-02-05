package id.mygetplus.getpluspos;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class CashierHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TableAdapter adapter;
    private ArrayList<String> tableNo = new ArrayList<>();
    private List<TableView> tableList;
    Snackbar sb;

    private PopupMessege popupMessege = new PopupMessege();
    private Context context = this;
    private Activity activity = this;

    String ipAddress;

    @BindView(R.id.your_points)
    TextView your_points;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.et_your_number)
    EditText et_your_number;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ivMerchant)
    ImageView ivMerchant;
    @BindView(R.id.img_profile)
    ImageView img_profile;

    private String TAG = "[Cashier Home]";
    String strInfo;
    InputMethodManager imm;
    String[] strScanQR;
    String strAccountRSN = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_home);
        ButterKnife.bind(this);

        Fungsi.CheckPermission(CashierHome.this, context);
        setSupportActionBar(toolbar);

        AuthPOS();

        your_points.setText(strInfo);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        et_your_number.setOnEditorActionListener(new EditText.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                    if(!TextUtils.isEmpty(et_your_number.getText().toString()))
                    {
                        strInfo = et_your_number.getText().toString() + " / 392670";
                        your_points.setText(strInfo);
                        Fungsi.storeToSharedPref(getApplicationContext(),et_your_number.getText().toString() + "#392670", Preference.PrefScanQR);
                    }

                    your_points.setVisibility(View.VISIBLE);
                    et_your_number.setVisibility(View.GONE);
                    your_points.requestFocus();
                    imm.hideSoftInputFromWindow(your_points.getWindowToken(), 0);
                    return true;
                }

                return false;
            }
        });

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences ipPref = getApplicationContext().getSharedPreferences("MyIP", 0);
        ipAddress = ipPref.getString("IPAddress"," ");

        tableList = new ArrayList<>();

        adapter = new TableAdapter(tableList,this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeJsonArrayRequest();
            }
        });
    }

    @OnClick({R.id.your_points, R.id.fab, R.id.ivMerchant})
    public void onViewClicked(View view)
    {
        switch(view.getId())
        {
            case R.id.your_points:
                your_points.setVisibility(View.GONE);
                et_your_number.setVisibility(View.VISIBLE);
                et_your_number.requestFocus();
                imm.showSoftInput(et_your_number, InputMethodManager.SHOW_IMPLICIT);
            break;
            case R.id.fab:
                ppMerchant ppMerchant = new ppMerchant(CashierHome.this);
                ppMerchant.setOnDismissListener(new DialogInterface.OnDismissListener()
                {
                    @Override
                    public void onDismiss(DialogInterface dialog)
                    {
                        AuthPOS();
                    }
                });

                ppMerchant.show();
            break;
            case R.id.ivMerchant:
                AuthPOS();
            break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.cashier_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.logout){
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPrefCashier", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();
            Intent in = new Intent(getApplicationContext(),ScrollingActivity.class);
            startActivity(in);
            finish();
        }
        if (id == R.id.setIP) {
            Bundle source = new Bundle();
            Intent in = new Intent(getApplicationContext(),setIP.class);
            source.putString("requestFrom","home");
            in.putExtras(source);
            startActivity(in);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void makeJsonArrayRequest(){
        if(Fungsi.isNetworkAvailable(context) == FixValue.TYPE_NONE)
        {
            swipeRefreshLayout.setRefreshing(false);
            popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgConnectionError));
            return;
        }

        if(strAccountRSN.matches(""))
        {
            swipeRefreshLayout.setRefreshing(false);
            return;
        }

        DataLink dataLink = Fungsi.BindingData();

        final Call<BrandsPojo> ReceivePojo = dataLink.MerchantBrandService(strAccountRSN);

        ReceivePojo.enqueue(new Callback<BrandsPojo>()
        {
            @Override
            public void onResponse(Call<BrandsPojo> call, retrofit2.Response<BrandsPojo> response)
            {
                if(response.isSuccessful())
                {
                    if (response.body().getStatusRsp().getCode() != FixValue.intSuccess)
                    {
                        swipeRefreshLayout.setRefreshing(false);
                        popupMessege.ShowMessege1(context, response.body().getStatusRsp().getMsg());
                    }
                    else
                    {
                        swipeRefreshLayout.setRefreshing(false);
                        tableList.clear();
                        tableNo.clear();
                        rv.invalidate();

                        if(response.body().getBrandsRsps() == null)
                        {
                            popupMessege.ShowMessege1(context, getString(R.string.msgProdukKosong));
                            rv.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
		                        rv.setVisibility(View.VISIBLE);
                            Brands_Adp brands_adp = new Brands_Adp(activity, context, response.body().getBrandsRsps());
                            brands_adp.notifyDataSetChanged();
                            rv.setAdapter(brands_adp);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BrandsPojo> call, Throwable t)
            {
                swipeRefreshLayout.setRefreshing(false);
                popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgServerFailure));
            }
        });

       /* final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, "http://"+ipAddress+"/orderapp/tableCheckOut.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                tableList.clear();
                tableNo.clear();
                Log.d("size of the","Sizw is rakjsdifns response "+response.length());
                for(int i = 0;i<response.length();i++){
                    try {
                        JSONObject table = (JSONObject) response.get(i);
                        String num = table.getString("id");
                        Log.d("size of the","Data is "+num );
                        tableNo.add(num);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                Log.d("size of the","Data is "+tableNo.size());
                //pDialog.hide();
                prepareData();
                swipeRefreshLayout.setRefreshing(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.hide();
                swipeRefreshLayout.setRefreshing(false);
                View view = findViewById(R.id.drawer_layout);
                sb = Snackbar.make(view, "Cannot connect to network", Snackbar.LENGTH_INDEFINITE);
                sb.setAction("Action", null);
                sb.show();



            }
        });

        AppController.getInstance().addToRequestQueue(req);
*/
    }

    private void prepareData() {
        for(int  i = 0 ; i < tableNo.size() ; i++){
            tableList.add(new TableView(tableNo.get(i)));
        }
        adapter.notifyDataSetChanged();
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void AuthPOS()
    {
        Fungsi.storeToSharedPref(context,0, Preference.PrefAuthPOS);

        if(Fungsi.isNetworkAvailable(context) == FixValue.TYPE_NONE)
            return;

        Integer intMerchantOwner = Fungsi.getIntFromSharedPref(context, Preference.PrefMerchantOwner);

        if(intMerchantOwner == 0)
            return;

        POSLink posLink = Fungsi.BindingPOS();
        UrnCredentials urnCredentials = new UrnCredentials();
        String strTemp = "";

        if(intMerchantOwner == 1)
        {
            // Pertamina
            urnCredentials.setSimAccountName("gpites01+pertamina.pos01@gmail.com");
            urnCredentials.setSimManagementGroup("LOYALTY_POINTOFSALE");
            urnCredentials.setSimSecurityCode("fEbKJbgHKIh4ug@$");
            img_profile.setImageResource(R.drawable.pertamina);
            strTemp = "Pertamina";
        }
        else
        if(intMerchantOwner == 2)
        {
            // CGV
            urnCredentials.setSimAccountName("gpites01+cgv.pos01@gmail.com");
            urnCredentials.setSimManagementGroup("LOYALTY_POINTOFSALE");
            urnCredentials.setSimSecurityCode("MHdtJ!ZM3TwQi3xw");
            img_profile.setImageResource(R.drawable.cgv);
            strTemp = "CGV";
        }
        else
        if(intMerchantOwner == 3)
        {
            // KFC
            urnCredentials.setSimAccountName("gpites01+kfc.pos01@gmail.com");
            urnCredentials.setSimManagementGroup("LOYALTY_POINTOFSALE");
            urnCredentials.setSimSecurityCode("TaYhZK%usn7sqaB3");
            img_profile.setImageResource(R.drawable.kfc);
            strTemp = "KFC";
        }
        else
        if(intMerchantOwner == 4)
        {
            // RANCH MARKET
            urnCredentials.setSimAccountName("gpites02+ranchmarket.pos01@gmail.com");
            urnCredentials.setSimManagementGroup("LOYALTY_POINTOFSALE");
            urnCredentials.setSimSecurityCode("e$!Ex#0!VH8pD7NF");
            img_profile.setImageResource(R.drawable.ranchmarket);
            strTemp = "Ranch Market";
        }
        else
        if(intMerchantOwner == 5)
        {
            // CENTRAL
            urnCredentials.setSimAccountName("gpites02+central.pos01@gmail.com");
            urnCredentials.setSimManagementGroup("LOYALTY_POINTOFSALE");
            urnCredentials.setSimSecurityCode("SgJgOd#!1nM9wdBJ");
            img_profile.setImageResource(R.drawable.central);
            strTemp = "Central Dept Store";
        }
        else
        if(intMerchantOwner == 6)
        {
            // HERO
            urnCredentials.setSimAccountName("gpites02+herociputra.01@gmail.com");
            urnCredentials.setSimManagementGroup("LOYALTY_POINTOFSALE");
            urnCredentials.setSimSecurityCode("W@4wCraSr56u");
            img_profile.setImageResource(R.drawable.logohero);
            strTemp = "Hero";
        }
        else
        if(intMerchantOwner == 7)
        {
	        // BLIBLI
	        urnCredentials.setSimAccountName("gpites02+blibli.pos01@gmail.com");
	        urnCredentials.setSimManagementGroup("LOYALTY_POINTOFSALE");
	        urnCredentials.setSimSecurityCode("hdn@J1Cl$5M5@9R#");
	        img_profile.setImageResource(R.drawable.logoblibli);
	        strTemp = "BliBli";
        }
        else
        if(intMerchantOwner == 8)
        {
	        // Bank Central Asia
	        urnCredentials.setSimAccountName("gpites01+bca.pos01@gmail.com");
	        urnCredentials.setSimManagementGroup("LOYALTY_POINTOFSALE");
	        urnCredentials.setSimSecurityCode("#DUrVBAo7YF5WMeT");
	        img_profile.setImageResource(R.drawable.logobca);
	        strTemp = "Bank Central Asia";
        }
        else
        if(intMerchantOwner == 9)
        {
	        // Tiket.com
	        urnCredentials.setSimAccountName("gpites01+tiket.pos01@gmail.com");
	        urnCredentials.setSimManagementGroup("LOYALTY_POINTOFSALE");
	        urnCredentials.setSimSecurityCode("cQI@kl3ul1XWLgmS");
	        img_profile.setImageResource(R.drawable.logotiket);
	        strTemp = "Tiket.com";
        }

        final String strProductBrand = strTemp;

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setUrnCredentials(urnCredentials);

        final Call<ResponsePojo> ReceivePojo = posLink.AccountPOSService(accountHolder);

        ReceivePojo.enqueue(new Callback<ResponsePojo>()
        {
            @Override
            public void onResponse(Call<ResponsePojo> call, retrofit2.Response<ResponsePojo> response)
            {
                if(response.isSuccessful())
                {
                    if (response.body().getAFaultCode().matches("0"))
                    {
                        tv_user_name.setText(response.body().getAValue().getBAccountOwnerDisplayValue());
                        your_points.setText(response.body().getAValue().getBAccountRSN());
                        response.body().getAValue().setbProductBrand(strProductBrand);
                        response.body().getAValue().setbBrandName(strProductBrand);
                        Fungsi.storeObjectToSharedPref(context, response.body().getAValue(), Preference.PrefMerchantInfo);
                        Fungsi.storeToSharedPref(context,1, Preference.PrefAuthPOS);
                        strAccountRSN = response.body().getAValue().getBAccountRSN();

                        makeJsonArrayRequest();
                    }
                    else
                        popupMessege.ShowMessege1(context, response.body().getAFaultDescription());
                }
                else
                    popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgUnknownError));
            }

            @Override
            public void onFailure(Call<ResponsePojo> call, Throwable t)
            {
                popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgServerFailure));
            }
        });
    }
}
