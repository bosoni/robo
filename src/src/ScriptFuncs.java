/**
 * @file ScriptFuncs.java
 * @author mjt, 2007
 * mixut@hotmail.com
 *
 */

package tstgame;

import java.util.HashMap;
import murlen.util.fscript.*;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.image.BufferedImage;

class Cursor
{
    String name=null;
    BufferedImage pic=null;
    
    void setup(String n, BufferedImage p)
    {
	name=n;
	pic=p;
    }
}

/**
 * luokan metodeita voidaan kutsua scriptitiedostosta.
 */
public class ScriptFuncs  extends BasicExtension
{
    static HashMap action=null; // käytetään tallentamaan scriptin muuttujien arvot
    static Vector cursor=null; // hiirikursoreiden kuvat (valmiiks ohjelmoituja on WALK, LOOK, TAKE)
    static HashMap picture=null; // muut kuvat
    static HashMap animation; // animaatioinfot
    static HashMap entity; // muut henkilöt, animoidut oliot
    static HashMap path; // reitit
    
    static public void reset()
    {
	if(action==null)
	{
	    action=new HashMap();
	    picture=new HashMap();
	    animation=new HashMap();
	    entity=new HashMap();
	    path=new HashMap();
	    
	    cursor=new Vector();
	    Cursor nullcur=new Cursor();
	    
	    cursor.add(nullcur); // tempit
	    cursor.add(nullcur); // varaa vain paikat ettei muut kursorit mene
	    cursor.add(nullcur); // näitten paikalle
	}
	
    }
    
