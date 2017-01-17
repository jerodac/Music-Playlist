package br.com.jerodac.Utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * @author Jean Rodrigo Dalbon Cunha on 17/01/17.
 */
public class SnackBarUtil {

    private Snackbar mSnackbar;
    private View mView;

    public SnackBarUtil(View view) {
        mView = view;
    }

    public void showError(final OnClickListener listener) {
        mSnackbar = Snackbar.make(mView, "Falha ao carregar", Snackbar.LENGTH_LONG);
        mSnackbar.setAction("Tentar novamente", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();
                mSnackbar.dismiss();
            }
        });
        mSnackbar.show();
    }

    public void onDestroy() {
        if (mSnackbar != null) {
            mSnackbar.dismiss();
            mSnackbar = null;
        }
    }

    public interface OnClickListener {
        public void onClick();
    }

}
