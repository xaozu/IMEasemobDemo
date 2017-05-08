/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.tj.imeasemobdemo.fragment;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.example.tj.imeasemobdemo.IMHelper;
import com.example.tj.imeasemobdemo.activity.ChatActivity;
import com.example.tj.imeasemobdemo.db.InviteMessgeDao;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.util.NetUtils;

import java.util.Hashtable;
import java.util.Map;

/**

 * contact list
 *
 */
public class ContactListFragment extends EaseContactListFragment {

    private static final String TAG = ContactListFragment.class.getSimpleName();
    private TextView tvUnread;
    private InviteMessgeDao inviteMessgeDao;

    @Override
    protected void initView() {
        super.initView();

        this.titleBar.setVisibility(View.GONE);
//        getView().findViewById(R.id.search_bar_view).setVisibility(View.GONE);
        registerForContextMenu(listView);
    }

    @Override
    public void refresh() {
        Map<String, EaseUser> m = IMHelper.getInstance().getContactList();
        if (m instanceof Hashtable<?, ?>) {
            m = (Map<String, EaseUser>) ((Hashtable<String, EaseUser>) m).clone();
        }
        setContactsMap(m);
        super.refresh();
        if (inviteMessgeDao == null) {
            inviteMessgeDao = new InviteMessgeDao(getActivity());
        }
    }


    @Override
    protected void setUpView() {
//        titleBar.setVisibility(View.GONE);
        titleBar.setRightLayoutClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                NetUtils.hasDataConnection(getActivity());
            }
        });
        //设置联系人数据
        Map<String, EaseUser> m = IMHelper.getInstance().getContactList();
        if (m instanceof Hashtable<?, ?>) {
            m = (Map<String, EaseUser>) ((Hashtable<String, EaseUser>) m).clone();
        }
        setContactsMap(m);
        super.setUpView();
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    EaseUser user = ((EaseUser) listView.getItemAtPosition(position));
                    if (user != null) {
                        startActivity(new Intent(getActivity(), ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, user.getUsername()));
                    }


                } catch (NullPointerException e) {
                    e.printStackTrace();

                }

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}