    public Object callFunction(String name, ArrayList params) throws FSException
    {
	
	if(name.equals("MessageBox"))
	{
	    String str=(String)params.get(0);
	    Game.MessageBox(str);
	    return null;
	}
	if(name.equals("setEGO"))
	{
	    Main.main.game.dork.enabled=((Integer)params.get(0)).intValue();
	    
	    return null;
	}
	if(name.equals("delay"))
	{
	    Main.setSleep(((Integer)params.get(0)).intValue());
	    return null;
	}
	if(name.equals("waitMousePressed"))
	{
	    Main.waitMousePressed=1;
	    return null;
	}
	if(name.equals("endGame"))
	{
	    Main.running=false;
	    return null;
	}
	if(name.equals("loadRoom")) // fileName position
	{
	    Game.load((String)params.get(0), (String)params.get(1));
	    return null;
	}
	if(name.equals("loadImage")) // fileName, name
	{
	    // poistetaanko kuva käytöstä?
	    if(((String)params.get(0)).equals("null"))
	    {
		picture.remove(params.get(1));
		return null;
	    }
	    
	    BufferedImage pic=Room.loadImage((String)params.get(0));
	    picture.put( params.get(1), (Object)pic);
	    return null;
	}
	if(name.equals("loadCursor")) // fileName, name
	{
	    // poistetaanko kursori käytöstä?
// todo forlooppi
	    if(((String)params.get(0)).equals("null"))
	    {
// hashmapin sydeemi tää o, ei tää vektoreil toimi!!
//		cursor.remove(params.get(1));
		return null;
	    }
	    
	    Cursor cur=new Cursor();
	    BufferedImage pic=Room.loadImage((String)params.get(0));
	    cur.setup( (String)params.get(1), pic);
	    
	    // tarkista onko walk/look/take
	    if(params.get(1).equals("WALK"))
	    {
		cursor.remove(0);
		cursor.add(0, cur);
		
		return null;
	    }
	    if(params.get(1).equals("LOOK"))
	    {
		cursor.remove(1);
		cursor.add(1, cur);
		
		return null;
	    }
	    if(params.get(1).equals("TAKE"))
	    {
		cursor.remove(2);
		cursor.add(2, cur);
		
		return null;
	    }
	    cursor.add(cur);
	    
	    return null;
	}
	if(name.equals("drawImage")) // x, y, name, yp
	{
	    int x=((Integer)params.get(0)).intValue();
	    int y=((Integer)params.get(1)).intValue();
	    String str=(String)params.get(2);
	    int yp=0; // hienosäätö y:hyn lisää tämän verran
	    if(params.size()==4) yp=((Integer)params.get(3)).intValue();
	    Main.main.drawImage(x, y, str, false, yp);
	    return null;
	}
	if(name.equals("playMidi")) // midifile
	{
	    String str=(String)params.get(0);
	    
	    MidiPlayer.stopMidi();
	    MidiPlayer.playMidi(str);
	    return null;
	}
	if(name.equals("loadAnimation")) // animName
	{
	    String animName=(String)params.get(0);
	    
	    // tarkista löytyykö animName nimistä animaatiota
	    if(Room.animInfo.containsKey(animName)==true)
	    {
		// tiedostonimet
		String[] files=(String[])Room.animInfo.get(animName);
		
		Animation anim=new Animation();
		// lataa kuvat
		for(int qq=0; qq<files.length; qq++)
		{
		    BufferedImage tmppic=Room.loadImage(files[qq]);
		    anim.add(tmppic); // kuva talteen
		}
		
		// animaatio talteen
		animation.put(animName, anim);
		
	    }
	    else
		FileIO.ErrorMessage(animName+" animaatiota ei löytynyt!");
	    
	    return null;
	}
	if(name.equals("setAnimation")) // objname, up_anim, down_anim, left_anim, right_anim
	{
	    String objname=(String)params.get(0);
	    String up=(String)params.get(1);
	    String down=(String)params.get(2);
	    String left=(String)params.get(3);
	    String right=(String)params.get(4);
	    
	    // jos vanha animaatio, poista se
	    if(entity.containsKey(objname)) entity.remove(objname);
	    
	    Entity ent=new Entity();
	    ent.name=objname;
	    
	    // animaatioiden nimet talteen
	    ent.animNames[0]=up;
	    ent.animNames[1]=down;
	    ent.animNames[2]=left;
	    ent.animNames[3]=right;
	    
	    if(objname.equals("EGO"))
	    {
		Game.dork.name="EGO";
		Game.dork.animNames=ent.animNames;
	    }
	    else
	    {
		entity.put(objname, ent);
	    }
	    
	    return null;
	}
	if(name.equals("updateAnimation")) // objname
	{
	    String objname=(String)params.get(0);
	    
	    // päivitä ohjattavan ukon paikka
	    if(objname.equals("EGO"))
	    {
		Game.dork.update();
	    }
	    else
		// päivitä animaatio jos objekti löytyy
	    {
		Entity tmpent=(Entity)entity.get(objname);
		if(tmpent==null) return null;
		
		tmpent.update();
		tmpent.updatePath();
	    }
	    return null;
	}
	if(name.equals("removeAnimation")) // objname
	{
	    String objname=(String)params.get(0);
	    entity.remove(objname);
	    //animation.remove(objname);
if(Main.DEBUG) System.out.println("remove "+objname)	    ;
	    
	    return null;
	}
	if(name.equals("drawAnimImage")) // objname, yp
	{
	    String objname=(String)params.get(0);
	    Entity tmpent=(Entity)entity.get(objname);
	    if(tmpent==null) return null;
	    
	    int yp=0; // hienosäätö y:hyn lisää tämän verran
	    if(params.size()==2) yp=((Integer)params.get(1)).intValue();
	    
	    if(tmpent.walkMode!=-1)
	    {
		Main.main.drawImage(-99, -99, objname, false, yp); // esim varjo todo fix drawimageen vaik tää tai jotain
		Main.main.drawImage(0, 0, objname, true, yp);
	    }
	    return null;
	}
	
	if(name.equals("setPath")) // objectName, pathName, walk_mode
	{
	    String objname=(String)params.get(0);
	    
	    if(entity.containsKey(objname))
	    {
		String pathname=(String)params.get(1);
		
		// aseta reitti
		Polygon pa=(Polygon)path.get(pathname);
		Entity ent=(Entity)entity.get(objname);
		ent.path=pa;
		
		ent.reset(ent.path.verts.get(0).x, ent.path.verts.get(0).y);
		ent.setWalkMode((Integer)params.get(2));
	    }
	    return null;
	}
	if(name.equals("message")) // font_size, x, y, text
	{
	    int size=((Integer)params.get(0)).intValue();
	    int x=((Integer)params.get(1)).intValue();
	    int y=((Integer)params.get(2)).intValue();
	    String text=(String)params.get(3);
	    
	    int waitmouse=1;
	    if(params.size()==5) waitmouse=((Integer)params.get(4)).intValue();
	    
	    Main.main.write(size, x, y, text, waitmouse==1 ? true : false);
	    
	    return null;
	}
	
	
	if(name.equals("using")) // hiirikursori, objekti, max_etäisyys
	{
	    if(Game.mbutton!=1) return "0";
	    String cur=(String)params.get(0);
	    
	    // jos eri hiirikursori, poistu
	    if(Main.main.game.selectedItem.equals(cur)==false) return "0";
	    
	    String objname=(String)params.get(1);
	    
	    // jos objektin xy liian kaukana, poistu
	    Entity ent=(Entity)entity.get(objname);
	    if(ent==null) return "0";
	    
	    int w=0, h=0;
	    // etsi kuvan tiedot, leveys ja korkeus
	    BufferedImage picture=(BufferedImage)ScriptFuncs.picture.get(objname);
	    // jos ei ole picturessa, ehkä se on animation:ssa
	    if(picture==null) picture=(BufferedImage) ((Animation)animation.get(objname)).pics.get(0);
	    
	    w=picture.getWidth();
	    h=picture.getHeight();
	    
	    // pitää tarkistaa että hiirellä klikattiin objektin alueella
	    if(Game.mx-Main.transX>=ent.x && Game.mx-Main.transX<=ent.x+w &&
		 Game.my-Main.transY>=ent.y-h && Game.my-Main.transY<=ent.y)
	    {
		// laske etäisyys
		int xx=Main.main.game.dork.x-ent.x;
		int yy=Main.main.game.dork.y-ent.y;
		
		double len=Math.sqrt(xx*xx + yy*yy);
		
		if(Main.DEBUG) System.out.println("arvot "+len+" "+xx+" "+yy);
		
		if(len > ((Integer)params.get(2)).intValue()) return "0";
		
		return "1";
	    }
	    
	    return "0";
	}
	
	
	throw new FSUnsupportedException(name);
    }
    
