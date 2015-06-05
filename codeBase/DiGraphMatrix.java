/**
 * DiGraphMatrix es una clase concreta que ud debe implementar
 *
 * @author Les profs
 * @version 1.0
 * @since 1.6
**/

import java.io.IOException;
import java.io.*;

/**
 *
 * @author eduardo
 */
public class DiGraphMatrix extends DiGraph {

    // estructura de la matriz de adyacencias que se debe utilizar
   private boolean matrix[][];

   /**
    * Crea un DiGraphMatrix con n nodos y sin arcos
    * @param n
    */
   public DiGraphMatrix(int n) {
      matrix = new boolean[n][n];
      numNodes = n;
   }

   /**
    * Crea un DiGraphMatrix a partir del contenido del archivo.
    *
    * @param fileName nombre del archivo
    */

      public DiGraphMatrix(String fileName) throws IOException {
	BufferedReader in= new BufferedReader (new FileReader(fileName));
		String s;
		String[] tokens;
               

		int n=0;
		if ((s=in.readLine())!=null) {
			tokens=s.split("\\b\\s");
			n=Integer.valueOf(tokens[0].trim()).intValue();
                        
		}
		 System.out.println("buu");
		for (int i=0;i<n;i++) {
			if((s=in.readLine())!=null) {
			tokens=s.split("\\b\\s");
                        
			if (tokens.length==2) {
                             
				if(Integer.valueOf(tokens[0].trim())!=null && Integer.valueOf(tokens[1].trim())!=null){
                                       
					int k=Integer.valueOf(tokens[0].trim()).intValue();
					int j=Integer.valueOf(tokens[1].trim()).intValue();
						if(  0<=k && 0<=j && k<n && j<n){
							matrix[k][j] = true;
							numArcs++;
						}
				}
			}
			}

		}

   }

   /**
    * Crea un DiGraphMatrix a partir del DiGraph g
    *
    * @param g el grafo fuente.
    */
   public DiGraphMatrix(DiGraph g) {
	int n = g.numNodes;
	matrix = new boolean[n][n];
	numNodes = g.numNodes;

	for(int i = 0 ; i < numNodes ; i++){
		for(int j = 0 ; j < numNodes ; j++){
			if(g.isArc(i,j)) matrix[i][j] = true;
			numArcs++;
		}
	}
   }

   @Override
   public DiGraphMatrix clone() {
	DiGraphMatrix digraph = new DiGraphMatrix(numNodes);
	for( int i = 0; i < numNodes; ++i ) {
	    for( int j = 0; j < numNodes; ++j ) {
		digraph.matrix[i][j] = matrix[i][j];
	    }
	}
	digraph.numArcs = numArcs;
	return digraph;
   }

    public void addNodes(int num) {
	int n = numNodes +  num;
        boolean[][] m0 = new boolean[n][n];

	for(int i = 0 ; i < numNodes ; i++){
		for(int k = 0 ; k < numNodes ; k++){
			if(isArc(i,k)) m0[i][k] = true;
		}
	}
	this.matrix = m0;
    }

    public Arc addArc(int src, int dst) {
		if(0 > src || src >= numNodes || 0 > dst || dst >= numNodes || isArc(src, dst)) return null;
	this.matrix[src][dst] = true;
	this.numArcs++;
	Arc nuevo = new Arc(src,dst);
	return nuevo;
    }

    public Arc addArc(int src, int dst, double costo) {
		if(0 > src || src >= numNodes || 0 > dst || dst >= numNodes || isArc(src, dst)) return null;
	this.matrix[src][dst] = true;
	this.numArcs++;
	Arc nuevo = new Arc(src,dst);
	nuevo.setCost(costo);
	return nuevo;
    }

    public List<Integer> getPredecesors(int nodeId) {
	Lista<Integer> predecesors=new Lista<Integer>();
	boolean added=false;
	for (int j=0;j<nodeId; j++) {
		if (matrix[nodeId][j]) {
			added=predecesors.add((Integer) j);
		}
	}
	return predecesors;
    }

    public List<Integer> getSucesors(int nodeId) {
	Lista<Integer> sucesors= new Lista<Integer>();
	boolean added=false;
	for (int k=nodeId;k<matrix.length;k++) {
		if (matrix[nodeId][k]) {
			added=sucesors.add((Integer)k);
		}
	}
	return sucesors;
    }

    public Arc getArc(int nodoSrc, int nodoDst) {
	Arc a0 = null;
        if (isArc(nodoSrc, nodoDst)){
		a0 = new Arc(nodoSrc, nodoDst);
	}
	return a0;
    }

    public void read(String fileName) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void write(String fileName) throws IOException {
		PrintStream out=new PrintStream(fileName);
		if (out==null) throw new IOException
									("Arch");
		Integer nNodos=new Integer(numNodes);
		Integer nArcos=new Integer(numArcs);
		String a= nNodos.toString();
		out.print(a);
		out.print(" ");
		String b=nArcos.toString();
		out.println(b);

		for (int k=0;k<numNodes ;k++) {
				for (int j=0;j<numNodes;j++) {

				if (isArc(k,j)) {
				Arc arco=new Arc(k,j);


				int src=arco.getSrc();
					System.out.println("hola");
				int dst=arco.getDst();
				Integer fuente=new Integer(src);
				Integer destino=new Integer(dst);
				String arcFuente=fuente.toString();
				out.print(arcFuente);
				out.print(" ");
				String arcDest= destino.toString();
				out.println(arcDest);
				}
				}
			}


    }




