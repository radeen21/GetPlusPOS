package id.mygetplus.getpluspos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Dibuat oleh : ignat
 * Tanggal : 20-Oct-17
 * HP/WA : 0857 7070 6 777
 */
public class Brands_Adp extends RecyclerView.Adapter<Brands_Adp.ViewHolder>
{
	private static String TAG = "[Brands_Adp]";
	private ProgressDialog progressDialog;
	private PopupMessege popupMessege = new PopupMessege();
	private Context context;
	private List<BrandsRsp> brandsRsps;
	private Activity activity;

	public Brands_Adp(Activity activity, Context context, List<BrandsRsp> brandsRsps)
	{
		this.context = context;
		this.brandsRsps = brandsRsps;
		this.activity = activity;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brands_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position)
	{
		holder.tvBrandName.setText(brandsRsps.get(position).getBrandName());
        holder.tvBrandDetail.setText(brandsRsps.get(position).getDetail());
        holder.tvBrandPrice.setText("Rp " + Fungsi.FormatDesimal(brandsRsps.get(position).getPrice()));

		Picasso.with(context).load(brandsRsps.get(position).getImageURL()).networkPolicy(NetworkPolicy.NO_CACHE)
			.memoryPolicy(MemoryPolicy.NO_CACHE)
			.into(holder.ivBrandImage);

		holder.llBrandGroup.setTag(position);
	}

	@Override
	public int getItemCount()
	{
		return brandsRsps.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.ivBrandImage)
		ImageView ivBrandImage;
		@BindView(R.id.llBrandGroup)
		LinearLayout llBrandGroup;
		@BindView(R.id.tvBrandName)
		TextView tvBrandName;
		@BindView(R.id.tvBrandDetail)
		TextView tvBrandDetail;
		@BindView(R.id.tvBrandPrice)
		TextView tvBrandPrice;

/*
		public TextView tvBrandName;
		public TextView tvBrandDetail;
        public TextView tvBrandPrice;
		public LinearLayout llBrandGroup;
		public ImageView ivBrandImage;
*/
		public ViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, itemView);
/*
			tvBrandName = view.findViewById(R.id.tvBrandName);
			tvBrandDetail = view.findViewById(R.id.tvBrandDetail);
            tvBrandPrice = view.findViewById(R.id.tvBrandPrice);
			llBrandGroup = view.findViewById(R.id.llBrandGroup);
			ivBrandImage = view.findViewById(R.id.ivBrandImage);
*/
		}

		@OnClick({R.id.ivBrandImage, R.id.tvBrandPrice, R.id.llBrandGroup})
		public void onViewClicked(View view)
		{
			int intTag = (int) llBrandGroup.getTag();

			switch(view.getId())
			{
				case R.id.ivBrandImage:
				case R.id.tvBrandPrice:
				case R.id.llBrandGroup:
					Calendar c = Calendar.getInstance();
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					String formattedDate = df.format(c.getTime());

					brandsRsps.get(intTag).setStrDate(Fungsi.getDate(formattedDate, "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy"));
					brandsRsps.get(intTag).setStrTime(Fungsi.getTime(formattedDate, "dd/MM/yyyy HH:mm:ss", "HH:mm:ss"));

					Random random = new Random();
					brandsRsps.get(intTag).setIntInvoice(random.nextInt(10000000) + 1);

					Fungsi.storeObjectToSharedPref(context, brandsRsps.get(intTag), Preference.PrefBrandItem);
					Fungsi.storeToSharedPref(context, "", Preference.PrefScanQR);
					Fungsi.storeToSharedPref(context, "", Preference.PrefScanQRConfirm);

					Intent ConfirmIntent = new Intent(activity, Confirm.class);
					activity.startActivity(ConfirmIntent);
					activity.finish();
				break;
			}
		}
	}

	private void AmbilDetailHistory(final int id, final String pemasokid, final int intTag)
	{
		progressDialog = ProgressDialog.show(context, context.getResources().getString(R.string.msgWait),
			context.getResources().getString(R.string.msgBrandDetail));
		progressDialog.setCancelable(false);

		if(Fungsi.isNetworkAvailable(context) == FixValue.TYPE_NONE)
		{
			progressDialog.dismiss();
			popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgConnectionError));
			return;
		}
	}
}
