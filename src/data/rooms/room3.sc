int ratrun=0

setAnimation("RAT", "RATRIGHT", "RATRIGHT", "RATLEFT", "RATRIGHT");
setPath("RAT", "rat4", 2);


func update()
	updateAnimation("EGO");

	ratrun=ratrun+1;

	# odottaa hetken ennenkuin rotta alkaa juoksemaan
	if ratrun>=200
		updateAnimation("RAT");
	endif

endfunc

func render()

	# piirr� hiiri
	drawAnimImage("RAT", 5);

endfunc


# tonkii p�yd�n lokeroita
func poyta()

	if action["LOKEROT"]==0 && action["ROBO"]==1 # korjausteht�v�
			message(15, -1, -1, "L�ysit lokeroista ty�kaluja. Korjasit konvertterin.");
			action["ROBO"]=2; # seuraava teht�v�
			action["LOKEROT"]=1; # ei t�nne en��
			action["ETSI"]=0; # ei viel� tiedet� mit� pit�� etsi�
	else
		message(15, -1, -1, "Et nyt tarvitse ty�kaluja.");
	endif

endfunc

# jos k�ytt�� sulaketta sulakekaappiin, virrat palaa
func sulake()

	if action["SULAKE"]==1 # ja pit�� tsekata onko luukku auki
			message(15, -1, -1, "Asensit uuden sulakkeen.");
			action["ROBO"]=6;
			action["SULAKE"]=2; # ei t�nne en��
			action["ETSI"]=0; # ei viel� tiedet� mit� pit�� etsi�
	else
		message(15, -1, -1, "Ei auta vaikka ty�nt�isit sormesi sulakkeisiin.");
	endif

endfunc
