package com.revaturelabs.ask.tags;

import org.springframework.web.bind.annotation.GetMapping;

public interface AppTagGetter {
	@GetMapping("/tag")
	public String getTags();
}
