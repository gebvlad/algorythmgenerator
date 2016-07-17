grammar edapl_lang;

/**/
algorythm
	: 'BEGIN' programbody 'END'
	;

programbody
	: block
	;
  
block
	: lin
	| condition
	
	
	;

lin
	:  'LIN' ('y' num)+ block  
	|
	;
	
condition
	: (alt
	| par
	| loop)
	;

alt
	: 'ALT' 'x' num block 'ENDALT' block  
	;
	
par
	: 'PAR' 'x' num 'P' block 'P2' block 'ENDPAR' block  
	;
	
loop
	: 'LOOP' 'x' num block 'ENDLOOP' block  
	;
	
num
	: ('0'|'1'|'2'|'3'|'4'|'5'|'6'|'7'|'8'|'9')+
	;