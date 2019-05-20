/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slcantina;

import javax.swing.JList;

/**
 *
 * @author Senai
 */
public class listFixer
{
    public listFixer(JList inherited)
    {
        fixRowCountForVisibleColumns(inherited);
    }
    public void fixRowCountForVisibleColumns(JList list)
    {
        int nCols = computeVisibleColumnCount(list);
        int nItems = list.getModel().getSize();

        // Compute the number of rows that will result in the desired number of
        // columns
        int nRows = nItems / nCols;
        if (nItems % nCols > 0) nRows++;
        list.setVisibleRowCount(nRows);
    }
    public int computeVisibleColumnCount(JList list) 
    {
        int cellWidth = list.getCellBounds(0, 0).width;
        int width = list.getVisibleRect().width;
        return width == 0 ? 1 : width / cellWidth;
    }
}