Hemos intentado generar el CUP pero nos generaba el siguiente error en el que parece que nos produce conflicto la gramatica,
y me parece extraño ya que en la revision de la memoria Eva no nos indico error en esa parte, ademas de estar un tiempo
 intentando solucionarlo mirando si nos habiamos equivocado en la asociatividad de los operadores, pero no lo hemos logrado encontrar.

Warning : * Shift/Reduce conflict found in state #52
  between E0 ::= E1 (*) 
  and     OpBinMenos ::= (*) RESTA 
  under symbol RESTA
  Resolved in favor of shifting.

Warning : * Shift/Reduce conflict found in state #63
  between E0 ::= E1 OpBinRel E1 (*) 
  and     OpBinMenos ::= (*) RESTA 
  under symbol RESTA
  Resolved in favor of shifting.

Error : * More conflicts encountered than expected -- parser generation aborted
------- CUP v0.11b beta 20140220 Parser Generation Summary -------
  1 error and 2 warnings
  56 terminals, 48 non-terminals, and 102 productions declared, 
  producing 172 unique parse states.
  0 terminals declared but not used.
  0 non-terminals declared but not used.
  0 productions never reduced.
  2 conflicts detected (0 expected).
  No code produced.
---------------------------------------------------- (v0.11b beta 20140220)