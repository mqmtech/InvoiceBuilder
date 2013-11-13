/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturasapp.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import storeDataModel.Invoice;
import storeDataModel.Product;
import storeDataModel.ProductRelation;

/**
 *
 * @author CiberXtrem
 */
public class HTMLManager {

    	public static void main(String args[]) throws FileNotFoundException, IOException{
		//createHTML();
		//getHTML();

                Invoice invoice = new Invoice("Matricula", "Modelo", 100.00f, 18.00f, 18.00f, 118.00f);
                

                invoice.addProductRelation(new ProductRelation(new Product("Code01","Descripcion larga porque el producto requiere mas informacion", 1), 1, 10));
                invoice.addProductRelation(new ProductRelation(new Product("Code02", "Descripcion corta", 10), 2, 20));
                invoice.addProductRelation(new ProductRelation(new Product("Code03", "Descripcion medianada es suficiente", 17), 3, 40));
                generateHistory(invoice, null);
	}

        public static void executeHTML(String path){
            File htmlOut = new File(path);
            Runtime loader = Runtime.getRuntime();
	    String parameters[] = {"cmd", "/c", "start", htmlOut.getAbsolutePath()};
        try {
            loader.exec(parameters);
        } catch (IOException ex) {
            Logger.getLogger(HTMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        public static File generateHistory(Invoice p_invoice, String pathOut) throws IOException{
            //Get File
            File htmlIn = new File("history\\templates\\default.html");
            Document doc = Jsoup.parse(htmlIn, "UTF-8");
	
            //Edit HTML

            //Inicio

            Date date = p_invoice.getFecha();
            String dateFormat = "dd/MM/yyyy";
            SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);
            
            

            String facturaInicio=
                    "<table border=\"0\">"
                    +"<tr>"
                    +"<th class=\"factura\"></th>"
                    +"<th class=\"fecha\"></th>"
                    +"<th class=\"matricula\"></th>"
                    +"<thclass=\"modelo\"></th>"
                    +"</tr>"

                    +"<tr>"
                    +"<td>"+p_invoice.getId()+"</td>"
                    +"<td>"+dFormat.format(date)+"</td>"
                    +"<td>"+p_invoice.getMatricula()+"</td>"
                    +"<td>"+p_invoice.getModelo()+"</td>"
                    +"</tr>"

                    +"</table>";

            Element inicio = doc.getElementById("inicio");
            inicio.html(facturaInicio);

            //Cesta
            String facturaCesta=
                    "<table border=\"0\">"
                    +"<tr>"
                    +"<th class=\"code\"></th>"
                    +"<th class=\"cant\"></th>"
                    +"<th class=\"desc\"></th>"
                    +"<th class=\"precio\"></th>"
                    +"<th class=\"imp\"></th>"
                    +"</tr>";

            ArrayList<ProductRelation> rels =  p_invoice.getProductRelations();
            Iterator it = rels.iterator();

            while(it.hasNext()){
                ProductRelation rel = (ProductRelation) it.next();
                    facturaCesta+="<tr>"
                    +"<td>"+ rel.getProducto().getCodigo() +"</td>"
                    +"<td>"+ rel.getCantidad() +"</td>"
                    +"<td>"+ rel.getDescripcion() +"</td>"
                    +"<td>"+ rel.getPrecio() +"</td>"
                    +"<td>"+ rel.getImporte() +"</td>"
                    +"</tr>";
            }
            facturaCesta+="</table>";

            Element cesta = doc.getElementById("cesta");
            cesta.html(facturaCesta);

            //resumen
            String facturaResumen=
                    "<table border=\"0\">"
                    +"<tr>"
                    +"<th class=\"base\"></th>"
                    +"<th class=\"iva\"></th>"
                    +"<th class=\"impiva\"></th>"
                    +"<th class=\"total\"></th>"
                    +"</tr>"

                    +"<tr>"
                    +"<td>"+p_invoice.getBaseImponible()+"</td>"
                    +"<td>"+p_invoice.getIva()*100+"</td>"
                    +"<td>"+p_invoice.getImporteIva()+"</td>"
                    +"<td>"+p_invoice.getTotal()+"</td>"
                    +"</tr>"

                    +"</table>";

            Element resumen = doc.getElementById("resumen");
            resumen.html(facturaResumen);

            //End Edit HTML

            //Save File
            File htmlOut = pathOut == null ? new File("history\\factura.html") : new File(pathOut);
            FileManager.setContents(htmlOut, doc.toString(), false);

            Runtime loader = Runtime.getRuntime();
	    String parameters[] = {"cmd", "/c", "start", htmlOut.getAbsolutePath()};
	    loader.exec(parameters);

            return htmlOut;
        }

         public static File generateHistoryFull(Invoice p_invoice, String pathOut) throws IOException{
            //Get File
            File htmlIn = new File("history\\templates\\default.html");
            Document doc = Jsoup.parse(htmlIn, "UTF-8");

            //Edit HTML

            //Inicio
            String facturaInicio=
                    "<table border=\"0\">"
                    +"<tr>"
                    +"<th>FACTURA</th>"
                    +"<th>FECHA</th>"
                    +"<th>MATRICULA</th>"
                    +"<th>MODELO</th>"
                    +"</tr>"

                    +"<tr>"
                    +"<td>"+p_invoice.getId()+"</td>"
                    +"<td>"+p_invoice.getFecha()+"</td>"
                    +"<td>"+p_invoice.getMatricula()+"</td>"
                    +"<td>"+p_invoice.getModelo()+"</td>"
                    +"</tr>"

                    +"</table>";

            Element inicio = doc.getElementById("inicio");
            inicio.html(facturaInicio);

            //Cesta
            String facturaCesta=
                    "<table border=\"0\">"
                    +"<tr>"
                    +"<th class=\"code\">CODIGO</th>"
                    +"<th class=\"desc\">DESCRIPCION</th>"
                    +"<th class=\"imp\">PRECIO</th>"
                    +"<th class=\"cant\">CANT.</th>"
                    +"<th class=\"imp\">IMPORTE</th>"
                    +"</tr>";

            ArrayList<ProductRelation> rels =  p_invoice.getProductRelations();
            Iterator it = rels.iterator();
            while(it.hasNext()){
                ProductRelation rel = (ProductRelation) it.next();
                    facturaCesta+="<tr>"
                    +"<td>"+ rel.getProducto().getCodigo() +"</td>"
                    +"<td>"+ rel.getDescripcion() +"</td>"
                    +"<td>"+ rel.getPrecio() +"</td>"
                    +"<td>"+ rel.getCantidad() +"</td>"
                    +"<td>"+ rel.getImporte() +"</td>"
                    +"</tr>";
            }
            facturaCesta+="</table>";

            Element cesta = doc.getElementById("cesta");
            cesta.html(facturaCesta);

            //resumen
            String facturaResumen=
                    "<table border=\"0\">"
                    +"<tr>"
                    +"<th>BASE IMP.</th>"
                    +"<th>% IVA</th>"
                    +"<th>IMPORTE IVA</th>"
                    +"<th>TOTAL FACTURA</th>"
                    +"</tr>"

                    +"<tr>"
                    +"<td>"+p_invoice.getBaseImponible()+"</td>"
                    +"<td>"+p_invoice.getIva()*100+"</td>"
                    +"<td>"+p_invoice.getImporteIva()+"</td>"
                    +"<td>"+p_invoice.getTotal()+"</td>"
                    +"</tr>"

                    +"</table>";

            Element resumen = doc.getElementById("resumen");
            resumen.html(facturaResumen);

            //End Edit HTML

            //Save File
            File htmlOut = pathOut == null ? new File("history\\factura.html") : new File(pathOut);
            FileManager.setContents(htmlOut, doc.toString(), false);

            Runtime loader = Runtime.getRuntime();
	    String parameters[] = {"cmd", "/c", "start", htmlOut.getAbsolutePath()};
	    loader.exec(parameters);

            return htmlOut;
        }

	public static void getHTML() throws IOException{
                File htmlIn = new File("history\\templates\\default.html");
		System.out.println(htmlIn.getAbsolutePath());
		Document doc = Jsoup.parse(htmlIn, "UTF-8");
		System.out.println(doc.toString());

                File htmlOut = new File("history\\factura.html");
		FileManager.setContents(htmlOut, doc.toString(), false);
	}

	public static void createHTML() throws FileNotFoundException, IOException{
		String html = "<html><head><title>First parse</title></head>"
			  + "<body><p>Parsed HTML into a doc.</p>" +
			  		"<div id=cesta/>" +
			  		"</body></html>";
		Document doc = Jsoup.parse(html);

		Element cesta = doc.getElementById("cesta");
		cesta.html("<label id=codigo>Codigo</label> <label id=cantidad>Cantidad</label>");

		Element codigoLabel = doc.getElementById("codigo");
		codigoLabel.append(" Modificado");

		System.out.println(doc.toString());

		File htmlFILE = new File("factura.html");
		FileManager.setContents(htmlFILE, doc.toString(), false);

		Runtime loader = Runtime.getRuntime();
	    String parameters[] = {"cmd", "/c", "start", htmlFILE.getAbsolutePath()};
	    loader.exec(parameters);
	}
}