    int getIndex(Object index)
    {
	Integer i=(Integer)index;
	return i.intValue();
    }
    
    // taulukot ---
    public Object getVar(String name,Object index)
    {
	if(name.equals("action"))
	{
	    if(action.get(index)==null) return new Integer(0);
	    return action.get(index);
	}
	if(name.equals("inventory"))
	{
	    if(Main.main.game.inventory.get(index)==null) return new Integer(0);
	    return Main.main.game.inventory.get(index);
	}
	if(name.equals("roomName"))
	{
	    if(Main.main.game.curRoom.name==null) return "_tmp_";
	    return (String)Main.main.game.curRoom.name;
	}
	
	System.out.println("error in script: "+name);
	return new Integer(0);
    }
    public void setVar(String name,Object index,Object value)
    {
	if(name.equals("action"))
	{
	    action.put(index, value);
	    return;
	}
	if(name.equals("inventory"))
	{
	    Main.main.game.inventory.put(index, value);
	    return;
	}
	
	System.out.println("error in script: "+name);
    }
    
}


class Animation
{
    // animaation kuvat
    Vector pics=new Vector();
    
    public void add(BufferedImage picname)
    {
	pics.add(picname);
    }
    
    public BufferedImage get(int i)
    {
	return (BufferedImage)pics.get(i);
    }
}
