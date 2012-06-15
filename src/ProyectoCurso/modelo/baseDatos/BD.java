package ProyectoCurso.modelo.baseDatos;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.SqlJetValueType;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

public class BD 
{
    // Creo las variables
    // Esta variable selecciona la base de datos
    private SqlJetDb bd;
    // Esta la uso para seleccionar una tabla de la base de datos
    private ISqlJetTable table;
    // En esta variable guardo la consultas
    private ISqlJetCursor cursor;
    private String ruta;
    
    /**
     * Constructor de la clase
     * Si la ruta donde la base de datos es creada existe, llama a la función de abrir base de datos,
     * si no existe, llama a la función de crear la base de datos
     * @param ruta Variable que guarda la ruta de la base de datos
     */
    public BD(String ruta)
    {
        this.ruta = ruta;
        File bd = new File(this.ruta);
        if(bd.exists())
            this.abrirBD(this.ruta);
        else
            this.crearBD(this.ruta);
    }
    
    /**
     * Este método crea la base de datos, si esta creada la sobreecribe
     * @param nombreBD Variable que contiene el nombre de la base de datos
     */
    public void crearBD(String nombreBD)
    {
        try 
        {
            // Abre la base de datos para realizar operaciones en ella
            this.bd = SqlJetDb.open(new File(nombreBD), true);
            this.bd.getOptions().setAutovacuum(true);
            // Esta función hace que las operaciones a realizar sean de escritura
            this.bd.beginTransaction(SqlJetTransactionMode.WRITE);
            this.bd.getOptions().setUserVersion(1);
        } 
        catch (SqlJetException ex) 
        {Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);}           
        finally 
        {
            try 
            {
                // finaliza la transacción y cierra la base de datos
                this.bd.commit();
                this.bd.close();
            } 
            catch (SqlJetException ex) 
            {Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);}
        }
    }
    
    public void eliminarBD()
    {
        File basedatos= new File(this.ruta);
        if(basedatos.exists())basedatos.delete();
    }
    
    /**
     * Este metodo abre la base de datos
     * @param nombreBD Variable que contiene el nombre de la base de datos
     */
    public void abrirBD(String nombreBD)
    {
        try 
        {
            this.bd = SqlJetDb.open(new File(nombreBD), true);
            this.bd.beginTransaction(SqlJetTransactionMode.WRITE);
        } 
        catch (SqlJetException ex) 
        {Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);}           
        finally 
        {
            try 
            {
                this.bd.commit();
                this.bd.close();
            } 
            catch (SqlJetException ex) 
            {Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);}
        }
    }
    
    /**
     * Este método crea una tabla en la base de datos
     * @param nombreTabla Variable que contiene el nombre de la tabla
     * @param tabla Variable que contiene un String con la consulta de crear la tabla
     * @param index Variabble que contiene todos los índices de esa tabla
     */
    public void crearTabla(String nombreTabla, String tabla, String[] index)
    {
        try {
            // En esta variable guardare la consulta de cada indice
            String consulta;
            this.bd.open();
            this.bd.beginTransaction(SqlJetTransactionMode.WRITE);
            // Funcion que recoge la consulta para crear la tabla
            this.bd.createTable(tabla);
                 
            // Por cada índice que le hallamos pasado por el array se crea un índice
            for(int i=0;i<index.length;i++)
            {
                // Aqui llamamos a la función crearIndice, que crea una consulta para crear índices
                consulta = this.crearIndice("indice_" + nombreTabla + index[i], nombreTabla, index[i]);
                // Esta función crea el índice en la base de datos a través de la consulta pasada
                this.bd.createIndex(consulta);
            }
            this.bd.commit();
            this.bd.close();
        } 
        catch (SqlJetException ex) 
        {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método que crea la consulta de 1 índice
     * @param nombreIndice Variable que contiene el nombre del índice
     * @param nombreTabla Variable que contiene el nombre de la tabla donde ira el índice
     * @param campo Variable que contiene el campo de la tabla del que se hará el índice
     * @return Devuelve la consulta para crear el índice
     */
    public String crearIndice(String nombreIndice, String nombreTabla, String campo)
    {
        String query;
        query = "CREATE INDEX " + nombreIndice + " ON " + nombreTabla + "(" + campo +")";
        // La consulta la guardo en la variable query
        return query;
    }
        
    /**
     * Método que borra una tabla antes creada
     * @param nombreTabla Variable que contiene el nombre de la tabla a borrar
     */
    public void borrarTabla(String nombreTabla) 
    { 
        try 
        {
            this.bd.open();
            this.bd.beginTransaction(SqlJetTransactionMode.WRITE);
            // Esta función borra la tabla que le pasemos por la variable
            this.bd.dropTable(nombreTabla);
            // Esta función se usa para que se realice la consulta
            this.bd.commit();
            this.bd.close();
        } 
        catch (SqlJetException ex) 
        {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    /**
     * Método que inserta registros en una tabla
     * @param nombreTabla Variable que contiene el nombre de la tabla
     * @param campos Variable que los datos introducidos por el usuario a ser insertados
     */
    public void insertarRegistros(String nombreTabla, ArrayList campos) 
    {
        try 
        {
            this.bd.open(); 
            this.bd.beginTransaction(SqlJetTransactionMode.WRITE);
            // Seleccionamos la tabla
            this.table = this.bd.getTable(nombreTabla);
            // creamos un switch con el numero de elementos de la variable "campos"
            switch (campos.size())
            {
                //Según el número de elementos del arraylist se metera en un caso u otro e insertará los campos en la tabla
                case 1:
                    table.insert(campos.get(0));break;
                case 2:
                    table.insert(campos.get(0),campos.get(1));break;
                case 3: 
                    table.insert(campos.get(0),campos.get(1),campos.get(2));break;
                case 4: 
                    table.insert(campos.get(0),campos.get(1),campos.get(2), campos.get(3));break;
                case 5:
                    table.insert(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4));break;
                case 6:
                    table.insert(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5));break;
                case 7:
                    table.insert(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5), campos.get(6));break;
                case 8:
                    table.insert(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5), campos.get(6), campos.get(7));break;
                case 9:
                    table.insert(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5), campos.get(6), campos.get(7),campos.get(8));break;
                case 10:
                    table.insert(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5), campos.get(6), campos.get(7),campos.get(8), campos.get(9));break;
                case 11:
                    table.insert(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5), campos.get(6), campos.get(7),campos.get(8), campos.get(9), campos.get(10));break;
                case 12:
                    table.insert(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5), campos.get(6), campos.get(7),campos.get(8), campos.get(9), campos.get(10), campos.get(11));break;
                case 13:
                default:
            }
            this.bd.commit();
            this.bd.close();
        } 
            catch (SqlJetException ex) 
        {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    public int extraerUltimoRegistro(String nombretabla, String[] campos)
    {
        int resultado = 0;
        try {
            this.bd.open();
            this.bd.beginTransaction(SqlJetTransactionMode.READ_ONLY);
            this.table = this.bd.getTable(nombretabla);
            // Con esta función buscamos en la tabla, según su índice, el valor
            this.cursor = table.order(table.getPrimaryKeyIndexName());   
            // devuelve el numero de campos en el registro actual
            // this.cursor.getFieldsCount();
            
            if (!cursor.eof()) 
            {
                do 
                {
                   resultado=(int)cursor.getRowId();
                } 
                while(cursor.next());
            }
           
            this.bd.commit();
            this.bd.close();
            } 
            catch (SqlJetException ex) 
            {
                Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            }
        return resultado;
    }
    
    /**
     * Método que elimina un registro de una tabla
     * @param nombreTabla Variable que contiene el nombre de la tabla donde se borrarán los registros
     * @param id Variable pasada por el usuario que indica el id a borrar
     */
    public void borrarRegistros(String nombreTabla, int id)
    {
        try {
            this.bd.open();
            this.table = this.bd.getTable(nombreTabla);
            this.bd.beginTransaction(SqlJetTransactionMode.WRITE); 
            // Esta función busca por calve primaria aquel id que concuerde con el pasado por el usuario
            this.cursor = table.lookup(this.table.getPrimaryKeyIndexName(),id);
            // Mientras no sea fin del cursor
            while (!this.cursor.eof()) 
            {       
                // Elimina la clave buscada y sus datos asociados
                this.cursor.delete();
            }
            this.cursor.close();
            this.bd.commit();
            this.bd.close();
        } catch (SqlJetException ex) 
        {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método que modifica los registros de una tabla
     * @param nombreTabla Variable que guarda el nombre de la tabla donde se realizarán los cambios
     * @param id Variable, pasada por el usuario, que indica qué registro será actualizado
     * @param campos Variable que contiene los datos introducidos por el usuario a insertar en la tabla
     */
    public void modificarRegistros(String nombreTabla, int id, ArrayList campos)
    {
        // Llamamos a la función de borrar el registro indicado
        this.borrarRegistros(nombreTabla, id);
        // Llamamos a la función de crear el nuevo registro
        this.insertarRegistros(nombreTabla, campos);
 
    }
    
    /**
     * Método que muestra los datos da la tabla indicada
     * @param nombreTabla Variable que contiene el nombre de la tabla de la que se desea mosrtrar los datos
     * @param campos Variable que contiene todos los nombres de los atributos de la tabla
     * @return Devuelve una arraylist con los resultados de la consulta
     */
    public ArrayList<String[]> mostrarDatos(String nombreTabla, String[] campos) 
    {
        int contador;
        String[] resultado;
        
        // Creamos el arraylist donde se guardará los resultados de la consulta
        ArrayList<String[]> tablaResultado = new ArrayList<String[]>();
        try 
        {
            this.bd.open();
            this.bd.beginTransaction(SqlJetTransactionMode.READ_ONLY);
            this.table = this.bd.getTable(nombreTabla);
            // Ordenamos la consulta por clave primaria
            this.cursor = table.order(table.getPrimaryKeyIndexName());   
            // Si no es fin de cursor 
            if (!cursor.eof()) 
            {
                do 
                {
                    // Inicializamos la variable a 0 antes de entrar en el segundo while
                    contador=0;
                    // Creamos un array de la longitud de los campos de la tabla
                    resultado = new String[campos.length];
                    // Mientras el contador sea menor que la longitud de campos de la tabla
                    while(contador<campos.length)
                    {
                        if(contador==0)
                            resultado[contador]=Long.toString(cursor.getRowId());
                        else
                        {
                            // si el campo que indica el contador en el array es un tipo float
                            if(cursor.getFieldType(campos[contador])==SqlJetValueType.FLOAT)
                                // cambia el resultado a string y lo guarda en resultado
                                resultado[contador] = Double.toString(cursor.getFloat(campos[contador]));
                            // Si es tipo integer
                            else if(cursor.getFieldType(campos[contador])==SqlJetValueType.INTEGER)
                                // cambia el resultado a string y lo guarda en resultado
                                resultado[contador] = Long.toString(cursor.getInteger(campos[contador]));
                            // Si es tipo text
                            else if(cursor.getFieldType(campos[contador])==SqlJetValueType.TEXT)
                                // Guarda en resultado el string
                                resultado[contador] = cursor.getString(campos[contador]);
                            // Aumentamos el valor del contador para que pase por todos los campos de la tabla
                        }
                        contador++;
                    }
                    // Añadimos en el arrayList el resultado según el contador
                    tablaResultado.add(resultado);
                } 
                while(cursor.next());
            }
            cursor.close();
            this.bd.commit();
            this.bd.close();
            
        } 
        catch (SqlJetException ex) 
        {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }            
        return tablaResultado;
    }
    

    /**
     * Método que busca registros según el valor pasado por el usuario
     * @param nombretabla Variable que contiene el nombre de la tabla donde se van a buscar datos
     * @param indice Variable que contiene el índice, por el cual se meterá el filtro
     * @param campos Variable que contiene los nombres de lso campos de la tabla
     * @param valor Variable que contiene un string con el valor por el cual se realizará la búsqueda
     * @return Devuelve una arraylist con los resultados de la consulta
     */
    public ArrayList<String[]> busquedaBD(String nombretabla, String indice, String[] campos, String valor)
    {
        int contador = 0;
        String[] resultado;
        // Creamos el arraylist donde se guardará los resultados de la búsqueda
        ArrayList<String[]> tablaResultado = new ArrayList<String[]>();
        try {
            this.bd.open();
            this.bd.beginTransaction(SqlJetTransactionMode.READ_ONLY);
            this.table = this.bd.getTable(nombretabla);
            // Con esta función buscamos en la tabla, según su índice, el valor
            this.cursor = table.lookup(indice, valor);
            // devuelve el numero de campos en el registro actual
            // this.cursor.getFieldsCount();
            
            if (!cursor.eof()) 
            {
                do 
                {
                    // Inicializamos la variable a 0 antes de entrar en el segundo while
                    contador=0;
                    // Creamos un array de la longitud de los campos de la tabla
                    resultado = new String[campos.length];
                    // Mientras el contador sea menor que la longitud de campos de la tabla
                    while(contador<campos.length)
                    {
                        if(contador==0)
                            resultado[contador]=Long.toString(cursor.getRowId());
                        else
                        {
                            // si el campo que indica el contador en el array es un tipo float
                            if(cursor.getFieldType(campos[contador])==SqlJetValueType.FLOAT)
                                // cambia el resultado a string y lo guarda en resultado
                                resultado[contador] = Double.toString(cursor.getFloat(campos[contador]));
                            // Si es tipo integer
                            else if(cursor.getFieldType(campos[contador])==SqlJetValueType.INTEGER)
                                // cambia el resultado a string y lo guarda en resultado
                                resultado[contador] = Long.toString(cursor.getInteger(campos[contador]));
                            // Si es tipo text
                            else if(cursor.getFieldType(campos[contador])==SqlJetValueType.TEXT)
                                // Guarda en resultado el string
                                resultado[contador] = cursor.getString(campos[contador]);
                            // Aumentamos el valor del contador para que pase por todos los campos de la tabla
                        }
                        contador++;
                    }
                    tablaResultado.add(resultado);
                } 
                while(cursor.next());
            }
           
            this.bd.commit();
            this.bd.close();
            } 
            catch (SqlJetException ex) 
            {
                Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            }
        return tablaResultado;
    }
    
    /**
     * Método que busca registros según el valor pasado por el usuario
     * @param nombretabla Variable que contiene el nombre de la tabla donde se van a buscar datos
     * @param indice Variable que contiene el índice, por el cual se meterá el filtro
     * @param campos Variable que contiene los nombres de lso campos de la tabla
     * @param valor Variable que contiene un integer con el valor por el cual se realizará la búsqueda
     * @return Devuelve una arraylist con los resultados de la consulta
     */
    public ArrayList<String[]> busquedaBD(String nombretabla, String indice, String[] campos, int valor)
    {
        int contador;
        String[] resultado;
        // Creamos el arraylist donde se guardará los resultados de la búsqueda
        ArrayList<String[]> tablaResultado = new ArrayList<String[]>();
        try {
            this.bd.open();
            this.bd.beginTransaction(SqlJetTransactionMode.READ_ONLY);
            this.table = this.bd.getTable(nombretabla);
            // Con esta función buscamos en la tabla, según su índice, el valor
            this.cursor = table.lookup(indice, valor);
            // devuelve el numero de campos en el registro actual
            // this.cursor.getFieldsCount();
            
            if (!cursor.eof()) 
            {
                do 
                {
                    // Inicializamos la variable a 0 antes de entrar en el segundo while
                    contador=0;
                    // Creamos un array de la longitud de los campos de la tabla
                    resultado = new String[campos.length];
                    // Mientras el contador sea menor que la longitud de campos de la tabla
                    while(contador<campos.length)
                    {
                        if(contador==0)
                            resultado[contador]=Long.toString(cursor.getRowId());
                        else
                        {
                            // si el campo que indica el contador en el array es un tipo float
                            if(cursor.getFieldType(campos[contador])==SqlJetValueType.FLOAT)
                                // cambia el resultado a string y lo guarda en resultado
                                resultado[contador] = Double.toString(cursor.getFloat(campos[contador]));
                            // Si es tipo integer
                            else if(cursor.getFieldType(campos[contador])==SqlJetValueType.INTEGER)
                                // cambia el resultado a string y lo guarda en resultado
                                resultado[contador] = Long.toString(cursor.getInteger(campos[contador]));
                            // Si es tipo text
                            else if(cursor.getFieldType(campos[contador])==SqlJetValueType.TEXT)
                                // Guarda en resultado el string
                                resultado[contador] = cursor.getString(campos[contador]);
                            // Aumentamos el valor del contador para que pase por todos los campos de la tabla
                        }
                        contador++;
                    }
                    tablaResultado.add(resultado);
                } 
                while(cursor.next());
            }
           
            this.bd.commit();
            this.bd.close();
            } 
            catch (SqlJetException ex) 
            {
                Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            }
        return tablaResultado;
    }
    
    /**
     * Método que busca registros según el valor pasado por el usuario
     * @param nombretabla Variable que contiene el nombre de la tabla donde se van a buscar datos
     * @param indice Variable que contiene el índice, por el cual se meterá el filtro
     * @param campos Variable que contiene los nombres de lso campos de la tabla
     * @param valor Variable que contiene un float con el valor por el cual se realizará la búsqueda
     * @return Devuelve una arraylist con los resultados de la consulta
     */
    public ArrayList<String[]> busquedaBD(String nombretabla, String indice, String[] campos, float valor)
    {
        int contador;
        String[] resultado;
        // Creamos el arraylist donde se guardará los resultados de la búsqueda
        ArrayList<String[]> tablaResultado = new ArrayList<String[]>();
        try {
            this.bd.open();
            this.bd.beginTransaction(SqlJetTransactionMode.READ_ONLY);
            this.table = this.bd.getTable(nombretabla);
            // Con esta función buscamos en la tabla, según su índice, el valor
            this.cursor = table.lookup(indice, valor);
            // devuelve el numero de campos en el registro actual
            // this.cursor.getFieldsCount();
            
            if (!cursor.eof()) 
            {
                do 
                {
                    // Inicializamos la variable a 0 antes de entrar en el segundo while
                    contador=0;
                    // Creamos un array de la longitud de los campos de la tabla
                    resultado = new String[campos.length];
                    // Mientras el contador sea menor que la longitud de campos de la tabla
                    while(contador<campos.length)
                    {
                        if(contador==0)
                            resultado[contador]=Long.toString(cursor.getRowId());
                        else
                        {
                            // si el campo que indica el contador en el array es un tipo float
                            if(cursor.getFieldType(campos[contador])==SqlJetValueType.FLOAT)
                                // cambia el resultado a string y lo guarda en resultado
                                resultado[contador] = Double.toString(cursor.getFloat(campos[contador]));
                            // Si es tipo integer
                            else if(cursor.getFieldType(campos[contador])==SqlJetValueType.INTEGER)
                                // cambia el resultado a string y lo guarda en resultado
                                resultado[contador] = Long.toString(cursor.getInteger(campos[contador]));
                            // Si es tipo text
                            else if(cursor.getFieldType(campos[contador])==SqlJetValueType.TEXT)
                                // Guarda en resultado el string
                                resultado[contador] = cursor.getString(campos[contador]);
                            // Aumentamos el valor del contador para que pase por todos los campos de la tabla
                        }
                        contador++;
                    }
                    tablaResultado.add(resultado);
                } 
                while(cursor.next());
            }
           
            this.bd.commit();
            this.bd.close();
            } 
            catch (SqlJetException ex) 
            {
                Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            }
        return tablaResultado;
    }

    /**
     * Método que busca registros según valores pasados por el usuario
     * @param nombretabla Variable que contiene el nombre de la tabla donde se van a buscar datos
     * @param indice Variable que contiene el índice, por el cual se meterá el filtro
     * @param campos Variable que contiene los nombres de lso campos de la tabla
     * @param A Variable que guarda uno de los valores a buscar
     * @param B Variable que guarda el segundo valor a buscar
     * @return 
     */
    public ArrayList<String[]> busquedaBD(String nombretabla, String indice,String[] campos, Object[] A, Object[] B)
    {
        int contador = 0;
        String[] resultado;
        // Creamos el arraylist donde se guardará los resultados de la búsqueda
        ArrayList<String[]> tablaResultado = new ArrayList<String[]>();
        try {
            this.bd.open();
            this.bd.beginTransaction(SqlJetTransactionMode.READ_ONLY);
            this.table = this.bd.getTable(nombretabla);
            // Función que por el cual, según los valores introducidos te hace la búsqueda
            this.cursor = table.scope(indice, A, B);
            // devuelve el numero de campos en el registro actual
            // this.cursor.getFieldsCount();
            
            if (!cursor.eof()) 
            {
                do 
                {
                    // Inicializamos la variable a 0 antes de entrar en el segundo while
                    contador=0;
                    // Creamos un array de la longitud de los campos de la tabla
                    resultado = new String[campos.length];
                    // Mientras el contador sea menor que la longitud de campos de la tabla
                    while(contador<campos.length)
                    {
                        if(contador==0)
                            resultado[contador]=Long.toString(cursor.getRowId());
                        else
                        {
                            // si el campo que indica el contador en el array es un tipo float
                            if(cursor.getFieldType(campos[contador])==SqlJetValueType.FLOAT)
                                // cambia el resultado a string y lo guarda en resultado
                                resultado[contador] = Double.toString(cursor.getFloat(campos[contador]));
                            // Si es tipo integer
                            else if(cursor.getFieldType(campos[contador])==SqlJetValueType.INTEGER)
                                // cambia el resultado a string y lo guarda en resultado
                                resultado[contador] = Long.toString(cursor.getInteger(campos[contador]));
                            // Si es tipo text
                            else if(cursor.getFieldType(campos[contador])==SqlJetValueType.TEXT)
                                // Guarda en resultado el string
                                resultado[contador] = cursor.getString(campos[contador]);
                            // Aumentamos el valor del contador para que pase por todos los campos de la tabla
                        }
                        contador++;
                    }
                    tablaResultado.add(resultado);
                } 
                while(cursor.next());
            }
           
            this.bd.commit();
            this.bd.close();
            } 
            catch (SqlJetException ex) 
            {
                Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            }
        return tablaResultado;
    }
    
    /**
     * Método para modificar algun registro con el método update, que no elimine e inserte uno nuevo
     * @param nombreTabla Variable que guarda el nombre de la tabla donde se va a modificar el registro
     * @param id Variable que guarda la clave primaria del registro que se va a modificar
     * @param campos Variable que contiene los datos nuevos que desea introducir el usuario
     */
    public void modificarRegistrosUpdate(String nombreTabla, int id, ArrayList campos)
    {
        try 
        {
            this.bd.open();
            this.bd.beginTransaction(SqlJetTransactionMode.WRITE);
            this.table = this.bd.getTable(nombreTabla);
            // Con esta función busco en la tabla el registro con esa id pasada por el usuario, al ser primaria nose repetirá
            this.cursor = table.lookup(table.getPrimaryKeyIndexName(), id);
            // Recorremos el arrayList hasta su número de campos
            for(int i = 0 ; i < campos.size() ; i++)
            {
                // Si el valor del campo es nulo
                if(campos.get(i) == null)
                    // Se quedan los datos que tenía en la base de datos, ya que solo se cambian aquellos que son datos nuevos
                    campos.set(i, this.cursor.getValue(i));
            }
            switch (campos.size())
            {
                //Según el número de elementos del arraylist se metera en un caso u otro y modificará los campos en la tabla
                case 1:
                    this.cursor.update(campos.get(0));break;
                case 2:
                    this.cursor.update(campos.get(0),campos.get(1));break;
                case 3: 
                    this.cursor.update(campos.get(0),campos.get(1),campos.get(2));break;
                case 4: 
                    this.cursor.update(campos.get(0),campos.get(1),campos.get(2), campos.get(3));break;
                case 5:
                    this.cursor.update(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4));break;
                case 6:
                    this.cursor.update(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5));break;
                case 7:
                    this.cursor.update(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5), campos.get(6));break;
                case 8:
                    this.cursor.update(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5), campos.get(6), campos.get(7));break;
                case 9:
                    this.cursor.update(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5), campos.get(6), campos.get(7),campos.get(8));break;
                case 10:
                    this.cursor.update(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5), campos.get(6), campos.get(7),campos.get(8), campos.get(9));break;
                case 11:
                    this.cursor.update(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5), campos.get(6), campos.get(7),campos.get(8), campos.get(9), campos.get(10));break;
                case 12:
                    this.cursor.update(campos.get(0),campos.get(1),campos.get(2), campos.get(3), campos.get(4), campos.get(5), campos.get(6), campos.get(7),campos.get(8), campos.get(9), campos.get(10), campos.get(11));break;
                case 13:
                default:
            }
            this.cursor.close();
            this.bd.commit();
            this.bd.close();            
        } 
        catch (SqlJetException ex) 
        {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
