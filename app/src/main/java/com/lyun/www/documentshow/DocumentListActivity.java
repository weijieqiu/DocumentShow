package com.lyun.www.documentshow;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.lyun.www.documentshow.bean.Document;
import com.lyun.www.documentshow.fragment.DocumentFragment;
import com.lyun.www.documentshow.fragment.DocumentListFragment;

/**
 * Created by Administrator on 2018/3/25.
 */

public class DocumentListActivity extends SingleFragmentActivity implements DocumentListFragment.Callbacks {
    @Override
    protected Fragment createFragment() {
        return new DocumentListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onDocumentSelected(Document document) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = DocumentPageActivity.newIntent(this, document.getId());
            startActivity(intent);
        } else {
            Fragment newDetail = DocumentFragment.newInstance(document.getId());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }
}
