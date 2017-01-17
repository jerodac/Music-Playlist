package br.com.jerodac.Fragments;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.jerodac.Adapters.MusicListAdapter;
import br.com.jerodac.Controllers.BaseController;
import br.com.jerodac.Controllers.PlayListController;
import br.com.jerodac.R;
import br.com.jerodac.Utils.SnackBarUtil;
import br.com.jerodac.business.ModelPresenter;
import butterknife.BindView;

/**
 * @author Jean Rodrigo Dalbon Cunha on 16/01/17.
 */
public class DetailFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    protected RecyclerView recyclerView;

    @BindView(R.id.container_loader)
    protected ViewGroup containerLoader;

    private MusicListAdapter adapter;
    private PlayListController controller;
    private SnackBarUtil snackBarUtil;


    @Override
    protected int getLayoutResource() {
        return R.layout.details_fragment;
    }

    @Override
    protected void initComponents(View rootView) {
        Bundle args = getArguments();

        Picasso.with(getContext())
                .load(args.getString("url_image"))
                .placeholder(R.drawable.ic_picasso_loading)
                .error(R.drawable.ic_picasso_loading)
                .into((ImageView) getActivity().findViewById(R.id.backdrop));

        AppBarLayout appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.app_bar);
        appBarLayout.setExpanded(true);

        controller = PlayListController.getInstance();
        controller.getTracks(args.getInt("id"));
        controller.attatchListener(resultListener);
        snackBarUtil = new SnackBarUtil(getView());
    }

    @Override
    protected void settings(View rootView) {

    }

    BaseController.ResultListener resultListener = new BaseController.ResultListener() {
        @Override
        public void onSucess(ModelPresenter modelPresenter) {
            containerLoader.removeAllViews();
            containerLoader.setVisibility(View.GONE);
            Log.v("TAG", "OBJETO NO FRAGMENT:" + modelPresenter.getMusics());
            adapter = new MusicListAdapter(getContext(), modelPresenter.getMusics());
            recyclerView.setAdapter(adapter);

            Log.v("TAG", "Size objects: " + adapter.getItemCount());
        }

        @Override
        public void onError(Exception ex) {
            snackBarUtil.showError(new SnackBarUtil.OnClickListener() {
                @Override
                public void onClick() {
                    controller.getTracks(getArguments().getInt("id"));
                }
            });
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ImageView) getActivity().findViewById(R.id.backdrop)).setImageResource(R.drawable.ic_music_parallax);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        snackBarUtil.onDestroy();
    }
}
