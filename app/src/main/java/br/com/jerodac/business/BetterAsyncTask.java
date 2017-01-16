package br.com.jerodac.business;

import android.os.AsyncTask;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import br.com.jerodac.DTOs.ResponseVO;
import retrofit.RetrofitError;

public abstract class BetterAsyncTask<Result extends ResponseVO>
        extends AsyncTask<Void, Void, Result> {

    private List<Runnable> mExitListeners = new ArrayList<>();
    private Exception mError;

    protected abstract Result doIt();

    protected abstract void onSuccess(Result result);

    protected void onAbort() {
    }

    protected void onError(Exception ex) {
    }

    protected void onFail(ResponseVO responseVO) {

    }

    public void addExitListener(Runnable runnable) {
        mExitListeners.add(runnable);
    }

    @Override
    protected Result doInBackground(Void... params) {
        try {
            return doIt();
        } catch (Exception ex) {
            mError = ex;
            return null;
        }
    }

    @Override
    protected void onPostExecute(Result result) {
        if (!isCancelled()) {
            onExit();
            if (mError == null) {
                onSuccess(result);
            } else {
                error();
            }
        }
    }

    @Override
    protected void onCancelled() {
        onExit();
    }

    private void error() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                onExit();
                try {
                    if (mError instanceof RetrofitError) {
                        ResponseVO responseVO = (ResponseVO) ((RetrofitError) mError).getBodyAs(ResponseVO.class);
                        if (responseVO != null) {
                            onFail(responseVO);
                        } else {
                            onError(mError);
                        }
                    } else {
                        onError(mError);
                    }
                } catch (Exception e) {
                    onError(e);
                }
            }
        });
    }

    private void onExit() {
        for (Runnable r : mExitListeners) {
            r.run();
        }
    }

}