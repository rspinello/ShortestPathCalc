public class Node implements Comparable<Node>
{
  public int vertex, pred, distanceFromStart; 
  
  public Node()
  {
    vertex = 0;
    pred = -1;
    distanceFromStart = 0;
  }//end class 
  
  public Node(int vertex, int pred, int distanceFromStart)
  {
    this.vertex = vertex;
    this.pred = pred;
    this.distanceFromStart = distanceFromStart;
  }//end class 
  
  public int getPred()
  {
    return pred; 
  }//end method 
  
  public int getDFS()
  {
    return distanceFromStart;
  }//end method
  
  public int getVertex()
  {
    return vertex;
  }//end method  
  
  public  void nodeToString()
  {
    System.out.println("Ver:" + vertex + " Pred:" + pred + "DFS:" + distanceFromStart);
  }//end 
  
  public int compareTo(Node o)
  {
    if(this.distanceFromStart < o.distanceFromStart)
      return -1;
    return 1;
  }//end compare
  
  
}//end class 