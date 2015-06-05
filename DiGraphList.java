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
import java.util.*;

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
		int n = 0;
		int m = 0;


		numArcs=0;

		if ((s=in.readLine())!=null) {
			tokens=s.split("\\b\\s");
			n=Integer.valueOf(tokens[0].trim()).intValue();
			m = Integer.valueOf(tokens[1].trim()).intValue();
			numNodes=n;
			inArcs = new Lista[n];
			outArcs = new Lista[n];
			for(int i = 0 ; i < numNodes ; i++){
				inArcs[i] = new Lista<Arc>();
				outArcs[i] = new Lista<Arc>();
			}
		}

		for (int i=0;i < m;i++) {
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
	/**
     * Genera una copia de este DiGraph.
     * @return una copia de este DiGraph.
     */
   @Override
   public DiGraphList clone() {
      DiGraphList clon = new DiGraphList(this);
	  return clon;
   }
    /**
     * Permite agregar <i>num</i> nuevos nodos a este DiGraph.
     *
     * @param num numero de nodos a a gregar
     */
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
		/**
     * Agrega un arco a este DiGraph
     *
     * @param src nodo fuente del arco
     * @param dst nodo destino del arco
     * @return El arco agregado
     *
     */
    public Arc addArc(int src, int dst) {
	if(0 > src || src >= numNodes || 0 > dst || dst >= numNodes || isArc(src, dst)) return null;
        Arc a0 = new Arc(src, dst);
	numArcs++;

	for(int i = 0 ; i < inArcs[dst].size() ; i++){
		Arc aux0 = inArcs[dst].get(i);
		if (src < aux0.getSrc()) inArcs[dst].add(i , (Arc)a0);
	}
		
	for(int k = 0 ; k < outArcs[src].size() ; k++){
		Arc aux0 = outArcs[src].get(k);
		if (dst < aux0.getDst()) outArcs[src].add(k , (Arc)a0);
	}
	return a0;
    }
	/**
     * Agrega un arco a este DiGraph
     *
     * @param src nodo fuente del arco
     * @param dst nodo destino del arco
     * @param costo del arco
     * @return El arco agregado
     *
     */
    public Arc addArc(int src, int dst, double costo) {
	if(0 > src || src >= numNodes || 0 > dst || dst >= numNodes || isArc(src, dst)) return null;
      	Arc a0 = new Arc(src, dst);
	a0.setCost(costo);
	numArcs++;
	inArcs[dst].add(a0);
	outArcs[src].add(a0);
	return a0;
    }
	/**
	     * Retorna la lista de predecesores del nodo nodeId
	     * 
	     * @param nodeId el id del nodo del que se quierenlos predecesores
	     * 
	     * @return lista de predecesores de nodeId
	     */
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
	 /**
	     * Retorna la lista de sucesores del nodo nodeId
	     *
	     * @param nodeId el id del nodo del que se quieren los sucesores
	     *
	     * @return lista de sucesores de nodeId
	     */
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
	 /**
	     * Retorna el Arco cuyo nodo fuente es nodoSrc y nodo destino es nodoDst.
	     *
	     * @param nodoSrc nodo fuente
	     * @param nodoDst nodo destino
	     *
	     * @return Arc cuya fuente es nodoSrc y destino nodosDst, false en caso de que
	     *no exista.
	     */

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
	 /**
	     * Carga en este DiGraph, el grafo contenido en el archivo
	     * 
	     * @param fileName nombre del archivo que contiene la representacion del
	     * grafo a cargar
	     * 
	     * @throws java.io.IOException
	     */
    public void read(String fileName) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
	  /**
	     * Escribe este DiGraph en un archivo en el formato establecido en el enunciado
	     *
	     * @param fileName nombre del archivo donde se escribira la representacion
	     * del grafo
	     *
	     * @throws java.io.IOException
	     */
    public void write(String fileName) throws IOException {
		PrintStream out=new PrintStream(fileName);
		if (out==null) throw new IOException
					    ("Arch");
		Integer b=new Integer(numNodes);
		Integer c=new Integer(numArcs);
		String a=b.toString();
		out.print(a);
		out.print(" ");
		String d= c.toString();
		out.println(d);

		for (int k=0;k<numNodes ;k++) {
			if (this.outArcs[k]!=null) {
				for (int j=0;j<outArcs[k].size();j++) {

				Arc arco=this.outArcs[k].get(j);


				int src=arco.getSrc();
				int dst=arco.getDst();
				Integer fuente = new Integer(src);
				Integer destino = new Integer(dst);
				String arcFuente = fuente.toString();
				out.print(arcFuente);
				out.print(" ");
				String arcDest= destino.toString();
				out.println(arcDest);
				}
			}
		}
    }
    /**
     * Retorna el grado de un nodo en este DiGraph.
     *
     * @param nodeId identificacion del nodo
     * @return el grado del nodo nodeId en este Grafo, -1 si el nodo no se
     * encuentra en el grafo
     */
    public int getDegree(int nodeId) {
	if(0 > nodeId || nodeId >= numNodes) return -1;
    	int resp = 0;
	resp = resp + getInDegree(nodeId)+getOutDegree(nodeId);
	return resp;
    }
    /**
     * Retorna el grado externo de un nodo en este DiGraph.
     *
     * @param nodeId identificacion del nodo
     * @return el grado externo del nodo nodeId en este Grafo, -1 si el nodo no
     * se encuentra en el grafo
     */
    public int getOutDegree(int nodeId) {
	if(0 > nodeId || nodeId >= numNodes) return -1;
    	int resp = this.outArcs[nodeId].size();
	return resp;
    }
/**
     * Retorna el grado interno de un nodo en este DiGraph.
     *
     * @param nodeId identificacion del nodo
     * @return el grado interno del nodo nodeId en este Grafo, -1 si el nodo no se
     * encuentra en el grafo
     */
    public int getInDegree(int nodeId) {
	if(0 > nodeId || nodeId >= numNodes) return -1;
    	int resp = this.inArcs[nodeId].size();
	return resp;
    }
   /**
     * Retorna el numero de nodos en el grafo
     *
     * @return numero de nodos en el grafo
     */
    public int getNumberOfNodes() {
    	return this.numNodes;
    }
/**
     * Retorna el numero de arcos en el grafo
     *
     * @return numero de arcos en el grafo
     */
    public int getNumberOfArcs() {
        return this.numArcs;
    }
/**
     * Retorna la lista de arcos que tienen a nodeId como fuente
     * @param nodeId identificador del nodo
     *
     * @return la lista de arcos que tienen a nodeId como fuente
     */
    public List<Arc> getOutEdges(int nodeId) {
	if(0 > nodeId || nodeId >= numNodes) return new Lista<Arc>();
        return this.outArcs[nodeId];
    }
 /**
     * Retorna la lista de arcos que tienen a nodeId como destino
     * @param nodeId identificador del nodo
     *
     * @return la lista de arcos que tienen a nodeId como destino
     */
    public List<Arc> getInEdges(int nodeId) {
	if(0 > nodeId || nodeId >= numNodes) return new Lista<Arc>();
        return this.inArcs[nodeId];
		}
  /**
     * Remueve un arco de este DiGraph
     *
     * @param nodeIniId nodo fuente del arco a eliminar
     * @param nodeFinId nodo destino del arco a eliminar
     *
     * @return arco eliminado, null en caso de que el arco no exista o no haya
     * sido eliminado
     */
    public Arc delArc(int nodeIniId, int nodeFinId) {
	if(0 > nodeIniId || nodeIniId >= numNodes || 0 > nodeFinId || 
		nodeFinId >= numNodes || !isArc(nodeIniId, nodeFinId)) return null;
        Arc deletedArc= new Arc(nodeIniId,nodeFinId);
	boolean deleted=this.inArcs[nodeFinId].remove((Arc)deletedArc);
	deleted= this.outArcs[nodeIniId].remove((Arc) deletedArc );
	numArcs--;
	return deletedArc;
    }
  /**
     * remieve todos los arcos de este grafo
     *
     * @return lista de arcos eliminados
     */
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
 /**
     * Invierte la direccion de un arco
     * @param nodeIniId nodo fuente del arco antes de invertirlo
     * @param nodeFinId nodo destino del arco antes de invertirlo
     * @return true si el arco fue invertido, false en caso contrario
     */
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
    /**
     * Invierte todos los arcos del DiGraph.
     *
     *
     * @return true si todos los arcos fueron invertidos, false en caso 
     * contrario. En caso de que algun nodo no puede ser invertido, el grafo
     * debe quedar sin alteraciones.
     */
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
    /**
     * Determina si el DiGraph g es igual a este DiGraph
     *
     * @param g el grafo con el que se quiere comparar
     * @return true si los dos DiGraph contienen los mismos nodos y los mismos
     * arcos
     */
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

	for(int i = 0; i < numNodes ; i++){
		aux2 = new Arc(i, i);
		g0.addArc(i, i);
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
	}

	return g0;
    }
  /**
     * Retorna la representacion en String de este DiGraph.
     * @return la representacion en String de este DiGraph.
     */
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
   /**
     * Indica si un arco existe en este DiGraph
     * 
     * @param src el id del nodo origen del arco
     * @param dst el id del nodo destino del arco
     * @return true si exite un arco desde el nodo src hasta el nodo dst.
     * false en caso contrario
     */
    @Override
    public boolean isArc(int src, int dst) {
        boolean esArco=false;
		
		Arc a = new Arc(src, dst);
		esArco = inArcs[dst].contains((Arc)a);	
		return esArco;
    }
															//Sujeta a Cambios
	

	public DiGraph reduccionTransitiva(){
		
		if(numNodes < 0) return null;
		DiGraphList g0 = clone();
		DiGraph g1= g0.royWarshall();

		for(int k = 0 ; k < numNodes ; k++){
			List<Integer> pred = g1.getPredecesors(k);
			for(int i = 0 ; i < pred.size() ; i++){
				int p0 = (int)pred.get(i);
				if ( g1.isArc(p0,k)&& (p0!=k) ) {
					List<Integer> sucesores = getSucesors(k);
					for(int j = 0 ; j < sucesores.size() ; j++){
						int s0 = (int)sucesores.get(j);
						if (g1.isArc(p0,s0) && g0.isArc(k,s0) &&(p0!=s0) && (s0!=k) )
			                            g0.delArc(p0,s0);
					}
				}
			}

		}

		return g0;

        }

        public List[] getInArcs () {
            return this.inArcs;
        }

        public List[] getOutArcs() {
            return this.outArcs;
        }

        public boolean[][] getMatrix() {
            return null;
        }




}














 


