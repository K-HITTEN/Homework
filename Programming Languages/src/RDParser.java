package programing;
import java.io.*;

public class RDParser {

	int token, ch,result=0,gresult = 0,equal=0,bool = 0,minus = 0,g =0;
	boolean torf,gtorf,storf,gstorf;
	int[] arr = new int[100];
	private PushbackInputStream input;
	
	RDParser(PushbackInputStream is) {
        input = is;
    }
    
    void error( ) {
        System.out.printf("syntax error");
        System.exit(1);
    }

    void command( ) {
        expr();
        if (token == '\n') {
        	if(bool == 0) {
        		System.out.println("값:"+result);
        	}else {
        		System.out.println("값:"+torf);
        	}
        	System.out.println("good syntax");
        }
        else error();
    }

    void match(int c) { 
        if (token == c) 
        	token = getToken();
        else error();
    }
    
    void expr( ) {
    	if(token == '!') {
    		arr[0] = token;
    		match('!');
    		expr();
    	}else {
    		bexp();
    		while (token == '&'|| token == '|') {
    			if(g ==0) {
    				storf = torf;
    			}else {
    				gtorf = torf;
    			}
            	if(token == '&') {
            		arr[0]= token;
            		match('&');
            		bexp();
            	}else {
            		arr[0]= token;
            		match('|');
            		bexp();
            	}
        	}
    	}
    }
    void bexp() {if(token == 't') {
		match('t');
		if(token == 'r') {
			match('r');
			if(token == 'u') {
				match('u');
				if(token == 'e') {
					match('e');
					if(bool == 0) {
						torf = true;
						}else {
							storf = torf;
							torf = true;
						}
					bool =1;
				}else {
					error();
				}
			}else {
				error();
			}
		}else {
			error();
		}
	}else if(token == 'f') {
		match('f');
		if(token == 'a') {
			match('a');
			if(token == 'l') {
				match('l');
				if(token == 's') {
					match('s');
					if(token == 'e') {
						match('e');
						if(bool == 0) {
							torf = false;
							}else {
								storf = torf;
								torf = false;
								if(arr[0]== '&') {
									if(storf==true && torf == true ) {
										torf = true;
									}else {
										torf = false;
									}
								}else if(arr[0] == '|') {
									if(storf == false && torf == false) {
										torf = false;
									}else {
										torf = true;
									}
								}
							}
						bool =1;
					}else {
						error();
					}
				}else {
					error();
				}
			}else {
				error();
			}
		}else {
			error();
		}
	}else {
    		aexp();
    		if (token == '='||token == '!'||token == '<' || token =='>') {
    			relop();
    			aexp();
    		}
		}
    }
    void relop(){
    	if(token == '=') {
    		match('=');
    		if(token == '=') {
    			arr[0] = token;
    			match('=');
    		}else {
    			error();
    		}
    	}else if(token == '!') {
    		match('!');
    		if(token == '=') {
    			match('=');
    			arr[0] = '!';
    			equal = 1;
    		}
    	}else if(token == '<') {
    		arr[0]=token;
    		match('<');
    		if(token == '='){
    			match('=');
    			equal =1;
    		}
    	}else if(token == '>') {
    		arr[0] = token;
    		match('>');
    		if(token == '='){
    			match('=');
    			equal =1;
    		}
    	}
    }
    
    void aexp() {
    	term();
    	while(token == '+'||token == '-') {
    		if(token == '+') {
    			arr[0]=token;
    			match('+');
     	   }else {
     		   arr[0]=token;
     		   match('-');
     	   }
            term();
    	}
    }

    void term( ) {
       factor();
       while (token == '*'|| token == '/') {
           if(token == '*') {
        	   arr[0]=token;
        	   match('*');
    	   }else {
    		   arr[0]=token;
    		   match('/');
    	   }
           factor();
       }
    }

    void factor() {
    	if (token == '-') {
    		match('-');
    		minus++;
    	}
        if (token == '(') {
        	match('(');
        	g++;
        	arr[100-g] = arr[0];
        	if(arr[0] == '&'||arr[0]=='|') { 
        		gstorf = torf;
        	}
        	arr[0] = 0;
            expr();
            match(')');
            arr[0] = arr[100-g];
            while(minus>0) {
            	gresult = -gresult;
            	minus--;
            }
            if(arr[0] == '-') {
            	result -= gresult;
            }else if(arr[0] == '*') {
            	result *= gresult;
            }else if(arr[0] == '/') {
            	result /= gresult;
            }else if (arr[0] == '=') {
        		if(result == gresult) {
        			torf = true;
        		}else {
        			torf = false;
        		} gresult = 0; bool =1;
        	}else if (arr[0] == '!') {
        		if(equal == 1) {
        			if(result != gresult) {
        				torf = true;
        			}else {
        				storf = false;
        			} gresult = 0; bool =1; equal=1;
        		}else {
        			if (torf = true) {
        				torf = false;
        			}else {
        				torf = true;
        			}
        		}
        	}else if (arr[0] == '<') {
        		if((result < gresult && equal==0) || (result <= gresult &&equal ==1)) {
        			torf = true;
        			equal = 0;
        		}else {
        			torf = false;
        		} gresult = 0; bool =1;
        	}else if (arr[0] == '>') {
        		if((result > gresult && equal==0) || (result >= gresult &&equal ==1)) {
        			torf = true;
        			equal = 0;
        		}else {
        			torf = false;
        		} gresult = 0; bool = 1;
        	}else if(arr[0]=='&'){
        		if(gstorf == true && torf == true) {
        			torf = true;
        		}else {
        			torf =false;
        		}
        	}else if(arr[0]=='|'){
        		if(gstorf == false && torf == false) {
        			torf = false;
        		}else {
        			torf =true;
        		}
        	}else if(arr[0] == 0 || arr[0] == '+') {
            	result += gresult;
            }
            gresult =0;
            g--;
            
            
        }else {
            number();
        }
    }

