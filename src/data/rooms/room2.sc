int ratrun=0

setAnimation("RAT", "RATRIGHT", "RATRIGHT", "RATLEFT", "RATRIGHT");
setPath("RAT", "rat3", 1);

func update()
	updateAnimation("EGO");

	ratrun=ratrun+1;

	# odottaa hetken ennenkuin rotta alkaa juoksemaan
	if ratrun>=100
		updateAnimation("RAT");
	endif

endfunc

func render()


	# piirrä hiiri
	drawAnimImage("RAT", 5);
endfunc

# kun tonkii romulaatikkoa, löytyy tavara
func romulaatikko()

	if action["ROMULAATIKKO"]==0 && action["ETSI"]==1  # tiedetään mitä haetaan
			message(15, -1, -1, "\"Ahhaa, löysin konvertterin! Mutta se on sökö. Sen takia se on romulaatikkoon joutunut. Otan sen mukaani.\"");
			action["ROBO"]=1; # seuraava tehtävä
			action["ROMULAATIKKO"]=1; # ei tänne enää
			action["ETSI"]=0; # ei vielä tiedetä mitä pitää etsiä
	else
		message(15, -1, -1, "\"Laatikossa on paljon romua mutten tiedä tarvitsenko jotain.\"");
	endif

endfunc
