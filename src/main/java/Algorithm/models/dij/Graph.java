package Algorithm.models.dij;

import Algorithm.TimeConv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph
{
    HashMap<Vertex, ArrayList<Connection>> nextList;

    public Graph(HashMap<Vertex, ArrayList<Connection>> nextList)
    {
        this.nextList = nextList;
    }

    public long timeCostFunction(Connection connection, TimeConv start_time)
    {
        if (start_time.sec <= connection.departure_time.sec)
        {
            return connection.arrival_time.sec - start_time.sec;
        } else {
            return 24L*3600L + Math.abs(connection.arrival_time.sec - start_time.sec);
        }
    }

    public void shortestTimePathDijkstra(Vertex start_stop, Vertex end_stop, TimeConv start_time)
    {
        TimeConv currentTime = new TimeConv(start_time);
        HashMap<Vertex, Long> shortestPathCost = new HashMap<>();
        HashMap<Vertex, Connection> incomingConnectionInShortestPath = new HashMap<>();
        for(Vertex v : nextList.keySet())
        {
            shortestPathCost.put(v, Long.MAX_VALUE);
            incomingConnectionInShortestPath.put(v, null);
        }
        shortestPathCost.put(start_stop, 0L);

        LinkedList<Vertex> q = new LinkedList<>(nextList.keySet());

        while(!q.isEmpty())
        {
            Vertex currentVertex = q.getFirst();
            Long currentVertexCost = shortestPathCost.get(currentVertex);
            for(Vertex v : q)
            {
                if(shortestPathCost.get(v) < currentVertexCost)
                {
                    currentVertexCost = shortestPathCost.get(v);
                    currentVertex = v;
                }
            }
            if(shortestPathCost.get(currentVertex) == Long.MAX_VALUE)
            {
                break;
            }
            q.remove(currentVertex);
            currentTime.sec = start_time.sec + shortestPathCost.get(currentVertex);
            if(shortestPathCost.get(currentVertex) == Long.MAX_VALUE)
                System.out.println(currentVertex);

            for(Connection c : nextList.get(currentVertex))
            {
                long nextVertexCost = timeCostFunction(c, currentTime) + shortestPathCost.get(currentVertex);
                if(shortestPathCost.get(c.end_stop) > nextVertexCost)
                {
                    shortestPathCost.put(c.end_stop, nextVertexCost);
                    incomingConnectionInShortestPath.put(c.end_stop, c);
                }
            }

        }

        LinkedList<Connection> shortestPath = new LinkedList<>();
        while(incomingConnectionInShortestPath.get(end_stop) != null)
        {
            shortestPath.add(incomingConnectionInShortestPath.get(end_stop));
            end_stop = incomingConnectionInShortestPath.get(end_stop).start_stop;
        }

        Iterator<Connection> itr = shortestPath.descendingIterator();
        int i = 0;
        System.out.println("The path is:");
        while(itr.hasNext())
        {
            Connection connection = itr.next();
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
