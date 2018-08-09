package com.mohamedomar.codechallenge.microservices.users.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

public class Temp {

/*	public static void main(String[] args) {
		String[] errorStringArray = { "2", "This line has junk text.", "121.18.19.20" };
		String[] errorStringArray2 = { "10", "20th Oct 2052", "6th Jun 1933", "26th May 1960", "20th Sep 1958",
				"16th Mar 2068", "25th May 1912", "16th Dec 2018", "26th Dec 2061", "4th Nov 2030", "28th Jul 1963" };
		String[] errorStringArray3 ={ "5",
		"a	100100"
		"b	100101",
		"c	110001",
		"d	100000",
		"[newline]	111111",
		"100100111111100101110001100000",	}*/
		// System.out.println(checkIPs(errorStringArray));
		//System.out.println(reformatDate(errorStringArray2));
		//System.out.println(reformatDate(errorStringArray3));
 

	static String[] checkIPs(String[] ip_array) {
		String[] errorStringArray = { "Invalid input!" };
		String neither = "Neither";
		String ipv4 = "IPV4";
		String ipv6 = "IPV6";
		if (ip_array.length < 2 || ip_array.length > 500) {

			return errorStringArray;
		}
		try {
			int arrayLength = Integer.parseInt(ip_array[0]);

			if (arrayLength > 50)
				return errorStringArray;
			String[] ret = new String[arrayLength];
			Pattern ipv4Pattern = Pattern
					.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
			Pattern ipv6Pattern = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
			Pattern ipv6PatternCompressed = Pattern.compile(
					"^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");
			for (int i = 1; i <= arrayLength; i++) {

				java.util.regex.Matcher ip4Matcher = ipv4Pattern.matcher(ip_array[i]);
				if (ip4Matcher.matches()) {
					int counter = 0;
					boolean validElement = true;
					while (ip4Matcher.find() && validElement && counter <= 3) {
						if (counter > 3)
							validElement = false;
						try {
							int element = Integer.parseInt(ip_array[1]);
							if (element < 0 || element > 255) {
								validElement = false;
							}
						} catch (Exception e) {
							validElement = false;
						}
						counter++;
					}
					if (validElement && counter <= 3) {
						ret[i - 1] = ipv4;
					} else
						ret[i - 1] = neither;
				} else {
					java.util.regex.Matcher ip6Matcher = ipv6Pattern.matcher(ip_array[i]);
					java.util.regex.Matcher ip6MatcherCompressed = ipv6Pattern.matcher(ip_array[i]);
					if (ip6Matcher.matches() || ip6MatcherCompressed.matches()) {
						ret[i - 1] = ipv6;
					} else {
						ret[i - 1] = neither;
					}
				}
				//
			}
			System.out.println(ret[0]);
			System.out.println(ret[1]);
			return ret;
		} catch (Exception e) {
			return errorStringArray;
		}
	}

	static String[] reformatDate(String[] dates) {
		// So basically one have to have 3 sets of data , cut each element and validate
		// the values and then finally add it to a new string!
		// will be on my eclipse for a short while!
		String[] errorStringArray = { "Invalid input!" };

		// Collections.addAll(daysSet,days);
		HashMap<String, String> months = new HashMap<String, String>();
		months.put("jan", "01");
		months.put("feb", "02");
		months.put("mar", "03");
		months.put("apr", "04");
		months.put("may", "05");
		months.put("jun", "06");
		months.put("jul", "07");
		months.put("aug", "08");
		months.put("sep", "09");
		months.put("oct", "10");
		months.put("nov", "11");
		months.put("dec", "12");
		if (dates.length < 2 || dates.length > 500) {

			return dates;
		}
		try {
			int arrayLength = Integer.parseInt(dates[0]);

			if (arrayLength > Math.pow(10, 4))
				return errorStringArray;
			String[] ret = new String[arrayLength];
			for (int i = 1; i <= arrayLength; i++) {
				StringBuffer output = new StringBuffer();
				String[] data = dates[i].split("\\s+");
				output.append(data[2] + "-" + months.get(data[1].toLowerCase()) + "-"
						+ data[0].substring(0, data[0].length() - 2));
				ret[i - 1] = output.toString();
			}
			return ret;
		} catch (Exception e) {
			return errorStringArray;
		}

	}

	static String decode(String[] codes, String encoded) {
		String  errorString  =  "Invalid input!"  ;
		if(encoded.length()<6 || encoded.length()%6 != 0 ||encoded.length() >7000)
			return errorString ;
		try {
			int arrayLength = Integer.parseInt(codes[0]);

			if (arrayLength > 100)
				return errorString;
			HashMap<String, String> codeMap = new HashMap<String, String>();
			for (int i = 1; i <= arrayLength; i++) {
		    
		    	String code = codes[i];
		    	code = code.replaceAll("\\s+","");
		    	StringBuffer value = new StringBuffer();
		    	StringBuffer key = new StringBuffer();
		    	if(code.indexOf("[newline]")> 0) {
		    		value.append("\\n");
		    		key.append(code.substring(code.indexOf("[newline]")+1,code.length()));
		    	}
		    		 
		    	else {
		    		value.append(code.charAt(0));
		    		key.append(code.substring(1,code.length()));
		    	}
		    	codeMap.put(key.toString(), value.toString());
		    }	 
		 
		    	int hopper =0;
		    	StringBuffer ret = new StringBuffer();
		    	while(hopper< encoded.length())
		    	{
		    		ret.append(codeMap.get(encoded.substring(hopper, hopper+5)));
		    	hopper+=6;	
		    	}
		    	return ret.toString();
		    
		}
		catch (Exception e) {
			return errorString;
		}
		 
	}
 
}
