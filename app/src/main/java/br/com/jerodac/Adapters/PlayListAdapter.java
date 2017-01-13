package br.com.jerodac.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Jean Rodrigo Dalbon Cunha on 13/01/17.
 */
// TODO: 13/01/17 Definir modelgem de datos a partir do DTO do Json do endpoint
public class PlayListAdapter extends RecyclerView
        .Adapter<PlayListAdapter
        .DataObjectHolder> {


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {

        public DataObjectHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
