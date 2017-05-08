package com.example.tj.imeasemobdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tj.imeasemobdemo.R;
import com.example.tj.imeasemobdemo.adapter.ConversationProvider;
import com.example.tj.imeasemobdemo.fragment.ConversationListFragment;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends BaseActivity {
    @BindView(R.id.rv_conversation)
    RecyclerView rvConversation;
    @BindView(R.id.btn_send_message)
    Button btnSendMessage;
    private String TAG = "MainActivity";

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected BaseActivity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getConversations();
            }
        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_conversation,new ConversationListFragment())
                .commit();

    }

    @OnClick({R.id.btn_send_message,R.id.btn_friend})
    public void myClick(View view){
        switch (view.getId()){
            case R.id.btn_send_message:
                sendMesage();
                break;
            case R.id.btn_friend:
                startActivity(new Intent(getActivity(),FriendActivity.class));
                break;
        }
    }


    //获取会话记录
    private void getConversations() {
        Map<String, EMConversation> hashMap = EMClient.getInstance().chatManager().getAllConversations();
        List<EMConversation> data = new ArrayList<>(hashMap.values());
        MultiTypeAdapter adapter = new MultiTypeAdapter(data);
        adapter.register(EMConversation.class, new ConversationProvider(this));
        rvConversation.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        rvConversation.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvConversation.setAdapter(adapter);

    }

    private void sendMesage() {
        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
        EMMessage message = EMMessage.createTxtSendMessage("hello", "testa");
        //如果是群聊，设置chattype，默认是单聊
        message.setChatType(EMMessage.ChatType.Chat);
        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);

        message.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.d(TAG,"发送成功");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getConversations();
                    }
                });
            }

            @Override
            public void onError(int code, String error) {
                Log.d(TAG,"发送失败 "+error);
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
