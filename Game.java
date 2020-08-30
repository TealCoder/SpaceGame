import java.awt.*;
import java.awt.GraphicsConfiguration;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.lang.Math;

class Game extends Frame implements MouseListener, KeyListener {

	static Image fighter;
	static Image advanced;
	static Image beacon;
	static Image destroy;
	static Image edestroy;
	static Image efighter;
	static Image egun;
	static Image generator;
	static Image gun;
	static Image large;
	static Image teleport;


	static GraphicsConfiguration[] gc;
	static int windowheight;
	static int size;
	static Frame frame;
	static int area = 0;
	static int level = 0;
	int mdx = 0;
	int mdy = 0;
	static Image offscreen;
	static String thereturn[];
	static int energy = 3000;
	Button play = new Button("Play");
	Button load = new Button("Load");
	Button cancel = new Button("Cancel");
	Button[] menu = new Button[10];
	static String miscstring;
	Graphics g;
	Font f = new Font("TimesRoman",Font.BOLD,12);
	int keyscroll=20;
	int mousescroll=20;
	int speed=20;
	int unit;
	int action=0;
	int[] selected={-1};
	int[][] gunits={{25,30,1,100},{5,45,10,120},{5,45,2,180}};
	String[][] bcommands={{"Generator","Teleporter","Large Teleporter","Advanced Teleporter"},{"","","",""},
		{"Fighter","","",""},{"Destroyer","","",""},{"Space Gun","","",""}};
	String[] act={"","Attack","Move","Stop"," "};

	// ******************Map
	int obj[] = {0,0,0,0,0};
	int mapsizex=1600; int mapsizey=1600;
	int stuff[][]={{725,1350,51,200,0,0,0,0},{50,50,-3,25,3,0,0,0},{1400,140,-3,25,3,0,0,0},{1400,830,-3,25,3,0,0,0}
			,{30,79,-3,25,3,0,0,0},{760,80,-3,25,3,0,0,0},{290,170,-2,20,3,0,0,0},{1090,170,-2,20,3,0,0,0}
			,{1100,770,-2,20,3,0,0,0},{1330,1330,-2,20,3,0,0,0},{1400,280,-1,7,3,0,0,0},{609,740,-1,7,3,0,0,0}
			,{670,730,-1,7,3,0,0,0},{870,240,-1,7,3,0,0,0},{960,670,-1,7,3,0,0,0},{30,930,-1,50,3,0,0,0}
			,{70,1460,-1,7,3,0,0,0},{590,480,-1,7,3,0,0,0}};
	Color mapcolor=Color.black;
	int mapx=400; int mapy=1100;
	static int scrollx=0;
	static int scrolly=0;
	// *****************/Map

	Game() {

		menu[0]=new Button("New Game");
		menu[1]=new Button(" ");
		menu[2]=new Button(" ");
		menu[3]=new Button(" ");
		menu[4]=new Button("Exit");
		setLayout(null);
		setBackground(Color.black);
		setSize(800,600);
		setTitle("Game");
		menu[0].setBounds(360,50,81,50);
		menu[1].setBounds(360,150,81,50);
		menu[2].setBounds(360,250,81,50);
		menu[3].setBounds(360,350,81,50);
		menu[4].setBounds(360,450,81,50);
		cancel.setBounds(700,500,81,50);
		play.setBounds(700,400,81,50);
		load.setBounds(700,400,81,50);
		for (int i=0;i<5;i++) {add(menu[i]);
		add(load); add(play); add(cancel);
		load.setVisible(false);
		play.setVisible(false);
		cancel.setVisible(false);
		menu[i].setVisible(true);}
		setVisible(true);
		requestFocus();
		toFront();
		repaint();
		addMouseListener(this);
		addKeyListener(this);
	}

