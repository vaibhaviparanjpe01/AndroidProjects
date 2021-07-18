package com.develeno.mylo.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.others.AIButton;
import com.develeno.mylo.others.Data;
import com.develeno.mylo.others.MyHelper;
import com.develeno.mylo.others.TTS;
import com.develeno.mylo.pojo.ChatUser;
import com.develeno.mylo.pojo.Message;
import com.develeno.mylo.pojo.UserObject;
import com.google.gson.JsonElement;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ai.api.AIServiceException;
import ai.api.PartialResultsListener;
import ai.api.RequestExtras;
import ai.api.android.AIConfiguration;
import ai.api.android.AIDataService;
import ai.api.model.AIContext;
import ai.api.model.AIError;
import ai.api.model.AIEvent;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Metadata;
import ai.api.model.Result;
import ai.api.model.Status;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChat extends Fragment implements AIButton.AIButtonListener {

    private static final String TAG = "TAG_AI";
    private View view;
    private MessagesListAdapter<IMessage> adapter;
    private Handler handler;
    private AIDataService aiDataService;
    private boolean method;
    private boolean speakSetting;
    private MessageInput inputView;

    public FragmentChat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_chat, container, false);

        speakSetting = Data.getSpeakSettings(getContext());
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        setup();

        adapter.notifyDataSetChanged();
        TTS.init(getContext());
        AIButton aiButton = view.findViewById(R.id.micButton);

        final AIConfiguration config = new AIConfiguration("b7e22c3b5ed942629741a2a17fcd19f6",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);


        config.setRecognizerStartSound(getResources().openRawResourceFd(R.raw.test_start));
        config.setRecognizerStopSound(getResources().openRawResourceFd(R.raw.test_stop));
        config.setRecognizerCancelSound(getResources().openRawResourceFd(R.raw.test_cancel));
        aiDataService = new AIDataService(getContext(), config);

        handler = new Handler(Looper.getMainLooper());
        aiButton.initialize(config);
        aiButton.setResultsListener(this);
        aiButton.setPartialResultsListener(new PartialResultsListener() {
            @Override
            public void onPartialResults(List<String> partialResults) {
                final String result = partialResults.get(0);
                if (!TextUtils.isEmpty(result)) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            inputView.getInputEditText().setText(result);
                        }
                    });
                }
            }
        });

        final AsyncTask<String, Void, AIResponse> task = getAsyncTask();
        task.execute("hello", "", "");

        return view;
    }

    @NonNull
    private AsyncTask<String, Void, AIResponse> getAsyncTask() {
        return new AsyncTask<String, Void, AIResponse>() {

            private AIError aiError;

            @Override
            protected AIResponse doInBackground(final String... params) {
                final AIRequest request = new AIRequest();
                String query = params[0];
                String event = params[1];

                if (!TextUtils.isEmpty(query))
                    request.setQuery(query);
                if (!TextUtils.isEmpty(event))
                    request.setEvent(new AIEvent(event));
                final String contextString = params[2];
                RequestExtras requestExtras = null;
                if (!TextUtils.isEmpty(contextString)) {
                    final List<AIContext> contexts = Collections.singletonList(new AIContext(contextString));
                    requestExtras = new RequestExtras(contexts, null);
                }

                try {
                    return aiDataService.request(request, requestExtras);
                } catch (final AIServiceException e) {
                    aiError = new AIError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(final AIResponse response) {
                if (response != null) {
                    onResult(response);
                } else {
                    onError(aiError);
                }
            }
        };
    }


    private void setup() {
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String s) {
                new MyHelper(getActivity()).loadImage(imageView, s);
            }
        };

        final String number = "123";
        adapter = new MessagesListAdapter<>(number, imageLoader);
        MessagesList messagesList = view.findViewById(R.id.messagesList);
        messagesList.setAdapter(adapter);

        inputView = view.findViewById(R.id.input);
        inputView.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                method = true;
                sendMessage(input.toString());
                return true;
            }
        });
    }

    private void sendMessage(String s) {
        final AsyncTask<String, Void, AIResponse> task = getAsyncTask();
        task.execute(s, "", "");
        //validate and send message
        UserObject userObject = FireBaseInteract.userObject;
        userObject.setNumber("123");
        Message message = new Message("123", s, new ChatUser(userObject));
        adapter.addToStart(message, true);
    }

    @Override
    public void onPause() {
        super.onPause();
        //  aiButton.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        //  aiButton.resume();
    }

    @Override
    public void onResult(final AIResponse response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onResult");

                Log.i(TAG, "Received success response");

                // this is example how to get different parts of result object
                final Status status = response.getStatus();
                Log.i(TAG, "Status code: " + status.getCode());
                Log.i(TAG, "Status type: " + status.getErrorType());

                final Result result = response.getResult();
                Log.i(TAG, "Resolved query: " + result.getResolvedQuery());

                Log.i(TAG, "Action: " + result.getAction());
                final String speech = result.getFulfillment().getSpeech();
                Log.i(TAG, "Speech: " + speech);
                if (speakSetting) {
                    TTS.speak(speech);
                }
                if (!method) {
                    UserObject userObject = new UserObject();
                    userObject.setNumber("123");
                    userObject.setName("User");
                    Message message = new Message("123", result.getResolvedQuery(), new ChatUser(userObject));
                    adapter.addToStart(message, true);
                }
                inputView.getInputEditText().setText("");
                /*chats.add(new Pair<>(speech, false));
                adapter.notifyDataSetChanged();
               // msgArea.setText("");
                listView.smoothScrollToPosition(listView.getCount());*/

                UserObject userObject2 = new UserObject();
                userObject2.setNumber("1234");
                userObject2.setName("user2");
                Message message2 = new Message("123", speech, new ChatUser(userObject2));
                adapter.addToStart(message2, true);

                final Metadata metadata = result.getMetadata();
                if (metadata != null) {
                    Log.i(TAG, "Intent id: " + metadata.getIntentId());
                    Log.i(TAG, "Intent name: " + metadata.getIntentName());
                }

                final HashMap<String, JsonElement> params = result.getParameters();
                if (params != null && !params.isEmpty()) {
                    Log.i(TAG, "Parameters: ");
                    for (final Map.Entry<String, JsonElement> entry : params.entrySet()) {
                        Log.i(TAG, String.format("%s: %s", entry.getKey(), entry.getValue().toString()));
                    }
                }
                method = false;
            }

        });
    }

    @Override
    public void onError(final AIError error) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onError");
                UserObject userObject2 = FireBaseInteract.userObject;
                userObject2.setNumber("1234");
                Message message2 = new Message("123", error.getMessage(), new ChatUser(userObject2));
                adapter.addToStart(message2, true);
                //  Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
                // msgArea.setText("");
            }
        });
    }

    @Override
    public void onCancelled() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onCancelled");
                //msgArea.setText("");
            }
        });
    }

}
