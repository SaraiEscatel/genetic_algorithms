import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgentCalculation extends Agent {
    DataSet dataSet = new DataSet();
    Operations operations = new Operations();
    MethodoPolinomial methodoPolinomial = new MethodoPolinomial();
    PolynomialRegression polynomialRegression = new PolynomialRegression();
    Predictions predictions = new Predictions();
    DiscreteMathematcs discreteMathematcs = new DiscreteMathematcs();
    Totales totales = new Totales();

    @Override
    protected void setup() {
        System.out.println("Agente calculador iniciado.");

        // Espera un mensaje REQUEST del coordinador
        MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage recibirMsg = blockingReceive(template);

        if (recibirMsg != null) {
            System.out.println("AgentCalculation: Recibió mensaje para calcular");
            try {
                // Usa los valores del DataSet directamente
                double[] xValues = dataSet.x;
                double[] yValues = dataSet.y;
                int grado = dataSet.grado;

                System.out.println("Usando el DataSet predefinido:");
                System.out.println("X: " + java.util.Arrays.toString(xValues));
                System.out.println("Y: " + java.util.Arrays.toString(yValues));
                System.out.println("Grado del polinomio: " + grado);


                // Realiza los cálculos de la Regresion Polinomial
                double[][] matrizX = methodoPolinomial.crearMatriz(grado, xValues);
                double[][] matrizXT = methodoPolinomial.crearMatrizT(matrizX, yValues);
                double[][] productoXTX = operations.productoMatrices(matrizXT, matrizX, grado);
                double[][] inversa = operations.IversaProducto(productoXTX);
                double []  XTVector = operations.productoMatrizTranspuestaPorVector(matrizXT,yValues);
                double [] Bhat = polynomialRegression.bDeHat;


                //Regresion Lineal
                double  SumatoriaX  = discreteMathematcs.sumatoriaX(xValues);
                double SumatoriaY  = discreteMathematcs.sumatoriaY(yValues);
                double[] multiplicacionXY = discreteMathematcs.multiplicacionXY(xValues, yValues);
                double SumXY = discreteMathematcs.sumatoriaXY(multiplicacionXY);
                double divisor = discreteMathematcs.divisor(xValues, yValues);
                double dividendo = discreteMathematcs.dividendo(xValues, yValues);
                double claB1 = totales.calcularB1(xValues, yValues);
                double calB0 = totales.calcularB0(xValues, yValues);


                // Convierte los resultados a String
                String matrizXStr = arrayToString(matrizX);
                String matrizXTStr = arrayToString(matrizXT);
                String productoXTXStr = arrayToString(productoXTX);
                String inversaStr = arrayToString(inversa);
                String XTTStr = arrayToString(XTVector);
                String BHatStr = arrayToString(Bhat);
                String SumatoriaxStr = String.valueOf(SumatoriaX);
                String SumatoriaYStr = String.valueOf(SumatoriaY);
                String multiplicacionXYStr = arrayToString(multiplicacionXY);
                String SumXYStr = String.valueOf(SumXY);
                String divisorStr = String.valueOf(divisor);
                String dividendStr = String.valueOf(dividendo);
                String calB1Str = String.valueOf(claB1);
                String calB0Str = String.valueOf(calB0);



                // Crea el objeto de resultados
                ResultOperations result = new ResultOperations(matrizXStr, matrizXTStr, productoXTXStr, inversaStr, XTTStr, BHatStr, SumatoriaxStr, SumatoriaYStr, multiplicacionXYStr,SumXYStr,divisorStr, dividendStr, calB1Str, calB0Str);

                // Envía el resultado al agente resultado
                enviarResultado(result);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error en el cálculo o envío de resultados.");
            }
        }
    }

    private void enviarResultado(ResultOperations resultado) {
        try {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(new AID("AgentResult", AID.ISLOCALNAME));
            msg.setContentObject(resultado);
            send(msg);
            System.out.println("Resultado enviado al agente resultado: ");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al enviar el resultado.");
        }
    }

    private String arrayToString(double[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (double[] row : matrix) {
            for (double val : row) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String arrayToString(double[] array) {
        StringBuilder sb = new StringBuilder();
        for (double val : array) {
            sb.append(val).append(" ");
        }
        return sb.toString();
    }
}
