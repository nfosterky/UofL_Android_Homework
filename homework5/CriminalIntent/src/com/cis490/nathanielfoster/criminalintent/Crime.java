package com.cis490.nathanielfoster.criminalintent;

import java.util.UUID;

public class Crime {
	private UUID mId;
	private String mTitle;
	
	public Crime() {
		mId = UUID.randomUUID();
	}
}
