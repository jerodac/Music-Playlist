package br.com.jerodac.Fragments;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.jerodac.R;

/**
 * @author Jean Rodrigo Dalbon Cunha on 16/01/17.
 */
public class DetailFragment extends BaseFragment {

    @Override
    protected int getLayoutResource() {
        return R.layout.details_fragment;
    }

    @Override
    protected void initComponents(View rootView) {
        ImageView image = (ImageView) rootView.findViewById(R.id.img_radio);

        Bundle args = getArguments();

        Picasso.with(getContext())
                .load(args.getString("url_image"))
                .placeholder(R.drawable.ic_picasso_loading)
                .error(R.drawable.ic_picasso_loading)
                .into((ImageView) getActivity().findViewById(R.id.backdrop));

        AppBarLayout appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.app_bar);
        appBarLayout.setExpanded(true);
    }

    @Override
    protected void settings(View rootView) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ImageView) getActivity().findViewById(R.id.backdrop)).setImageResource(R.drawable.ic_music_parallax);
    }
}
