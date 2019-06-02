/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author jms-m
 */
import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.expediente;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseField;
import com.lowagie.text.pdf.PdfBorderDictionary;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PdfPCell;

import com.lowagie.text.pdf.PdfWriter;

import com.lowagie.text.pdf.RadioCheckField;
import java.awt.Color;
import java.io.IOException;
import models.Conexion;
import org.springframework.jdbc.core.JdbcTemplate;

public class PdfViewDieta extends AbstractPdfView {
 private JdbcTemplate jdbcTemplate;
public PdfViewDieta() {

        
        Conexion conn = new Conexion();                                 //Instacia a la conexión de base de datos
        this.jdbcTemplate = new JdbcTemplate(conn.conectar());         //Instacia a la conexión de base de datos
        
    }
 @Override
 protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request,
   HttpServletResponse response) throws Exception {
  
  //response.setHeader("Content-Disposition", "attachment; filename=\"expedienteNutri.pdf\"");
  
  @SuppressWarnings("unchecked")
    List<Map<String, Object>> rows =  (List<Map<String, Object>>) model.get("Dieta");
  
  
  
            
            
  
  
  
  
  
  
  
  for(Map<String, Object> row : rows){
                    
  
  
  
  String sql = "select id_expediente from hojaexpediente where id_hojaExpediente=" + row.get("id_hojaExpediente").toString();   
        List datosL2 = this.jdbcTemplate.queryForList(sql);  
        String idExpediente=datosL2.get(0).toString().substring(15, datosL2.get(0).toString().length()-1);
     
  sql = "select no_boleta from expediente where id_expediente=" + idExpediente;   
         datosL2 = this.jdbcTemplate.queryForList(sql); 
         String noBoleta = datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length()-1);
         
  sql = "select nombre,ap_uno,ap_dos,edad,sexo,no_boleta,no_cedula from paciente where no_boleta=" + noBoleta;   
          List<Map<String, Object>> paciente = this.jdbcTemplate.queryForList(sql); 
         
         
         
///////HOJA 1 DEL EXPEDIENTE
  Chunk chunk = new Chunk("Plan de Alimentación Personaizado");
  Paragraph titulo = new Paragraph(chunk);
   titulo.setAlignment(titulo.ALIGN_CENTER);
   
           
  Chapter chapter = new Chapter(titulo,1);
  chapter.setNumberDepth(0);
  
  Paragraph prg = new Paragraph();
  
   Image image;
            try {
                image = Image.getInstance("C:/Users/jms-m/Desktop/PDF/web/WEB-INF/resource/imagenes/logo-sep.png");  
                image.setAlignment(Element.ALIGN_TOP);
                image.scaleAbsolute(100, 50);
                prg.add(image);
             //   chapter.add(image);
                 image = Image.getInstance("C:/Users/jms-m/Desktop/PDF/web/WEB-INF/resource/imagenes/latecnica.png");  
                image.setAlignment(Element.ALIGN_RIGHT);
                image.scaleAbsolute(100, 50);
                prg.add(image);
                chapter.add(prg);
            } catch (BadElementException ex) {
                System.out.println("Image BadElementException" +  ex);
            } catch (IOException ex) {
                System.out.println("Image IOException " +  ex);
            }
  
  
  

  
  
  Table table = new Table(2);
    table.getDefaultCell().setBorder(0);
   table.setBorder(0);
    for(Map<String, Object> pacientes : paciente){
        Cell cell = new Cell(new Phrase("Nombre:"+pacientes.get("nombre").toString() +" "+ pacientes.get("ap_uno").toString() +  " "+  pacientes.get("ap_dos").toString()+ " No. Boleta: "+   pacientes.get("no_boleta").toString()));
        cell.setColspan(2);
        table.addCell(cell);
        cell=new Cell(new Phrase("Edad:"+pacientes.get("edad").toString() + " Sexo: "+pacientes.get("sexo").toString() +" Fecha: "+row.get("fecha_ini").toString()));
        cell.setColspan(2);
        table.addCell(cell);
        
         response.setHeader("Content-Disposition", "attachment; filename=\"expediente"+pacientes.get("no_boleta").toString()+row.get("fecha_ini").toString()+".pdf\"");
    }
  
  
  
  chapter.add(table);
  
  
   table= new Table(4);
   table.getDefaultCell().setBorder(0);
   table.setBorder(0);
   
   Cell cell = new Cell(new Phrase("Tiempo de Comida"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
   
   
  
   
   cell = new Cell(new Phrase("Opción 1"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
    table.addCell(cell);
    
    
 cell = new Cell(new Phrase("Opción 2"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
   
   cell = new Cell(new Phrase("Opción 3"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
   
 
   cell = new Cell(new Phrase("Desayuno PONER HORARIO AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);
   
 
 
   cell = new Cell(new Phrase("PONER OPCIONES DE COMIDA"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
   
   cell = new Cell(new Phrase("PONER OPCION 2 AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);
   
   cell = new Cell(new Phrase("PONER OPCION 3 AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);
   
   cell = new Cell(new Phrase("Colación Matutina PONER HORARIO AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
   

    cell = new Cell(new Phrase("PONER OPCIONES DE COMIDA"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
   
   cell = new Cell(new Phrase("PONER OPCION 2 AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);
   
   cell = new Cell(new Phrase("PONER OPCION 3 AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);
    
    cell = new Cell(new Phrase("Comida PONER HORARIO AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
   
     
       cell = new Cell(new Phrase("PONER OPCIONES DE COMIDA"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
   
   cell = new Cell(new Phrase("PONER OPCION 2 AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);
   
   cell = new Cell(new Phrase("PONER OPCION 3 AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);  
     
        cell = new Cell(new Phrase("Colación Vespertinda PONER HORARIO AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);

     
     
          cell = new Cell(new Phrase("PONER OPCIONES DE COMIDA"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
   
   cell = new Cell(new Phrase("PONER OPCION 2 AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);
   
   cell = new Cell(new Phrase("PONER OPCION 3 AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);  
     
     
        cell = new Cell(new Phrase("Cena PONER HORARIO AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);

     
     
          cell = new Cell(new Phrase("PONER OPCIONES DE COMIDA"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
   
   cell = new Cell(new Phrase("PONER OPCION 2 AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);
   
   cell = new Cell(new Phrase("PONER OPCION 3 AQUI"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);  
     
     
     
     
   chapter.add(table);
  
   document.add(chapter);
  
   
  /*
  
    for(Map<String, Object> pacientes : paciente){
        sql = "select nombre,ap_uno,ap_dos,no_cedula from nutriologo where no_cedula=" +pacientes.get("no_cedula").toString() ;   
          List<Map<String, Object>> nutriologo = this.jdbcTemplate.queryForList(sql);
          
          
          for(Map<String, Object> nutriologos : nutriologo){
       chunk = new Chunk("Nutriólogo:"+nutriologos.get("nombre").toString() +" "+ nutriologos.get("ap_uno").toString() +  " "+  nutriologos.get("ap_dos").toString()+" No.Cédula: "+nutriologos.get("no_cedula").toString());
   titulo = new Paragraph(chunk);
   titulo.setAlignment(titulo.ALIGN_CENTER);
   chapter.add(titulo);
        
      }
    }*/
   
        
  
  
  
  
  
  
  document.add(chapter);
  
  
 
  
  
  
  
  
  
  
  }
  
  


   
 
  
  
 

            
            
              
         
  
 }

}
