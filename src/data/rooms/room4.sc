int ratrun=0

setAnimation("RAT", "RATRIGHT", "RATRIGHT", "RATLEFT", "RATRIGHT");
setPath("RAT", "rat5", 0);


# kutsutaan joka framella, piirt�miset t�nne
func update()
	updateAnimation("EGO");

	ratrun=ratrun+1;

	# odottaa hetken ennenkuin rotta alkaa juoksemaan
	if ratrun>=50
		updateAnimation("RAT");
	endif

endfunc

func render()
	# piirr� hiiri
	drawAnimImage("RAT", 5);

endfunc


# t�st� laatikosta joku tavara
func box1()

	if action["BOX1"]==0 && action["ROBO"]==2 && action["ETSI"]==1 # kajari
			message(15, -1, -1, "Tutkisit laatikollista romua ja l�ysit kaiuttimen. \"Suuritehoisempi kuin edellinen. No kaipa t�m� k�y.\"");
			action["ROBO"]=3; # seuraava teht�v�
			action["BOX1"]=1; # ei t�nne en��
			action["ETSI"]=0; # ei viel� tiedet� mit� pit�� etsi�
	else
		message(15, -1, -1, "Et l�yd� mit��n tarvittavaa.");
	endif

endfunc

# t�st� laatikosta l�ytyy ehj� sulake
func box2()

	if action["BOX2"]==0 && action["ROBO"]==4 # sulake
			message(15, -1, -1, "\"Sulake oli juuri siell� mihin olin sen piilottanut.\"");
			action["ROBO"]=5; # seuraava teht�v�
			action["BOX2"]=1; # ei t�nne en��
			action["ETSI"]=0; # ei viel� tiedet� mit� pit�� etsi�
			action["SULAKE"]=1;
	else
		message(15, -1, -1, "Et l�yd� mit��n tarvittavaa.");
	endif

endfunc
