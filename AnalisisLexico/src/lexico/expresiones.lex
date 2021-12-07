/* Fichero: expresiones.lex */
package lexico;
import java.io.InputStreamReader;
import java.lang.System;
%%
%char
%public
%standalone
%full
DIGITO = [0-9]
LETRA = [a-zA-Z] 
%%
; {System.out.print(" FinSentencia");}
\+ {System.out.print(" Mas");}
\- {System.out.print(" Menos");}
\* {System.out.print(" Por");}
\/ {System.out.print(" Entre");}
\/\/({LETRA}*|" ")* {System.out.print(" Comentario");}
\( {System.out.print(" Abre-Par");}
\) {System.out.print(" Cierra-Par");}
:= {System.out.print(" Asigna");}
(\+|\-)?{DIGITO}+\.{DIGITO}+ {System.out.print(" Real("+yytext()+")");}
"inicio" {System.out.print(" Palabra_reservada(inicio)");}
"fin" {System.out.print(" Palabra_reservada(fin)");}
\'({LETRA}*" ")*\' {System.out.print(" Cadena("+yytext()+")");}
[" "\t] { }
(\+|\-)?{DIGITO}+ {System.out.print(" Entero("+yytext()+")");}
imprime {System.out.print(" Palabra_reservada(imprime)");}
[a-z]({LETRA}|{DIGITO}|_+({LETRA}|{DIGITO}))* {System.out.print(" Variable("+yytext()+")");}
\n {System.out.print(yytext());}
. {System.out.print(" ERROR_Lexico");}