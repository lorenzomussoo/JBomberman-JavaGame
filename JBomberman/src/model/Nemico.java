package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * La classe Nemico è la classe che crea i nemici del gioco
 */
public class Nemico implements Serializable {
	
	/**
	 * variabile utile per la serializzazione
	 */
	private static final long serialVersionUID = 7142224605703007026L;
	/**
	 * punteggio
	 */
	private int punti;
	/**
	 * tipo nemico
	 */
	private int tipo;
	/**
	 * vite nemico
	 */
	private int vite;
	/**
	 * numero massimo di vite
	 */
	private int maxVite;
	/**
	 * dimensione nemico
	 */
	private final int dimension = 55;
	/**
	 * velocità nemico
	 */
	private double speed = 1.5;
	/**
	 * variaabile che indica se il nemico è in gioco
	 */
	private boolean inGioco;
	/**
	 * contenitore nemico
	 */
	private ImageView skin = new ImageView();
	/**
	 * immagine frontale
	 */
	private Image front = null; 
	/**
	 * immagine posteriore
	 */
	private Image back = null; 
	/**
	 * immagine profilo sinistro
	 */
	private Image leftProfile = null; 
	/**
	 * immagine profilo destro
	 */
	private Image rightProfile = null; 
	/**
	 * immagini animazione camminata in basso
	 */
	private List<Image> walkingDown = new ArrayList<>(); 
	/**
	 * immagini animazione camminata in alto
	 */
	private List<Image> walkingUp = new ArrayList<>();
	/**
	 * immagini animazione camminata a sinistra
	 */
	private List<Image> walkingLeft = new ArrayList<>();
	/**
	 * immagini animazione camminata a destra
	 */
	private List<Image> walkingRight = new ArrayList<>();
	/**
	 * coordinate x e y del nemico
	 */
	private double enemyX, enemyY;
	/**
	 * lista di interi del movimento
	 */
	private List<Integer> movimento = new ArrayList<>();
	/**
	 * indice direzione corrente
	 */
	private int currentDirectionIndex;
	/**
	 * direzione movimento
	 */
	private boolean northEnemy = false, southEnemy = false, eastEnemy = false, westEnemy = false;
	/**
	 * se il nemico cammina
	 */
	private boolean walkingEnemy = false;
	
	
	/**
	 * Costruttore di Nemico che crea un'istanza di nemico
	 */
	public Nemico()
	{
		
	}
	
