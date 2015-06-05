/**
 * DiGraphList es una clase comcreta que ud debe implementar
 * Los arcos son almacenados como una lista y son almacenados en un
 * arreglo donde la posicion i
 *
 * @author Les profs
 * @version 1.0
 * @since 1.6
**/

import java.io.IOException;
import java.io.*;

public class DiGraphList extends DiGraph {

    // arreglo de lista de los arcos, inArc[i] contine la lista
    // de los arcos que cuyo destino es el nodo i
  private List<Arc> inArcs[];
    // arreglo de lista de los arcos, outArc[i] contine la lista
    // de los arcos que cuyo fuente es el nodo i
  private List<Arc> outArcs[];


   /**
    * Crea un DiGraphList con n nodos y sin arcos.
    * @param n numero de nodos iniciales de grafo
    */
   public DiGraphList(int n) {

      inArcs = new List[n];
      outArcs = new List[n];
	  numNodes=n;
	  numArcs=0;
	  
	  for (int k=0; k<n; k++) {
		inArcs[k]=new Lista<Arc>();
		outArcs[k]=new Lista<Arc>();
	}
	  
	  
	  
   }

   /**
    * Crea un DiGraphList a partir del contenido de un archivo
    * @param fileName nombre del archivo
    */
    public DiGraphList(String fileName) throws IOException {
		BufferedReader in= new BufferedReader (new FileReader(fileName));
		String s; 
		String[] tokens;
		int n=0;
		
		
		numArcs=0;
		
		if ((s=in.readLine())!=null) {
			tokens=s.split("\\b\\s");
			n=Integer.valueOf(tokens[0].trim()).intValue();
			numNodes=n;
			inArcs = new Lista[n];
		outArcs = new Lista[n];
			for(int i = 0 ; i < numNodes ; i++){
			inArcs[i] = new Lista<Arc>();
			outArcs[i] = new Lista<Arc>();
		}
		}
		
		for (int i=0;i<numNodes;i++) {
			if((s=in.readLine())!=null) {
			tokens=s.split("\\b\\s");
				if(tokens.length >= 2){
					if(Integer.valueOf(tokens[0].trim())!=null && Integer.valueOf(tokens[1].trim())!=null){
						int k=Integer.valueOf(tokens[0].trim()).intValue();
						int j=Integer.valueOf(tokens[1].trim()).intValue();
							if(0 <= k && 0 <= j && k < n && j < n){	
								Arc a0 = new Arc(k,j);
								inArcs[j].add(a0);
								outArcs[k].add(a0);		
								numArcs++;
							}
					}
				}
			}
		}
		
		
   }

   /**
    * Crea un DiGraphList a partir del DiGraph g
    * @param g
    */
   public DiGraphList(DiGraph g) {
	final int n = g.getNumberOfNodes();
	inArcs = new List[n];
	outArcs = new List[n];
	numNodes = n;
	numArcs=0;
	for(int i = 0 ; i < n ; i++){
		inArcs[i] = new Lista<Arc>();
		outArcs[i] = new Lista<Arc>();
	}

	for(int i = 0 ; i < n ; i++){
		for(int j = 0 ; j < n ; j++){
			if(g.isArc(i, j)){
				Arc a0 = g.getArc(i,j);
				inArcs[j].add(a0);
				outArcs[i].add(a0);		
				numArcs++;
			}
		}
	}

   }

   @Override
   public DiGraphList clone() {
      DiGraphList clon = new DiGraphList(this);
	  return clon;
   }

    public void addNodes(int num) {
    int n = this.getNumberOfNodes();
	int m = n + num;
    	DiGraphList g0 = new DiGraphList(m);

	for(int i = 0 ; i < n ; i++){
		g0.inArcs[i] = this.inArcs[i].clone();
		g0.outArcs[i] = this.outArcs[i].clone();
	}

	this.inArcs = g0.inArcs;
	this.outArcs = g0.outArcs;
	this.numNodes = m;
    }
    public Arc addArc(int src, int dst) {
	if(0 > src || src >= numNodes || 0 > dst || dst >= numNodes || isArc(src, dst)) return null;
        Arc a0 = new Arc(src, dst);
	numArcs++;
		
	inArcs[dst].add((Arc)a0);
	outArcs[src].add((Arc)a0);
	return a0;
    }

    public Arc addArc(int src, int dst, double costo) {
	if(0 > src || src >= numNodes || 0 > dst || dst >= numNodes || isArc(src, dst)) return null;
      	Arc a0 = new Arc(src, dst);
	a0.setCost(costo);
	numArcs++;
	inArcs[dst].add(a0);
	outArcs[src].add(a0);
	return a0;
    }

