package slcantina;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class conexao { //Nome da minha classe

    private  Connection con;
    private  Statement stmt;
    public conexao()
    {
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Oracle JDBC driver loaded ok.");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "cantinaSL", "1234"); 
            System.out.println("Conectado com Sucesso!!");
            stmt = con.createStatement();
        } catch(Exception e)
        {
            System.err.println(e);
        }
    }
    public  void insertCliente() {

        try {
            String a = SLCliente.jTextField1.getText();
            String b = SLCliente.jFormattedTextField1.getText();
            int c = SLCliente.jComboBox1.getSelectedIndex();
            String insertInto = "INSERT INTO cliente values('" + b + "', '" + a + "', '" + c + "')";
            System.out.println(insertInto);
            stmt.executeUpdate(insertInto);


        } catch (Exception e) {
            System.err.println("Exception: A" + e.getMessage());
        }
    }
    public void UpdateCliente(){
        try {
        String a = SLEditClient.CPF.getText();
        String b = SLEditClient.Nome.getText();
        int c = SLEditClient.TipoCliente.getSelectedIndex();
        String insertInto = "UPDATE cliente set nome = '" + b + "', cpf = '" + a + "', indextipo='" + c + "' where CPF = '" + a + "'";
            System.out.println(insertInto);
            stmt.executeUpdate(insertInto);
        

        } catch (Exception e) {
            System.err.println("Exception: B" + e.getMessage());
        }
    
            
            
    }
    public  void insertUsuario() {
        try {
            String a = SLUsuario.jTextCPF.getText();
            String b = SLUsuario.jTextNome.getText();
            String c = SLUsuario.jTextUsuario.getText();
            String e = SLUsuario.jPasswordSenha.getText();
                int d = SLUsuario.SelectType.getSelectedIndex();

            String insertInto = "INSERT INTO usuario values('" + b + "', '" + d + "', '" + c + "', '"+ e +"','" + a + "')";
            System.out.println(insertInto);
            stmt.executeUpdate(insertInto);

        } catch (Exception e) {
            System.err.println("Exception: C" + e.getMessage());
        }

    }
    public void UpdateUsuario(String a, String b, String c, String e){
        try {
        
        String insertInto = "UPDATE usuario set nome = '" + a + "', cpf = '" + e + "', indextipo='" + b + "', usuario='" + c + "' where CPF = '" + e + "'";
            System.out.println(insertInto);
            stmt.executeUpdate(insertInto);
        

        } catch (Exception ex) {
            System.err.println("Exception: sdfsdfsdfsdfsdsdfsdfsdsf" + ex.getMessage());
        }
    }
        
    public void UpdateProduto(String a, String b, String c, String d){
        try {
        
        String insertInto = "UPDATE produto set codigo = '" + a + "', nomeproduto = '" + b + "', preco = '" + c + "', quantidade ='" + d + "' where codigo = '" + a + "'";
            System.out.println(insertInto);
            stmt.executeUpdate(insertInto);
        

        } catch (Exception ex) {
            System.err.println("Exception: Produto" + ex.getMessage());
        }
    
            
            
    }
    
    public void UpdateAgenda(String a, String b, String c){
        try {
        
        String insertInto = "UPDATE agenda set codigo = '" + a + "', cpfcliente = '" + b + "', dataagenda = '" + c + "' where codigo = '" + a + "'";
            System.out.println(insertInto);
            stmt.executeUpdate(insertInto);
        

        } catch (Exception ex) {
            System.err.println("Exception: Produto" + ex.getMessage());
        }
    
            
            
    }
    public  void insertProduto() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Oracle JDBC driver loaded ok.");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "cantinaSL", "1234"); 
            System.out.println("Conectado com Sucesso!!");

            Statement stmt = con.createStatement();

            String a = SLProduto.NomeProduto.getText();
            String b = SLProduto.PrecoProduto.getText();
            String c = SLProduto.QuantidadeProduto.getText();

            String insertInto = "INSERT INTO produto select nvl(max(codigo),0)+1,"+"'" + a + "', '" + b + "', '" + c + "'" + "from produto";
            //String insertInto = "INSERT INTO produto values(max(codigo),0)+1'" + a + "', '" + b + "', '" + c + "')";
            System.out.println(insertInto);
            stmt.executeUpdate(insertInto);

        } catch (Exception e) {
            System.err.println("Exception: D" + e.getMessage());
        }
    }
    public  void insertAgenda() {
        try {
            String a = SLAgenda.jFormattedTextField1.getText().replace(".","").replace("-", "");
            String b = SLAgenda.jFormattedTextField2.getText();
            String c = SLAgenda.AgendaDescricao.getText();

            String insertInto = "INSERT INTO agenda select nvl(max(codigo),0)+1,"+"'" + a + "', '" + b + "', '" + c + "'" + "from agenda";
            //String insertInto = "INSERT INTO produto values(max(codigo),0)+1'" + a + "', '" + b + "', '" + c + "')";
            System.out.println(insertInto);
            stmt.executeUpdate(insertInto);
            } catch (Exception e) {
                System.err.println("Exception: E" + e.getMessage());
        }
    }

    public ResultSet select(String sltc, int i, boolean b) {
        try{
            return stmt.executeQuery(sltc);
        }catch(Exception e)
        {
            System.err.println(e);
        }
        return null;
    }

    public void close() {
        try{con.close();}catch(Exception e){System.err.println(e);}
    }
    public void insert(String s)
    {
        try {
            stmt.executeUpdate(s);
            stmt.executeUpdate("commit");
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}