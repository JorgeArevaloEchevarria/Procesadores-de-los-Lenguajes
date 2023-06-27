package asint;

import asint.TinyASint.Acc;
import asint.TinyASint.And;
import asint.TinyASint.Booleanos;
import asint.TinyASint.Dec;
import asint.TinyASint.Decs_muchas;
import asint.TinyASint.Decs_ninguna;
import asint.TinyASint.Decs_una;
import asint.TinyASint.Distinto;
import asint.TinyASint.Div;
import asint.TinyASint.Dref;
import asint.TinyASint.Enteros;
import asint.TinyASint.Exp;
import asint.TinyASint.Id;
import asint.TinyASint.Igual;
import asint.TinyASint.Indx;
import asint.TinyASint.Is_asig;
import asint.TinyASint.Is_delete;
import asint.TinyASint.Is_if_then;
import asint.TinyASint.Is_if_then_else;
import asint.TinyASint.Is_muchas;
import asint.TinyASint.Is_new;
import asint.TinyASint.Is_nl;
import asint.TinyASint.Is_proc;
import asint.TinyASint.Is_read;
import asint.TinyASint.Is_seq;
import asint.TinyASint.Is_una;
import asint.TinyASint.Is_while;
import asint.TinyASint.Is_write;
import asint.TinyASint.Mayor;
import asint.TinyASint.MayorIgual;
import asint.TinyASint.Menor;
import asint.TinyASint.MenorIgual;
import asint.TinyASint.Menos;
import asint.TinyASint.Mod;
import asint.TinyASint.Mul;
import asint.TinyASint.Not;
import asint.TinyASint.Or;
import asint.TinyASint.Reales;
import asint.TinyASint.Resta;
import asint.TinyASint.String_exp;
import asint.TinyASint.StringLocalizado;
import asint.TinyASint.Suma;

import asint.ProcesamientoPorDefecto;

import java.util.HashMap;

class TablaSimbolos extends HashMap<String,String> {}

public class Vinculacion extends ProcesamientoPorDefecto {
	private TablaSimbolos ts;

	public Vinculacion() {
		ts = new TablaSimbolos();
	}

	public void procesa(Prog prog) {
		prog.decs().procesa(this);
		prog.decs().procesa2(this);
		prog.is().procesa(this);
	}

	// Vinculacion declaraciones fase 1
	//-------------------------------------------------------------------------------------
	
	public void procesa(Decs_muchas decs) {
		decs.decs().procesa(this);
		decs.dec().procesa(this);
	}
	
	public void procesa(Decs_una decs) {
		decs.dec().procesa(this);
	}
	
	public void procesa(Decs_ninguna decs) {
		//skip
	}
	
	public void procesa(Dec dec) {
		dec.type().procesa(this);
		//Recolecta
		if(ts.containsKey(dec.id().toString())) {
			throw new RuntimeException("Variable already defined: " + dec.id() + "Row " + dec.id().fila() + ", Column " + dec().id().columna());
		}
		else {
			ts.put(dec.id().toString(), dec.val().toString());
		}
	}

	public void procesa(Dec_proc dec) {
		dec.type().procesa(this);
		//Recolecta
		if(ts.containsKey(dec.id().toString())) {
			throw new RuntimeException("Variable already defined: " + dec.id() + "Row " + dec.id().fila() + ", Column " + dec().id().columna());
		}
		else {
			ts.put(dec.id().toString(), dec.val().toString());
		}
		dec.pfs().procesa(this);
		dec.decs().procesa(this);
		dec.pfs().procesa2(this);
		dec.decs().procesa2(this);
		dec.is().procesa(this);
	}

	public void procesa(Pfs_ninguno pfs) {
		//skip
	}

	public void procesa(Pfs_uno pfs) {
		pfs.pf().procesa(this);
	}

	public void procesa(Pfs_muchos pfs) {
		pfs.pfs().procesa(this);
		pfs.pf().procesa(this);
	}

	public void procesa(Pf pf) {
		pf.type().procesa(this);
		//Recolecta
		if(ts.containsKey(pf.id().toString())) {
			throw new RuntimeException("Variable already defined: " + pf.id() + "Row " + pf.id().fila() + ", Column " + pf().id().columna());
		}
		else {
			ts.put(pf.id().toString(), pf.val().toString());
		}
	}

	public void procesa(Enteros exp) {
		//skip
	}
	
	public void procesa(Reales exp) {
		//skip
	}
	
