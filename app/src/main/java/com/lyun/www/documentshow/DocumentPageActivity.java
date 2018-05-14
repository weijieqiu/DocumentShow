package com.lyun.www.documentshow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lyun.www.documentshow.bean.Document;
import com.lyun.www.documentshow.bean.DocumentLab;
import com.lyun.www.documentshow.fragment.DocumentFragment;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/3/25.
 */

public class DocumentPageActivity extends AppCompatActivity {
    private static final String EXTRA_DOCUMENT_ID = "com.lyun.www.documentshow.document_id";

    private ViewPager mViewPager;
    private List<Document> mDocuments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_pager);
        UUID  documentId = (UUID) getIntent().getSerializableExtra(EXTRA_DOCUMENT_ID) ;

        mViewPager = (ViewPager) findViewById(R.id.document_view_pager);

        mDocuments = DocumentLab.get(this).getDocuments();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Document document = mDocuments.get(position);
                return DocumentFragment.newInstance(document.getId());
            }

            @Override
            public int getCount() {
                return mDocuments.size();
            }
        });

        for(int i = 0; i< mDocuments.size(); i++){
            if(mDocuments.get(i).getId().equals(documentId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context packageContext, UUID documentId){
        Intent intent = new Intent(packageContext, DocumentPageActivity.class);
        intent.putExtra(EXTRA_DOCUMENT_ID, documentId);
        return intent;
    }
}
