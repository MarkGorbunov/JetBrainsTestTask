package steps;

import elements.header.HeaderElement;
import utils.PageReloadable;

public class BaseSteps implements PageReloadable {
    private HeaderElement topHeader = new HeaderElement();

    public HeaderElement getTopHeader() {
        return topHeader;
    }
}