	/**
	 * Costruttore di nemico che crea un'istanza di nemico che varia in base al tipo
	 * @param tipo tipo di nemico da creare
	 */
	public Nemico(int tipo) { 
		super();
		this.tipo = tipo;
		this.currentDirectionIndex = 0;
		switch (tipo)
		{
			case 0: default: vite = 1; maxVite = 1;
			 front = new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r4.png")); 
			 back = new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r7.png")); 
			 leftProfile = new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r9.png")); 
			 rightProfile = new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r13.png")); 
			 for (int i = 0; i < 2; i++) 
			 {
				 walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r5.png")));
				 walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r6.png")));
				 walkingUp.add(back);
				 walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r8.png")));
				 
				 walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r1.png")));
				 walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r2.png")));
				 walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r3.png")));
				 walkingDown.add(front);
				 
				 walkingLeft.add(leftProfile);
				 walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r10.png")));
				 walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r11.png")));
				 walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r12.png")));
				 
				 walkingRight.add(rightProfile);
				 walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r14.png")));
				 walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r15.png")));
				 walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r16.png")));
			 }
			 break;
			case 1: vite = 2; maxVite = 2;
			 front = new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b5.png")); 
			 back = new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b6.png")); 
			 leftProfile = new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b4.png")); 
			 rightProfile = new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b7.png")); 
			 for (int i = 0; i < 2; i++)
			 {
				 walkingUp.add(back);
				 walkingUp.add(back);
				 walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b1.png")));
				 walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b3.png"))); 
				 
				 walkingDown.add(front);
				 walkingDown.add(back);
				 walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b1.png")));
				 walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b3.png"))); 
				 
				 walkingLeft.add(leftProfile);
				 walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b2.png")));
				 walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b1.png")));
				 walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b3.png"))); 
				 
				 walkingRight.add(rightProfile);
				 walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b8.png")));
				 walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b9.png")));
				 walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b10.png"))); 
			 }
			 break;
			case 2: vite = 3; maxVite = 3;
			 front = new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank4.png")); 
			 back = new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank6.png")); 
			 leftProfile = new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank2.png")); 
			 rightProfile = new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank2.png")); 
			 for (int i = 0; i < 2; i++)
			 {
				 walkingUp.add(back);
				 walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank5.png")));
				 walkingUp.add(back);
				 walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank5.png")));
				 
				 walkingDown.add(front);
				 walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank3.png")));
				 walkingDown.add(front);
				 walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank3.png")));
				 
				 walkingLeft.add(leftProfile);
				 walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank1.png")));
				 walkingLeft.add(leftProfile);
				 walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank1.png")));
				 
				 walkingRight.add(rightProfile);
				 walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank1.png")));
				 walkingRight.add(rightProfile);
				 walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank1.png")));
			 }
			 break;
			case 3: vite = 4; maxVite = 4;
			 front = new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p2.png")); 
			 back = new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p6.png")); 
			 leftProfile = new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p9.png")); 
			 rightProfile = new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p11.png")); 
			 for (int i = 0; i < 2; i++) 
			 {
				 walkingUp.add(back);
				 walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p7.png")));
				 walkingUp.add(back);
				 walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p7.png")));
				 
				 walkingDown.add(front);
				 walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p3.png")));
				 walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p4.png")));
				 walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p3.png")));
				 
				 walkingLeft.add(leftProfile);
				 walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p10.png")));
				 walkingLeft.add(leftProfile);
				 walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p10.png")));
				 
				 walkingRight.add(rightProfile);
				 walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p12.png")));
				 walkingRight.add(rightProfile);
				 walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p12.png")));
			 }
			 break;
			case 4: vite = 5; maxVite = 5;
			 front = new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f1.png")); 
			 back = new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f6.png")); //era f5 
			 leftProfile = new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f3.png")); 
			 rightProfile = new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f7.png")); 
			 for (int i = 0; i < 2; i++)
			 {
				 walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f6.png")));
				 walkingUp.add(back);
				 walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f4.png")));
				 walkingUp.add(back);
				 
				 walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f2.png")));
				 walkingDown.add(front);
				 walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f8.png")));
				 walkingDown.add(front);
				 
				 walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f2.png")));
				 walkingLeft.add(leftProfile);
				 walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f4.png")));
				 walkingLeft.add(leftProfile);
				 
				 walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f8.png")));
				 walkingRight.add(rightProfile);
				 walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f6.png")));
				 walkingRight.add(rightProfile);
			 }
			 break;
		}
		skin.setImage(front);
		skin.setPreserveRatio(true);
		skin.setFitWidth(dimension);
		skin.setFitHeight(dimension);
	}
	
	/**
	 * Metodo che fa muovere il nemico utilizzando un coportamento definito dall'interfaccia ComportamentoNemico
	 * @param comp il coportamento che il nemico deve adottare
	 */
	public void muovi (ComportamentoNemico comp)
	{
		movimento.clear();
		movimento = comp.moveEnemy();
	}
	
	/**
	 * Metodo che diminuisce le vite di un nemico di una
	 */
	public void decreaseVite()
	{
		if (vite > 0)
		{
			vite--;
		}
	}
	
	/**
	 * Metodo che restituisce una lista di interi che rappresentano il movimento di un nemico
	 * @return lista di interi che rappresentano il movimento di un nemico
	 */
	public List<Integer> getMovimento() {
		return movimento;
	}

	/**
	 * Metodo che posiziona il nermico alle coordinate x e y
	 * @param x coordinata x
	 * @param y coordinata y
	 */
	public void relocate(double x, double y)
	{
		skin.relocate(x, y);
	}

	/**
	 * Metodo che restituisce il quantitativo di punti che si ottengono uccidendo il nemico
	 * @return quantitativo di punti che si ottengono uccidendo il nemico
	 */
	public int getPunti() {
		return punti;
	}

	/**
	 * Metodo che imposta il quantitativo di punti che si ottengono uccidendo il nemico
	 * @param punti quantitativo di punti che si ottengono uccidendo il nemico
	 */
	public void setPunti(int punti) {
		this.punti = punti;
	}

