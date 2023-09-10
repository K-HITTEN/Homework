package programing;
import java.util.Scanner;

public class Sint {
    static Scanner sc = new Scanner(System.in);
    static State state = new State();

    State Eval(Command c, State state) { 
	if (c instanceof Decl) {
	    Decls decls = new Decls();
	    decls.add((Decl) c);
	    return allocate(decls, state);
	}

	if (c instanceof Function) {
	    Function f = (Function) c; 
	    state.push(f.id, new Value(f)); 
	    return state;
	}

	if (c instanceof Stmt)
	    return Eval((Stmt) c, state); 
		
	    throw new IllegalArgumentException("no command");
    }
  
    State Eval(Stmt s, State state) {
        if (s instanceof Empty) 
	        return Eval((Empty)s, state);
        if (s instanceof Assignment)  
	        return Eval((Assignment)s, state);
        if (s instanceof If)  
	        return Eval((If)s, state);
        if (s instanceof While)  
	        return Eval((While)s, state);
        if (s instanceof Stmts)  
	        return Eval((Stmts)s, state);
	    if (s instanceof Let)  
	        return Eval((Let)s, state);
	    if (s instanceof Read)  
	        return Eval((Read)s, state);
	    if (s instanceof Print)  
	        return Eval((Print)s, state);
        if (s instanceof Call) 
	        return Eval((Call)s, state);
	    if (s instanceof Return) 
	        return Eval((Return)s, state);
	    if (s instanceof doWhile) 
		    return Eval((doWhile)s, state);
		if (s instanceof For) 
		    return Eval((For)s, state);
        throw new IllegalArgumentException("no statement");
    }

    // call without return value
    State Eval(Call c, State state) {
    	Value v = state.get(c.fid);
    	Function f = v.funValue();
    	State s = newFrame(state, c, f);
    	s = Eval(f.stmt, state);
    	for(int i = 0; i <c.args.size(); i++) {
    		s.set((Identifier)c.args.get(i), s.get(f.params.get(i).id));
    	}
    	s = deleteFrame(s, c, f);
	    return s;
    }

    // value-returning call 
    Value V (Call c, State state) { 
	    Value v = state.get(c.fid);  			// find function
        Function f = v.funValue();
        State s = newFrame(state, c, f);	// create new frame on the stack
        s = Eval(f.stmt, s); 						// interpret the call
	    v = s.peek().val;							// get the return value  v = s.get(new Identifier("return")); 
        s = deleteFrame(s, c, f); // delete the frame on the stack
    	return v;
    }

    State Eval(Return r, State state) {
        Value v = V(r.expr, state);
		return state.set(new Identifier("return"), v); 
    }

    State newFrame (State state, Call c, Function f) {
        if (c.args.size() == 0) 
            return state;
        Value val[] = new Value[f.params.size()];
        int i = 0;
        Decls decls= new Decls();
        for(Expr e: c.args) {
        	val[i] = V(e,state);
        	decls.add(f.params.get(i));
        	decls.get(i).expr = val[i];
        	allocate(decls,state);
        	i++;
        }
        state.push(new Identifier("return"), null);
        return state;
    }

    State deleteFrame (State state, Call c, Function f) {
	    state.pop();  // pop the return value
	    for(int i = 0; i <f.params.size(); i++)
	    	free(f.params,state);
	    return state;
    }

    State Eval(Empty s, State state) {
        return state;
    }
  
    State Eval(Assignment a, State state) {
        Value v = V(a.expr, state);
        if(a.ar != null) {
    		Value vs = state.get(a.ar.id);
    		System.out.println(vs.value());
    		Value[] arr = vs.arrValue();
    		arr[((Value)a.ar.expr).intValue()] = (Value)a.expr;
    		Value value = new Value(arr);
     		return state.set(a.ar.id, value);
    	}
	    return state.set(a.id, v);
    }

    State Eval(Read r, State state) {
        if (r.id.type == Type.INT) {
	        int i = sc.nextInt();
	        state.set(r.id, new Value(i));
	    } 

	    if (r.id.type == Type.BOOL) {
	        boolean b = sc.nextBoolean();	
            state.set(r.id, new Value(b));
	    }

		if (r.id.type == Type.STRING) {
			String s = sc.nextLine();
			state.set(r.id, new Value(s));
		}
	    return state;
    }

    State Eval(Print p, State state) {
	    System.out.println(V(p.expr, state));
        return state; 
    }
  
    State Eval(Stmts ss, State state) {
        for (Stmt s : ss.stmts) {
            state = Eval(s, state);
            if (s instanceof Return)  
                return state;
        }
        return state;
    }
  
    State Eval(If c, State state) {
        if (V(c.expr, state).boolValue( ))
            return Eval(c.stmt1, state);
        else
            return Eval(c.stmt2, state);
    }
 
    State Eval(While l, State state) {
        if (V(l.expr, state).boolValue( ))
            return Eval(l, Eval(l.stmt, state));
        else 
	        return state;
    }
    
    State Eval(doWhile l, State state) {
    	int i = 0;
    	while(V(l.expr,state).boolValue()) {
    		i++;
    		Eval(l.stmt,state);
    	}
		if(i == 0) {
			Eval(l.stmt,state);
		}
    	return state;
    }
    
    State Eval(For l, State state) {
    	Decls decls = new Decls();
	    decls.add((Decl)l.decl);
	    allocate(decls, state);
	    if(V(l.expr,state).boolValue()) {
	    	while(V(l.expr,state).boolValue()) {
	    		Eval(l.stmt2,state);
	    		Eval(l.stmt1,state);
	    	}
    		return state;
	    }else
    		return state;
    }

