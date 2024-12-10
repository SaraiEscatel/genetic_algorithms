package Evolutivo;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class AgenteEvolutivoReceptor extends Agent {

    public void setup(){
        System.out.println("Hola soy el agente: " + getLocalName());
        System.out.println("Mi trabajo es sugerir betas hasta encontrar la mas optima");

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage mensaje = receive();

                if (mensaje != null){
                    System.out.println("Mensaje Recibido");
                    try{
                        byte[] objetoSerializado = mensaje.getByteSequenceContent();
                        ByteArrayInputStream byteStream = new ByteArrayInputStream(objetoSerializado);
                        ObjectInputStream objectStream = new ObjectInputStream(byteStream);

                        DataSetEvolutivo dataSetEvolutivo = (DataSetEvolutivo) objectStream.readObject();
                        objectStream.close();

                        System.out.println("EvolutiveAgent receptor recibio el mensaje:");
                        System.out.println("X:" + java.util.Arrays.toString(dataSetEvolutivo.getX()));
                        System.out.println("Y:" + java.util.Arrays.toString(dataSetEvolutivo.getY()));


                        Ejecucion ejecucion = new Ejecucion();
                        ejecucion.ejecutar(dataSetEvolutivo);

                    } catch (IOException e){
                        System.out.println(e.getMessage());

                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }
                else{
                    block();
                }
            }
        });

    }

}