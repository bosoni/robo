int ratrun=0

setAnimation("RAT", "RATRIGHT", "RATRIGHT", "RATLEFT", "RATRIGHT");
setPath("RAT", "rat1", 0);

# kutsutaan joka framella
func update()

	updateAnimation("EGO");

	ratrun=ratrun+1;

	# odottaa hetken ennenkuin rotta alkaa juoksemaan
	if ratrun>=200
		updateAnimation("RAT");
	endif

endfunc


# kutsutaan kun on aika piirt‰‰
func render()

	if action["ALKUTEKSTI"]==0
		message(15, -1,-1, "\"Mainiota, pian robottini on valmis eik‰ aikaakaan kun\nminulla on kokonainen armeija niit‰! Sitten valloitamme\nmaailman!\" ajattelit hiljaa samalla kun ihailit tyˆsi tulosta.");
		action["ALKUTEKSTI"]=1;
	endif


	# piirr‰ hiiri
	drawAnimImage("RAT", 5);


endfunc

# kun koskee roboa
func robotti()

	# tsekkaukset onko tarvittavat tavarat asennettu robottiin

	if action["ROBO"]==0 && action["ETSI"]==0
		message(15, -1, -1, "\"Piip pipip!\" kuului vain kun k‰ynnistit robotin. Se ei ole viel‰ t‰ysin toimintakuntoinen.\"Hmm pit‰‰ asentaa viel‰ keino‰lykortin konvertteri.\" sanoit robotille, ihan kuin se tajuaisi jo jotain.");
		action["ETSI"]=1;
	endif

	if action["ROBO"]==1 && action["ETSI"]==0
		message(15, -1, -1, "Konvertteri on hajalla. Et halua tuhlata aikaa sekoiluun laittamalla viallisia osia sinne minne ne ei kuulu.");
		action["ETSI"]=1;
	endif

	if action["ROBO"]==2 && action["ETSI"]==0
		message(15, -1, -1, "\"Noniin robottini, kohta saat nauttia puhumisesta.\" sanoit kun aloit asentamaan konvertteria robotin sisuksiin. \"No voi perhana, tˆrkk‰sin vahingossa kajarin hajalle!\" huudahdit. \"T‰risen liikaa j‰nnityksest‰!\"");
		action["ETSI"]=1;
	endif

	if action["ROBO"]==3 && action["ETSI"]==0
		message(15, -1, -1, "Asensit uuden kajarin vanhan tilalle ja k‰ynnistit robotin. Robotti alkoi puhumaan, \"Piirien tarkistus.. Ymp‰ristˆskannaus..\". Hyv‰lt‰ vaikuttaa, ajattelit, kunnes: \"Piip piip, muisti loppui!\". \"No eih‰n t‰st‰ tule mit‰‰n!\" huusit raivoissasi. Tarvitset lis‰‰ muistia.");
		action["ETSI"]=1;
	endif

	if action["ROBO"]==4
		message(15, -1, -1, "Avasit hiirulaisen p‰‰kopan ja otit silt‰ aivot. Asettelit ne lis‰muistipaikalle. \"Nyt robon pit‰‰ l‰hte‰ jo p‰‰lle!\" ajattelit ja pistit virrat p‰‰lle. K‰ynnistyi, silm‰t vilkkui, sammui. Mit‰ tapahtui? Virrat katkesi. \"No voi sun perr...se poltti rakennuksen sulakkeet! Uusi kajari taisi vied‰ sen verran enemm‰n virtaa. Hˆh!\"");
		action["ETSI"]=1;
	endif

	if action["ROBO"]==6
		message(15, -1, -1, "Saas n‰hd‰ miten ‰ij‰n k‰y. K‰ynnistit robotin ja hei, seh‰n l‰hti p‰‰lle! \"P‰iv‰‰, herra.\" robotti tervehti. \"Mit‰ teemme t‰n‰‰n?\" robo kysyi yst‰v‰llisesti. \"Robottiarmeijan, roboni, ROBOTTIARMEIJAN!!\" kiljuit ekstaasissa!");

		# lataa loppudemot skripti jossa sit myˆs endgame
		setEGO(0); # disable
		loadRoom("end", "");

	endif



endfunc
