package br.com.jerodac.Fragments;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import br.com.jerodac.Adapters.PlayListAdapter;
import br.com.jerodac.Controllers.BaseController;
import br.com.jerodac.Controllers.PlayListController;
import br.com.jerodac.DTOs.RadioDTO;
import br.com.jerodac.R;
import br.com.jerodac.Utils.SnackBarUtil;
import br.com.jerodac.business.ModelPresenter;
import br.com.jerodac.widgets.DetailsTransition;
import br.com.jerodac.widgets.GridItemDecoration;
import butterknife.BindView;

/**
 * @author Jean Rodrigo Dalbon Cunha on 13/01/17.
 */
public class PlayListFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    protected RecyclerView recyclerView;

    @BindView(R.id.swiperefresh)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.container_loader)
    protected ViewGroup mContainerLoader;

    private RecyclerView.LayoutManager mLayoutManager;
    private PlayListAdapter mAdapter;
    private PlayListController controller;
    private SnackBarUtil snackBarUtil;

    @Override
    protected int getLayoutResource() {
        return R.layout.list_fragment;
    }

    @Override
    protected void initComponents(View rootView) {
        controller = PlayListController.getInstance();
        controller.attatchListener(resultListener);
        snackBarUtil = new SnackBarUtil(getView());
        swipeRefreshLayout.setOnRefreshListener(onSwipeRefresh);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridItemDecoration(2, dpToPx(10), true));

        if (controller.getModel().getRadios() == null) {
            PlayListController.getInstance().getPlayLists();
        } else {
            hideLoader();
            populateRecyclerView(controller.getModel().getRadios());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    SwipeRefreshLayout.OnRefreshListener onSwipeRefresh = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            PlayListController.getInstance().getPlayLists();
        }
    };

    @Override
    protected void settings(View rootView) {
        setRetainInstance(true);
    }

    private void hideLoader() {
        mContainerLoader.setVisibility(View.GONE);
        mContainerLoader.removeAllViews();
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    private void populateRecyclerView(List<RadioDTO> playList) {
        mAdapter = new PlayListAdapter(getContext(), playList);
        mAdapter.setOnItemClickListener(onItemClickListener);
        recyclerView.setAdapter(mAdapter);
    }


    BaseController.ResultListener resultListener = new BaseController.ResultListener() {
        @Override
        public void onSucess(ModelPresenter modelPresenter) {
            swipeRefreshLayout.setRefreshing(false);
            if (swipeRefreshLayout.getVisibility() != View.VISIBLE) {
                hideLoader();
            }
            recyclerView.removeAllViews();
            populateRecyclerView(modelPresenter.getRadios());
        }


        @Override
        public void onError(Exception ex) {
            snackBarUtil.showError(new SnackBarUtil.OnClickListener() {
                @Override
                public void onClick() {
                    controller.getPlayLists();
                }
            });

            swipeRefreshLayout.setRefreshing(false);
        }
    };

    PlayListAdapter.OnItemClickListener onItemClickListener = new PlayListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, RadioDTO radioDTO, View v) {
            Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show();
            DetailFragment detailFragment = new DetailFragment();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                detailFragment.setSharedElementEnterTransition(new DetailsTransition());
                detailFragment.setEnterTransition(new Fade());
                setExitTransition(new Fade());
                detailFragment.setSharedElementReturnTransition(new DetailsTransition());
            }

            Bundle bundle = new Bundle();
            bundle.putString("url_image", radioDTO.getPictureMedium());
            bundle.putInt("id", radioDTO.getId());
            detailFragment.setArguments(bundle);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addSharedElement((ImageView) v.findViewById(R.id.img_radio), "sharedImage")
                    .replace(R.id.container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        }
    };

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        snackBarUtil.onDestroy();
    }
}
