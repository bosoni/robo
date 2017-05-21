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

	# piirrä hiiri
	drawAnimImage("RAT", 5);

endfunc


# tonkii pöydän lokeroita
func poyta()

	if action["LOKEROT"]==0 && action["ROBO"]==1 # korjaustehtävä
			message(15, -1, -1, "Löysit lokeroista työkaluja. Korjasit konvertterin.");
			action["ROBO"]=2; # seuraava tehtävä
			action["LOKEROT"]=1; # ei tänne enää
			action["ETSI"]=0; # ei vielä tiedetä mitä pitää etsiä
	else
		message(15, -1, -1, "Et nyt tarvitse työkaluja.");
	endif

endfunc

# jos käyttää sulaketta sulakekaappiin, virrat palaa
func sulake()

	if action["SULAKE"]==1 # ja pitää tsekata onko luukku auki
			message(15, -1, -1, "Asensit uuden sulakkeen.");
			action["ROBO"]=6;
			action["SULAKE"]=2; # ei tänne enää
			action["ETSI"]=0; # ei vielä tiedetä mitä pitää etsiä
	else
		message(15, -1, -1, "Ei auta vaikka työntäisit sormesi sulakkeisiin.");
	endif

endfunc