	public static void main(String args[]) {
		/*******************
		Rectangle virtualBounds = new Rectangle();
			GraphicsEnvironment ge =
				GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] gs = ge.getScreenDevices();
			for (int j = 0; j < gs.length; j++) {
				GraphicsDevice gd = gs[j];
				gc = gd.getConfigurations();
						for (int i=0; i < gc.length; i++) {
							virtualBounds =
									virtualBounds.union(
									gc[i].getBounds());
			}
		}
		if (virtualBounds.width<800 || virtualBounds.height<600) {
		System.out.println("Your screen dimensions must be at least " +
			"800 by 600."); System.exit(0); } else {
		*******************/
		System.out.println("gettin images");
		fighter = Toolkit.getDefaultToolkit().getImage("images/fighter.gif");
		advanced = Toolkit.getDefaultToolkit().getImage("images/advanced.gif");
		beacon = Toolkit.getDefaultToolkit().getImage("images/beacon.gif");
		destroy = Toolkit.getDefaultToolkit().getImage("images/destroy.gif");
		edestroy = Toolkit.getDefaultToolkit().getImage("images/edestroy.gif");
		efighter = Toolkit.getDefaultToolkit().getImage("images/efighter.gif");
		egun = Toolkit.getDefaultToolkit().getImage("images/egun.gif");
		generator = Toolkit.getDefaultToolkit().getImage("images/generator.gif");
		gun = Toolkit.getDefaultToolkit().getImage("images/gun.gif");
		large = Toolkit.getDefaultToolkit().getImage("images/large.gif");
		teleport = Toolkit.getDefaultToolkit().getImage("images/teleport.gif");
		frame=new Game(); //****************** }

	}

	public boolean action(Event evt, Object arg) {
		if (evt.target instanceof Button) {
			String labl = (String)arg;
			if (labl.equals("Exit")) {
				System.exit(0);
				menu[0].setLabel("New Game");
				menu[3].setLabel("Intro");
				area=0;
				level=0;
				cancel.setVisible(false);
			}
			if (labl.equals("New Game")) {
				for (int i=0;i<5;i++) {menu[i].setVisible(false);}
				play.setVisible(true); cancel.setVisible(true);
				level++;
				area=1;
				readlevel("ins");
			}
			if (labl.equals("Cancel")) {
				if (area==1) {
					area=0;
					level=0;
					play.setVisible(false); cancel.setVisible(false);
					for (int i=0;i<5;i++) {menu[i].setVisible(true);}
				}
				if (area==2) {
					cancel.setVisible(false);
					for (int i=0;i<5;i++) {menu[i].setVisible(false);}
					area=10;
					frame.requestFocus();
					repaint();
				}
			}
			if (labl.equals("Load")) {
				load.setVisible(false);
				play.setVisible(true);
			}
			if (labl.equals("Play")) {
				menu[0].setLabel("Save");
				menu[3].setLabel("Restart");
				play.setVisible(false);
				cancel.setVisible(false);
				frame.requestFocus();
				loadmap();
			}
			repaint();
		}
		return true;
	}

	public void loadmap() {
		readlevel("map");
		for (int i=0; i<20; i++) {
			if ((miscstring=thereturn[i])!=null) {
			}
		}
		area=10;
		repaint();
	}

	public void readlevel(String part) {
			try {
				int position=0; thereturn=new String[20];
				RandomAccessFile leveldat = new RandomAccessFile(
					"levels/level" + level,"r");
				while((miscstring=leveldat.readLine())!=null
					&& miscstring.equals("*"+part)!=true) {
			}
				while((miscstring=leveldat.readLine())!=null
					&& miscstring.equals("/"+part)!=true && position!=20) {
					thereturn[position]=miscstring;
					position++;
			}

		} catch (Exception e) {System.out.println(e);}
	}

