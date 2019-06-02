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

public class PdfView extends AbstractPdfView {
 private JdbcTemplate jdbcTemplate;
public PdfView() {

        
        Conexion conn = new Conexion();                                 //Instacia a la conexión de base de datos
        this.jdbcTemplate = new JdbcTemplate(conn.conectar());         //Instacia a la conexión de base de datos
        
    }
 @Override
 protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request,
   HttpServletResponse response) throws Exception {
  
  //response.setHeader("Content-Disposition", "attachment; filename=\"expedienteNutri.pdf\"");
  
  @SuppressWarnings("unchecked")
    List<Map<String, Object>> rows =  (List<Map<String, Object>>) model.get("expediente");
  
  
  
            
            
  
  
  
  
  
  
  
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
  Chunk chunk = new Chunk("Historial Clínico");
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
  
  
   table= new Table(2);
   table.getDefaultCell().setBorder(0);
   table.setBorder(0);
   
   Cell cell = new Cell(new Phrase("¿Realiza algún tipo de actividad física?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
   
   
   String act_f="No";
   if(row.get("act_f").toString().equals(true)){
       act_f="Sí";
   }
   
   cell = new Cell(new Phrase(act_f));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
    table.addCell(cell);
    
    
 cell = new Cell(new Phrase("¿Qué tipo de actividad física?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
   
   cell = new Cell(new Phrase(row.get("tipo_act").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
   
 
   cell = new Cell(new Phrase("¿Con que frecuencia realiza actividad física?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);
   
 
 
   cell = new Cell(new Phrase(row.get("frecuencia").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
   
   cell = new Cell(new Phrase("¿Tiene algún padecimiento o enfermedad, cuál?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);
   
   cell = new Cell(new Phrase(row.get("padecimiento").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
     table.addCell(cell);
   
   cell = new Cell(new Phrase("¿Tiene antecedentes familiares con padecimientos o enfermedades crónicas o hereditarias, cuál?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
   
   cell = new Cell(new Phrase(row.get("antec_hf").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
    
   
   cell = new Cell(new Phrase("¿Consume tabaco?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
   
   String tabaco="No";
   if(row.get("tabaco").toString().equals(true)){
       tabaco="Sí";
   }
   
   cell = new Cell(new Phrase(tabaco));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
  
   
   cell = new Cell(new Phrase("¿Con qué frecuencia?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("frec_tabaco").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
    
   cell = new Cell(new Phrase("¿Consume bebidas alcohólicas?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
   
   
   String alcohol="No";
   if(row.get("alcohol").toString().equals(true)){
       alcohol="Sí";
   }
   
   cell = new Cell(new Phrase(alcohol));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
   

   
   
   cell = new Cell(new Phrase("¿Con qué frecuencia?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
   
   cell = new Cell(new Phrase(row.get("frec_alcohol").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     table.addCell(cell);
    
 
   
   chapter.add(table);
  
   document.add(chapter);
  
   
   /////////////////// HOJA 2
  chunk = new Chunk("Estado Ginecológico");
   titulo = new Paragraph(chunk);
   titulo.setAlignment(titulo.ALIGN_CENTER);
           
  chapter = new Chapter(titulo,2);
  chapter.setNumberDepth(0);
  
 
  
  
  table= new Table(2);
   table.getDefaultCell().setBorder(0);
   table.setBorder(0);
   
   cell = new Cell(new Phrase("¿Lleva una vida sexual activa?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
   
   
   String act_sex="No";
   if(row.get("act_sex").toString().equals(true)){
       act_sex="Sí";
   }
   
   cell = new Cell(new Phrase(act_sex));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
    table.addCell(cell);
    
    
     cell = new Cell(new Phrase("¿Se encuentra en estado de gestación?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
   
   
   String edo_gestacion="No";
   if(row.get("edo_gestacion").toString().equals(true)){
       edo_gestacion="Sí";
   }
   
   cell = new Cell(new Phrase(edo_gestacion));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
    table.addCell(cell);
  
   cell = new Cell(new Phrase("¿Cuánto tiempo lleva gestando?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("T_Gestacion").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
   
   cell = new Cell(new Phrase("¿Usa algún método anticonceptivo, cuál?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("m_anticonceptivo").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
   
   
     cell = new Cell(new Phrase("¿Lleva alguna terapia de reemplazo hormonal?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
   
   
   String terapia_rh="No";
   if(row.get("terapia_rh").toString().equals(true)){
       terapia_rh="Sí";
   }
   
   cell = new Cell(new Phrase(terapia_rh));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
    table.addCell(cell);
   
    
    
      cell = new Cell(new Phrase("¿Cuál?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("TipoTerapia").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
    
    cell = new Cell(new Phrase("Dosis"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("dosis").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
   
   
  
  chapter.add(table);
  
  

   
  
  
  
  
  
  
  document.add(chapter);
  
  
  
  
  
  
  
   
  /////////////////////HOJA 3
  chunk = new Chunk("Exploración Física");
    titulo = new Paragraph(chunk);
   titulo.setAlignment(titulo.ALIGN_CENTER);
           
   chapter = new Chapter(titulo,3);
  chapter.setNumberDepth(0);
  
  table= new Table(2);
   table.getDefaultCell().setBorder(0);
   table.setBorder(0);
  
  cell = new Cell(new Phrase("Peso"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("peso").toString()+ " Kg"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
   
   
   
    cell = new Cell(new Phrase("Estatura"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("talla").toString() +" cm"));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
   
   
    cell = new Cell(new Phrase("Temperatura"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("temperatura").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
   
   
   cell = new Cell(new Phrase("P/A"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("tension_art").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase("F/C"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("frec_cardiaca").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
  chapter.add(table);
  
  chunk = new Chunk("Medidas antropométricas");
    titulo = new Paragraph(chunk);
   titulo.setAlignment(titulo.ALIGN_CENTER);
  
   chapter.add(titulo);
  
  table= new Table(2);
   table.getDefaultCell().setBorder(0);
   table.setBorder(0);
  
  cell = new Cell(new Phrase("Cuello"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("cuello").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
   
   
    cell = new Cell(new Phrase("Tórax"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("torax").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
   cell = new Cell(new Phrase("Brazo"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("brazo").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
    cell = new Cell(new Phrase("Antebrazo"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("antebrazo").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
  
    cell = new Cell(new Phrase("Abdomen"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("abdomen").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
  
    cell = new Cell(new Phrase("Cadera"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("cadera").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
      cell = new Cell(new Phrase("Muslo"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("muslo").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
   cell = new Cell(new Phrase("Pierna"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("pierna").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
   chapter.add(table);
  
  
     chunk = new Chunk("Aspectos generales");
   titulo = new Paragraph(chunk);
   titulo.setAlignment(titulo.ALIGN_CENTER);
   chapter.add(titulo);
   
  table = new Table(1);
     table.getDefaultCell().setBorder(0);
   table.setBorder(0);
   cell = new Cell(new Phrase(row.get("aspect_grles").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
  chapter.add(table);
  
  
  
  
  
  document.add(chapter);
  
  
  ///////////////HOJA 4
  chunk = new Chunk("Indicadores Dietéicos");
  titulo = new Paragraph(chunk);
   titulo.setAlignment(titulo.ALIGN_CENTER);
           
  chapter = new Chapter(titulo,4);
  chapter.setNumberDepth(0);
  
  table= new Table(2);
   table.getDefaultCell().setBorder(0);
   table.setBorder(0);
   
   cell = new Cell(new Phrase("¿Se ha sometido algún tratamiento nutricional anteriormente?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
   
   
   String tratamiento_n="No";
   if(row.get("tratamiento_n").toString().equals(true)){
       tratamiento_n="Sí";
   }
   
   cell = new Cell(new Phrase(tratamiento_n));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
    table.addCell(cell);
  
   cell = new Cell(new Phrase("¿Qué tipo de tratamiento?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("tratamiento").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
   
   cell = new Cell(new Phrase("¿Por cuánto tiempo?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("tiempo").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
   cell = new Cell(new Phrase("¿Cuántas ingestas de comida tiene al día?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("CantidadIngesta").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
   
   
   
   cell = new Cell(new Phrase("¿Consume golosinas entre comidas?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
   
   
   String postre="No";
   if(row.get("postre").toString().equals(true)){
       postre="Sí";
   }
   
   cell = new Cell(new Phrase(postre));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
    table.addCell(cell);
   
   
   
    
     cell = new Cell(new Phrase("¿Cuáles?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("golosinas").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
   
    
    
   
      
   cell = new Cell(new Phrase("¿Tiene algún horario de comida?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
   
   
   String HorarioComida="No";
   if(row.get("HorarioComida").toString().equals(true)){
       HorarioComida="Sí";
   }
   
   cell = new Cell(new Phrase(HorarioComida));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
    table.addCell(cell);
    
    
    
    
     cell = new Cell(new Phrase("¿Cuáles?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("HorariosComida").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
   
   
    
    
   cell = new Cell(new Phrase("Tipo Alimentos de su gusto"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   cell.setColspan(2);
   
   table.addCell(cell);
   
   String dulce= "";
   System.out.println(row.get("dulce").toString());
  if(row.get("dulce").toString().equals("true")){
       dulce="x";
       
   }
   String amarga= "";
     System.out.println(row.get("amarga").toString());
  if(row.get("amarga").toString().equals("true")){
       amarga="x";
   }
  
   String salada= "";
     System.out.println(row.get("salada").toString());
  if(row.get("salada").toString().equals("true")){
       salada="x";
   }
  
    String picante= "";
      System.out.println(row.get("picante").toString());
  if(row.get("picante").toString().equals("true")){
       picante="x";
   }
  
    String acida= "";
      System.out.println(row.get("acida").toString());
  if(row.get("acida").toString().equals("true")){
       acida="x";
   }
  
   cell = new Cell(new Phrase("Dulce["+dulce+" ] Amarga["+amarga+" ] Salada["+salada+" ] Picante["+picante+" ] Ácida["+acida+" ]"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   cell.setColspan(2);
   table.addCell(cell);
   
   
   cell = new Cell(new Phrase("¿Tiene alergias a algun alimento, cúal?"));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
   
   table.addCell(cell);
  
  
   cell = new Cell(new Phrase(row.get("alergias").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
  chapter.add(table);
  
  document.add(chapter);
  
  
   ///////////////HOJA 5
    chunk = new Chunk("Diagnostico Clínico");
   titulo = new Paragraph(chunk);
   titulo.setAlignment(titulo.ALIGN_CENTER);
           
   chapter = new Chapter(titulo,5);
  chapter.setNumberDepth(0);
  
  
  
   chunk = new Chunk("Observaciones");
   titulo = new Paragraph(chunk);
   titulo.setAlignment(titulo.ALIGN_CENTER);
   chapter.add(titulo);
  
   table = new Table(1);
     table.getDefaultCell().setBorder(0);
   table.setBorder(0);
   cell = new Cell(new Phrase(row.get("observaciones").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
  chapter.add(table);
  
   chunk = new Chunk("Recomendaciones");
   titulo = new Paragraph(chunk);
   titulo.setAlignment(titulo.ALIGN_CENTER);
   chapter.add(titulo);
   
   table = new Table(1);
     table.getDefaultCell().setBorder(0);
   table.setBorder(0);
   cell = new Cell(new Phrase(row.get("recomendaciones").toString()));
   cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   table.addCell(cell);
  
  chapter.add(table);
  
  
  
   chunk = new Chunk("Documento avalado por");
   titulo = new Paragraph(chunk);
   titulo.setAlignment(titulo.ALIGN_CENTER);
   chapter.add(titulo);
  
  
  
   
   
  
    for(Map<String, Object> pacientes : paciente){
        sql = "select nombre,ap_uno,ap_dos,no_cedula from nutriologo where no_cedula=" +pacientes.get("no_cedula").toString() ;   
          List<Map<String, Object>> nutriologo = this.jdbcTemplate.queryForList(sql);
          
          
          for(Map<String, Object> nutriologos : nutriologo){
       chunk = new Chunk("Nutriólogo:"+nutriologos.get("nombre").toString() +" "+ nutriologos.get("ap_uno").toString() +  " "+  nutriologos.get("ap_dos").toString()+" No.Cédula: "+nutriologos.get("no_cedula").toString());
   titulo = new Paragraph(chunk);
   titulo.setAlignment(titulo.ALIGN_CENTER);
   chapter.add(titulo);
        
      }
    }
   
        
  
  
  
  
  
  
  document.add(chapter);
  
  
 
  
  
  
  
  
  
  
  }
  
  


   
 
  
  
 

            
            
              
         
  
 }

}
