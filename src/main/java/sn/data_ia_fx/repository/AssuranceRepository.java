package sn.data_ia_fx.repository;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sn.data_ia_fx.entity.Assurance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssuranceRepository implements IRepository<Assurance> {
    private Connection connection;

    public AssuranceRepository() {
        this.connection = new Database().getConnection();
    }

    public void insert(Assurance assurance) {

        String sql = "insert into assurance (numero, nom, montant) " +
                "values (?,?,?)";

        try {
            PreparedStatement result = this.connection.prepareStatement(sql);
            result.setString(1,assurance.getNumero());
            result.setString(2,assurance.getNomClient());
            result.setDouble(3,assurance.getMontant());
            result.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        String sql = "delete from assurance where id = ?";

        try {
            PreparedStatement result =  this.connection.prepareStatement(sql);
            result.setInt(1,id);
            result.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Assurance findById(int id){
        String sql = "select * from assurance where id = ?";
        Assurance assurance = new Assurance();
        try {
            PreparedStatement result = this.connection.prepareStatement(sql);
            result.setInt(1,id);
            ResultSet resultSet =  result.executeQuery();

            while(resultSet.next()){
                assurance.setNumero(resultSet.getString("numero"));
                assurance.setNomClient(resultSet.getString("nom"));
                assurance.setMontant(resultSet.getDouble("montant"));
                assurance.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return assurance;
    }

    public void update(Assurance assurance) {
         String sql = "update assurance set nom = ?, montant = ?, numero = ? where id = ?";
        try {
          PreparedStatement result  = connection.prepareStatement(sql);
          result.setString(1,assurance.getNomClient());
          result.setDouble(2,assurance.getMontant());
          result.setString(3,assurance.getNumero());
          result.setInt(4,assurance.getId());
          result.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Assurance> findAll(){
        List<Assurance> assurances = new ArrayList<Assurance>();

        String sql = "select * from assurance ";

        try {
            PreparedStatement result = this.connection.prepareStatement(sql);
            ResultSet resultSet =  result.executeQuery();

            while(resultSet.next()){
                Assurance assurance = new Assurance();
                assurance.setNumero(resultSet.getString("numero"));
                assurance.setNomClient(resultSet.getString("nom"));
                assurance.setMontant(resultSet.getDouble("montant"));
                assurance.setId(resultSet.getInt("id"));
                assurances.add(assurance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return FXCollections.observableArrayList(assurances);
    }

    public ObservableList<Assurance> findAllSearch(String search ){
        List<Assurance> assurances = new ArrayList<Assurance>();

        String sql = "select * from assurance where nom like ? or numero like ? or CAST(montant AS VARCHAR) like ?";

        try {
            PreparedStatement result = this.connection.prepareStatement(sql);
            result.setString(1,"%"+search+"%");
            result.setString(2,"%"+search+"%");
            result.setString(3,"%"+search+"%");
            ResultSet resultSet =  result.executeQuery();

            while(resultSet.next()){
                Assurance assurance = new Assurance();
                assurance.setNumero(resultSet.getString("numero"));
                assurance.setNomClient(resultSet.getString("nom"));
                assurance.setMontant(resultSet.getDouble("montant"));
                assurance.setId(resultSet.getInt("id"));
                assurances.add(assurance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return FXCollections.observableArrayList(assurances);
    }
}
