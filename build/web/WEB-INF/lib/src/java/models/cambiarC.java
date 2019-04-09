package models;

import org.springframework.validation.BindingResult;

/**
 *
 * @author Nutri-Coach
 */
public class cambiarC {
    private String contraseña;
    private String contraseña2;

    public cambiarC() {
    }

    public cambiarC(String contraseña, String contraseña2) {
        this.contraseña = contraseña;
        this.contraseña2 = contraseña2;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

     public String getContraseña2() {
        return contraseña2;
    }

    public void setContraseña2(String contraseña2) {
        this.contraseña2 = contraseña2;
    }
    
    
}
