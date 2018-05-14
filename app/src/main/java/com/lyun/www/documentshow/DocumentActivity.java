package com.lyun.www.documentshow;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.lyun.www.documentshow.fragment.DocumentFragment;

import java.util.UUID;

public class DocumentActivity extends SingleFragmentActivity {

    private static final String EXTRA_DOCUMENT_ID = "com.lyun.www.documentshow.document_id";

    public static Intent newIntent(Context packageContext, UUID documentId){
        Intent intent = new Intent(packageContext, DocumentActivity.class);
        intent.putExtra(EXTRA_DOCUMENT_ID, documentId);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        UUID documentId = (UUID) getIntent().getSerializableExtra(EXTRA_DOCUMENT_ID);
        return DocumentFragment.newInstance(documentId);
    }
}