	/**
	 * Metodo che restituisce l'oggetto ImageView che rappresenta il nemico
	 * @return oggetto ImageView che rappresenta il nemico
	 */
	public ImageView getEnemy(){
		return skin;
	}

	/**
	 * Metodo che restituisce l'immagine frontale del nemico
	 * @return immagine frontale del nemico
	 */
	public Image getFront() {
		return front;
	}

	/**
	 * Metodo che imposta l'immagine frontale del nemico
	 * @param front immagine frontale del nemico
	 */
	public void setFront(Image front) {
		this.front = front;
	}

	/**
	 * Metodo che restituisce l'immagine posteriore del nemico
	 * @return immagine posteriore del nemico
	 */
	public Image getBack() {
		return back;
	}

	/**
	 * Metodo che imposta l'immagine posteriore del nemico
	 * @param back immagine posteriore del nemico
	 */
	public void setBack(Image back) {
		this.back = back;
	}

	/**
	 * Metodo che restituisce l'immagine laterale sinistra del nemico
	 * @return immagine laterale sinistra del nemico
	 */
	public Image getLeftProfile() {
		return leftProfile;
	}

	/**
	 * Metodo che imposta l'immagine laterale sinistra del nemico
	 * @param leftProfile immagine laterale sinistra del nemico
	 */
	public void setLeftProfile(Image leftProfile) {
		this.leftProfile = leftProfile;
	}

	/**
	 * Metodo che restituisce l'immagine laterale destra del nemico
	 * @return immagine laterale destra del nemico
	 */
	public Image getRightProfile() {
		return rightProfile;
	}

	/**
	 * Metodo che imposta l'immagine laterale destra del nemico
	 * @param rightProfile immagine laterale destra del nemico
	 */
	public void setRightProfile(Image rightProfile) {
		this.rightProfile = rightProfile;
	}

	/**
	 * Metodo che restituisce le dimensioni del nemico
	 * @return dimensioni del nemico
	 */
	public int getDimension() {
		return dimension;
	}

	/**
	 * Metodo che restitiuisce il tipo del nemico
	 * @return tipo del nemico
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * Metodo che imposta il tipo del nemico
	 * @param tipo tipo del nemico
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * Metodo che restituisce la velocità del nemico
	 * @return velocità del nemico
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Metodo che imposta la velocità del nemico
	 * @param speed velocità del nemico
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	/**
	 * Metodo che restituisce la coordinata x del nemico
	 * @return coordinnata x del nemico
	 */
	public double getX()
	{
		return enemyX;
	}
	
	/**
	 * Metodo che imposta la coordinata x del nemico
	 * @param x coordinata x del nemico
	 */
	public void setX(double x)
	{
		enemyX = x;
	}
	
	/**
	 * Metodo che restituisce la coordinata y del nemico
	 * @return coordinata y del nemico
	 */
	public double getY() 
	{
		return enemyY;
	}
	
	/**
	 * Metodo che imposta la coordinata y del nemico
	 * @param y coordinata y del nemico
	 */
	public void setY(double y)
	{
		enemyY = y;
	}
	
	/**
	 * Metodo che restituisce la lista di immagini che formano l'animazione della camminata verso sinistra
	 * @return lista di immagini che formano l'animazione della camminata verso sinistra
	 */
	public List<Image> getAnimationLeft()
	{
		return walkingLeft;
	}
	
	/**
	 * Metodo che restituisce la lista di immagini che formano l'animazione della camminata verso destra
	 * @return lista di immagini che formano l'animazione della camminata verso destra
	 */
	public List<Image> getAnimationRight()
	{
		return walkingRight;
	}
	
	/**
	 * Metodo che restituisce la lista di immagini che formano l'animazione della camminata verso l'alto
	 * @return lista di immagini che formano l'animazione della camminata verso l'alto
	 */
	public List<Image> getAnimationUp()
	{
		return walkingUp;
	}
	
	/**
	 * Metodo che restituisce la lista di immagini che formano l'animazione della camminata verso il basso
	 * @return lista di immagini che formano l'animazione della camminata verso il basso
	 */
	public List<Image> getAnimationDown()
	{
		return walkingDown;
	}

	/**
	 * Metodo che restituisce la variabile che indica se il nemico è in gioco
	 * @return variabile che indica se il nemico è in gioco
	 */
	public boolean isInGioco() {
		return inGioco;
	}

