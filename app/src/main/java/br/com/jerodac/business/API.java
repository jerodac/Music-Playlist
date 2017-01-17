package br.com.jerodac.business;

import br.com.jerodac.DTOs.RadiosResponseVO;
import br.com.jerodac.DTOs.TracksVO;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * @author Jean Rodrigo Dalbon Cunha on 13/01/17.
 */
public interface API {

    final static String RADIO = "/radio";

    /* ===============================================================
    * Syncronized services API.
    * For use it need to be runned on another thread than UIThread
    */
    @GET(RADIO)
    RadiosResponseVO radio();

    @GET(RADIO + "/{id}/tracks")
    TracksVO tracks(@Path("id") int id);
}
