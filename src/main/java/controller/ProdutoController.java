package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.ProdutoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Produto;

public class ProdutoController implements Initializable {


	@FXML
	private Button btSalvar;

	@FXML
	private Button btLimpar;

	@FXML
	private TextField tfId;

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfQuantidade;

	@FXML
	private TextField tfPreco;

	@FXML
	private TableView<Produto> tblProdutos;

	@FXML
	private TableColumn<Produto, String> colunID;

	@FXML
	private TableColumn<Produto, String> colunNome;

	@FXML
	private TableColumn<Produto, String> colunQuantidade;

	@FXML
	private TableColumn<Produto, String> colunPreco;

	@FXML
	private Button btnListar;

	@FXML
	void listar(ActionEvent event) {
		List<Produto> produtos = ProdutoDao.getInstance().findAll();
		ObservableList<Produto> oblist = FXCollections.observableArrayList();
		
		oblist.addAll(produtos);

		colunID.setCellValueFactory(new PropertyValueFactory<Produto, String>("id"));
		colunNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
		colunPreco.setCellValueFactory(new PropertyValueFactory<Produto, String>("preco"));
		colunQuantidade.setCellValueFactory(new PropertyValueFactory<Produto, String>("quantidade"));

		tblProdutos.setItems(oblist);
	}

	@FXML
	void actionLimpar(ActionEvent event) {
		tfId.setText("");
		tfNome.setText("");
		tfQuantidade.setText("");
		tfPreco.setText("");
	}

	@FXML
	void actionSalvar(ActionEvent event) {
		Produto produto = new Produto();
		produto.setNome(tfNome.getText());
		produto.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
		produto.setPreco(Double.parseDouble(tfPreco.getText()));
		ProdutoDao.getInstance().persist(produto);
	}

	public List<Produto> getListaProdutos() {
		List<Produto> listaDeProdutos = ProdutoDao.getInstance().findAll();
		return listaDeProdutos;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// construtor, coloque aqui tudo que for iniciar com a classe

	}

}