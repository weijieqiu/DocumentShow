package com.lyun.www.documentshow.bean;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/3/25.
 */

public class DocumentLab {
    private static DocumentLab sDocumentLab;
    private List<Document> mDocuments;

    public static DocumentLab get(Context context) {
        if (sDocumentLab == null) {
            sDocumentLab = new DocumentLab(context);
        }
        return sDocumentLab;
    }

    private DocumentLab(Context context) {
        mDocuments = new ArrayList<>();
        Document document = new Document();
        document.setName("React+Native跨平台移动应用开发.pdf");
        mDocuments.add(document);
        Document document1 = new Document();
        document1.setName("android高级进阶.pdf");
        mDocuments.add(document1);
        Document document2 = new Document();
        document2.setName("阿里巴巴Android开发手册.pdf");
        mDocuments.add(document2);

    }

    public List<Document> getDocuments() {
        return mDocuments;
    }

    public Document getDocument(UUID id) {
        for (Document document : mDocuments){
            if(document.getId().equals(id)){
                return document;
            }
        }
        return null;
    }
}
