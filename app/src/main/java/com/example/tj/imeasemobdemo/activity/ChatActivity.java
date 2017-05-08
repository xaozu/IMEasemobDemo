package com.example.tj.imeasemobdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.tj.imeasemobdemo.R;
import com.example.tj.imeasemobdemo.fragment.ChatFragment;
import com.example.tj.imeasemobdemo.runtimepermissions.PermissionsManager;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.util.EasyUtils;

/**
 * Created by tsj029 on 2017/5/4.
 */

public class ChatActivity extends BaseActivity {

    private EaseChatFragment chatFragment;
    public   String toChatUsername;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_chat;
    }

    @Override
    protected BaseActivity getActivity() {
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toChatUsername = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_ID);
        //use EaseChatFratFragment
        chatFragment = new ChatFragment();
        //pass parameters to chat fragment
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
        int  chatTypeTemp = getIntent().getIntExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);

    }

//    private void initChat(){
//        String id=getIntent().getStringExtra("conversation_id");
//        if(TextUtils.isEmpty(id))return;
//        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(id);
//        //获取此会话的所有消息
//        List<EMMessage> messages = conversation.getAllMessages();
//        //SDK初始化加载的聊天记录为20条，到顶时需要去DB里获取更多
//        //获取startMsgId之前的pagesize条消息，此方法获取的messages SDK会自动存入到此会话中，APP中无需再次把获取到的messages添加到会话中
////        List<EMMessage> messagess = conversation.loadMoreMsgFromDB(startMsgId, pagesize);
//        Log.d("ChatActivity ","messages "+messages.size());
//        MultiTypeAdapter adapter=new MultiTypeAdapter(messages);
//        adapter.register(EMMessage.class,new MessageProvider());
//        rvChat.addItemDecoration(new MyDecoration(this,MyDecoration.HORIZONTAL_LIST));
//        rvChat.setAdapter(adapter);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // make sure only one chat activity is opened
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
        if (EasyUtils.isSingleActivity(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public String getToChatUsername(){
        return toChatUsername;
    }

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

}
