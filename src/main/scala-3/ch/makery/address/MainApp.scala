package ch.makery.address

import ch.makery.address.model.Person
import ch.makery.address.view.{PersonEditDialogController, PersonOverviewController}
import javafx.fxml.FXMLLoader
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes.*
import javafx.scene as jfxs
import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import scalafx.scene.image.Image
import scalafx.stage.{Modality, Stage}

object MainApp extends JFXApp3:

  //Window Root Pane
  var roots: Option[scalafx.scene.layout.BorderPane] = None
  var cssResource = getClass.getResource("view/DarkTheme.css")
  var overviewControl: Option[PersonOverviewController] = None
  /**
   * The data as an observable list of Persons.
   */
  val personData = new ObservableBuffer[Person]()

  /**
   * Constructor
   */
  personData += new Person("Hans", "Muster")
  personData += new Person("Ruth", "Mueller")
  personData += new Person("Heinz", "Kurz")
  personData += new Person("Cornelia", "Meier")
  personData += new Person("Werner", "Meyer")
  personData += new Person("Lydia", "Kunz")
  personData += new Person("Anna", "Best")
  personData += new Person("Stefan", "Meier")
  personData += new Person("Martin", "Mueller")


  override def start(): Unit =
    // transform path of RootLayout.fxml to URI for resource location.
    val rootResource = getClass.getResource("view/RootLayout.fxml")
    // initialize the loader object.
    //getClass getResource is relative value- full value is in relation to ch.makery.address.view
    //if ("ch/makery/address/view/RootLayout.fxml") then absolute value
    val loader = new FXMLLoader(rootResource)
    // Load root layout from fxml file.
    loader.load()

    // retrieve the root component BorderPane from the FXML
    roots = Option(loader.getRoot[jfxs.layout.BorderPane])

    stage = new PrimaryStage():
      title = "AddressApp"
      icons += new Image(getClass.getResource("/images/BookImage.png").toExternalForm)
      scene = new Scene():
        root = roots.get
        stylesheets = Seq(cssResource.toExternalForm)

    // call to display PersonOverview when app start
    showPersonOverview()
  // actions for display person overview window
  def showPersonOverview(): Unit =
    val resource = getClass.getResource("view/PersonOverview.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    val ctrl = loader.getController[PersonOverviewController]
    overviewControl = Option(ctrl)
    this.roots.get.center = roots

  def showPersonEditDialog(person: Person): Boolean =
    val resource = getClass.getResource("view/PersonEditDialog.fxml")
    val loader = new FXMLLoader(resource)
    loader.load();
    val roots2 = loader.getRoot[jfxs.Parent]
    val control = loader.getController[PersonEditDialogController]

    val dialog = new Stage():
      initModality(Modality.ApplicationModal)
      initOwner(stage)
      scene = new Scene:
        root = roots2
        stylesheets = Seq(cssResource.toExternalForm)

    control.dialogStage = dialog
    control.person = person
    dialog.showAndWait()

    control.okClicked




  //extra stuff
  val stringA = new StringProperty("sunway") //publisher
  val stringB = new StringProperty("monash") //subscriber
  val stringC = new StringProperty("taylor") //subscriber

  stringA.onChange((a, b, c) => {
    println("a has changed value from " + b + " to " + c)
  })

  stringB <== stringA
  stringC <==> stringA
  stringA.value = "segi"
  stringC.value = "utm"

  println("string a value " + stringA.value)
  println("string b value " + stringB.value)
  println("string C value " + stringC.value)

  stringA.value = "monash"

  val add: (Int, Int) => Int = (a,b) => { a+b}
  println(add(1,2))