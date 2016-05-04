package objects;

public class Swarm extends MovingGroup {

    @Override
    public void render() {
        for (MovingObject agent : swarmAgents) {
            agent.render();
        }
    }
}
