/**
 * StatisticsData.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 16, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.model;

/**
 * @author Sounak Ray
 */
public final class StatisticsData {
	private String startDate = "";
	private String endDate = "";
	private String averageMileage = "";
	private String lastMileage = "";
	private String totalDistance = "";
	private String totalDays = "";
	private String totalFuelConsumed = "";
	private String totalMoneyCnsmd = "";
	private String totalFuel = "";
	private String totalMoney = "";
	private String fuelPer100Dist = "";
	private String avgCostPerDist = "";

	private static final String UNIT_DIST_SHORT = "Km";
	private static final String UNIT_VOL_SHORT = "ltr";
	private static final String CURRENCY = "Rs.";

	/**
	 * @return the startDate
	 */
	public String getStartDate(){
		return this.startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final String startDate){
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate(){
		return this.endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(final String endDate){
		this.endDate = endDate;
	}

	/**
	 * @return the averageMileage
	 */
	public String getAverageMileage(){
		return this.averageMileage + ' ' + UNIT_DIST_SHORT + '/' + UNIT_VOL_SHORT;
	}

	/**
	 * @param averageMileage the averageMileage to set
	 */
	public void setAverageMileage(final String averageMileage){
		this.averageMileage = averageMileage;
	}

	/**
	 * @return the lastMileage
	 */
	public String getLastMileage(){
		return this.lastMileage + ' ' + UNIT_DIST_SHORT + '/' + UNIT_VOL_SHORT;
	}

	/**
	 * @param lastMileage the lastMileage to set
	 */
	public void setLastMileage(final String lastMileage){
		this.lastMileage = lastMileage;
	}

	/**
	 * @return the totalDistance
	 */
	public String getTotalDistance(){
		return this.totalDistance + ' ' + UNIT_DIST_SHORT;
	}

	/**
	 * @param totalDistance the totalDistance to set
	 */
	public void setTotalDistance(final String totalDistance){
		this.totalDistance = totalDistance;
	}

	/**
	 * @return the totalDays
	 */
	public String getTotalDays(){
		return this.totalDays + " days";
	}

	/**
	 * @param totalDays the totalDays to set
	 */
	public void setTotalDays(final String totalDays){
		this.totalDays = totalDays;
	}

	/**
	 * @return the totalFuelConsumed
	 */
	public String getTotalFuelConsumed(){
		return this.totalFuelConsumed + ' ' + UNIT_VOL_SHORT;
	}

	/**
	 * @param totalFuelConsumed the totalFuelConsumed to set
	 */
	public void setTotalFuelConsumed(final String totalFuelConsumed){
		this.totalFuelConsumed = totalFuelConsumed;
	}

	/**
	 * @return the totalMoneyConsumed
	 */
	public String getTotalMoneyCnsmd(){
		return CURRENCY + this.totalMoneyCnsmd;
	}

	/**
	 * @param totalMoneyCnsmd the totalMoneyConsumed to set
	 */
	public void setTotalMoneyCnsmd(final String totalMoneyCnsmd){
		this.totalMoneyCnsmd = totalMoneyCnsmd;
	}

	/**
	 * @return the totalFuel
	 */
	public String getTotalFuel(){
		return this.totalFuel + ' ' + UNIT_VOL_SHORT;
	}

	/**
	 * @param totalFuel the totalFuel to set
	 */
	public void setTotalFuel(final String totalFuel){
		this.totalFuel = totalFuel;
	}

	/**
	 * @return the totalMoney
	 */
	public String getTotalMoney(){
		return CURRENCY + this.totalMoney;
	}

	/**
	 * @param totalMoney the totalMoney to set
	 */
	public void setTotalMoney(final String totalMoney){
		this.totalMoney = totalMoney;
	}

	/**
	 * @return the fuelPer100Dist
	 */
	public String getFuelPer100Dist(){
		return this.fuelPer100Dist;
	}

	/**
	 * @param fuelPer100Dist the fuelPer100Dist to set
	 */
	public void setFuelPer100Dist(final String fuelPer100Dist){
		this.fuelPer100Dist = fuelPer100Dist;
	}

	/**
	 * @return the averageCostPerDist
	 */
	public String getAvgCostPerDist(){
		return CURRENCY + this.avgCostPerDist;
	}

	/**
	 * @param avgCostPerDist the averageCostPerDist to set
	 */
	public void setAvgCostPerDist(final String avgCostPerDist){
		this.avgCostPerDist = avgCostPerDist;
	}
}
