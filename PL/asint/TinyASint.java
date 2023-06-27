package asint;

public class TinyASint {

    public static abstract class Prog {
        private Decs decs;
        private Is is;
        public Prog(Decs decs, Is is) {
            this.decs = decs;
            this.is = is;
        }

        public Decs decs(){return decs;}
        public Is is(){return is;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        } 
    }
	
	public static abstract class Exp  {
	       public Exp() {
	       }   
	       public abstract int prioridad();
	       public abstract void procesa(Procesamiento procesamiento);
	    }
	
	 public static class StringLocalizado {
	     private String s;
	     private int fila;
	     private int col;
	     
	     public StringLocalizado(String s, int fila, int col) {
	         this.s = s;
	         this.fila = fila;
	         this.col = col;
	     }
	     
	     public int fila() {return fila;}
	     public int col() {return col;}
	     
	     public String toString() {
	       return s;
	     }
	     
	     public boolean equals(Object o) {
	         return (o == this) || (
	                (o instanceof StringLocalizado) &&
	                (((StringLocalizado)o).s.equals(s)));                
	     }
	     public int hashCode() {
	         return s.hashCode();
	     }
	 }
	 
	 private static abstract class ExpBin extends Exp {
	        private Exp arg0;
	        private Exp arg1;
	        public Exp arg0() {return arg0;}
	        public Exp arg1() {return arg1;}
	        public ExpBin(Exp arg0, Exp arg1) {
	            super();
	            this.arg0 = arg0;
	            this.arg1 = arg1;
	        }
	 }
	 
	// Nivel 0: operador relacional (<, >, <=, >=, ==, !=) no asociativos.
	 private static abstract class ExpOpe extends ExpBin {
	        public ExpOpe(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public final int prioridad() {
	            return 0;
	        }
	  }
	 
	 public static class Menor extends ExpOpe {
	        public Menor(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public void procesa(Procesamiento p) {
	           p.procesa(this); 
	        }     
	 }
	 
	 public static class Mayor extends ExpOpe {
	        public Mayor(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public void procesa(Procesamiento p) {
	           p.procesa(this); 
	        }     
     }
	 
	 public static class MenorIgual extends ExpOpe {
	        public MenorIgual(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public void procesa(Procesamiento p) {
	           p.procesa(this); 
	        }     
	 }
	 
	 public static class MayorIgual extends ExpOpe {
	        public MayorIgual(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public void procesa(Procesamiento p) {
	           p.procesa(this); 
	        }     
	 }
	 
	 public static class Igual extends ExpOpe {
	        public Igual(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public void procesa(Procesamiento p) {
	           p.procesa(this); 
	        }     
	 }
	 
	 public static class Distinto extends ExpOpe {
	        public Distinto(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public void procesa(Procesamiento p) {
	           p.procesa(this); 
	        }     
	 }
	 
	 
	// Nivel 1: + no asocia, - (binario) asocia a izquierdas. 
	 private static abstract class ExpAditiva extends ExpBin {
	        public ExpAditiva(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public final int prioridad() {
	            return 1;
	        }
	 }
	 
	 public static class Suma extends ExpAditiva {
	        public Suma(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public void procesa(Procesamiento p) {
	           p.procesa(this); 
	        }     
	 }
	 
	 public static class Resta extends ExpAditiva {
	        public Resta(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public void procesa(Procesamiento p) {
	           p.procesa(this); 
	        }     
	 }
	 
	 //Nivel 2: and no asocia, y or asocia a derechas.
	 
	 private static abstract class ExpAndOr extends ExpBin {
	        public ExpAndOr(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public final int prioridad() {
	            return 2;
	        }
	  }
	 
	 public static class And extends ExpAndOr {
	        public And(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public void procesa(Procesamiento p) {
	           p.procesa(this); 
	        }     
	 }
	 
	 public static class Or extends ExpAndOr {
	        public Or(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public void procesa(Procesamiento p) {
	           p.procesa(this); 
	        }     
	 }
	 
	 //Nivel 3: *, / y %. Operadores binarios, infijos, asociativos a izquierdas.

	 private static abstract class ExpMultiplicativa extends ExpBin {
	        public ExpMultiplicativa(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public final int prioridad() {
	            return 3;
	        }
	  }
	    