    public List<Integer> getPredecesors(int nodeId) {
        List<Integer> predecesor=new Lista<Integer>();
	if(0 > nodeId || nodeId >= numNodes) return predecesor;
		int k=0;
		if (this.inArcs[nodeId]!=null) {
			while (k<this.inArcs[nodeId].size()) {
				Arc a=this.inArcs[nodeId].get(k);
				boolean b=predecesor.add(a.getSrc());
				k++;
			}
		}
		
		return predecesor;
		
		}

    public List<Integer> getSucesors(int nodeId) {
        List<Integer> sucesor=new Lista<Integer>();
	if(0 > nodeId || nodeId >= numNodes) return sucesor;
		int k=0;
		if (this.outArcs[nodeId]!=null) {
			while (k<this.outArcs[nodeId].size()) {
				Arc a=this.outArcs[nodeId].get(k);
				boolean b=sucesor.add(a.getDst());
				k++;
			}
		}
		
		return sucesor;
		
    }

    public Arc getArc(int nodoSrc, int nodoDst) {
	Arc a0 = null;
	int n0 = getOutDegree(nodoSrc);
	boolean b = false;
    	for(int i = 0; i < n0 && !b ; i++){
		a0 = outArcs[nodoSrc].get(i);
		b = (a0.getDst() == nodoDst);
	}
	if(b) return a0;
	return null;
    }

    public void read(String fileName) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

