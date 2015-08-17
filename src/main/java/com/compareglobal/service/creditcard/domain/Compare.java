package com.compareglobal.service.creditcard.domain;


import org.apache.commons.lang3.StringUtils;

public class Compare {
	private String id;
	private String locale;
	private View view;
	private Filter filter;

	public enum Filter {
		AIRMILES("hasAirmiles"),
		CASHBACK("hasCashBack"),
		CONTACTLESS("hasContactlessPayment"),
		DINING("hasDining"),
		ONLINESHOPPING("hasProOnline"),
		PREMIUM("hasPremium"),
		PREMIUMSG("premium"),
		BESTDEALS("hasBestDeal"),
        FREE("hasFree"),
        DISCOUNT("hasDiscount"),
        POINTS("hasPoints"),
		FIRSTCARD("has1stcard");

		private final String value;

		Filter(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum View {
		DEFAULT,
		HK,
        PT,
		ID
	}


	public String getLocale() {
		return locale;
	}


	public void setLocale(String locale) {
		this.locale = locale;
	}


	public Filter getFilter() {
		return filter;
	}


	public void setFilter(Filter filter) {
		this.filter = filter;
	}


	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public String getCountrySuffix() {
		if (StringUtils.isNotBlank(locale)) {
			return locale.substring(3);
		}
		return "";
	}

	@Override
	public String toString() {
		return "Compare{" +
				"id='" + id + '\'' +
				", locale='" + locale + '\'' +
				", view=" + view +
				", filter=" + filter +
				'}';
	}
}
