package drag.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DragAndDrop extends Composite {

  private static DragAndDropUiBinder uiBinder = GWT.create(DragAndDropUiBinder.class);

  interface DragAndDropUiBinder extends UiBinder<Widget, DragAndDrop> {
  }

  @UiField(provided = true)
  FlowHolder firstPanel;

  // Holders
  private static String[] FIRST_HOLDER_LABELS = {"Drag Me 0", "Drag Me 1", "Drag Me 2", "Drag Me 3"};

  private static DragLabel label;
  private static int offsetX, offsetY;

  public DragAndDrop() {
    firstPanel = new FlowHolder(FIRST_HOLDER_LABELS);
    initWidget(uiBinder.createAndBindUi(this));
  }

  public static void setDragDropLabel(DragLabel label, int offsetX, int offsetY) {
    DragAndDrop.label = label;
    DragAndDrop.offsetX = offsetX;
    DragAndDrop.offsetY = offsetY;
  }

  static DragLabel getDragLabel() {
    return label;
  }

  public static void clearDragLabel() {
    DragAndDrop.label = null;
  }

  public static int getOffsetX() {
    return offsetX;
  }

  public static int getOffsetY() {
    return offsetY;
  }

}