	public void pause(int time) {
		try {
			Thread.sleep(time);
		} catch(InterruptedException e) {}
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		if (e.getX()<100 && e.getY()>500) {
			mapx=e.getX()*mapsizex/100-400;
			mapy=(e.getY()-500)*mapsizey/100-250;
			if (mapx>mapsizex-800) mapx=mapsizex-800;
			if (mapx<0) mapx=0;
			if (mapy>mapsizey-500) mapy=mapsizey-500;
			if (mapy<0) mapy=0;
		}
			mdx=e.getX()+mapx;
			mdy=e.getY()+mapy;
		if (area==10) {
			if (e.getY()<500) {
				if (action==0 || selected[0]==-1) {
					for (int i=0;i<stuff.length;i++) {
					if (stuff[i][2]>50) { size=150; } else {
						if (stuff[i][2]>0) size=gunits[stuff[i][2]-1][1];
					}
						if (stuff[i][0]<=e.getX()+mapx && stuff[i][0]+size>=e.getX()+mapx && stuff[i][1]<=e.getY()+mapy && stuff[i][1]+size>=e.getY()+mapy && stuff[i][2]>0) {
							if (e.isShiftDown() && stuff[i][2]<50 && stuff[selected[0]][2]<50 && stuff[i][2]>0) { int arr[]=new int[selected.length+1];
								int h=0;
								for (int j=0; j<selected.length; j++) {
									arr[j]=selected[j]; if (arr[j]==i) h++;
								} arr[selected.length]=i; if (h==0) selected=arr;
							} else {
							int arr[]=new int[1]; arr[0]=i; selected=arr;
							}
						}
					}
				} else {
					if (stuff[selected[0]][2]<50) {
						for (int i=0;i<selected.length;i++) {
							stuff[selected[i]][4]=action;
							stuff[selected[i]][5]=e.getX()+mapx;
							stuff[selected[i]][6]=e.getY()+mapy;
						}
						action=0;
					} else {
						int[][] getit={{e.getX()+mapx,e.getY()+mapy,52,50,0,0,0,0},{e.getX()+mapx,e.getY()+mapy,53,75,0,0,0,0},
							{e.getX()+mapx,e.getY()+mapy,54,100,0,0,0,0},
							{e.getX()+mapx,e.getY()+mapy,55,100,0,0,0,0},
							{e.getX()+mapx,e.getY()+mapy,1,5,3,0,0,0},
							{e.getX()+mapx,e.getY()+mapy,2,15,3,0,0,0},
							{e.getX()+mapx,e.getY()+mapy,3,20,3,0,0,0}};
						int[] h={-1};
						if (stuff[selected[0]][2]==51) {
							int reqenergy=action*300-300;
							if (action==1) reqenergy=1000;
							if (energy>=reqenergy) {
							energy-=reqenergy;
							if (action==1) h=getit[0];
							if (action==2) h=getit[1];
							if (action==3) h=getit[2];
							if (action==4) h=getit[3];}
						}
						if (stuff[selected[0]][2]==53  && action==1) {
							if (energy>=100) { energy-=100; h=getit[4]; }
						}
						if (stuff[selected[0]][2]==54  && action==1) {
							if (energy>=200) { energy-=200; h=getit[5]; }
						}
						if (stuff[selected[0]][2]==55  && action==1) {
							if (energy>=350) { energy-=350; h=getit[6]; }
						}
						if (h[0]!=-1) {
							int arra[][]=new int[stuff.length+1][7];
							for (int j=0; j<stuff.length; j++) {
								arra[j]=stuff[j];
							} arra[stuff.length]=h; stuff=arra;
						}
					}
					action=0;
				}
			}
            if (selected[0]!=-1) {
			if (e.getY()>500 && e.getX()>600 && e.getY()<566) {
				if (e.getX()<700) {
					if (e.getY()<533) { action=1; } else { action=3; } }
				else { if (e.getY()<533) { action=2; } else { action=4; } }
			if (action>1 && stuff[selected[0]][2]>51) action=0;
			if (action!=0 && stuff[selected[0]][2]==52) action=0;
			} }
		}
	}

