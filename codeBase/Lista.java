public class List<E> implements List {

	private Nodo ele;
	
	private class Nodo() {
		private Object info;
		private Nodo sig;
		
		public Nodo() {
			info=null;
			sig=null;
		}	
	}
	
	public List<E> () {
		ele=new Nodo();
	}
	
	 /**
     * Agrega <i>element</i> a la lista.
     *
     * @param Elemento de tipo E, con el que se declaro el objeto
     * lista particular,
     * @return true si el elemento fue insertado, false en caso contrario
     */

    public boolean add(E element) {
		boolean added=false;
		
	
	}
    /**
     * Agrega <i>element</i> a la lista en la posicion <i>i</i>, si <i> &gt;
     * que size() el elemento se agrega al final de lista.
     *
     * @param Elemento de tipo E, con el que se declaro el objeto
     * lista particular,
     * @return true si el elemento fue insertado, false en caso contrario
     */

    public boolean add(int index, E element);

    /**
     * Elimina todos los elementos de la lista. La lista debe quedar como recien creada.
     *
     */

    public void clear();

    /**
     * Retorna una nueva {@code List} con los mismos elementos que esta
     * {@code List}.
     *
     * @return una lista con los mismos elementosque esta lista
     * @see java.lang.Cloneable
     */

    public List clone();

    /**
     * Determina si el objeto <i>o</i> esta contenidoe n la lista. {@code Object equals}
     *
     * @see Object#equals
     */
    public boolean contains(Object o);

    /**
     * Determina si el objeto <i>o</i> es igual a la lista actual. 
     *
     * @param la lista con la que se desea comparar
     * 
     * @return true si las dos listas tienen el mismo tamaño y contienen los mismos
     * objetos en el mismo orden. false en caso contrario
     *
     */

    public boolean equals(List<Integer> o) {
		boolean equal=true;
		while (ele!=null && o!=null && equal) {
			if (ele.info!=o.info) {
				equal=false;
			}
		}
		return equal;
	}

    /**
     * Devuelve el elemento al macenado en la posicion index de la lista.
     *
     * @param index posicion del elemento a devolver.
     * 
     * @return null si index &gt; size()
     */
    public int get(int index) {
		int element=Integer.MAX_VALUE;
		int k=0;
		boolean encontrado=false;
		while (ele!=null && !encontrado) {
			if (k==index) {
				element=ele.info;
				encontrado=true;
			}
			ele=ele.sig;
			k++;
		}
		return element;
	}
		
			

    /**
     * Determina la posicion del elemento <i>o</i> en la lista
     * 
     * @param o el objeto
     * @return si el elemento esta en la lista retorna suy posicion, sino -1
     */
    public int indexOf(Object o) { 
		int posi=-1;
		int k=0;
		while (ele!=null && ele.info!=(int) o) {
			ele=ele.sig;
			k++;
		}
		if (ele!=null) {
			posi=k;
		}
		return posi;
	}	
		

    /**
     * Determina si la lista no tiene elementos.
     *
     * @return true si size() &eq; 0. falso en caso contrario
     */
    public boolean isEmpty() {
		boolean empty=(size()>0);
		return empty;
	}

    /**
     * Elimina el elemento en la posicion index.
     *
     * @param index la posicion del elemento a eliminar, 0 &le; index &lt; size()
     * @return el elemento eliminado, si no se elimino ningun elemento retorna
     * null
     */
    public E remove(int index);
    
    /**
     * Elimina el elemento <i>o</i>.
     *
     * @param o el elemento a eliminar.
     * @return true si el elemento existia y fue eliminado, false en caso contrario.
     * null
     */
    public boolean remove(Object o);

    /**
     * Retorna el numero de elementos enla lista
     *
     * @return el numero de elementos en la lista
     */
    public int size() {
		int tam=0;
		while (ele!=null) {
			tam++;
			ele=ele.sig;
		}
		return tam;
	}

    /**
     * Retorna un nuevo arreglo que contiene todos los elementos
     * en esta lista {@code List}.
     *
     * @return an array of the elements from this {@code LinkedList}.
     */

    public Object[] toArray() {
		(int)[] arrayList =new (int)[size()];
		int k=0;
		while (k<arrayList.length && ele!=null) {
			arrayList[k]=ele.info;
			k++;
		}
		return arrayList;
	}

    /**
     * Retorna la retpresentacion en String de esta {@code List}
     *
     * @return la retpresentacion en String de esta {@code List}
     */
    @Override
    public String toString() { 
		String contenido="";
		while (ele!=null) {
			contenido=contenido +" "+ele.info;
			ele=ele.sig;
		}
		return contenido;
		
	}
	
}

		
		
			
		