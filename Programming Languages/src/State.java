package programing;
import java.util.*;

class Pair {
   Identifier id;
   Value val;
   
   Pair (Identifier id, Value v) {
     this.id = id;
     this.val = v;
   }
}

class State extends Stack<Pair> {
    public State( ) { }
    
    public State(Identifier id, Value val) {
        push(id, val);
    }
    
    public State push(Identifier id, Value val) {
        super.push(new Pair(id, val));
	return this;
    }

	
    public int lookup (Identifier v) {
       for (int i=size()-1; i>=0; i--) 
         if (v.equals(((Pair)get(i)).id))
            return i;
       
       for (int i= 0; i <= size()-1; i++) 
         if (v.equals(((Pair)get(i)).id))
            return i;

       return -1;
   }

    // get the value of a variable in a State
    public Value get (Identifier id) {
	int i = lookup(id);
        if (i == -1) 
            return null;
        Pair p = (Pair)(get(i));
        return (Value)(p.val);
    }

    public State set(Identifier id, Value val) {
	int i = lookup(id);
        if (i != -1) 
            super.set(i, new Pair(id, val));  
	return this;
    }
    
    public void display() {
    	int x = this.size()-1;
    	while(x>=0) {
    		Pair p = (Pair)(get(x));
    		p.id.display(1);
    		if(p.val == null) {
    			System.out.println("\n Value: 0");
    		}else {
    			p.val.display(1);}
    		System.out.println();
    		x--;
    	}
    }
}
