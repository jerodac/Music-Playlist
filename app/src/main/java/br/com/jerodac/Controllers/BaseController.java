package br.com.jerodac.Controllers;

import br.com.jerodac.business.ModelPresenter;

/**
 * @author Jean Rodrigo Dalbon Cunha on 14/01/17.
 */
public class BaseController {

    //Model Presenter
    protected static ModelPresenter mModel;

    //Listener, return model present to view
    protected ResultListener mResultListener = null;

    public void attatchListener(ResultListener resultListener) {
        mResultListener = resultListener;
    }

    public ModelPresenter getModel() {
        return mModel;
    }

    protected void notifySucess() {
        if (mResultListener != null) {
            mResultListener.onSucess(mModel);
        }
    }

    protected void notifyError(Exception ex) {
        if (mResultListener != null) {
            mResultListener.onError(ex);
        }
    }

    public interface ResultListener {
        public void onSucess(ModelPresenter modelPresenter);

        public void onError(Exception ex);
    }
}
