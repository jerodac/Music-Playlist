package br.com.jerodac.Utils;

import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * @author Jean Rodrigo Dalbon Cunha on 17/01/17.
 */
public class MusicPlayer {

    private MediaPlayer player;

    public MusicPlayer() {
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void play(final String musicUrl) {
        try {
            if (player.isPlaying()) {
                player.stop();
                player = null;
                player = new MediaPlayer();
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            }
            player.setDataSource(musicUrl);
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    player.start();
                }
            });
            player.prepareAsync();
        } catch (Exception e) {

        }
    }

    public void onDestroy() {
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
            }
        }
        player = null;
    }
}
