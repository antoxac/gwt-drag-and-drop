package drag.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Label;

public class DragLabel extends Label {

  public interface Style extends ClientBundle {
    @Source("DragLabel.css")
    CSS css();

    public interface CSS extends CssResource {
      String style();

      String hover();
    }
  }

  private static final Style style = GWT.create(Style.class);

  public DragLabel(String text) {
    super(text);

    // Styling
    style.css().ensureInjected();
    setStyleName(style.css().style());

    // Drag and Drop logic
    getElement().setDraggable(Element.DRAGGABLE_TRUE);
    addDragStartHandler(e -> {
      int offsetX = e.getNativeEvent().getClientX() - getAbsoluteLeft();
      int offsetY = e.getNativeEvent().getClientY() - getAbsoluteTop();

      DragAndDrop.setDragDropLabel(this, offsetX, offsetY);

      e.setData("ID", text);
      e.getDataTransfer().setDragImage(getElement(), offsetX, offsetY);
    });

    // Handlers for Chrome hover effect support
    addMouseOverHandler(e -> {
      addStyleName(style.css().hover());
    });
    addMouseOutHandler(e -> {
      removeStyleName(style.css().hover());
    });
  }

}
