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

import br.com.jerodac.DTOs.MusicDTO;
import br.com.jerodac.R;

/**
 * @author Jean Rodrigo Dalbon Cunha on 13/01/17.
 */
public class MusicListAdapter extends RecyclerView
        .Adapter<MusicListAdapter
        .DataObjectHolder> {

    private List<MusicDTO> mDataset;
    private static OnItemClickListener onItemClickListener;
    private Context mContext;

    public MusicListAdapter(Context context, List<MusicDTO> dataset) {
        mContext = context;
        mDataset = dataset;
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {

        TextView tvMusic;
        TextView tvArtist;
        ImageView imgArtist;


        public DataObjectHolder(View itemView) {
            super(itemView);
            tvMusic = (TextView) itemView.findViewById(R.id.tv_music_name);
            tvArtist = (TextView) itemView.findViewById(R.id.tv_artist_name);
            imgArtist = (ImageView) itemView.findViewById(R.id.img_artist);

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
                .inflate(R.layout.template_music_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.tvMusic.setText(mDataset.get(position).getTitle());
        holder.tvArtist.setText(mDataset.get(position).getArtist().getName());
        Picasso.with(mContext)
                .load(mDataset.get(position).getArtist().getPicture())
                .placeholder(R.drawable.ic_picasso_loading)
                .error(R.drawable.ic_picasso_loading)
                .into(holder.imgArtist);
    }

    public void addItem(MusicDTO musicDTO, int index) {
        mDataset.add(index, musicDTO);
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
        public void onItemClick(int position, MusicDTO musicDTO, View v);
    }
}
