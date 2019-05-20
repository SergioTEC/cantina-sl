/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customTables;

import javax.swing.JList;
/**
 *
 * @author Senai
 */
public class searchableList<Param> extends JList<Param>
{
     public searchableList()
    {
        //fixRowCountForVisibleColumns();
        this.setSelectionMode(1);
    }
    public searchableList(DBListBase model)
    {
        this.setModel(model);
        model.updateElements();
        fixRowCountForVisibleColumns();
        this.setSelectionMode(1);
    }

    /**
     *
     */
    public final void fixRowCountForVisibleColumns()
    {
        int nCols = computeVisibleColumnCount();
        int nItems = this.getModel().getSize();

        // Compute the number of rows that will result in the desired number of
        // columns
        int nRows = nItems / nCols;
        if (nItems % nCols > 0) nRows++;
        this.setVisibleRowCount(nRows);
    }
    private final int computeVisibleColumnCount() 
    {
        int cellWidth = this.getCellBounds(0, 0).width;
        int width = this.getVisibleRect().width;
        return width == 0 ? 1 : width / cellWidth;
    }
}
