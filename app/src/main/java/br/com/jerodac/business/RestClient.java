package br.com.jerodac.business;

import android.util.Log;

import br.com.jerodac.DTOs.RadiosResponseVO;
import br.com.jerodac.DTOs.TracksVO;

/**
 * @author Jean Rodrigo Dalbon Cunha on 13/01/17.
 */
public class RestClient {

    public static RadiosResponseVO radios() {
        return Configuration.getApi().radio();
    }

    public static TracksVO trackList(int id) {
        Log.v("TAG", "id: " + id);
        return Configuration.getApi().tracks(id);
    }

}
