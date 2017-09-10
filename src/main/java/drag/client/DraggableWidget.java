package drag.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DraggableWidget extends Composite {

  private static DraggableWidgetUiBinder uiBinder = GWT.create(DraggableWidgetUiBinder.class);

  interface DraggableWidgetUiBinder extends UiBinder<Widget, DraggableWidget> {
  }

  @UiField(provided = true)
  FlowHolder firstPanel;

  private static String[] FIRST_PANEL_LABELS = {"Drag Me 0", "Drag Me 1", "Drag Me 2", "Drag Me 3"};

  private static DragDropLabel label;
  private static int offsetX, offsetY;

  public DraggableWidget() {
    firstPanel = new FlowHolder(FIRST_PANEL_LABELS);
    initWidget(uiBinder.createAndBindUi(this));
  }

  static DragDropLabel getDragDropLabel() {
    return label;
  }

  public static void clearDraggable() {
    DraggableWidget.label = null;
  }

  public static void hasDraggable() {
    DraggableWidget.label = null;
  }

  public static void setDragDropLabel(DragDropLabel label, int offsetX, int offsetY) {
    DraggableWidget.label = label;
    DraggableWidget.offsetX = offsetX;
    DraggableWidget.offsetY = offsetY;
  }

  public static int getOffsetX() {
    return offsetX;
  }

  public static int getOffsetY() {
    return offsetY;
  }



}
