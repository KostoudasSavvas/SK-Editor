package paginationEditorPack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.text.BoxView;
import javax.swing.text.Element;
import javax.swing.text.View;

import paginationEditorPack.PageableEditorKit.PageableParagraphView;

public class SectionView extends BoxView {
    int pageNumber = 0;
    protected int pageWidth = 1100;
    protected int pageHeight = 800;
    public static int DRAW_PAGE_INSET = 65;
    protected Insets pageMargins = new Insets(400,400,400,400);
    

    public SectionView(Element elem, int axis) {
        super(elem, axis);
    }

    public int getPageCount() {
        return pageNumber;
    }

 
    protected void layout(int width, int height) {
        width = pageWidth - 2 * DRAW_PAGE_INSET - pageMargins.left - pageMargins.right;
        this.setInsets( (short) (DRAW_PAGE_INSET + pageMargins.top), (short) (DRAW_PAGE_INSET + pageMargins.left), (short) (DRAW_PAGE_INSET + pageMargins.bottom),
                       (short) (DRAW_PAGE_INSET + pageMargins.right));
        super.layout(width, height);
    }

 
    public float getMaximumSpan(int axis) {
        return getPreferredSpan(axis);
    }

  
    public float getMinimumSpan(int axis) {
        return getPreferredSpan(axis);
    }

   
    public float getPreferredSpan(int axis) {
        float span = 0;
        if (axis == View.X_AXIS) {
            span = pageWidth;
        }
        else {
            span = pageHeight * getPageCount();
        }
        return span;
    }

  
    protected void layoutMajorAxis(int targetSpan, int axis, int[] offsets, int[] spans) {
        super.layoutMajorAxis(targetSpan, axis, offsets, spans);
        int totalOffset = 0;
        int n = offsets.length;
        pageNumber = 0;
        for (int i = 0; i < n; i++) {
            offsets[i] = totalOffset;
            View v = getView(i);
            if (v instanceof MultiPageView) {
                ( (MultiPageView) v).setBreakSpan(0);
                ( (MultiPageView) v).setAdditionalSpace(0);
            }

            if ( (offsets[i] + spans[i]) > (pageNumber * pageHeight - DRAW_PAGE_INSET * 2 - pageMargins.top - pageMargins.bottom)) {
                if ( (v instanceof MultiPageView) && (v.getViewCount() > 1)) {
                    MultiPageView multipageView = (MultiPageView) v;
                    int space = offsets[i] - (pageNumber - 1) * pageHeight;
                    int breakSpan = (pageNumber * pageHeight - DRAW_PAGE_INSET * 2 - pageMargins.top - pageMargins.bottom) - offsets[i];
                    multipageView.setBreakSpan(breakSpan);
                    multipageView.setPageOffset(space);
                    multipageView.setStartPageNumber(pageNumber);
                    multipageView.setEndPageNumber(pageNumber);
                    int height = (int) getHeight();

                    int width = ( (BoxView) v).getWidth();
                    if (v instanceof PageableParagraphView) {
                        PageableParagraphView parView = (PageableParagraphView) v;
                        parView.layout(width, height);
                    }

                    pageNumber = multipageView.getEndPageNumber();
                    spans[i] += multipageView.getAdditionalSpace();
                }
                else {
                    offsets[i] = pageNumber * pageHeight;
                    pageNumber++;
                }
            }
            totalOffset = (int) Math.min( (long) offsets[i] + (long) spans[i], Integer.MAX_VALUE);
        }
    }

    
    public void paint(Graphics g, Shape a) {
        Rectangle alloc = (a instanceof Rectangle) ? (Rectangle) a : a.getBounds();
        Shape baseClip = g.getClip().getBounds();
        int pageCount = getPageCount();
        Rectangle page = new Rectangle();
        page.x = alloc.x;
        page.y = alloc.y;
        page.height = pageHeight;
        page.width = pageWidth;
        String sC = Integer.toString(pageCount);
        for (int i = 0; i < pageCount; i++) {
            page.y = alloc.y + pageHeight * i;
            paintPageFrame(g, page, (Rectangle) baseClip);
            g.setColor(Color.blue);
            String sN = Integer.toString(i + 1);
            String pageStr = "Page: " + sN;
            pageStr += " of " + sC;
            g.drawString(pageStr,
                         page.x + page.width - 100,
                         page.y + page.height - 3);
        }
        super.paint(g, a);
        g.setColor(Color.gray);
        // Fills background of pages
        int currentWidth = (int) alloc.getWidth();
        int currentHeight = (int) alloc.getHeight();
        int x = page.x + DRAW_PAGE_INSET;
        int y = 0;
        int w = 0;
        int h = 0;
        if (pageWidth < currentWidth) {
            w = currentWidth;
            h = currentHeight;
            g.fillRect(page.x + page.width, alloc.y, w, h);
        }
        if (pageHeight * pageCount < currentHeight) {
            w = currentWidth;
            h = currentHeight;
            g.fillRect(page.x, alloc.y + page.height * pageCount, w, h);
        }
    }

   
    public void paintPageFrame(Graphics g, Shape page, Rectangle container) {
        Rectangle alloc = (page instanceof Rectangle) ? (Rectangle) page : page.getBounds();
        if (container.intersection(alloc).height <= 0)
            return;
        Color oldColor = g.getColor();

        //borders
        g.setColor(Color.gray);
        g.fillRect(alloc.x, alloc.y, alloc.width, DRAW_PAGE_INSET);
        g.fillRect(alloc.x, alloc.y, DRAW_PAGE_INSET, alloc.height);
        g.fillRect(alloc.x, alloc.y + alloc.height - DRAW_PAGE_INSET, alloc.width, DRAW_PAGE_INSET);
        g.fillRect(alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y, DRAW_PAGE_INSET, alloc.height);

        //frame
        g.setColor(Color.black);
        g.drawLine(alloc.x + DRAW_PAGE_INSET, alloc.y + DRAW_PAGE_INSET, alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y + DRAW_PAGE_INSET);
        g.drawLine(alloc.x + DRAW_PAGE_INSET, alloc.y + DRAW_PAGE_INSET, alloc.x + DRAW_PAGE_INSET, alloc.y + alloc.height - DRAW_PAGE_INSET);
        g.drawLine(alloc.x + DRAW_PAGE_INSET, alloc.y + alloc.height - DRAW_PAGE_INSET, alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y + alloc.height - DRAW_PAGE_INSET);
        g.drawLine(alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y + DRAW_PAGE_INSET, alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y + alloc.height - DRAW_PAGE_INSET);

        //shadow
        g.fillRect(alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y + DRAW_PAGE_INSET + 4, 4, alloc.height - 2 * DRAW_PAGE_INSET);
        g.fillRect(alloc.x + DRAW_PAGE_INSET + 4, alloc.y + alloc.height - DRAW_PAGE_INSET, alloc.width - 2 * DRAW_PAGE_INSET, 4);

        g.setColor(oldColor);
    }
}