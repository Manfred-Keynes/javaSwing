
package logic;

/**
 *
 * @author mfer_
 */
public class Puesto {
    
    private int id_puesto;
    private String puesto;

    public Puesto() {
        this.id_puesto = 0;
        this.puesto = "";
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public String toString(){
        
        return getPuesto();
    }
}
