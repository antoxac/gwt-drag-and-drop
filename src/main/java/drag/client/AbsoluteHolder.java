package drag.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AbsoluteHolder extends Composite {

  private static AbsoluteHolderUiBinder uiBinder = GWT.create(AbsoluteHolderUiBinder.class);

  interface AbsoluteHolderUiBinder extends UiBinder<Widget, AbsoluteHolder> {
  }

  interface Style extends CssResource {
    String targetHover();
  }

  @UiField
  Style style;

  @UiField
  AbsolutePanel target;

  public AbsoluteHolder() {
    initWidget(uiBinder.createAndBindUi(this));

    target.addDomHandler(e -> {
      // Changing border style on DragOver
      target.addStyleName(style.targetHover());
    }, DragOverEvent.getType());

    target.addDomHandler(e -> {
      target.removeStyleName(style.targetHover());
    }, DragLeaveEvent.getType());

    target.addDomHandler(e -> {
      e.preventDefault();
      target.removeStyleName(style.targetHover());

      // Calculating position
      int x = e.getNativeEvent().getClientX() - target.getAbsoluteLeft() - DraggableWidget.getOffsetX();
      int y = e.getNativeEvent().getClientY() - target.getAbsoluteTop() - DraggableWidget.getOffsetY();

      target.add(DraggableWidget.getDragDropLabel(), x, y);
      DraggableWidget.clearDraggable();

    }, DropEvent.getType());
  }



}
