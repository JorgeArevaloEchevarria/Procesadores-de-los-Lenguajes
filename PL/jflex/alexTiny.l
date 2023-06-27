package alex;

import errors.GestionErroresExp;

%%
%cup
%column
%line
%class AnalizadorLexicoExp
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresExp errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresExp errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
id = {letra}({letra}|{digito}|\_)*
digPos = [1-9]
digito = ({digPos}|0)
parteEntera = {digPos}{digito}*
parteDecimal = \. {digito}* {digPos}
parteExponencial = [Ee]{signo}?{parteEntera}
signo = [\+,\-]
separador = [ \t\r\b\n]
comentario = #[^\n]* 
enteros = {signo}? {parteEntera}
reales = {signo}? {parteEntera}({parteDecimal}|{parteExponencial}|{parteDecimal}{parteExponencial})
suma = \+
mul = \*
resta = \-
div = \/
mod = \%
parApertura = \(
parCierre = \)
corApertura = \[
corCierre = \]
igual = \=
igualdad = \=\=
distitno = \!\=
menorIgual = \<\=
mayorIgual = \>\=
menor = \<
mayor = \>
punto = \.
indireccion = \^
dosPuntos = \:
coma = \,
puntoYComa = \;
int = (i|I)(n|N)(t|T)
real = (r|R)(e|E)(a|A)(l|L)
bool = (b|B)(o|O)(o|O)(l|L)
string = (s|S)(t|T)(r|R)(i|I)(n|N)(g|G)
and = (a|A)(n|N)(d|D)
or = (o|O)(r|R)
not = (n|N)(o|O)(t|T)
null = (n|N)(u|U)(l|L)(l|L)
true = (t|T)(r|R)(u|U)(e|E)
false = (f|F)(a|A)(l|L)(s|S)(e|E)
proc = (p|P)(r|R)(o|O)(c|C)
if = (i|I)(f|F)
then = (t|T)(h|H)(e|E)(n|N)
else = (e|E)(l|L)(s|S)(e|E)
while = (w|W)(h|H)(i|I)(l|L)(e|E)
do = (d|D)(o|O)
seq = (s|S)(e|E)(q|Q)
begin = (b|B)(e|E)(g|G)(i|I)(n|N)
end = (e|E)(n|N)(d|D)
record = (r|R)(e|E)(c|C)(o|O)(r|R)(d|D)
array = (a|A)(r|R)(r|R)(a|A)(y|Y)
of = (o|O)(f|F)
new = (n|N)(e|E)(w|W)
delete = (d|D)(e|E)(l|L)(e|E)(t|T)(e|E)
read = (r|R)(e|E)(a|A)(d|D)
write = (w|W)(r|R)(i|I)(t|T)(e|E)
nl = (n|N)(l|L)
var = (v|V)(a|A)(r|R)
type = (t|T)(y|Y)(p|P)(e|E)

%%
{separador}               {}
{comentario}              {}
{enteros}            	  {return ops.unidadNumEnt();}
{reales}              	  {return ops.unidadNumReal();}
{id}		              {return ops.unidadID();}
{suma}           		  {return ops.unidadSuma();}
{mul}  					  {return ops.unidadMul();}
{resta}           		  {return ops.unidadResta();}
{div}        			  {return ops.unidadDiv();}
{mod}          			  {return ops.unidadMod();}
{parApertura}      		  {return ops.unidadPAp();}
{parCierre}       		  {return ops.unidadPCierre();}
{corApertura}        	  {return ops.unidadCAp();}
{corCierre}          	  {return ops.unidadCCierre();}
{igual}      			  {return ops.unidadAsig();}
{igualdad}       		  {return ops.unidadIgualdad();}
{distitno}       		  {return ops.unidadDistinto();}
{menorIgual}     		  {return ops.unidadLTE();}
{mayorIgual}     		  {return ops.unidadGTE();}
{menor}           		  {return ops.unidadLT();}
{mayor}          		  {return ops.unidadGT();}
{punto}           		  {return ops.unidadPunto();}
{indireccion}     		  {return ops.unidadIndirec();}
{dosPuntos}               {return ops.unidadDosPuntos();}
{coma}                    {return ops.unidadComa();}
{puntoYComa}              {return ops.unidadPuntoYComa();}
{int}             		  {return ops.unidadInt();}
{real}             		  {return ops.unidadReal();}
{bool}             		  {return ops.unidadBool();}
{string}             	  {return ops.unidadString();}
{and}             		  {return ops.unidadAnd();}
{or}             		  {return ops.unidadOr();}
{not}             		  {return ops.unidadNot();}
{null}             		  {return ops.unidadNull();}
{true}             		  {return ops.unidadTrue();}
{false}             	  {return ops.unidadFalse();}
{proc}             		  {return ops.unidadProc();}
{if}             		  {return ops.unidadIf();}
{then}             		  {return ops.unidadThen();}
{else}             		  {return ops.unidadElse();}
{while}             	  {return ops.unidadWhile();}
{do}             		  {return ops.unidadDo();}
{seq}             		  {return ops.unidadSeq();}
{begin}             	  {return ops.unidadBegin();}
{end}             		  {return ops.unidadEnd();}
{record}             	  {return ops.unidadRecord();}
{array}             	  {return ops.unidadArray();}
{of}             		  {return ops.unidadOf();}
{new}             		  {return ops.unidadNew();}
{delete}             	  {return ops.unidadDelete();}
{read}             		  {return ops.unidadRead();}
{write}             	  {return ops.unidadWrite();}
{nl}             		  {return ops.unidadNl();}
{var}             		  {return ops.unidadVar();}
{type}             		  {return ops.unidadType();}
[^]                       {errores.errorLexico(fila(),columna(),lexema());}  
