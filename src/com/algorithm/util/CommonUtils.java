package com.algorithm.util;

public class CommonUtils {
	/**
	 * 判断对象值是否有意义。<p>string型的非"" 非null<p>Long/Integer大于0
	 * @param obj
	 * @return
	 */
	
	public static boolean isVailable(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof String) {		
			if("".equals(obj.toString())){
				return false;
			}else{
				return true;
			}
		} else if (obj instanceof Long) {
			try {
				long Lobj = ((Long) obj).longValue();
				if (Lobj > 0) {
					return true;
				} else {
					return false;
				}
			} catch (Exception ex) {
				return false;
			}
		} else if (obj instanceof Integer) {
			try {
				int Iobj = ((Integer) obj).intValue();
				if (Iobj > 0) {
					return true;
				} else {
					return false;
				}
			} catch (Exception ex) {
				return false;
			}
		} else {
			return true;
		}
	}
}
