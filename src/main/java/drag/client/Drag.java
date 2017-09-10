package drag.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Drag implements EntryPoint {

  public void onModuleLoad() {
    // Adding draggable widget
    RootPanel.get().add(new DraggableWidget());
  }

}
