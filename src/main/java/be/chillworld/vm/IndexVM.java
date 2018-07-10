package be.chillworld.vm;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.MatchMedia;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.zk.ui.Executions;

/**
 *
 * @author chillworld
 */
public class IndexVM implements TemplateConstants {

	private String url;
	private static final String URL_PREFIX = "~./zul/webpages/templates/";
	private String template = null;

//	private Button clickMeButton; // TODO - ViewModel MUST not contain view elements!
	private String trifonProperty = "INITIAL VALUE";;


	@Init
	public void init(@QueryParam("page") String queryParam) {
		url = (String) Executions.getCurrent().getAttribute("page") + ".zul";
	}

	@MatchMedia("all and (max-width: 500px)")
	public void handleMax500() {
		switchTemplate(MOBILE);
	}

	@MatchMedia("all and (min-width: 501px)")
	public void handleMin500() {
		switchTemplate(DESKTOP);
	}

	public void switchTemplate(String newTemplate) {
		if (template == null || !newTemplate.equals(template)) {
			this.template = newTemplate;
			BindUtils.postNotifyChange(null, null, this, "template");
			BindUtils.postNotifyChange(null, null, this, "mobile");
		}
	}

	public String getTemplate() {
		return template;
	}

	public boolean isMobile() {
		return MOBILE.equals(template);
	}
	public boolean isDesktop() {
		return DESKTOP.equals(template);
	}

	public String getUrl() {
		if (url == null) {
			return null;
		}
		return URL_PREFIX + url;
	}

	public String getTrifonProperty() {
		return trifonProperty;
	}
	public void setTrifonProperty(String trifonProperty) {
		this.trifonProperty = trifonProperty;
	}

	@Command // @Command("handleButtonClicked") -- onClick="@command(indexVM.handleButtonClicked())"
//	@GlobalCommand("handleButtonClicked")
	// Works!!!(onClick="@command('handleButtonClicked')) But now it works only if we use - BindUtils.postNotifyChange(
	@NotifyChange({"trifonProperty"})
	public void handleButtonClicked() {
		System.out.println("TRIFON - button clicked!");
//		setTrifonProperty("NEW VALUE");
		trifonProperty = "NEW VALUE";
//		BindUtils.postNotifyChange(null, null, this, "trifonProperty"); // NOT necessary!!! @NotifyChange() works!

		// TODO - ViewModel MUST not contain view elements!
//		if (clickMeButton != null) {
//			clickMeButton.setLabel("BUTTON CLICKED!");
//		}
	}
}
