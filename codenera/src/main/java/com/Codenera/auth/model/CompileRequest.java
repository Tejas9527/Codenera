package com.Codenera.auth.model;

public class CompileRequest {
    private String code;
    
    private String inputData;

    public String getInputData() {
		return inputData;
	}

	public void setInputData(String inputData) {
		this.inputData = inputData;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
