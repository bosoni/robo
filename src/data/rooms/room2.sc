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


	# piirr� hiiri
	drawAnimImage("RAT", 5);
endfunc

# kun tonkii romulaatikkoa, l�ytyy tavara
func romulaatikko()

	if action["ROMULAATIKKO"]==0 && action["ETSI"]==1  # tiedet��n mit� haetaan
			message(15, -1, -1, "\"Ahhaa, l�ysin konvertterin! Mutta se on s�k�. Sen takia se on romulaatikkoon joutunut. Otan sen mukaani.\"");
			action["ROBO"]=1; # seuraava teht�v�
			action["ROMULAATIKKO"]=1; # ei t�nne en��
			action["ETSI"]=0; # ei viel� tiedet� mit� pit�� etsi�
	else
		message(15, -1, -1, "\"Laatikossa on paljon romua mutten tied� tarvitsenko jotain.\"");
	endif

endfunc
