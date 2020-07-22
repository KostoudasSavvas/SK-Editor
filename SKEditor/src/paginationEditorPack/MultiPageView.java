package paginationEditorPack;

public interface MultiPageView {
    public void performMultiPageLayout(int targetSpan, int axis, int[] offsets, int[] spans);
    public int getStartPageNumber();
    public int getEndPageNumber();
    public int getAdditionalSpace();
    public int getBreakSpan();
    public int getPageOffset();
    public void setStartPageNumber(int startPageNumber);
    public void setEndPageNumber(int endPageNumber);
    public void setAdditionalSpace(int additionalSpace);
    public void setBreakSpan(int breakSpan);
    public void setPageOffset(int pageOffset);
}