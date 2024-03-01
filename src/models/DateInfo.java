package models;

public class DateInfo {
	private int startMonth;
	private int endMonth;
	private int startYear;
	
	public DateInfo() {
	}
	public DateInfo(int startMonth, int endMonth, int startYear) {
		this.startMonth = startMonth;
		setEndMonth(endMonth);
		this.startYear = startYear;
	}
	public int getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}
	public int getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(int endMonth) {
		if(endMonth == 0) {
			endMonth=12;
		}
		this.endMonth = endMonth;
	}
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	@Override
	public String toString() {
		return "DateInfo [startMonth=" + startMonth + ", endMonth=" + endMonth + ", startYear=" + startYear + "]";
	}
	
	
	

}
