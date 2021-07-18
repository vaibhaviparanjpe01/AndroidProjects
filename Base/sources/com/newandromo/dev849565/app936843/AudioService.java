package com.newandromo.dev849565.app936843;

public class AudioService {
    public static final String ACTION_PAUSE_IF_PLAYING = "";
    public static final String ACTION_PLAY_IF_PAUSED = "";
    public static final String AUDIO_ITEM = "";
    public static final String BROADCAST_ERROR = "";
    public static final String BROADCAST_STATE_CHANGED = "";
    public static final String CURRENT_ITEM = "";
    public static final String ERROR_MESSAGE = "";
    public static final String PLAYER_TYPE = "";
    public static final String STATE = "";

    enum State {
        Stopped,
        Preparing,
        Playing,
        Paused
    }
}
