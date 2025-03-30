
package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import modal.Product;
import modal.din_DAO;
import modal.din_modal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private List<String> cartItems = new ArrayList<>();
    private Stack<Scene> sceneStack = new Stack<>();
    private List<Product> products = new ArrayList<>();
    private List<String> allProducts = new ArrayList<>();
    private din_modal currentUser; // To hold the current user's info
    private int currentBannerIndex = 0;
    private ImageView bannerImageView = new ImageView();

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        primaryStage.setTitle("Dinkart");
        initializeProducts();
        showLoginPage();
    }

private void initializeProducts() {
       products.add(new Product("IPhone 14 Pro", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\product3.jpg", "Mobiles", "256"));
       products.add(new Product("Samsung U24", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\samsung.jpeg", "Mobiles", "300"));
       products.add(new Product("IQOO Z7 Pro", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\mobiles\\IQOO Z7 Pro 5G.jpg", "Mobiles", "194"));
       products.add(new Product("Oppo F25 Pro 5G", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\mobiles\\oppo F25 Pro 5G.jpg", "Mobiles", "310"));
       products.add(new Product("Lava Yuva 2 Pro", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\mobiles\\Lava Yuva 2 Pro.jpg", "Mobiles", "222"));

       
       products.add(new Product("HP Elite", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\hp.jpeg", "Laptops", "260"));
       products.add(new Product("ASUS Laptop L510", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Laptops\\ASUS Laptop L510 Ultra Thin Laptop.jpg", "Laptops", "294"));
       products.add(new Product("Lenovo N4020", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Laptops\\Lenovo IdeaPad 1 Intel Core Celeron N4020.jpg", "Laptops", "294"));
       products.add(new Product("HP Pavilion 13.3 FHD Laptop", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\laptop.jpeg", "Laptops", "200"));
       products.add(new Product("MacBook", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\macbook.jpeg", "Laptops", "350"));
       // Add more products as needed with valid prices
       
       products.add(new Product("Golden Ring", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Jewellery\\Jewellery_Golden Eternity Ring.png", "Jewellery", "800"));
       products.add(new Product("Gold Wreath Pendant", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Jewellery\\Jewellery_Rose Gold Drop Wreath Pendant With Link Chain.png", "Jewellery", "750"));
       products.add(new Product("Gold Studs", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Jewellery\\Jewellery_Rose Gold Shining Flower Studs.png", "Jewellery", "690"));
       products.add(new Product("Silver Bracelet", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Jewellery\\Jewellery_Silver Forever Linked Bracelet.png", "Jewellery", "810"));
       products.add(new Product("Silver Necklace", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Jewellery\\Jewellery_Silver Star Constellation Necklace.png", "Jewellery", "700"));
       
       products.add(new Product("AIR FORCE", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Shoes\\SHOE - AIR - FORCE.png", "Shoes", "59"));
       products.add(new Product("AIR MAX", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Shoes\\SHOE - NIKE - AIR - MAX.jpeg", "Shoes", "47"));
       products.add(new Product("BLAZER PHANTOM", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Shoes\\SHOE - NIKE - BLAZER - PHANTOM.png", "Shoes", "63"));
       products.add(new Product("NIKE DUNK LOW", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Shoes\\SHOE - NIKE - DUNK -LOW.png", "Shoes", "78"));
       products.add(new Product("AIR-JORDAN-RETRO-HIGH-OG", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Shoes\\SHOE-AIR-JORDAN-RETRO-HIGH-OG.png", "Shoes", "55"));
       
       products.add(new Product("BABY MOISTURIZER", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Health\\HEALTH - BABY - MOISTURIZER.png", "Health", "26"));
       products.add(new Product("BABY WIPES", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Health\\HEALTH - BABY - WIPES.png", "Health", "10"));
       products.add(new Product("FACE WASH", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Health\\HEALTH - FACE WASH.png", "Health", "15"));
       products.add(new Product("SANITARY PADS", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Health\\HEALTH - SANITARY - PADS.png", "Health", "12"));
       products.add(new Product("SUNSCREEN", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Health\\HEALTH - SUNSCREEN.png", "Health", "21"));

       products.add(new Product("Metal Floor Lamp(Brown Jute)", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Home&Kitchen\\Home&Kitchen_Crosscut Furniture Metal Floor Lamp with 3 shelves (Brown Jute).jpg","Home & Kitchen", "35"));
       products.add(new Product("Femora Borosilicate Oil Dispenser", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Home&Kitchen\\Home&Kitchen_Femora Borosilicate Glass Leak Proof Oil Dispenser for Cooking with Stainless Steel Metallic Lid, 500 ML, Pack of 1.jpg","Home & Kitchen", "42"));
       products.add(new Product("PHILIPS Air Fryer HD9252_90", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Home&Kitchen\\Home&Kitchen_PHILIPS Digital Air Fryer HD9252_90 with Touch Panel, uses up to 90_ less fat, 7 Pre-set Menu, 1400W, 4.1 Liter, with Rapid Air Technology (Black), Large.jpg","Home & Kitchen", "39"));
       products.add(new Product("TAUKIR microfiber rugs", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Home&Kitchen\\Home&Kitchen_TAUKIR CARPETS Handmade collection Super soft microfiber silk touch rugs, SIZE 10x14,feet color, IVORY_SKY MULTI.jpg","Home & Kitchen", "43"));

       products.add(new Product("GODREJ QUBE 30 LITRES REFRIGERATOR", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Electronic appliances\\EA - GODREJ QUBE 30 LITRES SINGLE DOOR REFRIGERATOR.png","Electronic Accessories", "200"));
       products.add(new Product("LG 32 INCH  LED SMART TV", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Electronic appliances\\EA - LG 80CM (32 INCH) HD READY LED SMART TV.png","Electronic Accessories", "250"));
       products.add(new Product("SWISS STEAM IRON", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Electronic appliances\\EA - SWISS STEAM IRON.webp","Electronic Accessories", "100"));
       products.add(new Product("TOSHIBA 55 INCH 4K ULTRA HD LED TV", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Electronic appliances\\EA - TOSHIBA C350MP 139 CM(55 INCH) 4K ULTRA HD LED TV.png","Electronic Accessories", "279"));
       
       products.add(new Product("Overcome procrastination", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Books\\Do It Today Overcome procrastination.jpg", "Books", "26"));
       products.add(new Product("Positive Thinking", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Books\\Power Of Positive Thinking.jpg", "Books", "52"));
       products.add(new Product("The Dhoni Touch", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Books\\The Dhoni Touch.jpg", "Books", "34"));
       products.add(new Product("Think Straight", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Books\\Think Straight.jpg", "Books", "43"));

       products.add(new Product("Lakme Matte Lipstick", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Beauty Accessories\\Lakme 9 To 5 Primer + Matte Lipstick.jpg", "Beauty", "43"));
       products.add(new Product("Lakme Rose Face Powder", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Beauty Accessories\\Lakme Rose Face Powder, Soft Pink, 40g.jpg", "Beauty", "32"));
       products.add(new Product("Lk Mascara", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Beauty Accessories\\Lk Eyeconic Mascara 8.5ml.jpg", "Beauty", "55"));
       products.add(new Product("Maybelline Eyeliner", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Beauty Accessories\\Maybelline New York Colossal Bold Pencil Eyeliner, Black, 3Ml, Matte Finish.jpg", "Beauty", "63"));

       products.add(new Product("Army Cargo Trousers", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Clothing\\Men_s Army Green Cargo Trousers.jpg", "Clothing", "43"));
       products.add(new Product("Vintage Stripe Button Down T-Shirt", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Clothing\\YANHOO Men_s Vintage Stripe Button Down T-Shirt - Slim Fit India.jpg", "Clothing", "54"));
       products.add(new Product("Cotton Track Pan", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Clothing\\Krystle Men_s Cotton Track Pant.jpg", "Clothing", "62"));
       products.add(new Product("Cotton Solid Shirts", "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\Clothing\\Beyoung Sky Blue - Cotton Solid Shirts For Men.jpg", "Clothing", "35"));

   }


    private void showLoginPage() {
        VBox loginPage = new VBox(20);
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setPadding(new Insets(20));
        loginPage.setStyle("-fx-background-color: #3498DB;");

        Label welcomeLabel = new Label("Welcome to Dinkart");
        welcomeLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: white;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");

        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.WHITE);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-font-size: 14px;");
        loginButton.setOnAction(event -> {
            din_modal d = new din_modal();
            d.setUsername(usernameField.getText());
            d.setpw(passwordField.getText());
            din_DAO dao = new din_DAO();
            try {
                if (dao.fetch_data(d)) {
                    currentUser = d; // Set the current user
                    showHomePage();
                } else {
                    messageLabel.setText("Invalid username or password.");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });

        Button createAccountButton = new Button("Create Account");
        createAccountButton.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-font-size: 14px;");
        createAccountButton.setOnAction(event -> showCreateAccountPage());

        loginPage.getChildren().addAll(welcomeLabel, usernameField, passwordField, loginButton, createAccountButton, messageLabel);

        if (primaryStage.getScene() != null) {
            sceneStack.push(primaryStage.getScene());
        }

        Scene loginScene = new Scene(loginPage, 800, 600);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void showCreateAccountPage() {
        VBox createAccountPage = new VBox(20);
        createAccountPage.setAlignment(Pos.CENTER);
        createAccountPage.setPadding(new Insets(20));
        createAccountPage.setStyle("-fx-background-color: #3498DB;");

        Label createAccountLabel = new Label("Create Account");
        createAccountLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: white;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");

        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.WHITE);

        Button createButton = new Button("Create");
        createButton.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-font-size: 14px;");
        createButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (!username.isEmpty() && !password.isEmpty()) {
                din_modal d = new din_modal();
                d.setUsername(username);
                d.setpw(password);

                din_DAO dao = new din_DAO();
                try {
                    dao.insert_data(d);
                    showLoginPage();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            } else {
                messageLabel.setText("Username already exists or invalid input.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-font-size: 14px;");
        backButton.setOnAction(event -> {
            if (!sceneStack.isEmpty()) {
                primaryStage.setScene(sceneStack.pop());
            }
        });

        createAccountPage.getChildren().addAll(createAccountLabel, usernameField, passwordField, createButton, backButton, messageLabel);

        if (primaryStage.getScene() != null) {
            sceneStack.push(primaryStage.getScene());
        }

        Scene createAccountScene = new Scene(createAccountPage, 800, 600);
        primaryStage.setScene(createAccountScene);
    }

    private void showHomePage() {
        rootLayout = new BorderPane();
        Scene scene = new Scene(rootLayout, 800, 600);

        HBox header = buildHeader();
        rootLayout.setTop(header);

        VBox mainContent = buildMainContent();
        
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true); // Ensure the content width fits the ScrollPane's width
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scrollbar as needed
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Disable horizontal scrollbar
        rootLayout.setCenter(scrollPane);

        if (primaryStage.getScene() != null) {
            sceneStack.push(primaryStage.getScene());
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox buildHeader() {
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(10));
        header.setStyle("-fx-background-color: #2C3E50;");

        ImageView logo = loadImage("C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\logo.jpeg", 50, 50);
        Circle logoClip = new Circle(25, 25, 25);
        logo.setClip(logoClip);

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.5);
        logo.setEffect(colorAdjust);

        Label companyName = new Label("Dinkart");
        companyName.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");

        ComboBox<String> categoryDropdown = new ComboBox<>();
        categoryDropdown.getItems().addAll("Mobiles", "Laptops", "Clothing", "Books", "Beauty",
                "Electronic Accessories", "Home & Kitchen", "Shoes", "Health", "Jewellery");
        categoryDropdown.setPromptText("Select Category");
        categoryDropdown.setOnAction(event -> {
            String selectedCategory = categoryDropdown.getValue();
            if (selectedCategory != null) {
                showCategoryWithImages(selectedCategory);
            }
        });

        Button homeButton = createNavButton("Home");
        homeButton.setOnAction(event -> showHomePage());

        Button cartButton = createNavButton("Cart");
        cartButton.setOnAction(event -> showCartPage());

        Button accountButton = createNavButton("Account");
        accountButton.setOnAction(event -> showAccountPage());

        Button logoutButton = createNavButton("Logout"); // Add this line
        logoutButton.setOnAction(event -> showLoginPage()); // Add this line

        HBox.setHgrow(homeButton, Priority.ALWAYS);
        HBox.setHgrow(cartButton, Priority.ALWAYS);
        HBox.setHgrow(accountButton, Priority.ALWAYS);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(logo, companyName, categoryDropdown, homeButton, cartButton, accountButton, logoutButton); // Add logoutButton here
        header.setSpacing(10);
        header.setPadding(new Insets(10, 20, 10, 20));

        return header;
    }

    private VBox buildMainContent() {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));
        mainContent.setAlignment(Pos.TOP_CENTER);
        mainContent.prefWidthProperty().bind(rootLayout.widthProperty()); // Bind the width of the main content to the width of the root layout

        // List of banner images
        List<String> bannerImages = Arrays.asList(
            "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\banner1.jpg",
           "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\banner2.jpg",
           "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\banner3.jpg",
           "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\banner4.jpg",           
           "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\banner5.jpg",
           "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\banner6.jpg",
           "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\banner7.jpg",
           "C:\\Users\\PADMANABAN\\eclipse-workspace\\New_Dinkart\\src\\resources\\banner8.jpg"

        );

        // Set the first image
        setImageToImageView(bannerImages.get(currentBannerIndex));

        // Use a StackPane to center the banner image
        StackPane bannerContainer = new StackPane(bannerImageView);
        bannerContainer.setAlignment(Pos.CENTER);
        bannerContainer.prefWidthProperty().bind(rootLayout.widthProperty()); // Bind the width of the banner container to the width of the root layout
        bannerContainer.setMaxHeight(300); // Fixed height for the banner area
        bannerImageView.setFitWidth(rootLayout.getWidth());
        bannerImageView.setPreserveRatio(true);

        // Timeline for changing banners
        Timeline bannerTimeline = new Timeline(
            new KeyFrame(Duration.seconds(5), event -> {
                currentBannerIndex = (currentBannerIndex + 1) % bannerImages.size();
                setImageToImageView(bannerImages.get(currentBannerIndex));
            })
        );
        bannerTimeline.setCycleCount(Timeline.INDEFINITE);
        bannerTimeline.play();

        // Product container
        HBox productContainer = new HBox(20);
        productContainer.setPadding(new Insets(10));
        productContainer.setAlignment(Pos.CENTER);
        productContainer.setStyle("-fx-background-color: #F0F0F0;");

        // Randomly select three products
        Random rand = new Random();
        Set<Integer> selectedIndices = new HashSet<>();
        while (selectedIndices.size() < 3 && selectedIndices.size() < products.size()) {
            int randomIndex = rand.nextInt(products.size());
            selectedIndices.add(randomIndex);
        }

        // Add selected products to the UI
        for (int index : selectedIndices) {
            Product product = products.get(index);
            VBox productBox = createProductBox(product);
            productContainer.getChildren().add(productBox);
        }

        // Add banner and products to main content
        mainContent.getChildren().addAll(bannerContainer, productContainer);

        return mainContent;
    }

    private void setImageToImageView(String imagePath) {
        try {
            Image bannerImage = new Image(new FileInputStream(imagePath));
            bannerImageView.setImage(bannerImage);
            bannerImageView.setPreserveRatio(true); // Preserve the aspect ratio of the image
            bannerImageView.setFitWidth(rootLayout.getWidth()); // Set the width to match the width of the root layout
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showCartPage() {
        VBox cartPage = new VBox(20);
        cartPage.setAlignment(Pos.TOP_CENTER);
        cartPage.setPadding(new Insets(20));
        cartPage.setStyle("-fx-background-color: #2C3E50;");

        Label cartLabel = new Label("Shopping Cart");
        cartLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #ECF0F1;");

        VBox cartItemsContainer = new VBox(15);
        cartItemsContainer.setAlignment(Pos.TOP_CENTER);
        cartItemsContainer.setPadding(new Insets(10));

        updateCartItemsContainer(cartItemsContainer);

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: #ECF0F1; -fx-font-size: 14px;");

        Button checkoutButton = new Button("Checkout");
        checkoutButton.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        checkoutButton.setOnAction(event -> {
            if (cartItems.isEmpty()) {
                messageLabel.setText("Your cart is empty.");
                return;
            }
            for (String itemName : cartItems) {
                Product product = findProductByName(itemName);
                if (product != null) {
                    try {
                        din_DAO dao = new din_DAO();
                        dao.saveOrder(currentUser.getUsername(), product.getName(), product.getPrice(), new Date());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            messageLabel.setText("Checkout completed.");
            cartItems.clear(); // Clear the cart after checkout
            updateCartItemsContainer(cartItemsContainer); // Update the cart items container
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        backButton.setOnAction(event -> {
            if (!sceneStack.isEmpty()) {
                primaryStage.setScene(sceneStack.pop());
            }
        });

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(checkoutButton, backButton);

        cartPage.getChildren().addAll(cartLabel, cartItemsContainer, buttonBox, messageLabel);

        if (primaryStage.getScene() != null) {
            sceneStack.push(primaryStage.getScene());
        }

        Scene cartScene = new Scene(cartPage, 800, 600);
        primaryStage.setScene(cartScene);
    }

    private void updateCartItemsContainer(VBox cartItemsContainer) {
        cartItemsContainer.getChildren().clear();
        double totalAmount = 0;

        for (String itemName : cartItems) {
            Product product = findProductByName(itemName);

            if (product != null) {
                totalAmount += Integer.parseInt(product.getPrice());

                HBox cartItemBox = new HBox(20);
                cartItemBox.setAlignment(Pos.CENTER_LEFT);
                cartItemBox.setStyle("-fx-background-color: #34495E; -fx-padding: 10px; -fx-border-color: #ECF0F1; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

                Label itemNameLabel = new Label(itemName);
                itemNameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #ECF0F1;");

                Label itemPriceLabel = new Label("Price: $" + product.getPrice());
                itemPriceLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #ECF0F1;");

                Button deleteButton = new Button("Delete");
                deleteButton.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5px 10px;");
                deleteButton.setOnAction(event -> {
                    cartItems.remove(itemName);
                    updateCartItemsContainer(cartItemsContainer);
                });

                cartItemBox.getChildren().addAll(itemNameLabel, itemPriceLabel, deleteButton);
                cartItemsContainer.getChildren().add(cartItemBox);
            }
        }

        Label totalAmountLabel = new Label("Total Amount: $" + totalAmount);
        totalAmountLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #ECF0F1; -fx-font-weight: bold;");
        cartItemsContainer.getChildren().add(totalAmountLabel);
    }

    private Product findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }


    private void showAccountPage() {
        VBox accountPage = new VBox(20);
        accountPage.setAlignment(Pos.CENTER);
        accountPage.setPadding(new Insets(20));
        accountPage.setStyle("-fx-background-color: #3498DB;");

        Label accountLabel = new Label("Account Details");
        accountLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label usernameLabel = new Label("Username: " + (currentUser != null ? currentUser.getUsername() : "Guest"));
        usernameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-font-size: 14px;");
        backButton.setOnAction(event -> {
            if (!sceneStack.isEmpty()) {
                primaryStage.setScene(sceneStack.pop());
            }
        });

        accountPage.getChildren().addAll(accountLabel, usernameLabel, backButton);

        if (primaryStage.getScene() != null) {
            sceneStack.push(primaryStage.getScene());
        }

        Scene accountScene = new Scene(accountPage, 800, 600);
        primaryStage.setScene(accountScene);
    }

    private VBox createProductBox(Product product) {
        VBox productBox = new VBox(10);
        productBox.setAlignment(Pos.CENTER);
        productBox.setPadding(new Insets(10));
        productBox.setStyle("-fx-background-color: white; -fx-border-color: #DDDDDD; -fx-border-width: 1px;");
        productBox.setMaxWidth(150); // Set max width for product boxes

        ImageView productImageView = new ImageView();
        try {
            Image productImage = new Image(new FileInputStream(product.getImagePath()));
            productImageView.setImage(productImage);
            productImageView.setFitHeight(100);
            productImageView.setFitWidth(100);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Label productNameLabel = new Label(product.getName());
        productNameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label productPriceLabel = new Label("Price: $" + product.getPrice()); // Add price label
        productPriceLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #3498DB;"); // Optional styling

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white;");
        addToCartButton.setOnAction(event -> cartItems.add(product.getName()));

        productBox.getChildren().addAll(productImageView, productNameLabel, productPriceLabel, addToCartButton); // Include the price label

        return productBox;
    }

    private void showCategoryWithImages(String category) {
        VBox categoryPage = new VBox(20);
        categoryPage.setAlignment(Pos.CENTER);
        categoryPage.setPadding(new Insets(20));
        categoryPage.setStyle("-fx-background-color: #3498DB;");

        Label categoryLabel = new Label(category);
        categoryLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: white;");

        HBox productContainer = new HBox(20);
        productContainer.setPadding(new Insets(10));
        productContainer.setAlignment(Pos.CENTER);
        productContainer.setStyle("-fx-background-color: #F0F0F0;");

        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                VBox productBox = createProductBox(product);
                productContainer.getChildren().add(productBox);
            }
        }

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-font-size: 14px;");
        backButton.setOnAction(event -> {
            if (!sceneStack.isEmpty()) {
                primaryStage.setScene(sceneStack.pop());
            }
        });

        categoryPage.getChildren().addAll(categoryLabel, productContainer, backButton);

        if (primaryStage.getScene() != null) {
            sceneStack.push(primaryStage.getScene());
        }

        Scene categoryScene = new Scene(categoryPage, 800, 600);
        primaryStage.setScene(categoryScene);
    }

    private ImageView loadImage(String imagePath, int width, int height) {
        try {
            Image image = new Image(new FileInputStream(imagePath));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            return imageView;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Button createNavButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 16px;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: white; -fx-text-fill: #2C3E50; -fx-font-size: 16px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 16px;"));
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}