	public void mouseReleased(MouseEvent e) {
		if ((e.getX()+mapx-mdx)*(e.getX()+mapx-mdx)>2500 && (e.getY()+mapy-mdy)*(e.getY()+mapy-mdy)>2500) {
		int gx=e.getX()+mapx;
		int lx=mdx;
		int gy=e.getY()+mapy;
		int ly=mdy;
		if (lx>gx) {gx=mdx; lx=e.getX()+mapx;}
		if (ly>gy) {gy=mdy; ly=e.getY()+mapy;}
		if (e.isShiftDown()!=true || stuff[selected[0]][2]>50) { int arr[]=new int[1]; arr[0]=-1; selected=arr; }
		for (int i=0;i<stuff.length;i++) {
			if (stuff[i][2]<50 && stuff[i][2]>0) {
				size=gunits[stuff[i][2]-1][1];
				if (stuff[i][0]+size>lx && stuff[i][0]<gx && stuff[i][1]+size>ly && stuff[i][1]<gy) {
					if ((e.isShiftDown() && stuff[i][2]>0) || selected[0]!=-1) { int arr[]=new int[selected.length+1];
						int h=0;
							for (int j=0; j<selected.length; j++) {
							arr[j]=selected[j]; if (arr[j]==i) h++;
						} arr[selected.length]=i; if (h==0) selected=arr;
					} else {
						int arr[]=new int[1]; arr[0]=i; selected=arr;
					}
				}
			}
	} } mdx=0; mdy=0; }

	public void mouseEntered(MouseEvent e) {
		scrollx=0;
		scrolly=0;
	}

