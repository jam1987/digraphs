public class DigraphMatrix extends Digraph {

    private boolean matrix[][];

    private void copy_matrix(boolean src[][], boolean dst[][]) {
	for( int i = 0; i < num_nodes; ++i ) {
	    for( int j = 0; j < num_nodes; ++j ) {
		dst[i][j] = src[i][j];
	    }
	}
    }

    public DigraphMatrix(int n) {
	super(n);
	matrix = new boolean[num_nodes][num_nodes];
    }

    public DigraphMatrix clone() {
	DigraphMatrix digraph = new DigraphMatrix(num_nodes);
	copy_matrix(matrix,digraph.matrix);
	digraph.num_arcs = num_arcs;
	return digraph;
    }

    public String toString() {
	String string = "";
	for( int i = 0; i < num_nodes; ++i ) {
	    for( int j = 0; j < num_nodes; ++j ) {
		string += matrix[i][j] ? "1 " : "0 ";
	    }
	    string += "\n";
	}
	return string;
    }

    public void clear() {
	for( int i = 0; i < num_nodes; ++i ) {
	    for( int j = 0; j < num_nodes; ++j ) {
		matrix[i][j] = false;
	    }
	}
    }

    public void add_node(int n) {
	boolean m[][] = new boolean[num_nodes+n][num_nodes+n];
	copy_matrix(matrix,m);
	matrix = m;
    }

    public void add_arc(int i, int j) {
	matrix[i][j] = true; 
    }
    public void remove_arc(int i, int j) { 
	matrix[i][j] = false;
    }
    public boolean is_arc(int i, int j) { 
	return matrix[i][j]; 
    }

    public void RoyWarshall(Digraph digraph) {

    }

}

