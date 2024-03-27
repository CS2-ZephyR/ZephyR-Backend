package com.github.ioloolo.zephyr.domain.react;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ReactController {

	@RequestMapping(value = {"/", "/{x:[\\w\\-]+}", "/{x:^(?!api$).*$}/**/{y:[\\w\\-]+}"})
	public String getIndexPage() {

		return "/index.html";
	}

	@GetMapping("/connect")
	public RedirectView getConnectPage() {

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("steam://connect/teamzephyr.kro.kr/0on6mB85Esc9ZcUn");

		return redirectView;
	}
}
