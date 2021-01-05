//Robert Spinello
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*; 
import java.util.*;
import java.io.*;

public class DistanceCalc extends JFrame 
{
  private JLabel origin, destination, mileage; 
  private JButton clear, compute, exit; 
  private JTextArea totalDistance, path; 
  private JScrollPane startScrollPane, endScrollPane, pathScrollPane;  
  private JList startList, endList; 
  private JPanel top, middle, bottom, buttons, mil;
  private String[] cities = new String[325];
  public Data[] graph = new Data[325]; 
  public PriorityQueue<Node> pq; 
  
  public DistanceCalc()
  {
    super("Shortest Distance Between Two Cities");
    setBounds(0,0,650,500);
    initiateCities();
    initiateGraph();
    
    //top panel start
    top = new JPanel(new FlowLayout());
    origin = new JLabel("Origin");
    origin.setFont(new Font("courier", Font.BOLD, 15));
    origin.setForeground(Color.WHITE);
    startList = new JList(cities);
    startList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    startScrollPane = new JScrollPane(startList);
    destination = new JLabel("Destination");
    destination.setFont(new Font("courier", Font.BOLD, 15));
    destination.setForeground(Color.WHITE);
    endList = new JList(cities); 
    endList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    endScrollPane = new JScrollPane(endList);
    top.add(origin);
    top.add(startScrollPane);
    top.add(destination);
    top.add(endScrollPane);
    top.setBackground(Color.GRAY);
    add(top, BorderLayout.NORTH);
    //top panel end 
    
    //middle panel start
    middle = new JPanel(new GridLayout(2,1));
    mil = new JPanel(new FlowLayout()); 
    mileage = new JLabel("Mileage:");
    mileage.setFont(new Font("courier", Font.BOLD, 15));
    mileage.setForeground(Color.WHITE);
    totalDistance = new JTextArea(2,47); 
    totalDistance.setForeground(Color.RED);
    mil.add(mileage);
    mil.add(totalDistance);
    buttons = new JPanel(new FlowLayout());
    clear = new JButton("Clear");
    compute = new JButton("Compute");
    exit = new JButton("Exit");
    buttons.add(clear);
    buttons.add(compute);
    buttons.add(exit);
    middle.add(mil);
    middle.add(buttons);
    mil.setBackground(Color.GRAY);
    middle.setBackground(Color.GRAY);
    buttons.setBackground(Color.GRAY);
    add(middle, BorderLayout.CENTER);
    //middle panel end 
    
    //bottom panel start
    bottom = new JPanel(new FlowLayout());
    path = new JTextArea(15,25); 
    pathScrollPane = new JScrollPane(path);
    bottom.add(pathScrollPane);
    bottom.setBackground(Color.GRAY);
    add(bottom, BorderLayout.SOUTH);
    //bottom panel end
    
    exit.addActionListener(new ButtonListener());
    clear.addActionListener(new ButtonListener());
    compute.addActionListener(new ButtonListener());
    
    setResizable(false);
    setVisible(true);  
  }//end constructor 
  
  private class ButtonListener implements ActionListener // responds to button event
  {
    public void actionPerformed(ActionEvent e) // ActionListener Interface method
    {
      if(e.getSource() == exit)
        System.exit(0);
      if(e.getSource() == clear)
      {
        path.setText("");
        totalDistance.setText("");
      }//end if
      if(e.getSource() == compute)
      {
        int startIndex = startList.getSelectedIndex(); 
        int endIndex = endList.getSelectedIndex(); 
        
        totalDistance.setText("Total Distance:\t" + shortestPath(startIndex, endIndex));
      }//end if   
    }//end method 
  }//end class 
  
  public void initiateCities()
  {
    Scanner inputStream = null; 
    int count = 0; 
    
    try 
    {
      inputStream = new Scanner(new FileInputStream("CityNames.txt"));
    }//end try
    catch(FileNotFoundException e)
    {
      System.out.println("File not found program to close.");
      System.exit(0);
    }//end catch    
    
    while(inputStream.hasNext())
    {
      String s = inputStream.nextLine();
      cities[count] = s;
      count ++;
    }//end while
    count = 0;   
  }//end method 
  