     public static class Mul extends ExpMultiplicativa {
        public Mul(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
     
    public static class Div extends ExpMultiplicativa {
        public Div(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class Mod extends ExpMultiplicativa {
        public Mod(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    private static abstract class ExpUn extends Exp {
        private Exp arg0;
        public Exp arg0() {return arg0;}
        public ExpUn(Exp arg0) {
            super();
            this.arg0 = arg0;
        }
    }
     
   //Nivel 4: - (unario) y not. Operadores unarios, prefijos, asociativos. 
	    
    private static abstract class ExpNotMenos extends ExpUn {
        public ExpNotMenos(Exp arg0) {
            super(arg0);
        }
        public final int prioridad() {
            return 4;
        }
    }
    
    public static class Menos extends ExpNotMenos {
        public Menos(Exp arg0) {
            super(arg0);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class Not extends ExpNotMenos {
        public Not(Exp arg0) {
            super(arg0);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
   // Nivel 5: Operadores de indexación, de acceso a registro y de indirección.
    
    //enteros :  string → Exp 
    public static class Enteros extends Exp {
        private StringLocalizado ent;
        public Enteros(StringLocalizado ent) {
            super();
            this.ent = ent;
        }
        public StringLocalizado ent() {return ent;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 6;
        }
    }
    
    //reales :  string → Exp
    public static class Reales extends Exp {
        private StringLocalizado real;
        public Reales(StringLocalizado real) {
            super();
            this.real = real;
        }
        public StringLocalizado real() {return real;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 6;
        }
    }
    
    //booleanos : string → Exp
    public static class Booleanos extends Exp {
        private StringLocalizado bool;
        public Booleanos(StringLocalizado bool) {
            super();
            this.bool = bool;
        }
        public StringLocalizado bool() {return bool;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 6;
        }
    }
    //cadena : string → Exp
    public static class String_exp extends Exp {
        private StringLocalizado str;
        public String_exp(StringLocalizado str) {
            super();
            this.str = str;
        }
        public StringLocalizado str() {return str;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 6;
        }
    }
    
    //id : string → Exp
    public static class Id extends Exp {
        private StringLocalizado id;
        public Id(StringLocalizado id) {
            super();
            this.id = id;
        }
        public StringLocalizado id() {return id;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 6;
        }
    }
    
    //dref : Exp → Exp 
    
    public static class Dref extends Exp {
        private Exp arg0;
        public Dref(Exp arg0) {
            super();
            this.arg0 = arg0;
        }
        public Exp arg0() {return arg0;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 6;
        }
    }
    
    //acc : Exp x  string → Exp
    public static class Acc extends Exp {
        private Exp arg0;
        private StringLocalizado str;
        public Acc(Exp arg0, StringLocalizado str) {
            super();
            this.arg0 = arg0;
            this.str = str;
        }
        public Exp arg0() {return arg0;}
        public StringLocalizado str() {return str;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 6;
        }
    }
    //indx : Exp x  Exp → Exp
    
    public static class Indx extends Exp {
        private Exp arg0;
        private Exp arg1;
        public Indx(Exp arg0, Exp arg1) {
            super();
            this.arg0 = arg0;
            this.arg1 = arg1;
        }
        public Exp arg0() {return arg0;}
        public Exp arg1() {return arg1;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 6;
        }
    }




    //Declaraciones
    public static class Dec  {
        private Exp type;
        private StringLocalizado id;
        private StringLocalizado val;
        public Dec() {
            this.type = null;
            this.id = null;
            this.val = null;
        }
        public Dec(Exp type, StringLocalizado id, StringLocalizado val) {
            this.type = type;
            this.id = id;
            this.val = val;
        }
        public Exp type() {return type;}
        public StringLocalizado id() {return id;}
        public StringLocalizado val() {return val;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static abstract class Decs {
        public Decs() {
        }
        public abstract void procesa(Procesamiento p);
     }
    
    public static class Decs_ninguna extends Decs {
        private Dec dec; 
        public Decs_ninguna() {
           super();
           this.dec = new Dec();
        }   
        public Dec dec() {return dec;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
     }
    
    public static class Decs_una extends Decs {
        private Dec dec; 
        public Decs_una(Dec dec) {
           super();
           this.dec = dec;
        }   
        public Dec dec() {return dec;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
     }
    
    public static class Decs_muchas extends Decs {
        private Dec dec;
        private Decs decs;
        public Decs_muchas(Decs decs, Dec dec) {
           super();
           this.dec = dec;
           this.decs = decs;
        }
        public Dec dec() {return dec;}
        public Decs decs() {return decs;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
     }

     public static class Dec_proc extends Dec {
        private StringLocalizado id;
        private Ps ps;
        private Decs decs;
        private Is is;
        public Dec_proc() {
            this.id = null;
            this.ps = null;
            this.decs = null;
            this.is = null;
        }

        public Dec_proc(StringLocalizado id, Ps ps, Decs decs, Is is) {
            this.id = id;
            this.ps = ps;
            this.decs = decs;
            this.is = is;
        }

        public StringLocalizado id() {return id;}
        public Ps ps() {return ps;}
        public Decs decs() {return decs;}
        public Is is() {return is;}

        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }
    }

    public static class Pf {
        private Exp type;
        private StringLocalizado id;
        private StringLocalizado val;
        public Pf(Exp type, StringLocalizado id, StringLocalizado val) {
            this.type = type;
            this.id = id;
            this.val = val;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
    }

    public static abstract class Pfs {
        public Pfs() {
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
    }

    public static class Pfs_ninguno extends Pfs {
        private Pf pf;
        public Pfs_ninguno(){
            super();
            pf = new Pf();
        }
        public Pf pf() {return pf;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
    }

    public static class Pfs_uno extends Pfs {
        private Pf pf;
        public Pfs_uno(Pf pf){
            super();
            this.pf = pf;
        }
        public Pf pf() {return pf;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
    }

    public static class Pfs_muchos extends Pfs {
        private Pf pf;
        private Pfs pfs;
        public Pfs_muchos(Pfs pfs, Pf pf){
            super();
            this.pfs = pfs;
            this.pf = pf;
        }
        public Pf pf() {return pf;}
        public Pfs pfs() {return pfs;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
    }
  
    
    public static abstract class I {
        public I() {
        }
        public abstract void procesa(Procesamiento p);
     }
    
    public static abstract class Is {
        public Is() {
        }
        public abstract void procesa(Procesamiento p);
     }
    
    public static class Is_una extends Decs {
        private I i; 
        public Is_una(I i) {
           super();
           this.i = i;
        }   
        public I i() {return i;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
     }
    
    public static class Is_muchas extends Decs {
        private I i;
        private Is is;
        public Is_muchas(Is is, I i) {
           super();
           this.i = i;
           this.is = is;
        }
        public I i() {return i;}
        public Is is() {return is;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
     }
    
    //is_asig :  Exp  x Exp → I
    
    public static class Is_asig extends I {
        private Exp arg0;
        private Exp arg1;
        public Exp arg0() {return arg0;}
        public Exp arg1() {return arg1;}
        public Is_asig(Exp arg0, Exp arg1) {
            super();
            this.arg0 = arg0;
            this.arg1 = arg1;
        }
        
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
    }
    
    //is_if_then: Exp x Is → I

    public static class Is_if_then extends I {
        private Exp arg0;
        private Is arg1;
        public Exp arg0() {return arg0;}
        public Is arg1() {return arg1;}
        public Is_if_then(Exp arg0, Is arg1) {
            super();
            this.arg0 = arg0;
            this.arg1 = arg1;
        }
        
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
    }
    
    //is_if_then_else: Exp x Is x Is → I
    
    public static class Is_if_then_else extends I {
        private Exp arg0;
        private Is arg1;
        private Is arg2;
        public Exp arg0() {return arg0;}
        public Is arg1() {return arg1;}
        public Is arg2() {return arg2;}
        public Is_if_then_else(Exp arg0, Is arg1, Is arg2) {
            super();
            this.arg0 = arg0;
            this.arg1 = arg1;
            this.arg2 = arg2;
        }
        
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
    }
    
    //is_while : Exp x Is → I

    public static class Is_while extends I {
        private Exp arg0;
        private Is arg1;
        public Exp arg0() {return arg0;}
        public Is arg1() {return arg1;}
        public Is_while(Exp arg0, Is arg1) {
            super();
            this.arg0 = arg0;
            this.arg1 = arg1;
        }
        
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
    }
   
   // is_read: Exp → I

    
    public static class Is_read extends I {
        private Exp arg0;
        public Exp arg0() {return arg0;}
        public Is_read(Exp arg0) {
            super();
            this.arg0 = arg0;
        }
        
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
    }
    
    //is_write:  Exp → I
    
    public static class Is_write extends I {
        private Exp arg0;
        public Exp arg0() {return arg0;}
        public Is_write(Exp arg0) {
            super();
            this.arg0 = arg0;
        }
        
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
    }
    
    //is_nl : → I
    
    public static class Is_nl extends I {
        
        public Is_nl() {
            super();
        }
        
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
    }
    
    //is_new :   Exp → I
    
    public static class Is_new extends I {
        private Exp arg0;
        public Exp arg0() {return arg0;}
        public Is_new(Exp arg0) {
            super();
            this.arg0 = arg0;
        }
        
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
    }

    //is_delete : Exp → I
    
    public static class Is_delete extends I {
        private Exp arg0;
        public Exp arg0() {return arg0;}
        public Is_delete(Exp arg0) {
            super();
            this.arg0 = arg0;
        }
        
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
    }
    
    //is_proc :  Exp x Preals → I

    public static class Is_proc extends I {
        private Exp arg0;
        private Preals arg1;
        public Exp arg0() {return arg0;}
        public Preals arg1() {return arg1;}
        public Is_proc(Exp arg0, Preals arg1) {
            super();
            this.arg0 = arg0;
            this.arg1 = arg1;
        }
        
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
    }
    
    //is_seq : Decs x Is → I
    
    public static class Is_seq extends I {
        private Decs arg0;
        private Is arg1;
        public Decs arg0() {return arg0;}
        public Is arg1() {return arg1;}
        public Is_seq(Decs arg0, Is arg1) {
            super();
            this.arg0 = arg0;
            this.arg1 = arg1;
        }
        
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
    }

}
