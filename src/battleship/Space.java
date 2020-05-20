package battleship;
public class Space {
	
	public boolean isHit;
	public boolean isShip;
	
	public Space(boolean isShip)
	{
		this.isShip = isShip;
		isHit = false;
	}

	public boolean getIsHit() {
		return isHit;
	}

	public void setIsHit(boolean isHit) {
		this.isHit = isHit;
	}
	
	public boolean getIsShip() {
		return isShip;
	}

	public void setIsShip(boolean isShip) {
		this.isShip = isShip;
	}
}
