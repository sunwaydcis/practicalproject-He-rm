package ch.makery.address.view
import ch.makery.address.MainApp

import javafx.event.ActionEvent
import javafx.fxml.FXML


@FXML
class RootLayoutController():

  @FXML
  def handleClose(action: ActionEvent): Unit = {
    MainApp.stage.close()
    //System.exit(0)  <-- also can use to close app instead of MainApp.stage.close()
  }

  @FXML
    def handleDelete(action: ActionEvent): Unit =
    //needs to access the personOverviewController
    MainApp.overviewControl.map(x => x.handleDeletePerson(action))