package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class MainController implements Initializable{
	@FXML private Button btnMyPage;
	@FXML private Button btnSingOut;
	@FXML private Button btnAll;
	@FXML private Button btnCafe;
	@FXML private Button btnBuffet;
	@FXML private Button btnVege;
	@FXML private Button btnGlobal;
	@FXML private Button btnWestern;
	@FXML private Button btnChinese;
	@FXML private Button btnJapanese;
	@FXML private Button btnKorean;
	
	@FXML private ImageView imgSearch;
	@FXML private ImageView imgOpen;
	
	@FXML private Label lblRecommend;
	
	@FXML private ComboBox<String> cbDong;
	@FXML private ComboBox<String> cbGu;
	@FXML private ComboBox<String> cbArrange;
	
	@FXML private ListView<RestImageList> listView;
	private final ObservableList<RestImageList> imageData = FXCollections.observableArrayList();
	private String localUrl = ""; // 이미지 파일 경로
	private Image localImage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RecommendlabelSetting();
		
	}

	public void RecommendlabelSetting() {
		
		RestaurantDAO restaurantDAO = new RestaurantDAO();
		int maxCount = 0;
		String gu = "";
		for (int i = 0; i < 13; i++) {
			if(maxCount < restaurantDAO.getCountbyGu("강남구")) {
				maxCount = restaurantDAO.getCountbyGu("강남구");
				gu = "강남구";
			}else if(maxCount < restaurantDAO.getCountbyGu("관악구")){
				maxCount = restaurantDAO.getCountbyGu("관악구");
				gu = "관악구";
			}else if(maxCount < restaurantDAO.getCountbyGu("광진구")){
				maxCount = restaurantDAO.getCountbyGu("광진구");
				gu = "광진구";
			}else if(maxCount < restaurantDAO.getCountbyGu("동대문구")){
				maxCount = restaurantDAO.getCountbyGu("동대문구");
				gu = "동대문구";
			}else if(maxCount < restaurantDAO.getCountbyGu("마포구")){
				maxCount = restaurantDAO.getCountbyGu("마포구");
				gu = "마포구";
			}else if(maxCount < restaurantDAO.getCountbyGu("서대문구")){
				maxCount = restaurantDAO.getCountbyGu("서대문구");
				gu = "서대문구";
			}else if(maxCount < restaurantDAO.getCountbyGu("성동구")){
				maxCount = restaurantDAO.getCountbyGu("성동구");
				gu = "성동구";
			}else if(maxCount < restaurantDAO.getCountbyGu("성북구")){
				maxCount = restaurantDAO.getCountbyGu("성북구");
				gu = "성북구";
			}else if(maxCount < restaurantDAO.getCountbyGu("용산구")){
				maxCount = restaurantDAO.getCountbyGu("용산구");
				gu = "용산구";
			}else if(maxCount < restaurantDAO.getCountbyGu("은평구")){
				maxCount = restaurantDAO.getCountbyGu("은평구");
				gu = "은평구";
			}else if(maxCount < restaurantDAO.getCountbyGu("종로구")){
				maxCount = restaurantDAO.getCountbyGu("종로구");
				gu = "강남구";
			}else if(maxCount < restaurantDAO.getCountbyGu("중구")){
				maxCount = restaurantDAO.getCountbyGu("중구");
				gu = "중구";
			}else if(maxCount < restaurantDAO.getCountbyGu("중랑구")){
				maxCount = restaurantDAO.getCountbyGu("중랑구");
				gu = "중랑구";
			}
		}
		
		lblRecommend.setText(gu);

	}

	public void setListWithImage() {
		ObservableList<RestImageList> data = FXCollections.observableArrayList();
        data.addAll(new RestImageList("Cheese", "address", 1.1), new RestImageList("Horse", "addr 2", 3.3), new RestImageList("Jam", "111", 4.4));

        listView = new ListView<RestImageList>(data);
        listView.setCellFactory(new Callback<ListView<RestImageList>, ListCell<RestImageList>>() {
            @Override
            public ListCell<RestImageList> call(ListView<RestImageList> listView) {
                return new CustomListCell();
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(listView);
	}
	
	 private class CustomListCell extends ListCell<RestImageList> {
	        private HBox content;
	        private Text name;
	        private Text price;

	        public CustomListCell() {
	            super();
	            name = new Text();
	            price = new Text();
	            VBox vBox = new VBox(name, price);
	            
	            FileInputStream input;
				try {
					File dirSave = new File("/Users/kimsojin/Desktop/n"); 
					input = new FileInputStream(new File(dirSave.getAbsolutePath()+"//"+"moodindigo.jpg"));
					Image image = new Image(input);
					ImageView imageView = new ImageView(image);
					imageView.setFitWidth(200);
					imageView.setFitHeight(200);
					content = new HBox(imageView, vBox);
					content.setSpacing(10);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }

	        @Override
	        protected void updateItem(RestImageList item, boolean empty) {
	            super.updateItem(item, empty);
	            if (item != null && !empty) { // <== test for null item and empty parameter
	                name.setText(item.getName());
	                setGraphic(content);
	            } else {
	                setGraphic(null);
	            }
	        }
	    }
}