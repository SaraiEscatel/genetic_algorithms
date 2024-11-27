import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class MultiAgents {

    public static void  main(String[] args){
        //Contenedor principal
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.GUI, "true");
        ContainerController mainContainer = runtime.createMainContainer(profile);

        try {
            AgentController agentManager = mainContainer.createNewAgent("AgentManager",AgentManager.class.getName(),null);
            AgentController agentCalculation = mainContainer.createNewAgent("AgentCalculation",AgentCalculation.class.getName(),null);
            AgentController agentResult = mainContainer.createNewAgent("AgentResult",AgentResult.class.getName(), null);

            agentManager.start();
            agentCalculation.start();
            agentResult.start();
        }catch (StaleProxyException e){
            e.printStackTrace();
        }
    }
}
