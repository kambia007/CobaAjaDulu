 
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
 
class GraphLinkedList
{
    private Map<Integer, List<Integer>> adjacencyList;
 
    public GraphLinkedList(int v)
    {
        adjacencyList = new HashMap<Integer, List<Integer>>();
        for (int i = 1; i <= v; i++)
            adjacencyList.put(i, new LinkedList<Integer>());
    }
 
    public void setEdge(int from, int to)
    {
        if (to > adjacencyList.size() || from > adjacencyList.size())
            System.out.println("The vertices does not exists");
        /*
         * List<Integer> sls = adjacencyList.get(to);
         * sls.add(from);
         */
        List<Integer> dls = adjacencyList.get(from);
        dls.add(to);
    }
 
    public List<Integer> getEdge(int to)
    {
        if (to > adjacencyList.size())
        {
            System.out.println("The vertices does not exists");
            return null;
        }
        return adjacencyList.get(to);
    }
 
    public boolean checkDAG()
    {
        Integer count = 0;
        Iterator<Integer> iteratorI = this.adjacencyList.keySet().iterator();
        Integer size = this.adjacencyList.size() - 1;
        while (iteratorI.hasNext())
        {
            Integer i = iteratorI.next();
            List<Integer> adjList = this.adjacencyList.get(i);
            if (count == size)
            {
                return true;
            }
            if (adjList.size() == 0)
            {
                count++;
                System.out.println("Target Node - " + i);
                Iterator<Integer> iteratorJ = this.adjacencyList.keySet()
                        .iterator();
                while (iteratorJ.hasNext())
                {
                    Integer j = iteratorJ.next();
                    List<Integer> li = this.adjacencyList.get(j);
                    if (li.contains(i))
                    {
                        li.remove(i);
                        System.out.println("Deleting edge between target node "
                                + i + " - " + j + " ");
                    }
                }
                this.adjacencyList.remove(i);
                iteratorI = this.adjacencyList.keySet().iterator();
            }
        }
        return false;
    }
 
    public void printGraph()
    {
        System.out.println("The Graph is: ");
        for (int i = 1; i <= this.adjacencyList.size(); i++)
        {
            List<Integer> edgeList = this.getEdge(i);
            if (edgeList.size() != 0)
            {
                System.out.print(i);
                for (int j = 0; j < edgeList.size(); j++)
                {
                    System.out.print(" -> " + edgeList.get(j));
                }
                System.out.println();
            }
        }
    }
}
 
public class CheckDAG
{
    public static void main(String args[])
    {
        int v, e, count = 1, to, from;
        Scanner sc = new Scanner(System.in);
        GraphLinkedList glist;
        try
        {
            System.out.println("Enter the number of vertices: ");
            v = sc.nextInt();
            System.out.println("Enter the number of edges: ");
            e = sc.nextInt();
            glist = new GraphLinkedList(v);
            System.out.println("Enter the edges in the graph : <from> <to>");
            while (count <= e)
            {
                to = sc.nextInt();
                from = sc.nextInt();
                glist.setEdge(to, from);
                count++;
            }
            glist.printGraph();
            System.out
                    .println("--Processing graph to check whether it is DAG--");
            if (glist.checkDAG())
            {
                System.out
                        .println("Result: \nGiven graph is DAG (Directed Acyclic Graph).");
            }
            else
            {
                System.out
                        .println("Result: \nGiven graph is not DAG (Directed Acyclic Graph).");
            }
        }
        catch (Exception E)
        {
            System.out
                    .println("You are trying to access empty adjacency list of a node.");
        }
        sc.close();
    }
}