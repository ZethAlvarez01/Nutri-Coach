package models;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Zeth
 */
public class PacienteValidar implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Paciente.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Paciente paciente=(Paciente)o;
        
        //Se valida que cada elemento de nuestro formulario no se encuentre en blanco
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "no_boleta", "required.no_boleta","Tu número de boleta o de trabajador es obligatorio");
        System.out.println("No encontre el numero de boleta");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "required.nombre","Tu nombre es obligatorio");
        System.out.println("NO ENCONTRE el nombre");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ap_uno", "required.ap_uno","Tu primer apellido es obligatorio");
        System.out.println("NO ENCONTRE ap_uno");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ap_dos", "required.ap_dos","Tu primer apellido es obligatorio");
        System.out.println("NO ENCONTRE ap_dos");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sexo", "required.sexo","Tu genero es obligatorio");
        System.out.println("NO ENCONTRE sexo");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "required.telefono","El número de contacto es obligatorio");
        System.out.println("NO ENCONTRE telefono");
        
         ////////////////////////////////////////////////////////////////   
        
      //validacion de nombre
      //vdalida que solo se encuentren caracteres alfabeticos minusculos y mayusculos y prohibe la entreda de digitos y caracteres especiales
        if (paciente.getNombre().matches("^[a-zA-Z]+$")){
           
            System.out.println("EL Nombre ES VALIDO");
        }
        else{
            System.out.println(paciente.getNombre());
            errors.rejectValue("nombre", "nombre.incorrect","El nombre no es valido"); //Si ninguna condición se cumple el nombre es invalido
        }
        
        
        ////////////////////////////////////////////////////////////////   
        
      //validacion de primer apellido
      //vdalida que solo se encuentren caracteres alfabeticos minusculos y mayusculos y prohibe la entreda de digitos y caracteres especiales
        if (paciente.getAp_uno().matches("^[a-zA-Z]+$")){
            System.out.println("EL primer apellido ES VALIDO");
        }
        else{
            errors.rejectValue("ap_uno", "ap_uno.incorrect","El primer apellido no es valido"); //Si ninguna condición se cumple el primer apellido  es invalido
        }
      
      ////////////////////////////////////////////////////////////////   
        
      //validacion de segundo apellido
      //vdalida que solo se encuentren caracteres alfabeticos minusculos y mayusculos y prohibe la entreda de digitos y caracteres especiales
        if (paciente.getAp_dos().matches("^[a-zA-Z]+$")){
            System.out.println("EL Segundo apellido ES VALIDO");
        }
        else{
            errors.rejectValue("ap_dos", "ap_dos.incorrect","El segundo apellido no es valido"); //Si ninguna condición se cumple el segundo apellido es invalido
        }
      
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha_n", "required.fecha_n","Tu fecha de nacimiento es obligatoria");
        System.out.println("NO ENCONTRE fecha_n");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "domicilio", "required.domicilio","Tu domicilio es obligatorio");
        System.out.println("NO ENCONTRE domicilio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correo", "required.correo","Tu correo es obligatorio");
        System.out.println("NO ENCONTRE correo");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña", "required.contraseña","Definir una contraseña es obligatorio");
        System.out.println("NO ENCONTRE contraseña");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña2", "required.contraseña2","Repetir tu contraseña es obligatorio");
        System.out.println("NO ENCONTRE contraseña2");
        
     /////////////////////////////////////////////////////////////   
        //Validación de telefono
        
        //valida telefonos con 8 y 10 digitos
        if (paciente.getTelefono().matches("\\d{10}")|| paciente.getTelefono().matches("\\d{8}")){
            System.out.println("EL TELEFONO ES VALIDO");
        }
        else{
            errors.rejectValue("telefono", "telefono.incorrect","El telefono no es valido"); //Si ninguna condición se cumple el telefono es invalido
        }
         
   ////////////////////////////////////////////////////////////////   
        
      //validacion de nombre
      
 /*       if (paciente.getNombre().matches("\\[A-Z]")){
            System.out.println("EL Nombre ES VALIDO");
        }
        else{
            errors.rejectValue("nombre", "nombre.incorrect","El nombre no es valido"); //Si ninguna condición se cumple el telefono es invalido
        }*/
      
        
        
        
      /*  if(!paciente.getContraseña().equals(paciente.getContraseña2())){
            errors.rejectValue("contraseña", "contraseña.incorrect","Las contraseñas no coinciden");
        }*/
        
    }
    
}
