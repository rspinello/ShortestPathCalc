public class Edge 
{
  public int vertex, distanceFromStart; 
  public Edge next; 
  
  public Edge()
  {
    vertex = 0;
    distanceFromStart = 0;
    next = null;
  }//end default
  
  public Edge(int vertex, int distanceFromStart)
  {
    this.vertex = vertex;
    this.distanceFromStart = distanceFromStart;
    next = null;
  }//end default
  
  public int getVertex()
  {
    return vertex;
  }//end method
  
  public int getDFS()
  {
    return distanceFromStart;
  }//end method
  
  public void setNext(Edge e)
  {
    next = e; 
  }//end 
  
  public Edge getNext()
  {
    return this.next;
  }//end method 
  
}//end class 