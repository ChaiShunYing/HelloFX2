package Project_CSC3104;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.UUID;

//helooooooooooo
//hungry?
//eat what
public class OnlinePrintingInterface extends Application {

    private String orderId;
    private String customerName;
    private String customerContact;
    private int totalPages = 1; // Default pages
    private double pricePerPagePlain = 0.10;
    private double pricePerPageGlossy = 0.20;
    private double pricePerPageColorPaper = 0.30;
    private double colorMultiplier = 2.0;
    private double blackAndWhiteMultiplier = 1.0;
    private double greyscaleMultiplier = 1.2;
    private double bindingPrice = 5.0;
    private double cuttingPrice = 3.0;
    private double laminatePrice = 7.0;

    private double totalPrice; // Holds the final calculated price for display in Stage 4
    private StringBuilder priceBreakdown; // Detailed price breakdown

    @Override
    public void start(Stage primaryStage) {
        showCustomerDetailsStage(primaryStage);
    }

    // Stage 1: Customer Details
    private void showCustomerDetailsStage(Stage primaryStage) {
        TextField nameField = new TextField();
        TextField contactField = new TextField();
        orderId = UUID.randomUUID().toString().substring(0, 8);

        Button nextButton = new Button("Next");

        nextButton.setOnAction(e -> {
            customerName = nameField.getText();
            customerContact = contactField.getText();

            if (customerName.isEmpty() || customerContact.isEmpty()) {
                showAlert("Error", "Please fill in all details.");
            } else {
                showPrintOptionsStage(primaryStage);
            }
        });

        VBox layout = new VBox(10, 
            new Label("Enter Your Details:"), 
            new Label("Name:"), nameField, 
            new Label("Contact Number:"), contactField, 
            new Label("Order ID: " + orderId), 
            nextButton
        );
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Customer Details");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Stage 2: Print Options
    private void showPrintOptionsStage(Stage primaryStage) {
    	FileChooser fileChooser = new FileChooser();
        Label fileLabel = new Label("No file selected");
        Button uploadButton = new Button("Upload File");
        uploadButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) fileLabel.setText(file.getName());
        });

        // Page size selection
        ComboBox<String> pageSizeBox = new ComboBox<>();
        pageSizeBox.getItems().addAll("A4 (Base Price)", "A3 (+RM 0.10/page)", "Letter (+RM 0.05/page)");
        pageSizeBox.setValue("A4 (Base Price)");

        ComboBox<String> paperTypeBox = new ComboBox<>();
        paperTypeBox.getItems().addAll("Plain Paper (RM 0.10/page)", "Glossy Paper (RM 0.20/page)", "Color Paper (RM 0.30/page)");
        paperTypeBox.setValue("Plain Paper (RM 0.10/page)");

        ComboBox<Integer> pagesPerSheetBox = new ComboBox<>();
        pagesPerSheetBox.getItems().addAll(1, 2, 4, 6);
        pagesPerSheetBox.setValue(1);

        TextField pagesField = new TextField("1");
        pagesField.setPromptText("Enter total pages");

        ToggleGroup layoutGroup = new ToggleGroup();
        RadioButton portraitButton = new RadioButton("Portrait");
        RadioButton landscapeButton = new RadioButton("Landscape");
        portraitButton.setToggleGroup(layoutGroup);
        landscapeButton.setToggleGroup(layoutGroup);
        portraitButton.setSelected(true);

        ToggleGroup printSidesGroup = new ToggleGroup();
        RadioButton oneSidedButton = new RadioButton("One-sided");
        RadioButton twoSidedButton = new RadioButton("Two-sided");
        oneSidedButton.setToggleGroup(printSidesGroup);
        twoSidedButton.setToggleGroup(printSidesGroup);
        oneSidedButton.setSelected(true);

        ToggleGroup colorGroup = new ToggleGroup();
        RadioButton colorButton = new RadioButton("Color");
        RadioButton blackAndWhiteButton = new RadioButton("Black & White");
        RadioButton greyscaleButton = new RadioButton("Greyscale");
        colorButton.setToggleGroup(colorGroup);
        blackAndWhiteButton.setToggleGroup(colorGroup);
        greyscaleButton.setToggleGroup(colorGroup);
        colorButton.setSelected(true);

        Spinner<Integer> copiesSpinner = new Spinner<>(1, 100, 1);

        CheckBox bindingBox = new CheckBox("Binding (RM 5)");
        CheckBox cuttingBox = new CheckBox("Cutting (RM 3)");
        CheckBox laminateBox = new CheckBox("Laminate (RM 7)");

        Button nextButton = new Button("Next");
        Button backButton = new Button("Back");

        // Display a clearer price list
        TextArea priceList = new TextArea();
        priceList.setText("""
            *** Price List ***
            1. Paper Types:
               - Plain Paper: RM 0.10 per page
               - Glossy Paper: RM 0.20 per page
               - Color Paper: RM 0.30 per page

            2. Page Sizes:
               - A4: Base price
               - A3: Additional RM 0.10 per page
               - Letter: Additional RM 0.05 per page

            3. Print Modes:
               - One-sided: Standard price
               - Two-sided: Calculated per effective page

            4. Color Modes:
               - Color: 2x base price
               - Greyscale: 1.2x base price
               - Black & White: 1x base price

            5. Additional Services:
               - Binding: RM 5
               - Cutting: RM 3
               - Laminate: RM 7
            """);
        priceList.setEditable(false);

        nextButton.setOnAction(e -> {
            try {
                totalPages = Integer.parseInt(pagesField.getText());
                int pagesPerSheet = pagesPerSheetBox.getValue();
                int copies = copiesSpinner.getValue();

                // Determine page size multiplier
                double pageSizeMultiplier = switch (pageSizeBox.getValue()) {
                    case "A3 (+RM 0.10/page)" -> 0.10;
                    case "Letter (+RM 0.05/page)" -> 0.05;
                    default -> 0.0;
                };

                double paperPrice = switch (paperTypeBox.getValue()) {
                    case "Glossy Paper (RM 0.20/page)" -> pricePerPageGlossy;
                    case "Color Paper (RM 0.30/page)" -> pricePerPageColorPaper;
                    default -> pricePerPagePlain;
                };

                double colorFactor = colorButton.isSelected() ? colorMultiplier : blackAndWhiteButton.isSelected() ? blackAndWhiteMultiplier : greyscaleMultiplier;

                int effectivePages = (int) Math.ceil((double) totalPages / pagesPerSheet);
                totalPrice = (effectivePages * (paperPrice + pageSizeMultiplier)) * copies * colorFactor;

                priceBreakdown = new StringBuilder("Price Breakdown:\n");
                priceBreakdown.append("Base Pages (").append(effectivePages).append(" pages): RM ").append(String.format("%.2f", effectivePages * (paperPrice + pageSizeMultiplier))).append("\n");
                priceBreakdown.append("Page Size: ").append(pageSizeBox.getValue()).append("\n");
                priceBreakdown.append("Color Factor: ").append(colorButton.isSelected() ? "Color (x2)" : greyscaleButton.isSelected() ? "Greyscale (x1.2)" : "Black & White (x1)").append("\n");

                if (bindingBox.isSelected()) {
                    totalPrice += bindingPrice;
                    priceBreakdown.append("Binding: RM ").append(String.format("%.2f", bindingPrice)).append("\n");
                }
                if (cuttingBox.isSelected()) {
                    totalPrice += cuttingPrice;
                    priceBreakdown.append("Cutting: RM ").append(String.format("%.2f", cuttingPrice)).append("\n");
                }
                if (laminateBox.isSelected()) {
                    totalPrice += laminatePrice;
                    priceBreakdown.append("Laminate: RM ").append(String.format("%.2f", laminatePrice)).append("\n");
                }

                priceBreakdown.append("Total Price: RM ").append(String.format("%.2f", totalPrice));

                showDeliveryStage(primaryStage);
            } catch (NumberFormatException ex) {
                showAlert("Error", "Invalid page number!");
            }
        });

        backButton.setOnAction(e -> showCustomerDetailsStage(primaryStage));

        VBox layout = new VBox(10,
            new Label("Upload File:"), uploadButton, fileLabel,
            new Label("Page Size:"), pageSizeBox,
            new Label("Paper Type:"), paperTypeBox,
            new Label("Pages per Sheet:"), pagesPerSheetBox,
            new Label("Page Layout:"), new HBox(10, portraitButton, landscapeButton),
            new Label("Print Sides:"), new HBox(10, oneSidedButton, twoSidedButton),
            new Label("Color Mode:"), new HBox(10, colorButton, blackAndWhiteButton, greyscaleButton),
            new Label("Enter Total Pages:"), pagesField,
            new Label("Number of Copies:"), copiesSpinner,
            new Label("Additional Services:"), new HBox(10, bindingBox, cuttingBox, laminateBox),
            new Label("Price List:"), priceList,
            new HBox(10, backButton, nextButton)
        );
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 800);
        primaryStage.setTitle("Print Options and Price List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Stage 4: Delivery and Cost Breakdown
    private void showDeliveryStage(Stage primaryStage) {
        TextArea breakdownArea = new TextArea(priceBreakdown.toString());
        breakdownArea.setEditable(false);

        Button finishButton = new Button("Finish");
        finishButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Summary");
            alert.setHeaderText("Thank you, " + customerName + "!");
            alert.setContentText("Order ID: " + orderId + "\n" + breakdownArea.getText());
            alert.showAndWait();
            primaryStage.close();
        });

        VBox layout = new VBox(10, 
            new Label("Cost Breakdown:"), breakdownArea, finishButton
        );
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setTitle("Cost Breakdown and Summary");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Utility: Show alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
