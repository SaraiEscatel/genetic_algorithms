import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgentResult extends Agent {

    protected void setup(){
        System.out.println("Agente Resultado iniciado!");

        //espera el mensaje del agente calculador

        MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage recibirMsg = blockingReceive(template);

        if (recibirMsg != null){

            try {
                Object recibido = recibirMsg.getContentObject();
                if(recibido instanceof ResultOperations){
                    ResultOperations result = (ResultOperations) recibido;
                    System.out.println("------------------------Regresion Polinomial----------------------------");
                    System.out.println("\nMatriz X se recibio correctamente! \n"+"\nX: \n" +result.getMatrizX());

                    System.out.println("\nMatriz X^T se recibio correctamente! \n"+"\nX^T: \n" +result.getMatrizX());

                    System.out.println("\nMatriz (X^T * X) se recibio correctamente! \n"+"\n" +result.getProductoXTX());

                    System.out.println("\nMatriz (X^T * X)^-1 se recibio correctamente! \n"+"\n" +result.getInversa());

                    System.out.println("\n X^T * Y se recibio correctamente! \n" +"\n(X^T * Y): \n"+result.getXTY());

                    System.out.println("\n BHat se recibio correctamente! \n" +"\n(X^T * X)^-1 - (X^T * Y): \n"+result.getBhat());

                    System.out.println();

                    System.out.println("\n-------------Regresion Lineal------------------------------\n");
                    System.out.println("La Sumatoria de X  es: " + result.getSumX());
                    System.out.println("La Sumatoria de Y es: " + result.getSumY());
                    System.out.println("La Sumatoria X * Y es: " + result.getSumXY());
                    System.out.println("El divisor: " + result.getDivisor());
                    System.out.println("El dividendo es: " + result.getDividendo());
                    System.out.println("\nLas betas calculadas son: ");
                    System.out.println("B1 es: " + result.getCalB1());
                    System.out.println("B0 es: " + result.getCalB0());

                }else {
                    System.out.println("Esta informacion no es correcta");
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
