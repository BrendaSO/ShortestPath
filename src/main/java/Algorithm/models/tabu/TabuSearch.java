package Algorithm.models.tabu;

import Algorithm.TimeConv;
import Algorithm.models.dij.Connection;
import Algorithm.models.dij.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;

public class TabuSearch{

    ArrayList<Vertex> bestMilestones;
    ArrayList<Connection> bestPath;
    long bestTime = Long.MAX_VALUE;
    TimeConv start_time;
    GraphForTabu dijkstra;

    public TabuSearch(GraphForTabu dijkstra,ArrayList<Vertex> milestones, TimeConv start_time) {
        bestMilestones = milestones;
        this.start_time = start_time;
        this.dijkstra = dijkstra;
    }

    public void iterate(int n) {
        calWeight(bestMilestones);
        for (int i = 0; i < n; i++)
            calNeighboursForBestPath();
        printPath(bestPath);
    }

    private void calNeighboursForBestPath() {

        for (int i = 1; i < bestPath.size() - 1; i++) {

            ArrayList<Vertex> current = new ArrayList<>(bestMilestones);
            Vertex temp = current.get(i);
            current.add(i, current.get(i + 1));
            current.add(i + 1, temp);

            calWeight(current);
        }
    }

    private void calWeight(ArrayList<Vertex> milestones){

        int weight = 0;
        TimeConv currrentTime = start_time;
        ArrayList<Connection> currentPath = new ArrayList<>();

        for (int i = 1; i < milestones.size() - 1; i++)
        {
            ArrayList<Connection> connections = dijkstra.shortestTimePath(milestones.get(i), milestones.get(i + 1), currrentTime);
            weight += calWeight(connections.get(connections.size() - 1), currrentTime);
            currrentTime = connections.get(connections.size() - 1).arrival_time;

            currentPath.addAll(connections);
        }

        if (weight < this.bestTime) {
            this.bestTime = weight;
            this.bestPath = currentPath;
            this.bestMilestones = milestones;
        }
    }

    private long calWeight(Connection endConnection, TimeConv startTime)
    {
        long weight = endConnection.arrival_time.sec - startTime.sec;
        return weight;
    }

    private void printPath(ArrayList<Connection> path) {
        int i = 0;
        for (Connection connection : path) {
            System.out.println("stop " + i
                    +" - Line: " + connection.line
                    + ", From: " + connection.start_stop
                    + ", Departure time:" + connection.departure_time
                    + ", To: " + connection.end_stop
                    + ", Arrival time: " + connection.arrival_time
            );
            i++;
        }
    }

}
