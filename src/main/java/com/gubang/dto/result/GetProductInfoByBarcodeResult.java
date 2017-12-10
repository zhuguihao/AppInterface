package com.gubang.dto.result;

public class GetProductInfoByBarcodeResult {
	
   private Integer id;
   
   private String series;

   private String name;

   private String machineCode;

   private String voltageRange;

   private Double distributePrice;

   private Double retailPrice;

   private String functionDescp;

   private String policy;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSeries() {
		return series;
	}
	
	public void setSeries(String series) {
		this.series = series;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMachineCode() {
		return machineCode;
	}
	
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	
	public String getVoltageRange() {
		return voltageRange;
	}
	
	public void setVoltageRange(String voltageRange) {
		this.voltageRange = voltageRange;
	}
	
	public Double getDistributePrice() {
		return distributePrice;
	}
	
	public void setDistributePrice(Double distributePrice) {
		this.distributePrice = distributePrice;
	}
	
	public Double getRetailPrice() {
		return retailPrice;
	}
	
	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	public String getFunctionDescp() {
		return functionDescp;
	}
	
	public void setFunctionDescp(String functionDescp) {
		this.functionDescp = functionDescp;
	}
	
	public String getPolicy() {
		return policy;
	}
	
	public void setPolicy(String policy) {
		this.policy = policy;
	}

}
