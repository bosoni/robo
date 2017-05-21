
loadImage("robo1.png", "ROBO1");
loadImage("robo2.png", "ROBO2");
loadImage("right.png", "UKKO");

int sx=-20

int ll=0
int count=1
int y=360

int state=0
int robo=1

func update()

	# ilmestyy yksitellen
	if count<12
		ll=ll+1;
		if ll==20
			count=count+1;
			ll=0;
		endif
	endif

	if count==12
		state=1;
	endif

	# komentaja tulee
	if state==1
		drawImage(sx, 400, "UKKO");
		sx=sx+2;
	endif

	if sx>50
		robo=2;
	endif


	if sx>640
		endGame();
	endif


endfunc

# kutsutaan kun on aika piirt‰‰
func render()

	int c=0
	int x=0

	# piirr‰ robotit riviin
	while c < count
    	x=c*50;
    	c=c+1;

		if robo==1
	    	drawImage(x, y, "ROBO1");
	    else
			drawImage(x, y, "ROBO2");
		endif

	endwhile


	if sx>300 && action["LOPPU"]==0
		message(40, -1, 60, "\"Me TULEMME valloittamaan maailman!\"");
		action["LOPPU"]=1;
	endif


	if sx>500 && action["LOPPU"]==1
		message(30, -1, 50, "Mutta se j‰‰ n‰ht‰v‰ksi.. peli l‰pi.");
		action["LOPPU"]=2;
	endif


endfunc
