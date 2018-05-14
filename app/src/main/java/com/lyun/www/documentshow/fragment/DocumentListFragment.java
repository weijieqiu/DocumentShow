package com.lyun.www.documentshow.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.listener.Callbacks;
import com.lyun.www.documentshow.DocumentActivity;
import com.lyun.www.documentshow.DocumentPageActivity;
import com.lyun.www.documentshow.R;
import com.lyun.www.documentshow.bean.Document;
import com.lyun.www.documentshow.bean.DocumentLab;

import java.util.List;

/**
 * Created by Administrator on 2018/3/25.
 */

public class DocumentListFragment extends Fragment {
    private RecyclerView mDocumentRecyclerView;
    private DocumentAdapter mAdapter;
    private Callbacks mCallbacks;

    public interface Callbacks{
        void onDocumentSelected(Document document);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_document_list, container, false);
        mDocumentRecyclerView = (RecyclerView) view.findViewById(R.id.document_recycler_view);
        mDocumentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private class DocumentHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mNameTextView;
        private TextView mDateTextView;
        private Document mDocument;

        public DocumentHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_docunment, parent, false));
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.document_name);
            mDateTextView = (TextView) itemView.findViewById(R.id.document_date);
        }

        public void bind(Document document){
            mDocument = document;
            mNameTextView.setText(mDocument.getName());
            mDateTextView.setText(mDocument.getDate().toString());
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(getActivity(), mDocument.getName() + "  clicked!", Toast.LENGTH_SHORT).show();
//            Intent intent = DocumentActivity.newIntent(getActivity(), mDocument.getId());
//            Intent intent = DocumentPageActivity.newIntent(getActivity(), mDocument.getId());
//            startActivity(intent);
            mCallbacks.onDocumentSelected(mDocument);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private class DocumentAdapter extends RecyclerView.Adapter<DocumentHolder> {
        private List<Document> mDocuments;

        public DocumentAdapter(List<Document> documents) {
            mDocuments = documents;
        }

        @NonNull
        @Override
        public DocumentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new DocumentHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DocumentHolder holder, int position) {
            Document document = mDocuments.get(position);
            holder.bind(document);

        }

        @Override
        public int getItemCount() {
            return mDocuments.size();
        }
    }

    private void updateUI(){
        DocumentLab documentLab = DocumentLab.get(getActivity());
        List<Document> documents = documentLab.getDocuments();
        mAdapter = new DocumentAdapter(documents);
        mDocumentRecyclerView.setAdapter(mAdapter);
    }
}
