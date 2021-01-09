package com.fs.crm.util;

public class IDUtil {

    public static boolean isValid(Long id) {
    	if ((id==null) || (id <= 0)) {
        	return false;
        }
    	
    	if ( String.valueOf(id).length() > 20) {
    		return false;
    	}
        
        return true;
    }
}