  public void initiateGraph()
  {
    Scanner inputStream = null;
    String orig, dest;
    int distanceTo;
    
    initiateDataArray();
    
    try 
    {
      inputStream = new Scanner(new FileInputStream("CityVerticies.txt"));
    }//end try
    catch(FileNotFoundException e)
    {
      System.out.println("File not found program to close.");
      System.exit(0);
    }//end catch    
    
    while(inputStream.hasNext())
    {
      orig = inputStream.next(); 
      dest = inputStream.next(); 
      distanceTo = inputStream.nextInt(); 
      
      
      if( graph[findVertex(orig)].getNext() != null)
      {
        Edge p =  graph[findVertex(orig)].getNext();  
        while(p.next != null)
        {
          p = p.next;
        }//end while
        
        p.next =  makeEdge(findVertex(dest), distanceTo);
        
      }//end if 
      else 
        graph[findVertex(orig)].setNext(makeEdge(findVertex(dest), distanceTo));
      
      
    }//end while
    
  }//end method 
  
  public int findVertex(String s)
  {
    for(int i = 0; i < cities.length; i++)
    {
      if(s.equals(cities[i]))
        return i;
    }//end for 
    return -1;    
  }//end 
  
  public Edge makeEdge(int vert,int dis)
  {
    Edge e = new Edge(vert, dis);
    return e;
  }//end 
  
  public void initiateDataArray()
  {
    for(int i = 0; i < graph.length; i++)
    {
      graph[i] = new Data(); 
    }//end for   
  }//end     
  

  
  public void printDataArray()
  {
    for(int i = 0; i < graph.length; i++)
    {
      System.out.print(cities[i] + " --------------- ");
      if(graph[i].getNext() != null)
      {
        Edge p =  graph[i].next;  
        while(p.getNext() != null)
        {
          edgeToString(p);
          p = p.next;
        }//end while 
        edgeToString(p);
      }//end if 
      System.out.println();
    }//end for
    
  }//end print method 
  
  public void edgeToString(Edge e)
  {
    System.out.print( " " + cities[e.getVertex()] + " " + e.getDFS() + " ");
  }//end method 
  

  
  public int shortestPath(int start, int end)
  {
    pq = new PriorityQueue<Node>(); 
    boolean done = false; 
    Node n = new Node(start, -1, 0);
    pq.insert(n);
    while(!done && !pq.empty())
    {
      Node x = pq.remove(); 
      int v = x.getVertex();
      //x.nodeToString();
      if(graph[v].getVisited() == false)
      {
        graph[v].setVisited();
        graph[v].setDist(x.getDFS()); 
        graph[v].setPred(x.getPred());
        
        if(v == end)
          done = true; 
        else 
        {
          if(graph[v].getNext() != null)
          {
            Edge p =  graph[v].next;  
            while(p.getNext() != null)
            {
              if(!graph[p.getVertex()].getVisited())
              {
                int d = graph[v].getDist() + p.getDFS();
                Node temp = new Node(p.getVertex(), v, d);
                pq.insert(temp);
              }//end if 
              p = p.next;
            }//end while 
            if(!graph[p.getVertex()].getVisited())
            {
              int d = graph[v].getDist() + p.getDFS();
              Node temp = new Node(p.getVertex(), v, d);
              pq.insert(temp);
            }//end if 
          }//end if
          
        }//end else 
      }//end if
    }//end while
    if(done == false)
      path.setText("No Path Found");
    else 
    {
      Stack<Node> shortPath = new Stack<Node>();
      int ver = end;
      while(ver != start)
      {
        Node m = new Node(ver, graph[ver].getPred(), graph[ver].getDist());
        shortPath.push(m);
        ver = graph[ver].getPred(); 
      }//end while
      
      String s = "";
      
      while(!shortPath.empty())
      {
        Node m = shortPath.pop();
        s = s + "\n" + cities[m.getPred()] + " " + cities[m.getVertex()] + " " + m.getDFS();
        path.setText(s);
      }//end 
    }//emd else
    int h = graph[end].getDist();
    initiateGraph();
    
    return h;
  }//end method 
  
  
  
  
  public static void main(String[] args)
  {
    DistanceCalc dc = new DistanceCalc(); 
  }//end main 
}//end class