package cz.codecamp.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.codecamp.mvp.R;
import cz.codecamp.mvp.domain.Quake;

/**
 * Adapter for {@link cz.codecamp.mvp.ui.fragment.EarthQuakeFragment}
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
public class EarthQuakeAdapter extends RecyclerView.Adapter<EarthQuakeAdapter.VH> {
    public static final String TAG = EarthQuakeAdapter.class.getName();

    List<Quake> data = new ArrayList<>();

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quake, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.showQuake(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public List<Quake> getData() {
        return data;
    }

    public void setData(List<Quake> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class VH extends RecyclerView.ViewHolder {
        public VH(View itemView) {
            super(itemView);
        }

        public void showQuake(Quake quake) {
            TextView mag = (TextView) itemView.findViewById(R.id.txt_mag);
            TextView name = (TextView) itemView.findViewById(R.id.txt_name);
            TextView time = (TextView) itemView.findViewById(R.id.txt_time);

            mag.setText(String.valueOf(quake.getProperties().getMag()));
            name.setText(quake.getProperties().getPlace());
            time.setText(new Date(quake.getProperties().getTime()).toString());
        }
    }
}
