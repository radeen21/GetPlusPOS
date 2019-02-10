package id.mygetplus.getpluspos.mvp.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.mygetplus.getpluspos.R;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MainHolder> {

    private Context context;
    private String title[];
    private ItemClickListener mClickListener;

    public HomeAdapter(Context context, String[] title) {
        this.context = context;
        this.title = title;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_main_home,
                parent, false);
        MainHolder mainHolder = new MainHolder(view);
        return mainHolder;
    }

    @Override
    public void onBindViewHolder(MainHolder mainHolder, int position) {
        mainHolder.txtItemView.setText(title[position]);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public String getItem(int id) {
        return title[id];
    }

    public class MainHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_view_main)
        TextView txtItemView;

        public MainHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
