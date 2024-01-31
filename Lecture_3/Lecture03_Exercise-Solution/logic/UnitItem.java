package logic;

public class UnitItem extends Item{

	private int amount;
	
	public UnitItem(String name,double unitPrice,int amount) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.unitPrice = unitPrice;
		this.amount = amount;
	}
	
	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return this.unitPrice * this.amount;
	}

	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String toString(){
		return String.format("UnitItem   : %s\t%d\t$%.2f\t$%.2f", this.name,this.amount,this.unitPrice,this.cost());
	}
}
