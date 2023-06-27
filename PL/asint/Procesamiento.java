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
import asint.TinyASint.Suma;

public interface Procesamiento {
	
	void procesa(Menor exp);
	void procesa(Mayor exp);
	void procesa(MenorIgual exp);
	void procesa(MayorIgual exp);
	void procesa(Igual exp);
	void procesa(Distinto exp);
	
	void procesa(Suma exp);
	void procesa(Resta exp);
	
	void procesa(And exp);
	void procesa(Or exp);
	
	void procesa(Mul exp);
	void procesa(Div exp);
	void procesa(Mod exp);
	
	void procesa(Menos exp);
	void procesa(Not exp);
	
	void procesa(Enteros exp);
	void procesa(Reales exp);
	void procesa(Booleanos exp);
	void procesa(String_exp exp);
	void procesa(Id exp);
	void procesa(Dref exp);
	void procesa(Acc exp);
	void procesa(Indx exp);
	
	void procesa(Decs_muchas decs);
    void procesa(Decs_una decs);
    void procesa(Decs_ninguna decs);
    void procesa(Dec dec);
    
    void procesa(Is_muchas is);
    void procesa(Is_una is);
    void procesa(Is_asig i);
    void procesa(Is_if_then i);
    void procesa(Is_if_then_else i);
    void procesa(Is_while i);
    void procesa(Is_read i);
    void procesa(Is_write i);
    void procesa(Is_nl i);
    void procesa(Is_new i);
    void procesa(Is_delete i);
    void procesa(Is_proc i);
    void procesa(Is_seq i);
    
	

}