    void number() {
    	try {
    	int resultb=0;
    	int n =1;
    	arr[n] = Integer.parseInt(Character.toString((char)token));
    	digit();
    	while (Character.isDigit(token)) {
    		n++;
    		arr[n] = Integer.parseInt(Character.toString((char)token));
    		digit();
    	}
    	for(int i=1; i<=n;i++) {
    		resultb+=arr[i];
    		if(i<n) {
    			resultb*=10;
    		}
    	}
    	while(minus > 0) {
    		if(g == 0) {
    		resultb = -resultb;
    		minus--;
    		}else {
    			break;
    		}
    	}
    	if(g == 1) {
    		if(arr[0] == '-') {
        		gresult -= resultb;
        	}else if (arr[0] == '*') {
        		gresult *= resultb;
        	}else if (arr[0] == '/') {
        		gresult /= resultb;
        	}else if (arr[0] == '=') {
        		if(gresult == resultb) {
        			torf = true;
        		}else {
        			torf = false;
        		} gresult = 0; bool =1;
        	}else if (arr[0] == '!') {
        		if(equal == 1) {
        			if(gresult != resultb) {
        				torf = true;
        			}else {
        				torf = false;
        			} gresult = 0; bool =1; equal=1;
        		}else {
        			if (torf = true) {
        				torf = false;
        			}else {
        				torf = true;
        			}
        		}
        	}else if (arr[0] == '<') {
        		if((gresult < resultb && equal==0) || (gresult <= resultb &&equal ==1)) {
        			torf = true;
        			equal = 0;
        		}else {
        			torf = false;
        		} gresult = 0; bool =1;
        	}else if (arr[0] == '>') {
        		if((gresult > resultb && equal==0) || (gresult >= resultb &&equal ==1)) {
        			torf = true;
        			equal = 0;
        		}else {
        			torf = false;
        		} gresult = 0; bool = 1;
        	}else if(arr[0]=='&'){
        		if(gtorf == true && torf == true) {
        			torf = true;
        		}else {
        			torf =false;
        		}
        	}else if(arr[0]=='|'){
        		if(gtorf == false && torf == false) {
        			torf = false;
        		}else {
        			torf =true;
        		}
        	}else {
        		gresult += resultb;
        		}
    	}else {
    	if(arr[0] == '-') {
    		result -= resultb;
    	}else if (arr[0] == '*') {
    		result *= resultb;
    	}else if (arr[0] == '/') {
    		result /= resultb;
    	}else if (arr[0] == '=') {
    		if(result == resultb) {
    			torf = true;
    		}else {
    			torf = false;
    		} result = 0; bool =1;
    	}else if (arr[0] == '!') {
    		if(equal == 1) {
    			if(result != resultb) {
    				torf = true;
    			}else {
    				torf = false;
    			} result = 0; bool = 1; equal =0;
    		}else {
        		if(torf == true) {
        			torf = false;
        		}else {
        			torf = true;
        		}
    		}
    	}else if (arr[0] == '<') {
    		if((result < resultb && equal==0) || (result <= resultb &&equal ==1)) {
    			torf = true;
    			equal = 0;
    		}else {
    			torf = false;
    		} result = 0; bool =1;
    	}else if (arr[0] == '>') {
    		if((result > resultb && equal==0) || (result >= resultb &&equal ==1)) {
    			torf = true;
    			equal = 0;
    		}else {
    			torf = false;
    		} result = 0; bool = 1;
    	}else if(arr[0]=='&'){
    		if(storf == true && torf == true) {
    			torf = true;
    		}else {
    			torf =false;
    		}
    	}else if(arr[0]=='|'){
    		if(storf == false && torf == false) {
    			torf = false;
    		}else {
    			torf =true;
    		}
    	}else {
    		result += resultb;
    		}
    	}
    	}catch(NumberFormatException exp){
    		System.out.println("syntax error");
    		error();
    	}
    }
    
    void digit() {
    	if (Character.isDigit(token))
    		match(token);
    	else
    		error();
    }
    
	int getToken() {
        while(true) {
            try  {
	            ch = input.read();
                if (ch == ' ' || ch == '\t' || ch == '\r') ;
                else 
                	return ch;
	        } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
        
    void parse( ) {
        token = getToken();
        command();          
    }
    
	public static void main(String[] args) { 
		RDParser parser = new RDParser(new PushbackInputStream(System.in));
        while(true) {
            System.out.print(">> ");
            parser.parse();
            parser.result=0;
            parser.gresult = 0;
            parser.equal=0;
            parser.bool = 0;
            parser.minus = 0;
            parser.g =0;
            parser.arr[0] = 0;
        }
    }

}