	public void mouseExited(MouseEvent e) {
		if (e.getX()<30) scrollx-=mousescroll;
		if (e.getX()>770) scrollx=mousescroll;
		if (e.getY()<30) scrolly-=mousescroll;
		if (e.getY()>570) scrolly=mousescroll;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if (area==10) {
			switch(e.getKeyCode()) {
				case 40:
					if (mapy<mapsizey-500) mapy += keyscroll;
					break;
				case 38:
					if (mapy>0) mapy -= keyscroll;
					break;
				case 37:
					if (mapx>0) mapx -= keyscroll;
					break;
				case 39:
					if (mapx<mapsizex-800) mapx += keyscroll;
					break;
				case 27:
					area=2;
					menu[4].setVisible(true);
					cancel.setVisible(true);
					repaint();
					break;
				case 65:
					action=1;
					break;
				case 77:
					action=2;
					break;
				case 83:
					action=3;
					break;
				default:
					break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {}

	public void update(Graphics o) {
		paint(o);
	}

	public void paint(Graphics o) { int i;
		if (area==0) {
			offscreen=createImage(800,600);
			g=offscreen.getGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,800,600);
		}
		if (area==1) {
			g.setColor(Color.green);
			g.setFont(f);
			for (i=0;i<20;i++) {
				if (thereturn[i]!=null) g.drawString(thereturn[i],10,20*i+40);
			}
		}
		if (area==2) {
			g.setColor(Color.black);
			g.fillRect(0,0,800,600);
		}
		if (area==10) {
			if (scrollx!=0 || scrolly!=0) {
				if (mapx<mapsizex-800 && scrollx>0) mapx+=scrollx;
				if (mapx>0 && scrollx<0) mapx+=scrollx;
				if (mapy<mapsizey-500 && scrolly>0) mapy+=scrolly;
				if (mapy>0 && scrolly<0) mapy+=scrolly;
			}
			for (i=0;i<stuff.length;i++) {
				if (stuff[i][2]==52) energy++;
				if (stuff[i][2]<50) {
					if (stuff[i][4]<3) {
					    if (stuff[i][2]<0) { unit=-1-stuff[i][2]; } else { unit=stuff[i][2]-1; }
						if (stuff[i][7]!=0 && getdistance(i,stuff[i][7])>gunits[unit][3]) {
							stuff[i][5]=stuff[stuff[i][7]][0];
							stuff[i][6]=stuff[stuff[i][7]][1];
							System.out.println("attacking");
						}
						int x=stuff[i][5]-stuff[i][0];
						int y=stuff[i][6]-stuff[i][1];
						int move;
						if (stuff[i][2]>0) { move=gunits[stuff[i][2]-1][0]; }
						else { move=gunits[-1-stuff[i][2]][0]; }
						if ((x*x)<(move*move) && (y*y)<(move*move)) {
							stuff[i][0]=stuff[i][5];
							stuff[i][1]=stuff[i][6];
							x=0; y=0; }
						if (x!=0 || y!=0) {
							stuff[i][0]+=move*x/squareroot((x*x)+(y*y));
							stuff[i][1]+=move*y/squareroot((x*x)+(y*y));
						} else { stuff[i][4]=3; }
					}
				}
			}
			g.setColor(mapcolor);
			g.fillRect(0,0,800,500); int z=0; /*
			g.setColor(new Color(0,16,0));
			if (Math.random()<.3) {
				g.drawString("You allways circle all 'A's when you don't know the answers.",(int)Math.floor(Math.random()*400),(int)Math.floor(Math.random()*500)+20);
			} else if (Math.random()<.5) {
				g.drawString("'A' is the right answer today. You want to circle A.",(int)Math.floor(Math.random()*400),(int)Math.floor(Math.random()*500)+20);
			} else {
				g.drawString("You must circle all 'A's, today!",(int)Math.floor(Math.random()*400),(int)Math.floor(Math.random()*500)+20);
			} */
			for (i=0; i<stuff.length; i++) {
				if (stuff[i][2]<0) z=1;
				if (stuff[i][2]>50) { size=150; } else {
					if (stuff[i][2]>0) size=gunits[stuff[i][2]-1][1];
					if (stuff[i][2]<0) size=gunits[-1-stuff[i][2]][1]; }
				if (stuff[i][0]<mapx+800 && stuff[i][0]+size>mapx-120 &&
					stuff[i][1]<mapy+500 && stuff[i][1]+size>mapy) {
					if (stuff[i][2]==1) g.drawImage(fighter,stuff[i][0]-mapx,
						stuff[i][1]-mapy,this);
					if (stuff[i][2]==2) g.drawImage(destroy,stuff[i][0]-mapx,
						stuff[i][1]-mapy,this);
					if (stuff[i][2]==3) g.drawImage(gun,stuff[i][0]-mapx,
						stuff[i][1]-mapy,this);
					if (stuff[i][2]==51) g.drawImage(beacon,stuff[i][0]-mapx,
						stuff[i][1]-mapy,this);
					if (stuff[i][2]==52) g.drawImage(generator,stuff[i][0]-mapx,
						stuff[i][1]-mapy,this);
					if (stuff[i][2]==53) g.drawImage(teleport,stuff[i][0]-mapx,
						stuff[i][1]-mapy,this);
					if (stuff[i][2]==54) g.drawImage(large,stuff[i][0]-mapx,
						stuff[i][1]-mapy,this);
					if (stuff[i][2]==55) g.drawImage(advanced,stuff[i][0]-mapx,
						stuff[i][1]-mapy,this);
					if (stuff[i][2]==-1) g.drawImage(efighter,stuff[i][0]-mapx,
						stuff[i][1]-mapy,this);
					if (stuff[i][2]==-2) g.drawImage(edestroy,stuff[i][0]-mapx,
						stuff[i][1]-mapy,this);
					if (stuff[i][2]==-3) g.drawImage(egun,stuff[i][0]-mapx,
						stuff[i][1]-mapy,this);
				}
				if (stuff[i][2]<50) { for (int j=0; j<stuff.length; j++) {
					if (((stuff[i][2]>0 && stuff[j][2]<0) || (stuff[i][2]<0 && stuff[j][2]>0)) && stuff[i][7]==0 && stuff[i][4]!=2) {
						if (getdistance(i,j)<300) {
							stuff[i][7]=j;
							stuff[i][4]=1;
						}
					}
				} } if (stuff[i][2]<50) {
				if (stuff[i][2]<0) { unit=-1-stuff[i][2];
					 g.setColor(Color.yellow);
					if (Math.random()<.5) g.setColor(Color.red);
					if (Math.random()<.33) g.setColor(Color.pink);
					if (Math.random()<.25) g.setColor(Color.orange);
				} else { unit=stuff[i][2]-1; 
					g.setColor(Color.blue);
					if (Math.random()<.5) g.setColor(Color.green);
					if (Math.random()<.33) g.setColor(Color.white);
					if (Math.random()<.25) g.setColor(Color.cyan);
				}
				if (stuff[i][7]!=0 && getdistance(i,stuff[i][7])<=gunits[unit][3]) {
					System.out.println("fighting"+unit);
					stuff[i][5]=stuff[i][0]; stuff[i][6]=stuff[i][1];
					stuff[stuff[i][7]][3]-=gunits[unit][2];
					g.drawLine(stuff[i][0]+gunits[unit][1]/2-mapx,stuff[i][1]+gunits[unit][1]/2-mapy,
						stuff[stuff[i][7]][0]+15-mapx, stuff[stuff[i][7]][1]+25-mapy);
					if (stuff[stuff[i][7]][3]<=0) { int[][] arra=new int[stuff.length-1][8];
						 int k;
						System.out.println("kill made:"+stuff[i][7]);
						for (k=0; k<stuff.length; k++) {
							if (k!=i) stuff[k][7]=0;
						}
						for (k=0; k<stuff.length; k++) {
							if (k<stuff[i][7]) arra[k]=stuff[k];
							if (k>stuff[i][7]) arra[k-1]=stuff[k];
						}
						int l=-1;
						for (k=0; k<selected.length; k++) {
							if (selected[k]==stuff[i][7]) l=k;
						} if (selected[0]==stuff[i][7] && selected.length==1) selected[0]=-1;
						if (l!=-1 && selected.length!=1) { int[] arr=new int [selected.length-1];
							for (k=0; k<selected.length; k++) {
								if (k<l) arr[k]=selected[k];
								if (k>l) arr[k-1]=selected[k];
							}
							selected=arr;
						}
						for (k=0; k<selected.length; k++) {
							if (selected[k]>stuff[i][7]) selected[k]--;
						}
						stuff=arra;
						if (i>stuff[i][7]) i--;
						stuff[i][7]=0;
					}
				} }
			} if (selected[0]!=-1) {
				g.setColor(Color.white);
				for (i=0; i<selected.length; i++) {
					if (stuff[selected[i]][0]<mapx+800 && stuff[selected[i]][0]>mapx-120 &&
						stuff[selected[i]][1]<mapy+500 && stuff[selected[i]][1]>mapy) {
						if (stuff[selected[i]][2]>50) { size=150; } else {
							size=gunits[stuff[selected[i]][2]-1][1]; }
							g.drawRect(
							stuff[selected[i]][0]-mapx,stuff[selected[i]][1]-mapy,
							size,size);
			} } }
			g.setColor(new Color(50,0,0));
			g.fillRect(100,500,700,100);
			g.setColor(Color.white);
			g.drawLine(0,500,800,500);
			g.drawLine(600,500,600,600);
			g.drawLine(700,500,700,600);
			g.drawLine(600,533,800,533);
			g.drawLine(600,566,800,566);
			g.setColor(Color.darkGray);
			g.fillRect(0,500,100,100);
			g.setColor(Color.blue);
			g.fillRect(mapx*100/mapsizex,mapy*100/mapsizey+500,80000/mapsizex,
				50000/mapsizey);
			for (i=0; i<stuff.length; i++) {
				g.setColor(Color.green);
				if (stuff[i][2]<0) g.setColor(Color.red);
				if (stuff[i][2]>50) { size=150; } else {
					if (stuff[i][2]>0) size=gunits[stuff[i][2]-1][1];
					if (stuff[i][2]<0) size=gunits[-1-stuff[i][2]][1]; }
				g.fillRect(stuff[i][0]*100/mapsizex,
					stuff[i][1]*100/mapsizey+500,
					size*100/mapsizex,size*100/mapsizey);
			}
			g.setFont(new Font("TimesRoman",Font.PLAIN,14));
			g.setColor(Color.white);
			if (selected[0]!=-1 && stuff[selected[0]][2]<50 && stuff[selected[0]][2]>0) {
				g.drawString(act[1], 610, 523);
				g.drawString(act[2], 710, 523);
				g.drawString(act[3], 610, 556);
				g.drawString(act[4], 710, 556);
				g.setColor(Color.green);
				g.drawString(bcommands[stuff[selected[0]][2]+1][0], 110, 519);
				g.setColor(Color.blue);
				g.drawString("Shields:   "+stuff[selected[0]][3],110,538);
				g.setColor(Color.gray);
				g.drawString("Speed:   "+gunits[stuff[selected[0]][2]-1][0],110,557);
				g.drawString("Damage:   "+gunits[stuff[selected[0]][2]-1][2],110,576);
				g.drawString("Range:   "+gunits[stuff[selected[0]][2]-1][3],110,595);
				g.setColor(Color.blue);
				g.drawString(act[stuff[selected[0]][4]], 610, 589);
				g.drawString(act[action], 710, 589);
			}
			if (selected[0]!=-1 && stuff[selected[0]][2]>50 && stuff[selected[0]][2]>0) {
				g.drawString(bcommands[stuff[selected[0]][2]-51][0], 604, 523);
				g.drawString(bcommands[stuff[selected[0]][2]-51][1], 704, 523);
				g.drawString(bcommands[stuff[selected[0]][2]-51][2], 604, 556);
				g.drawString(bcommands[stuff[selected[0]][2]-51][3], 704, 556);
				g.setColor(Color.blue);
				if (action!=0)
				g.drawString(bcommands[stuff[selected[0]][2]-51][action-1], 710, 589);
				g.setColor(Color.green);
				if (stuff[selected[0]][2]>51) { g.drawString(bcommands[0][stuff[selected[0]][2]-52], 110, 519);
				} else { g.drawString("Teleport Beacon", 110, 519); }
				g.setColor(Color.blue);
				g.drawString("Shields:   "+stuff[selected[0]][3],110,538);
			}
			g.setColor(Color.yellow);
			if (Math.random()<.98) {
			g.drawString("Energy:   "+energy,310,533); } 
			//else { g.drawString("Circle every 'A'.",310,533);}
			g.setColor(Color.pink);
			if (selected[0]!=-1)
			g.drawString("Selected Units:   "+selected.length,310,566);
			if (z==0) {
				area=12;
			}
			pause(speed);
		}

		if (area==12) {
			g.setFont(new Font("TimesRoman",Font.BOLD,48));
			g.setColor(Color.yellow);
			g.drawString("YOU ARE VICTORIOUS!",10,250);
			menu[4].setVisible(true);
		}
		o.drawImage(offscreen,0,0,this);
	}

	public int squareroot(int square) {
		int guess=100; if (square<0) square=-square; square++;
		for (int i=1;i<11;i++) { guess=(square/guess+guess)/2; }
		return guess;
	}

	public int getdistance(int i,int j) {
		return squareroot((stuff[i][0]-stuff[j][0])*(stuff[i][0]-stuff[j][0])+(stuff[i][1]-stuff[j][1])*(stuff[i][1]-stuff[j][1]));
	}
}