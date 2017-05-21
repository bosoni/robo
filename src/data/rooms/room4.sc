int ratrun=0

setAnimation("RAT", "RATRIGHT", "RATRIGHT", "RATLEFT", "RATRIGHT");
setPath("RAT", "rat5", 0);


# kutsutaan joka framella, piirtämiset tänne
func update()
	updateAnimation("EGO");

	ratrun=ratrun+1;

	# odottaa hetken ennenkuin rotta alkaa juoksemaan
	if ratrun>=50
		updateAnimation("RAT");
	endif

endfunc

func render()
	# piirrä hiiri
	drawAnimImage("RAT", 5);

endfunc


# tästä laatikosta joku tavara
func box1()

	if action["BOX1"]==0 && action["ROBO"]==2 && action["ETSI"]==1 # kajari
			message(15, -1, -1, "Tutkisit laatikollista romua ja löysit kaiuttimen. \"Suuritehoisempi kuin edellinen. No kaipa tämä käy.\"");
			action["ROBO"]=3; # seuraava tehtävä
			action["BOX1"]=1; # ei tänne enää
			action["ETSI"]=0; # ei vielä tiedetä mitä pitää etsiä
	else
		message(15, -1, -1, "Et löydä mitään tarvittavaa.");
	endif

endfunc

# tästä laatikosta löytyy ehjä sulake
func box2()

	if action["BOX2"]==0 && action["ROBO"]==4 # sulake
			message(15, -1, -1, "\"Sulake oli juuri siellä mihin olin sen piilottanut.\"");
			action["ROBO"]=5; # seuraava tehtävä
			action["BOX2"]=1; # ei tänne enää
			action["ETSI"]=0; # ei vielä tiedetä mitä pitää etsiä
			action["SULAKE"]=1;
	else
		message(15, -1, -1, "Et löydä mitään tarvittavaa.");
	endif

endfunc