    State Eval(Let l, State state) {
        State s = allocate(l.decls, state);
        s = Eval(l.stmts,s);
	    return free(l.decls, s);
    }

    State allocate (Decls ds, State state) {
    	if(ds != null) {
    		int x = ds.size();
    		Decl a = ds.get(x-1);
    		if(a.arraysize>0) {
    			Value arr[] = new Value[a.arraysize];
    			state.push(a.id,new Value(arr));
    		}else {
    			Value v = V(a.expr, state);
    			state.push(a.id,v);
    		}
    	}
    	
        return state;
    }

    State free (Decls ds, State state) {
    	if (ds != null) {
    		state.pop();
    	}
        return state;
    }

    Value binaryOperation(Operator op, Value v1, Value v2) {
        check(!v1.undef && !v2.undef,"reference to undef value");
	    switch (op.val) {
	    case "+":
            return new Value(v1.intValue() + v2.intValue());
        case "-": 
            return new Value(v1.intValue() - v2.intValue());
        case "*": 
            return new Value(v1.intValue() * v2.intValue());
        case "/": 
            return new Value(v1.intValue() / v2.intValue());
        case ">":
        	return new Value(v1.intValue() > v2.intValue());
        case ">=":
        	return new Value(v1.intValue() >= v2.intValue());
        case "<":
        	return new Value(v1.intValue() < v2.intValue());
        case "<=":
        	return new Value(v1.intValue() <= v2.intValue());
        case "==":
        	return new Value(v1.intValue() == v2.intValue());
        case "!=":
        	return new Value(v1.intValue() != v2.intValue());
        case "&":
        	return new Value(v1.boolValue() && v2.boolValue());
        case "|":
        	return new Value(v1.boolValue() || v2.boolValue());
	    default:
	        throw new IllegalArgumentException("no operation");
	    }
    } 
    
    Value unaryOperation(Operator op, Value v) {
        check( !v.undef, "reference to undef value");
	    switch (op.val) {
        case "!": 
            return new Value(!v.boolValue( ));
        case "-": 
            return new Value(-v.intValue( ));
        default:
            throw new IllegalArgumentException("no operation: " + op.val); 
        }
    } 

    static void check(boolean test, String msg) {
        if (test) return;
        System.err.println(msg);
    }

    Value V(Expr e, State state) {
        if (e instanceof Value) 
            return (Value) e;

        if (e instanceof Identifier) {
	        Identifier v = (Identifier) e;
            return (Value)(state.get(v));
	    }

        if (e instanceof Binary) {
            Binary b = (Binary) e;
            Value v1 = V(b.expr1, state);
            Value v2 = V(b.expr2, state);
            return binaryOperation (b.op, v1, v2); 
        }

        if (e instanceof Unary) {
            Unary u = (Unary) e;
            Value v = V(u.expr, state);
            return unaryOperation(u.op, v); 
        }

        if (e instanceof Call) 
    	    return V((Call)e, state);  
        
        if (e instanceof Array) {
        	Value val = (Value)(state.get(((Array) e).id));
        	Value[] s = val.arrValue();
        	Value v = (Value)((Array)e).expr;
            return (Value) s[v.intValue()];
        }
        
        if (e == null) {
        	return (Value)null;
        }
        throw new IllegalArgumentException("no operation");
    }

    public static void main(String args[]) {
	    if (args.length == 0) {
	        Sint sint = new Sint(); 
			Lexer.interactive = true;
            System.out.println("Language S Interpreter 2.0");
            System.out.print(">> ");
	        Parser parser  = new Parser(new Lexer());

	        do { // Program = Command*
	            if (parser.token == Token.EOF)
		            parser.token = parser.lexer.getToken();
	       
	            Command command=null;
                try {
	                command = parser.command();
                    // command.display(0);    // display AST    
	                // System.out.println("\nType checking...");
                    // command.type = TypeChecker.Check(command); 
                    // System.out.println("\nType: "+ command.type); 
                } catch (Exception e) {
                    System.out.println(e);
		            System.out.print(">> ");
                    continue;
                }

	            if (command.type != Type.ERROR) {
                    // System.out.println("\nInterpreting..." );
                    try {
                        state = sint.Eval(command, state);
                    } catch (Exception e) {
                         System.err.println(e);  
                    }
                    // System.out.println("\nFinal State");
                    // state.display( );
                }
		    System.out.print(">> ");
	        } while (true);
	    }
        else {
	        // System.out.println("Begin parsing... " + args[0]);
	        Command command = null;
	        Parser parser  = new Parser(new Lexer(args[0]));
	        Sint sint = new Sint();

	        do {	// Program = Command*
	            if (parser.token == Token.EOF)
                    break;
	         
                try {
                	command = parser.command();
		             // command.display(0);      // display AST
		             // System.out.println("\nType checking..." + args[0]);
                     // command.type = TypeChecker.Check(command);    
                     // System.out.println("\nType: "+ command.type);  
                } catch (Exception e) {
                    System.out.println(e);
                    continue;
                }

	            if (command.type!=Type.ERROR) {
                    System.out.println("\nInterpreting..." + args[0]);
                    try {
                        state = sint.Eval(command, state);
                    } catch (Exception e) {
                        System.err.println(e);  
                    }
					// System.out.println("\nFinal State");
                    // state.display( );
                }
	        } while (command != null);
        }        
    }
}