    public int getDegree(int nodeId) {
	if(0 > nodeId || nodeId >= numNodes) return -1;
        int degree=this.getOutDegree(nodeId)+this.getInDegree(nodeId);
	return degree;
    }

    public int getOutDegree(int nodeId) {
	if(0 > nodeId || nodeId >= numNodes) return -1;
	int outDegree=0;
        for (int k=0;k<matrix.length;k++) {
		if (matrix[nodeId][k]) {
			outDegree++;
		}
	}
	return outDegree;
    }

    public int getInDegree(int nodeId) {
	if(0 > nodeId || nodeId >= numNodes) return -1;
	int inDegree=0;
	for (int j=0;j<matrix.length;j++) {
		if (matrix[j][nodeId]) {
			inDegree++;
		}
	}
	return inDegree;
    }

    public int getNumberOfNodes() {
        return numNodes;
    }

    public int getNumberOfArcs() {
        int numberOfArcs=0;
	for (int k=0;k<matrix.length;k++) {
		for (int j=0;k<matrix.length;k++) {
			if (matrix[k][j]) {
				numberOfArcs++;
			}
		}
	}
	return numberOfArcs;
    }

    public List<Arc> getOutEdges(int nodeId) {
        Lista<Arc> l0 = new Lista<Arc>();
	if(0 > nodeId || nodeId >= numNodes) return l0;
	for(int k = 0 ; k < numNodes ; k++){
		if (isArc(nodeId, k)){
			Arc n0 = new Arc (nodeId, k);
			boolean b = l0.add(n0);
		}
	}
	return l0;
    }

    public List<Arc> getInEdges(int nodeId) {
        Lista<Arc> l0 = new Lista<Arc>();
	if(0 > nodeId || nodeId >= numNodes) return l0;
	for(int k = 0 ; k < numNodes ; k++){
		if (isArc(k, nodeId)){
			Arc n0 = new Arc (k, nodeId);
			boolean b = l0.add(n0);
		}
	}
	return l0;
    }

    public Arc delArc(int nodeIniId, int nodeFinId) {
        Arc a0 = new Arc(nodeIniId, nodeFinId);
	if(isArc(nodeIniId, nodeFinId)){
		matrix[nodeIniId][nodeFinId] = false;
		return a0;
	}
	else{
		return null;
	}
    }

    public List<Arc> removeAllArcs() {
	Lista<Arc> l0 = new Lista<Arc>();
        for(int i = 0 ; i < numNodes ; i++){
		for(int j = 0 ; j < numNodes ; j++){
			if(isArc(i, j)){
				Arc a0 = this.delArc(i, j);;
				boolean b = l0.add(a0);
			}
		}
	}
	return l0;
    }

    public boolean reverseArc(int nodeIniId, int nodeFinId) {
        if(isArc(nodeIniId, nodeFinId)){
		matrix[nodeFinId][nodeIniId] = true;
		return true;
	} else {
		return false;
	}
    }

    public boolean reverseArcs() {
	boolean b = (numArcs > 0);
        for (int j = 0 ; j<matrix.length ; j++) {
		for (int k=0;k<matrix.length ; k++) {
			b = (b || reverseArc(j, k));
		}
	}
	return b;
    }

    public boolean equals(DiGraph g) {
		boolean resp = true;
		int i = 0;
		if (this.numNodes == g.numNodes){
			while(i < numNodes && resp){
				for(int k = 0 ; k < numNodes ; k++){
					resp = (this.isArc(i,k) == g.isArc(i,k));
				}
			}
		}

		else{
			resp = false;
		}
		return resp;
    }

    /**
     * Retorna un Digraph que es la clausura transitiva de este DiGraph
     * calculada usando el algoritmo Roy-Warshal
     *
     * @return un Digraph que es la clausura transitiva de este DiGraph
     * calculada usando el algoritmo Roy-Warshal
     */

    public DiGraph royWarshall() {
        DiGraphMatrix g0 = new DiGraphMatrix(numNodes);


	//Sumo la matriz identidad.
	for(int i = 0 ; i < numNodes ; i++){
		for(int k = 0 ; k < numNodes ; k++){
			if(isArc(i,k))	g0.addArc(i,k);
		}
		g0.addArc(i,i);
	}

	for(int k = 0 ; k < numNodes ; k++){
		for(int i = 0 ; i < numNodes ; i++){
			if ((i != k) && g0.isArc(i,k)){
				for(int j = 0 ; j < numNodes ; j++){
					if (g0.isArc(k,j)) g0.addArc(i,j);
				}
			}
		}

	}

	return g0;
    }

    @Override
    public String toString() {
	String string = "";
	for( int i = 0; i < numNodes; ++i ) {
		for( int j = 0; j < numNodes; ++j ) {
			string += matrix[i][j] ? "1 " : "0 ";
		}
		string += "\n";
	}
	return string;
    }

    public boolean isArc(int src, int dst) {
	boolean isArc=false;
	isArc=matrix[src][dst];
	return isArc;
    }

}