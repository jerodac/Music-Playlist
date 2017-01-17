package br.com.jerodac.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.jerodac.DTOs.RadioDTO;
import br.com.jerodac.R;

/**
 * @author Jean Rodrigo Dalbon Cunha on 13/01/17.
 */
public class PlayListAdapter extends RecyclerView
        .Adapter<PlayListAdapter
        .DataObjectHolder> {

    private List<RadioDTO> mDataset;
    private static OnItemClickListener onItemClickListener;
    private Context mContext;

    public PlayListAdapter(Context context, List<RadioDTO> dataset) {
        mContext = context;
        mDataset = dataset;
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {

        TextView tvDescribe;
        ImageView imgRadio;


        public DataObjectHolder(View itemView) {
            super(itemView);
            tvDescribe = (TextView) itemView.findViewById(R.id.tv_title_radio);
            imgRadio = (ImageView) itemView.findViewById(R.id.img_radio);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(getAdapterPosition(), mDataset.get(getAdapterPosition()), v);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item_radios, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.tvDescribe.setText(mDataset.get(position).getTitle());
        Picasso.with(mContext)
                .load(mDataset.get(position).getPictureMedium())
                .placeholder(R.drawable.ic_picasso_loading)
                .error(R.drawable.ic_picasso_loading)
                .into(holder.imgRadio);
    }

    public void addItem(RadioDTO radioDTO, int index) {
        mDataset.add(index, radioDTO);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(int position, RadioDTO radioDTO, View v);
    }
}
