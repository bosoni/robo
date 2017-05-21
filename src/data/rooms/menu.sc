# käsikuva, sillä valitaan "Aloitus" tai "Lopetus"
loadCursor("get_cur.PNG", "TAKE");

func update()
endfunc
func render()
endfunc

func aloitus()
	# lataa datat

	# vakiokursorit, joiden toiminnallisuus ohjelmoitu java-ohjelmaan
	loadCursor("walk_cur.PNG", "WALK");
	loadCursor("eye_cur.PNG", "LOOK");
	loadCursor("get_cur.PNG", "TAKE");

	# BACKGROUND laittaa taustalle kuvan (ja null poistaa)
	#loadImage("tausta.jpg", "BACKGROUND"); // asetus
	#loadImage(null, "BACKGROUND"); // poisto
	
	# lataa ohjattavan ukon asennot
	loadAnimation("UP");
	loadAnimation("DOWN");
	loadAnimation("LEFT");
	loadAnimation("RIGHT");

	# aseta animaatio (EGO on ohjattava ukko)
	# parameters: mihin_objektiin, ylös, alas, vasen, oikea
	setAnimation("EGO", "UP", "DOWN", "LEFT", "RIGHT");

	# rotta
	loadAnimation("RATRIGHT");
	loadAnimation("RATLEFT");
	setAnimation("RAT", "RATRIGHT", "RATRIGHT", "RATLEFT", "RATRIGHT");

	# lataa varjot
	# lataa EGOon ja RATtiin. koska animaatio ja imaget on erikseen, nämä ei korvaa
	# imageita ja saadaan ne kummatkin "linkattua" samoihin objekteihin (EGO, RAT, ..)
	loadImage("varjo1.png", "EGO");
	loadImage("varjo2.png", "RAT");

	# lataa peli
	loadRoom("room1", "aloitus");


endfunc

func lopetus()
	endGame();
endfunc
