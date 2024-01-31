package logic;

public class WeightItem extends Item{
	
	private double weight;
	
	public WeightItem(String name,double unitPrice) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.unitPrice = unitPrice;
		this.weight = this.scale();
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return this.unitPrice * this.weight;
	}
	
	public double scale(){
		return ((Math.random() * 3.99) + 0.01);
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String toString(){
		return String.format("WeightItem : %s\t%.2f\t$%.2f\t$%.2f", this.name,this.weight,this.unitPrice,this.cost());
	}
}
