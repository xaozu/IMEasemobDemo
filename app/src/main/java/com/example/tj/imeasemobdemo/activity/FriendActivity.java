package com.example.tj.imeasemobdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tj.imeasemobdemo.IMHelper;
import com.example.tj.imeasemobdemo.R;
import com.example.tj.imeasemobdemo.adapter.FriendAdapter;
import com.example.tj.imeasemobdemo.fragment.ContactListFragment;
import com.example.tj.imeasemobdemo.utils.LogUtils;
import com.example.tj.imeasemobdemo.view.MyDecoration;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by tsj029 on 2017/5/4.
 *
 */

public class FriendActivity extends BaseActivity {
    @BindView(R.id.rv_friend)
    RecyclerView rvFriend;
    private ContactListFragment mContactListFragment;
    private List<String> mContacts=new ArrayList<>();
    private MultiTypeAdapter multiTypeAdapter;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_friend;
    }

    @Override
    protected BaseActivity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContactListFragment=new ContactListFragment();
        initView();
        initFriend();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_friends,mContactListFragment)
                .commit();
    }
    private void initView(){
        rvFriend.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rvFriend.addItemDecoration(new MyDecoration(getActivity(),MyDecoration.HORIZONTAL_LIST));
        multiTypeAdapter=new MultiTypeAdapter(mContacts);
        multiTypeAdapter.register(String.class,new FriendAdapter(this));
        rvFriend.setAdapter(multiTypeAdapter);
    }
    private void initFriend(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mContacts=EMClient.getInstance().contactManager().getAllContactsFromServer();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
                LogUtils.d("数据 "+mContacts.get(0));
                for (String  username:mContacts) {
                    IMHelper.getInstance().addContact(username);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mContactListFragment.refresh();
                    }
                });
            }
        }).start();

    }

    @OnClick({R.id.btn_refresh})
    public void myClick(View view){
        switch (view.getId()){
            case R.id.btn_refresh:
                initView();
                break;
        }
    }


}
