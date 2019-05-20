/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customTables;

import slcantina.conexao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Senai
 * @param <Param>
 */
public class DBListBase<Param> extends DefaultListModel<Param>
{
    private final String selection;
    private final Integer[] columnSelect;
    private final String[] cType;
    private final int apearSelect;
    public final  Vector<Object> auxiliarVector = new Vector<Object>();
    private final Vector<Param> delegate = new Vector<Param>();
    private String order ="";
  
    protected Object[] Produto() throws SQLException
    {
        
        return null;
    }
    public DBListBase(String stable, Integer primaryColumn, Integer[] columnSelect, String[] cType, String columnOrdener)
    {
        this.selection = "select * from "+stable+" "+columnOrdener;       
        this.columnSelect = columnSelect;
        this.order = columnOrdener;
        this.apearSelect = primaryColumn;
        this.cType = cType;
        updateElements();
    }
    public DBListBase(String stable, Integer primaryColumn, Integer[] columnSelect, String[] cType)
    {
        this.selection = "select * from "+stable;        
        this.columnSelect = columnSelect;
        this.apearSelect = primaryColumn;
        this.cType = cType;
        //updateElements();
    }
    public final void updateElements()
    {
        if(!delegate.isEmpty()) delegate.clear();
        conexao db = new conexao();
        try {
            ResultSet rt = db.select(this.selection, 1, false);
            while(rt.next())
            {
                this.delegate.add((Param) rt.getString(apearSelect));
                for(int i = 0; i < this.columnSelect.length; i++)
                {
                    switch(cType[i])
                    {
                        case "String":
                            this.auxiliarVector.add((Object)rt.getString(this.columnSelect[i]));
                            break;
                        case "Int":
                            this.auxiliarVector.add((Object)rt.getInt(this.columnSelect[i]));
                            break;
                        case "Float":
                            this.auxiliarVector.add((Object)rt.getFloat(this.columnSelect[i]));
                            break;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBListBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        refil(this.delegate);
    }
    public final void selectOnlyItemsWith(Param filter)
    {
        updateElements();
        this.clear();
        String splitted2 ="";
        Vector<Param> r = new Vector<>();
        for(Param p : this.delegate)
        {
            String[] s = ((String)p).toLowerCase().split("");  
            String[] s1 = ((String)p).toLowerCase().replaceAll("-", " ").split(" ");
            for(String l : s1)
            {
               if(filter.equals(l)||filter.equals(splitted2)) r.add(p);
               else 
               {
                    for (String item : l.split(""))
                    {
                        if(filter.equals(item)||filter.equals(splitted2)) r.add(p);
                        else splitted2+=item;
                        System.out.println(splitted2);
                    }
                    splitted2 = "";
               }
            }
            for (String item : s)
            {
                if(filter.equals(item)||filter.equals(splitted2)) r.add(p);
                else splitted2+=item;
                System.out.println(splitted2);
            }
            splitted2 = "";
            refil(r);
        }
    }
    private void refil(Vector<Param> v)
    {
        v.forEach((p) -> {
            if(!this.contains(p)) this.addElement(p);
        });
    }   
    public Integer getVectorSize()
    {
        return delegate.size() / 2;
    }
    public Object[] getSelectedElementRowData(Param s)
    {
        Object[] join = new Object[columnSelect.length];
        if(this.delegate.contains(s))
        {
            for(int i = this.auxiliarVector.indexOf(s) - 1; i < this.auxiliarVector.indexOf(s) - 1 + columnSelect.length; i++)
            {
                System.out.println(this.auxiliarVector.indexOf(s));
                System.out.println(this.auxiliarVector.indexOf(s) - 1 + columnSelect.length);
                System.out.println(this.auxiliarVector.get(i));
                System.out.println(i+1-this.auxiliarVector.indexOf(s));
                join[i+1-this.auxiliarVector.indexOf(s)] = this.auxiliarVector.get(i);
            }
        }
        return join;
    }
}