package es.pgv.Servidor;

public class Aerogenerador {
   
	private int id;
    private boolean estado;
    private double velocidad; 

    public Aerogenerador(int id, boolean estado, double velocidad) {
        this.id = id;
        this.estado = estado;
        configurarVelocidad();
    }

    public int getId() {
        return id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
        configurarVelocidad();
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public void configurarVelocidad() {
        if (estado) {
            velocidad = 11.0;
        } else {
            velocidad = 0.0;
        }
    }

    
	@Override
	public String toString() {
		String estadoStr = (estado) ? "Activado" : "Desactivado";
		return "Aerogenerador [ "+id + " , Estado: " + estadoStr + ", Velocidad: " + velocidad +" ]";
	}
    

}