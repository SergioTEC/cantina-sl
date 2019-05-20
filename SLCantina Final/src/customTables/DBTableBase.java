/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customTables;

import slcantina.conexao;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Senai
 */
public class DBTableBase extends DefaultTableModel
{
    private static String tableName;
    private static Integer[] columnsSelect;
    private static String[] columnsName;
    private static String[] cType;
    private static String select;
    
    //Setted Up Table
    public DBTableBase(String tb, Integer[] columnsSelect, String[] columnsName, String[] cType)
    {
        this.tableName = tb;
        this.columnsSelect = columnsSelect;
        this.columnsName = columnsName;
        this.cType = cType;
        this.select = "select * from "+this.tableName;
        for(String i : this.columnsName) this.addColumn(i);
        extendedConstructor();
        updateElements();
    }
    //OrdenableTable
    public DBTableBase(String tb, Integer[] columnsSelect, String[] columnsName, String[] cType, String orderned)
    {
        this.tableName = tb;
        this.columnsSelect = columnsSelect;
        this.columnsName = columnsName;
        this.cType = cType;
        this.select = "select * from "+this.tableName+ " order by "+orderned;
        for(String i : this.columnsName) this.addColumn(i);
        extendedConstructor();
        updateElements();
    }
    
    public final void updateElements()
    {
        while (this.getRowCount() > 0)this.removeRow(0);        
        conexao db = new conexao();
        Object[] line = new Object[this.columnsSelect.length];
        try{
            ResultSet result = db.select(select, 1, false);          
            while(result.next())
            {
                for(int i = 0; i < this.columnsSelect.length; i++)
                {
                    switch(cType[i])
                    {
                        case "String":
                            line[i] = result.getString(this.columnsSelect[i]);
                            break;
                        case "Int":
                            line[i] = result.getBigDecimal(this.columnsSelect[i]);
                            break;
                        case "Float":
                            line[i] = result.getFloat(this.columnsSelect[i]);
                            break;
                        case "Date":
                            line[i] = result.getDate(this.columnsSelect[i]);
                            break;
                    }
                }
                this.addRow(line);
            }
            db.close();
        } catch (SQLException e)
        {
            Logger.getLogger(DBTableBase.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    protected void extendedConstructor()
    {
     
    }
    
    @Override
    public boolean isCellEditable(int row, int col)
    {
        return false;
    }
}
