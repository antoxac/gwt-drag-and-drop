package drag.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class FlowHolder extends Composite {

  private static FlowHolderUiBinder uiBinder = GWT.create(FlowHolderUiBinder.class);

  interface FlowHolderUiBinder extends UiBinder<Widget, FlowHolder> {
  }

  interface Style extends CssResource {
    String holderHover();
  }

  @UiField
  Style style;

  @UiField
  Label name;

  @UiField
  FlowPanel holder;

  private static String NAME_PREFIX = "Holder ";
  private static int index = 0;

  public FlowHolder() {
    initWidget(uiBinder.createAndBindUi(this));

    name.setText(NAME_PREFIX + index++);

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
      addLabel(DragAndDrop.getDragLabel());
    }, DropEvent.getType());
  }

  public FlowHolder(String... names) {
    this();

    for (String name : names)
      addLabel(new DragLabel(name));
  }

  void addLabel(DragLabel label) {
    // If drag is made outside of GWT check for null
    // This is made for integration with other JS modules
    if (label != null)
      holder.add(label);

    DragAndDrop.clearDragLabel();
  }

}
