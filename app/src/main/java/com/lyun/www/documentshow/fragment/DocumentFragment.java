package com.lyun.www.documentshow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.lyun.www.documentshow.R;
import com.lyun.www.documentshow.bean.Document;
import com.lyun.www.documentshow.bean.DocumentLab;

import java.util.UUID;

/**
 * Created by Administrator on 2018/3/25.
 */

public class DocumentFragment extends Fragment {

    private static final String ARG_DOCUMENT_ID = "document_id";

    private Document mDocument;
    private PDFView mPDFView;
    private TextView mDocumentName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mDocument = new Document();
//        mDocument.setName("ReactNative.pdf");
//        UUID documentId = (UUID) getActivity().getIntent().getSerializableExtra(DocumentActivity.EXTRA_DOCUMENT_ID);
        UUID documentId = (UUID) getArguments().getSerializable(ARG_DOCUMENT_ID);
        mDocument = DocumentLab.get(getActivity()).getDocument(documentId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_document, container, false);

        mDocumentName = (TextView) v.findViewById(R.id.document_name);
        mDocumentName.setText(mDocument.getName());
        mPDFView = v.findViewById(R.id.pdfView);
        mPDFView.fromAsset(mDocument.getName()).load();
        return v;
    }

    public static DocumentFragment newInstance(UUID documentId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DOCUMENT_ID, documentId);

        DocumentFragment fragment = new DocumentFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
