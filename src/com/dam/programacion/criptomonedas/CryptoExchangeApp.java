package com.dam.programacion.criptomonedas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CryptoExchangeApp extends JFrame {

    private Map<String, Double> preciosCriptomonedas;
    private Map<String, Double> carteraUsuario;

    private JComboBox<String> criptomonedasComboBox;
    private JLabel precioLabel;
    private JLabel cantidadLabel;
    private JTextField cantidadTextField;
    private JTextArea carteraTextArea;

    public CryptoExchangeApp() {
        setTitle("Crypto Exchange App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        preciosCriptomonedas = new HashMap<>();
        carteraUsuario = new HashMap<>();

        // Cargar precios de criptomonedas desde un archivo (simulado)
        cargarPreciosCriptomonedas();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        // Combo box para seleccionar la criptomoneda
        criptomonedasComboBox = new JComboBox<>(preciosCriptomonedas.keySet().toArray(new String[0]));
        panel.add(criptomonedasComboBox);

        // Etiqueta para mostrar el precio de la criptomoneda seleccionada
        precioLabel = new JLabel("Precio: ");
        panel.add(precioLabel);

        // Campo de texto para ingresar la cantidad de criptomonedas a comprar/vender
        cantidadLabel = new JLabel("Cantidad: ");
        panel.add(cantidadLabel);
        cantidadTextField = new JTextField();
        panel.add(cantidadTextField);

        // Botón para realizar la operación de compra/venta
        JButton operacionButton = new JButton("Realizar Operación");
        operacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOperacion();
            }
        });
        panel.add(operacionButton);

        // Área de texto para mostrar la cartera del usuario
        carteraTextArea = new JTextArea();
        panel.add(new JScrollPane(carteraTextArea));

        add(panel);

        // Mostrar la cartera inicial del usuario
        actualizarCartera();
    }

    private void cargarPreciosCriptomonedas() {
        preciosCriptomonedas.put("Bitcoin", 50000.0);
        preciosCriptomonedas.put("Ethereum", 2000.0);
        preciosCriptomonedas.put("Litecoin", 150.0);
        preciosCriptomonedas.put("Ripple", 1.0);
        // Simulación de carga desde archivo
    }

    private void realizarOperacion() {
        String criptomonedaSeleccionada = (String) criptomonedasComboBox.getSelectedItem();
        double precioCriptomoneda = preciosCriptomonedas.get(criptomonedaSeleccionada);
        double cantidad = Double.parseDouble(cantidadTextField.getText());

        // Simulación de operación de compra/venda
        if (carteraUsuario.containsKey(criptomonedaSeleccionada)) {
            carteraUsuario.put(criptomonedaSeleccionada, carteraUsuario.get(criptomonedaSeleccionada) + cantidad);
        } else {
            carteraUsuario.put(criptomonedaSeleccionada, cantidad);
        }

        // Actualizar la cartera y mostrarla en el área de texto
        actualizarCartera();
    }

    private void actualizarCartera() {
        carteraTextArea.setText("Cartera del Usuario:\n");
        for (Map.Entry<String, Double> entry : carteraUsuario.entrySet()) {
            carteraTextArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }
    }


    }

