abstract public class Digraph {

    protected int num_nodes;
    protected int num_arcs;

    public Digraph(int n) {
	num_nodes = n;
	num_arcs = 0;
    }

    public int num_nodes() { 
	return this.num_nodes; 
    }
  
    public int num_arcs() { 
	return this.num_arcs; 
    }

    abstract public void clear();
    abstract public void add_node(int n);
    abstract public void add_arc(int i, int j);
    abstract public void remove_arc(int i, int j);
    abstract public boolean is_arc(int i, int j);
    
    public abstract void RoyWarshall(Digraph digraph) ;
}


