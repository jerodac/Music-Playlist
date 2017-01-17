package br.com.jerodac.Fragments;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.jerodac.Adapters.MusicListAdapter;
import br.com.jerodac.Controllers.BaseController;
import br.com.jerodac.Controllers.PlayListController;
import br.com.jerodac.DTOs.MusicDTO;
import br.com.jerodac.MainActivity;
import br.com.jerodac.R;
import br.com.jerodac.Utils.MusicPlayer;
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
    private MusicPlayer musicPlayer;


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

        controller = PlayListController.getInstance();
        controller.getTracks(args.getInt("id"));
        controller.attatchListener(resultListener);
        snackBarUtil = new SnackBarUtil(getView());
        musicPlayer = new MusicPlayer();
    }

    @Override
    protected void settings(View rootView) {
        AppBarLayout appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.app_bar);
        appBarLayout.setExpanded(true);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((TextView) getActivity().findViewById(R.id.describe_playlist)).setText(getArguments().getString("playlist"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    BaseController.ResultListener resultListener = new BaseController.ResultListener() {
        @Override
        public void onSucess(ModelPresenter modelPresenter) {
            containerLoader.removeAllViews();
            containerLoader.setVisibility(View.GONE);
            adapter = new MusicListAdapter(getContext(), modelPresenter.getMusics());
            adapter.setOnItemClickListener(onItemClickListener);
            recyclerView.setAdapter(adapter);
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
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        ((TextView) getActivity().findViewById(R.id.describe_playlist)).setText(getArguments().getString(getResources().getString(R.string.describe_playlist)));
    }

    MusicListAdapter.OnItemClickListener onItemClickListener = new MusicListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, MusicDTO musicDTO, View v) {
            Snackbar.make(getView(), "Carregando musica via stream.", Snackbar.LENGTH_SHORT).show();
            musicPlayer.play(musicDTO.getUrlPreview());
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        snackBarUtil.onDestroy();
        musicPlayer.onDestroy();
    }
}
