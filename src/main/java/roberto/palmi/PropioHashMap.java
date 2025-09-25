package roberto.palmi;

public class PropioHashMap<c, v> {
    private int capacity = 256;
    private Object[] claves;
    private Object[] valores;

    public PropioHashMap (int capacity){
        this.capacity = capacity;
        claves = new Object[capacity];
        valores = new Object[capacity];
    }

    private int getIndex(c key){
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(c clave, v valor){
        int index = getIndex(clave);

        while(claves[index] != null && !claves[index].equals(clave)){
            index = (index + 1) % capacity;
        }
        claves[index] = clave;
        valores[index] = valor;
    }

    public v get(c clave){
        int index = getIndex(clave);
        while(claves[index] != null){
            if(claves[index].equals(clave)){
                return (v) valores[index];
            }
            index = (index + 1) % capacity;
        }
        return null;
    }

    public void remove(c clave){
        int index = getIndex(clave);
        while(claves[index] != null){
            if(claves[index].equals(clave)){
                claves[index] = null;
                valores[index] = null;
                return;
            }
            index = (index + 1) % capacity;
        }
    }


}
