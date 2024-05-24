package services;

import java.util.ArrayList;
import java.util.List;
import entities.Veiculo;

public class VeiculoService {
    private List<Veiculo> veiculosDB;

    public VeiculoService() {
        this.veiculosDB = new ArrayList<>();
    }

    public void save(Veiculo veiculo) throws Exception {
        // Verifica se o veículo já está cadastrado
        if (getVeiculoByPlaca(veiculo.getPlaca()) != null) {
            throw new Exception("Veículo com placa " + veiculo.getPlaca() + " já está cadastrado.");
        }
        // Adiciona o veículo à lista
        veiculosDB.add(veiculo);
    }

    public List<Veiculo> getVeiculosDB() {
        return veiculosDB;
    }

    public Veiculo getVeiculoByPlaca(String placa) {
        for (Veiculo veiculo : veiculosDB) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    public void save(List<Veiculo> veiculos) {
        this.veiculosDB = veiculos;
    }
}