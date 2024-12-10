package Evolutivo;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AgenteEvolutivoDatos extends Agent {

    protected void setup(){
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                double[] x = {23, 26, 30, 34, 43, 48, 52, 57, 58};
                double[] y = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};


                // Crear un objeto para representar el dataset
                DataSetEvolutivo dataSetEvolutivo = new DataSetEvolutivo(x, y);

                try{
                    //serializacion del objeto
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
                    objectStream.writeObject(dataSetEvolutivo);
                    objectStream.close();

                    //convertir a arreglo de byte para enviarlo
                    byte[] serializedObject = byteStream.toByteArray();
                    ACLMessage mensaje = new ACLMessage(ACLMessage.REQUEST);
                    mensaje.addReceiver(getAID("AgenteEvolutivo")); // Nombre del receptor
                    mensaje.setByteSequenceContent(serializedObject); // Usar byte array como contenido

                    send(mensaje);
                    System.out.println("Mensaje enviado a EvolutiveAgent1:");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}