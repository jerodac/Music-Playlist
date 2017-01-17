package br.com.jerodac.Controllers;

import android.util.Log;

import br.com.jerodac.DTOs.RadiosResponseVO;
import br.com.jerodac.DTOs.TracksVO;
import br.com.jerodac.business.BetterAsyncTask;
import br.com.jerodac.business.ModelPresenter;
import br.com.jerodac.business.RestClient;

/**
 * @author Jean Rodrigo Dalbon Cunha on 13/01/17.
 */
public class PlayListController extends BaseController {

    //Singleton object
    private static PlayListController mController;

    public static synchronized PlayListController getInstance() {
        if (mController == null) {
            mController = new PlayListController();
            mModel = new ModelPresenter();
        }
        return mController;
    }

    public void getPlayLists() {
        new BetterAsyncTask<RadiosResponseVO>() {

            @Override
            protected RadiosResponseVO doIt() {
                return RestClient.radios();
            }

            @Override
            protected void onSuccess(RadiosResponseVO responseVO) {
                mModel.setRadios(responseVO.getData());
                notifySucess();
            }

            @Override
            protected void onError(Exception ex) {
                notifyError(ex);
            }
        }.execute();
    }

    public void getTracks(final int id) {
        new BetterAsyncTask<TracksVO>() {

            @Override
            protected TracksVO doIt() {
                return RestClient.trackList(id);
            }

            @Override
            protected void onSuccess(TracksVO tracksVO) {
                mModel.setMusics(tracksVO.getMusics());
                notifySucess();
            }

            @Override
            protected void onError(Exception ex) {
                notifyError(ex);
            }
        }.execute();
    }

}
