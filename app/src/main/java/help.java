import com.github.barteksc.pdfviewer.PDFView;

public class help {

    PDFView mPDFView = (PDFView)findViewById(R.id.pdfView);
    mPDFView.fromAsset("earlychildhood_everyday_i_learn_through_play.pdf").load();

}
