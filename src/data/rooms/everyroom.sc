# suoritetaan joka huoneessa jos vain ladattuna.

int count=0

func update()

	# jos k�ytet��n rautaa rottaan
	if using("rauta", "RAT", 50)=="1"

		removeAnimation("RAT"); # h�vit� hiiri

		# luo uusi hiiri
		if roomName[0]=="room1"
			setAnimation("RAT", "RATRIGHT", "RATRIGHT", "RATLEFT", "RATRIGHT");
			setPath("RAT", "rat2", 0); # eri reitti
		endif

		if action["ROBO"]!=3
			if count==0
		        message(35, -1, -1, "Poks otit hiiren kiinni ja s�it sen!");
		    endif
			if count==1
		        message(25, -1, -1, "Pamautit hiirelt� kallon halki ja potkaisit sen nurkaan odottamaan siivousp�iv��.");
		    endif
			if count==2
		        message(20, -1, -1, "Kokeilit hienoa golf-ly�nti� ja humautit hiiren komeassa kaaressa sein��n.");
		    endif
			if count==3
		        count=0;
		    endif
		else
			if action["ROBO"]!=4
				message(15, -1, -1, "\"Hiiren aivot voivatkin k�yd� lis�muistiksi. Nerokasta!\" L�it hiirelt� tajun kankaalle ja pistit taskuusi.");
				action["ROBO"]=4;
			endif
		endif

		count=count+1;

	endif

endfunc
