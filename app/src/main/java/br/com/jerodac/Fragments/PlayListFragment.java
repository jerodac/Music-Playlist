package br.com.jerodac.Fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import br.com.jerodac.Adapters.PlayListAdapter;
import br.com.jerodac.Controllers.BaseController;
import br.com.jerodac.Controllers.PlayListController;
import br.com.jerodac.R;
import br.com.jerodac.business.ModelPresenter;
import butterknife.BindView;

/**
 * @author Jean Rodrigo Dalbon Cunha on 13/01/17.
 */
public class PlayListFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    protected RecyclerView recyclerView;

    @BindView(R.id.swiperefresh)
    protected SwipeRefreshLayout swipeRefreshLayout;

    private PlayListAdapter mAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.list_fragment;
    }

    @Override
    protected void initComponents(View rootView) {
        swipeRefreshLayout.setOnRefreshListener(onSwipeRefresh);
        PlayListController.getInstance().attatchListener(resultListener);
        PlayListController.getInstance().getPlayLists();
    }

    SwipeRefreshLayout.OnRefreshListener onSwipeRefresh = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            PlayListController.getInstance().getPlayLists();
        }
    };

    @Override
    protected void settings(View rootView) {

    }

    BaseController.ResultListener resultListener = new BaseController.ResultListener() {
        @Override
        public void onSucess(ModelPresenter modelPresenter) {
            Toast.makeText(getContext(), "Sucesso", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
            mAdapter = new PlayListAdapter(getContext(), modelPresenter.getRadios());
            recyclerView.setAdapter(mAdapter);
        }

        @Override
        public void onError(Exception ex) {
            Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    };
}