	public void procesa(Booleanos exp) {
		//skip
	}
	
	public void procesa(String_exp exp) {
		//skip
	}
	
	public void procesa(Id exp) {
		if(!ts.containsKey(exp.id().toString())) {
			throw new RuntimeException("Variable not defined: " + dec.id() + "Row " + dec.id().fila() + ", Column " + dec().id().columna());
		}
	}
	
	public void procesa(Dref exp) {
		exp.arg0().procesa(this);//Exp
	}
	
	public void procesa(Acc exp) {
		exp.arg0().procesa(this);//Exp
	}
	
	public void procesa(Indx exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}

	// Vinculacion declaraciones fase 2
	//-------------------------------------------------------------------------------------
	
	public void procesa2(Decs_muchas decs) {
		decs.decs().procesa2(this);
		decs.dec().procesa2(this);
	}
	
	public void procesa2(Decs_una decs) {
		decs.dec().procesa2(this);
	}
	
	public void procesa2(Decs_ninguna decs) {
		//skip
	}

	public void procesa(Dec dec) {
		dec.type().procesa(this);
	}

	public void procesa2(Enteros exp) {
		//skip
	}
	
	public void procesa2(Reales exp) {
		//skip
	}
	
	public void procesa2(Booleanos exp) {
		//skip
	}
	
	public void procesa2(String_exp exp) {
		//skip
	}

	public void procesa2(Id exp) {

	}
	
	public void procesa2(Dref exp) {
		exp.arg0().procesa(this);//Exp
	}
	
	public void procesa2(Acc exp) {
		exp.arg0().procesa(this);//Exp
	}
	
	public void procesa2(Indx exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}

	public void procesa2(Pfs_ninguno pfs) {
		//skip
	}

	public void procesa2(Pfs_uno pfs) {
		pfs.pf().procesa2(this);
	}

	public void procesa2(Pfs_muchos pfs) {
		pfs.pfs().procesa2(this);
		pfs.pf().procesa2(this);
	}

	public void procesa2(Pf pf) {
		pf.type().procesa2(this);
	}

	// Vinculacion Instrucciones
	//-------------------------------------------------------------------------------------

	public void procesa(Menor exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}
	
	public void procesa(Mayor exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}
	
	public void procesa(MenorIgual exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}
	
	public void procesa(MayorIgual exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}
	
	public void procesa(Igual exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}
	
	public void procesa(Distinto exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}
	
	public void procesa(Suma exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}
	
	public void procesa(Resta exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}
	
	public void procesa(And exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}
	
	public void procesa(Or exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}
	
	public void procesa(Mul exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}
	
	public void procesa(Div exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}
	
	public void procesa(Mod exp) {
		exp.arg0().procesa(this);//Exp
		exp.arg1().procesa(this);//Exp
	}
	
	public void procesa(Menos exp) {
		exp.arg0().procesa(this);//Exp
	}
	
	public void procesa(Not exp) {
		exp.arg0().procesa(this);//Exp
	}

	public void procesa(Is_muchas is) {
		is.is().procesa(this);
		is.i().procesa(this);
	}
	
	public void procesa(Is_una is) {
		is.i().procesa(this);
	}
	
	public void procesa(Is_asig i) {
		i.arg0().procesa(this);//Exp
		i.arg1().procesa(this);//Exp
	}
	
	public void procesa(Is_if_then i) {
		i.arg0().procesa(this);//Exp
		i.arg1().procesa(this);//Is
	}
	
	public void procesa(Is_if_then_else i) {
		i.arg0().procesa(this);//Exp
		i.arg1().procesa(this);//Is
		i.arg2().procesa(this);//Is
	}
	
	public void procesa(Is_while i) {
		i.arg0().procesa(this);//Exp
		i.arg1().procesa(this);//Is
	}
	
	public void procesa(Is_read i) {
		i.arg0().procesa(this);//Exp
	}
	
	public void procesa(Is_write i) {
		i.arg0().procesa(this);//Exp
	}
	
	public void procesa(Is_nl i) {
		//skip
	}
	
	public void procesa(Is_new i) {
		i.arg0().procesa(this);//Exp
	}
	
	public void procesa(Is_delete i) {
		i.arg0().procesa(this);//Exp
	}
	
	public void procesa(Is_proc i) {
		i.arg0().procesa(this);//Exp
		i.arg1().procesa(this);//Preals
	}
	
	public void procesa(Is_seq i) {
		//TODO
	}
}