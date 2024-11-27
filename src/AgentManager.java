import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class AgentManager extends Agent {

    protected  void  setup(){
        System.out.println("Agente manager iniciado.");

        //Enviar tarea al Agente cordinador!

        System.out.println("AgentManager: Envio el mensaje, correctamente!!");
        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
        message.addReceiver(new AID("AgentCalculation", AID.ISLOCALNAME));
        send(message);
    }

}
