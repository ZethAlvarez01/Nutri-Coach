package models;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Zeth
 */
public class PsicologoValidar implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Psicologo.class.isAssignableFrom(type);
    }

    @Override
      public void validate(Object o, Errors errors) {
        Psicologo psicologo=(Psicologo)o;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "no_cedula","required.no_cedula","el numero de cedula es oligatorio");
        System.out.println("NO ENCONTRE la cedula");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "no_empleado","required.no_empleado","Tu no. de empleado es obligatorio");
        System.out.println("NO ENCONTRE EL NUMERO DE EMPLEADO");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre","required.nombre","Tu nombre es obligatorio");
        System.out.println("NO ENCONTRE EL Nombre");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ap_uno","required.ap_uno","Tu primer apellido es obligatorio");
        System.out.println("NO ENCONTRE EL primer apellido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ap_dos","required.ap_dos","Tu primer apellido es obligatorio");
        System.out.println("NO ENCONTRE EL primer apellido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono","required.telefono","Ingresa un número de contacto");
        System.out.println("NO ENCONTRE EL telefono");
        
         ////////////////////////////////////////////////////////////////   
        
      //validacion de nombre
      //vdalida que solo se encuentren caracteres alfabeticos minusculos y mayusculos y prohibe la entreda de digitos y caracteres especiales
        if (psicologo.getNombre().matches("^[a-zA-Z]+$")){
           
            System.out.println("EL Nombre ES VALIDO");
        }
        else{
            System.out.println(psicologo.getNombre());
            errors.rejectValue("nombre", "nombre.incorrect","El nombre no es valido"); //Si ninguna condición se cumple el nombre es invalido
        }
        
        
        ////////////////////////////////////////////////////////////////   
        
      //validacion de primer apellido
      //vdalida que solo se encuentren caracteres alfabeticos minusculos y mayusculos y prohibe la entreda de digitos y caracteres especiales
        if (psicologo.getAp_uno().matches("^[a-zA-Z]+$")){
            System.out.println("EL primer apellido ES VALIDO");
        }
        else{
            errors.rejectValue("ap_uno", "ap_uno.incorrect","El primer apellido no es valido"); //Si ninguna condición se cumple el primer apellido  es invalido
        }
      
      ////////////////////////////////////////////////////////////////   
        
      //validacion de segundo apellido
      //vdalida que solo se encuentren caracteres alfabeticos minusculos y mayusculos y prohibe la entreda de digitos y caracteres especiales
        if (psicologo.getAp_dos().matches("^[a-zA-Z]+$")){
            System.out.println("EL Segundo apellido ES VALIDO");
        }
        else{
            errors.rejectValue("ap_dos", "ap_dos.incorrect","El segundo apellido no es valido"); //Si ninguna condición se cumple el segundo apellido es invalido
        }
      
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correo","required.correo","Tu correo es obligatorio");
        System.out.println("NO ENCONTRE EL correo");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña","required.contraseña","Definir una contraseña es obligatorio");
        System.out.println("NO ENCONTRE la contraseña1");
       //System.out.println(nutriologo.getContraseña());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña2","required.contraseña2","Repetir tu contraseña es obligatorio");
        System.out.println("NO ENCONTRE la contraseña2");
        System.out.println(psicologo.getContraseña2());
  
        /////////////////////////////////////////////////////////////   
        //Validación de telefono
        
        //valida telefonos con 8 y 10 digitos
        if (psicologo.getTelefono().matches("\\d{10}")|| psicologo.getTelefono().matches("\\d{8}")){
            System.out.println("EL TELEFONO ES VALIDO");
        }
        else{
            errors.rejectValue("telefono", "telefono.incorrect","El telefono no es valido"); //Si ninguna condición se cumple el telefono es invalido
        }
       
        
       
  /*      if(persona.getPais()==0){
            errors.rejectValue("pais", "reguired.pais","Seleccione un pais");        }
        
      }
       */
        
    }
    
}