	/**
	 * Metodo che imposta la variabile che indica se il nemico è in gioco
	 * @param inGioco variabile che indica se il nemico è in gioco
	 */
	public void setInGioco(boolean inGioco) {
		this.inGioco = inGioco;
	}

	/**
	 * Metodo che restiuisce l'indice che indica la direzione corrente
	 * @return indice che indica la direzione corrente
	 */
	public int getCurrentDirectionIndex() {
		return currentDirectionIndex;
	}

	/**
	 * Metodo che imposta l'indice che indica la direzione corrente
	 * @param currentDirectionIndex indice che indica la direzione corrente
	 */
	public void setCurrentDirectionIndex(int currentDirectionIndex) {
		this.currentDirectionIndex = currentDirectionIndex;
	}

	/**
	 * Metodo che restituisce la variabile che indica se il nemico si sta muovendo verso nord
	 * @return variabile che indica se il nemico si sta muovendo verso nord
	 */
	public boolean isNorthEnemy() {
		return northEnemy;
	}

	/**
	 * Metodo che imposta la variabile che indica se il nemico si sta muovendo verso nord
	 * @param northEnemy variabile che indica se il nemico si sta muovendo verso nord
	 */
	public void setNorthEnemy(boolean northEnemy) {
		this.northEnemy = northEnemy;
	}

	/**
	 * Metodo che restituisce la variabile che indica se il nemico si sta muovendo verso sud
	 * @return variabile che indica se il nemico si sta muovendo verso sud
	 */
	public boolean isSouthEnemy() {
		return southEnemy;
	}

	/**
	 * Metodo che imposta la variabile che indica se il nemico si sta muovendo verso sud
	 * @param southEnemy variabile che indica se il nemico si sta muovendo verso sud
	 */
	public void setSouthEnemy(boolean southEnemy) {
		this.southEnemy = southEnemy;
	}

	/**
	 * Metodo che restituisce la variabile che indica se il nemico si sta muovendo verso est
	 * @return variabile che indica se il nemico si sta muovendo verso est
	 */
	public boolean isEastEnemy() {
		return eastEnemy;
	}

	/**
	 * Metodo che imposta la variabile che indica se il nemico si sta muovendo verso est
	 * @param eastEnemy variabile che indica se il nemico si sta muovendo verso est
	 */
	public void setEastEnemy(boolean eastEnemy) {
		this.eastEnemy = eastEnemy;
	}

	/**
	 * Metodo che restituisce la variabile che indica se il nemico si sta muovendo verso ovest
	 * @return variabile che indica se il nemico si sta muovendo verso ovest
	 */
	public boolean isWestEnemy() {
		return westEnemy;
	}

	/**
	 * Metodo che imposta la variabile che indica se il nemico si sta muovendo verso ovest
	 * @param westEnemy variabile che indica se il nemico si sta muovendo verso ovest
	 */
	public void setWestEnemy(boolean westEnemy) {
		this.westEnemy = westEnemy;
	}

	/**
	 * Metodo che restituisce la variabile che indica se il nemico si sta muovendo
	 * @return variabile che indica se il nemico si sta muovendo
	 */
	public boolean isWalkingEnemy() {
		return walkingEnemy;
	}

	/**
	 * Metodo che imposta la variabile che indica se il nemico si sta muovendo
	 * @param walkingEnemy variabile che indica se il nemico si sta muovendo
	 */
	public void setWalkingEnemy(boolean walkingEnemy) {
		this.walkingEnemy = walkingEnemy;
	}
	
	/**
	 * Metodo che restituisce il numero di vite del nemico
	 * @return numero di vite del nemico
	 */
	public int getVite()
	{
		return vite;
	}
	
	/**
	 * Metodo che imposta il numero di vite del nemico
	 * @param vite numero di vite del nemico
	 */
	public void setVite(int vite)
	{
		this.vite = vite;
	}
	
	/**
	 * Metodo che restituisce il numero massimo di vite del nemico
	 * @return numero massimo di vite del nemico
	 */
	public int getMaxVite()
	{
		return maxVite;
	}

	/**
	 * Metodo che imposta il numero massimo di vite di un nemico
	 * @param i numero massimo di vite di un nemico
	 */
	public void setMaxVite(int i) {
		maxVite = i;
	}
}