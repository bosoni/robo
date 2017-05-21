# suoritetaan joka huoneessa jos vain ladattuna.

int count=0

func update()

	# jos käytetään rautaa rottaan
	if using("rauta", "RAT", 50)=="1"

		removeAnimation("RAT"); # hävitä hiiri

		# luo uusi hiiri
		if roomName[0]=="room1"
			setAnimation("RAT", "RATRIGHT", "RATRIGHT", "RATLEFT", "RATRIGHT");
			setPath("RAT", "rat2", 0); # eri reitti
		endif

		if action["ROBO"]!=3
			if count==0
		        message(35, -1, -1, "Poks otit hiiren kiinni ja söit sen!");
		    endif
			if count==1
		        message(25, -1, -1, "Pamautit hiireltä kallon halki ja potkaisit sen nurkaan odottamaan siivouspäivää.");
		    endif
			if count==2
		        message(20, -1, -1, "Kokeilit hienoa golf-lyöntiä ja humautit hiiren komeassa kaaressa seinään.");
		    endif
			if count==3
		        count=0;
		    endif
		else
			if action["ROBO"]!=4
				message(15, -1, -1, "\"Hiiren aivot voivatkin käydä lisämuistiksi. Nerokasta!\" Löit hiireltä tajun kankaalle ja pistit taskuusi.");
				action["ROBO"]=4;
			endif
		endif

		count=count+1;

	endif

endfunc