       public void write(String fileName) throws IOException {
        PrintStream out=new PrintStream(fileName);
		if (fileName==null) throw new IOException
									("Arch");
		Integer b=new Integer(this.numNodes);
		Integer c=new Integer(this.numArcs);
		String a=b.toString();
		out.print(a);
		out.print(" ");
		String d= c.toString();
		out.println(d);
		
		for (int k=0;k<numNodes ;k++) { 
			if (this.inArcs!=null) {
				for (int j=0;j<inArcs[k].size();j++) {
				
			
				Arc arco=new Arc(k,j);
				if (this.inArcs[k].contains)
				
				int src=arco.getSrc();
					System.out.println("hola");
				int dst=arco.getDst();
				Integer fuente=new Integer(src);
				Integer destino=new Integer(dst);
				String arcFuente=fuente.toString();
				out.print(arcFuente);
				out.print(" ");
				String arcDest= destino.toString();
				out.println(d);
				}
				}
			}
		}
		
    }
    public int getDegree(int nodeId) {
	if(0 > nodeId || nodeId >= numNodes) return -1;
    	int resp = 0;
	resp = resp + getInDegree(nodeId)+getOutDegree(nodeId);
	return resp;
    }

    public int getOutDegree(int nodeId) {
	if(0 > nodeId || nodeId >= numNodes) return -1;
    	int resp = this.outArcs[nodeId].size();
	return resp;
    }

    public int getInDegree(int nodeId) {
	if(0 > nodeId || nodeId >= numNodes) return -1;
    	int resp = this.inArcs[nodeId].size();
	return resp;
    }

    public int getNumberOfNodes() {
    	return this.numNodes;
    }

    public int getNumberOfArcs() {
        return this.numArcs;
    }

    public List<Arc> getOutEdges(int nodeId) {
	if(0 > nodeId || nodeId >= numNodes) return new Lista<Arc>();
        return this.outArcs[nodeId];
    }

    public List<Arc> getInEdges(int nodeId) {
	if(0 > nodeId || nodeId >= numNodes) return new Lista<Arc>();
        return this.inArcs[nodeId];
		}

    public Arc delArc(int nodeIniId, int nodeFinId) {
	if(0 > nodeIniId || nodeIniId >= numNodes || 0 > nodeFinId || 
		nodeFinId >= numNodes || !isArc(nodeIniId, nodeFinId)) return null;
        Arc deletedArc= new Arc(nodeIniId,nodeFinId);
	boolean deleted=this.inArcs[nodeFinId].remove((Arc)deletedArc);
	deleted= this.outArcs[nodeIniId].remove((Arc) deletedArc );
	numArcs--;
	return deletedArc;
    }

    public List<Arc> removeAllArcs() {
	int degree = 0;
	int nodeFin = 0;
	Arc a0 = null;
	boolean trash = false;
	List<Arc> l0 = new Lista<Arc>();
	for(int i = 0 ; i < numNodes ; i++){
		degree = getOutDegree(i);
		for(int k = 0 ; k < degree ; k++){
			a0 = delArc(i,k);
			trash = l0.add(a0);
		}
	}
	return l0;
    }

    public boolean reverseArc(int nodeIniId, int nodeFinId) {
	if(0 > nodeIniId || nodeIniId >= numNodes || 0 > nodeFinId || nodeFinId >= numNodes) return false;
       	Arc a0 = getArc(nodeIniId, nodeFinId);
	boolean resp = (a0 != null);
	if(resp){
		Arc a1 = new Arc(nodeFinId , nodeIniId);
		a1.setCost(a0.getCost());
		a0 = delArc(nodeIniId, nodeFinId);
		a1 = addArc(nodeFinId, nodeIniId, a0.getCost());
	}
	return resp;
    }

    public boolean reverseArcs() {
        DiGraph g0 = new DiGraphList(this.numNodes);
	Arc a0 = null;
	boolean resp = true;
	int i = 0;
	int deg = 0;
	int k = 0;
	while(i < numNodes && resp){
		deg = getOutDegree(i);
		while(k < deg && resp){
			a0 = outArcs[i].get(k);
			int src = a0.getSrc();
			int dst = a0.getDst();
			double costo = a0.getCost();
			a0 = g0.addArc(dst, src, costo);
			resp = resp && (a0 != null);
			k++;
		}
		i++;
	}
	if(resp){
		for(i = 0 ; i < numNodes ; i++){
			inArcs[i] = g0.getInEdges(i);
			outArcs[i] = g0.getOutEdges(i);
		}
	}
	return resp;
    }

    public boolean equals(DiGraph g) {
	boolean resp = g.numNodes == numNodes && g.numArcs == numArcs;
	if (resp){
		for(int i = 0 ; i < numNodes && resp; i++){
			resp = resp && inArcs[i].equals(g.getInEdges(i));
			resp = resp && outArcs[i].equals(g.getOutEdges(i));	
		}
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
   	DiGraph g0 = this.clone();
	int aux0, aux1;
	Arc aux2;
	List<Arc> agregar = new Lista<Arc>();
	int cota = 0;
	int bound = 0;
	
	if(numNodes > numArcs)	cota = numNodes;
	if(numNodes <= numArcs)	cota = numArcs;

	for(int i = 0; i < numNodes ; i++){
		aux2 = new Arc(i, i);
		agregar.add(aux2);
	}
	
	while(!agregar.isEmpty()) {
		for(int i = 0 ; i < numNodes ; i++){
			List<Integer> n = g0.getSucesors(i);
			List<Integer> m = g0.getPredecesors(i);
			for(int k = 0 ; k < m.size() ; k++){
				aux0 =(int) m.get(k);
				for(int j = 0 ; j < n.size() ; j++){
					aux1 = (int) n.get(j);
					aux2 = new Arc(aux0, aux1);
					if (!g0.isArc(aux0, aux1)) agregar.add(aux2);
				}
			}
		}
												
		for(int h = 0 ; h < agregar.size()  ; h++){					
			aux2 = agregar.get(h);							
			aux0 = aux2.getSrc();							
			aux1 = aux2.getDst();							
			agregar.remove(aux2);
			if(!g0.isArc(aux0, aux1))g0.addArc(aux0, aux1);
		}
		bound++;

	}

	return g0;
    }

    @Override
    public String toString() {
	String graph = "";
	for(int i = 0 ; i < numNodes ; i++){
		
	}
      	graph=graph+"\nInArcs: \n";
	for (int i=0;i<numNodes;i++) {
		if (this.inArcs[i]!=null) {
		graph=graph+" "+ i +" "+ this.inArcs[i].toString();
		}
	}
	graph=graph+ "\nOutArcs: \n";
	for (int j=0;j<numNodes;j++) {
			if (this.outArcs[j]!=null) {
		graph=graph+" "+ j +" "+ this.outArcs[j].toString();
		}
	}
	   
	return graph;
	   
    }

    @Override
    public boolean isArc(int src, int dst) {
        boolean esArco=false;
		
		Arc a = new Arc(src, dst);
		esArco = inArcs[dst].contains((Arc)a);	
		return esArco;
    }
	
	public static void main (String[] args) {			//Revisar casos en que los parametros no existan. en DiGraphMatrix tambien
		DiGraphList gafo=new DiGraphList(5);
		
		//gafo.addNodes(3);
		gafo.addArc(0,1);
		gafo.addArc(1,2);
		gafo.addArc(2,3);
		gafo.addArc(3,5);
		gafo.addArc(4,0);
		gafo.addArc(3,4);
		gafo.addNodes(2);
		gafo.addArc(0,5);
		gafo.addArc(6,0);
		String s = "Grafo Original: "+gafo.toString();
		System.out.println(s);
		DiGraph gafo2 = gafo.royWarshall();
		s = "RW:\n"+gafo2.toString();
		System.out.println(s);
		/*List<Integer> d=gafo.getPredecesors(6);
		int degree= gafo.getDegree(6);
		String f=d.toString();
		int m=gafo.getNumberOfArcs();
		
	
		
		
		
		String j=gafo.toString();
		
		System.out.println(f);
	
		*/
	
	}


}














 


