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
    String holderHover();
  }

  @UiField
  Style style;

  @UiField
  AbsolutePanel holder;

  public AbsoluteHolder() {
    initWidget(uiBinder.createAndBindUi(this));

    holder.addDomHandler(e -> {
      // Changing border style on DragOver
      holder.addStyleName(style.holderHover());
    }, DragOverEvent.getType());

    holder.addDomHandler(e -> {
      holder.removeStyleName(style.holderHover());
    }, DragLeaveEvent.getType());

    holder.addDomHandler(e -> {
      e.preventDefault();
      holder.removeStyleName(style.holderHover());

      // Calculating position
      int x = e.getNativeEvent().getClientX() - holder.getAbsoluteLeft() - DragAndDrop.getOffsetX();
      int y = e.getNativeEvent().getClientY() - holder.getAbsoluteTop() - DragAndDrop.getOffsetY();

      holder.add(DragAndDrop.getDragLabel(), x, y);
      DragAndDrop.clearDragLabel();

    }, DropEvent.getType());
  }

}
