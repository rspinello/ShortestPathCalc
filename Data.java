public class Data 
{
  public int dist, pred; 
  public boolean visited;
  public Edge next; 
  
  public Data()
  {
    dist = 0;
    pred = -1; 
    visited = false; 
    next = null;
  }//end default
  
  public Data(int dist, int pred)
  {
    this.dist = dist;
    this.pred = pred; 
    visited = false; 
    next = null;
  }//end default
  
  public int getDist()
  {
    return dist; 
  }//end method 
  
  public void setDist(int dist)
  {
     this.dist = dist; 
  }//end method 
  
  public int getPred()
  {
    return pred; 
  }//end method 
  
  public void setPred(int pred)
  {
    this.pred = pred; 
  }//end method

  public boolean getVisited()
  {
    return visited; 
  }//end method 
  
  public void setVisited()
  {
    if(!visited)
      visited = true; 
    else 
      visited = false; 
  }//end method 
  
  public void setNext(Edge e)
  {
    next = e; 
  }//end method 
  
  public Edge getNext()
  {
    return this.next;
  }//end method 
  
  
}//end class 